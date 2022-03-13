package co.com.mercadolibre.validator;

import co.com.mercado.libre.commons.dto.DNASequence;
import co.com.mercado.libre.commons.exception.InvalidLengthException;
import co.com.mercado.libre.commons.exception.LetterInvalidException;

/**
 * Clase encargada de la validacion de una cadena de ADN
 *
 * @author Michael Vega
 *
 */
public class ADNValidator {

	/**
	 * Metodo encargado de validar si un adn tiene una mutacion o no
	 *
	 * @param dna
	 * @return true si la sequencia cumple con las reglas generadas
	 */
	public static boolean isMutant(String[] dna) {

		// Validar tamano de las filas y columnas
		validateADN(dna);

		// Convertir el arreglo a bidimensional
		final var matrix = createMatrix(dna);

		return validateHor(dna);
	}

	/**
	 * Metodo encargado de recorrer el arreglo del ADN de forma horizontal buscando
	 * 4 caracteres repetidos
	 *
	 * @param dna
	 * @return true si alguna posicion del arreglo tiene 4 letras repetidas
	 */
	private static boolean validateHor(String[] dna) {

		var counter = 1;

		for (final String cadena : dna) {

			final var letters = cadena.toCharArray();
			for (var i = 0; i < letters.length; i++) {

				// No validar ni la primera ni la ultima posicion
				if (i == 0 || i == letters.length - 1) {
					continue;
				}

				// resetea contado si la letra de la derecha no es igual a la de la izquierda
				if (letters[i] == letters[i - 1]) {
					counter++;
				} else {
					counter = 1;
				}

				// finaliza como verdadero si hay 4 letras iguales consecutivas
				if (counter == 4)
					return true;

			}
		}

		return false;
	}

	/**
	 * Metodo que valida que la cadena sea de nXn
	 *
	 * @param dna
	 */
	private static void validateADN(String[] dna) {

		// Obtener el tamano de la cadena
		final var arrayLength = dna.length;

		// Recorrer la cadena comparando el tamaño de cada fila
		for (final String base : dna) {

			if (base.length() != arrayLength)
				throw new InvalidLengthException();

			// Recorrer las letras de cada base del ADN
			final var letters = base.toCharArray();
			for (final char letter : letters) {

				// Validar que la letra no sea invalida
				validLetter(letter);

			}

		}

	}

	/**
	 * Metodo encargado de convertir un arreglo unidimensional a uno bidimensional
	 *
	 * @param adn
	 * @return un arreglo bidimensional
	 */
	private static String[][] createMatrix(String[] adn) {

		int row;
		int column;

		final var matriz = new String[adn.length][adn.length];

		for (row = 0; row < adn.length; row++) {

			final var cadena = adn[row];
			for (column = 0; column < cadena.toCharArray().length; column++) {
				final var letra = cadena.toCharArray();
				matriz[row][column] = String.valueOf(letra[column]);
			}

		}

		return matriz;
	}

	/**
	 * Metodo que valida si los caracteres ingresados corresponden a (A,T,C,G)
	 *
	 * @param charAt
	 * @throws Exception
	 */
	private static void validLetter(char charAt) {
		if (DNASequence.ALLOW_VALUES.indexOf(charAt) < 0)
			throw new LetterInvalidException(charAt + " no es un caracter valido");
	}

	public static void main(String[] args) {

		final String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCTTA", "TCACTG" };

		if (isMutant(dna)) {
			System.out.println("Es mutante>>>>>>>>>>>");
		} else {
			System.out.println("<<<<<<<<<<<<<No es mutante");
		}

	}

}
