package co.com.mercadolibre.validator;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
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

		return validateHor(dna) || validateVertically(dna) || validateDiag(dna);

	}

	/**
	 * Metodo que se encarga de encontrar una mutacion en las filas diagonales de la
	 * cadena
	 *
	 * @param dna
	 * @return
	 */
	private static boolean validateDiag(String[] dna) {

		final var matriz = createMatrix(dna);

		// Obtener los valores de la linea en X
		var y = 0;

		var cordX = 0;
		var cordY = 0;

		while (y < dna.length - 3) {

			cordX = 0;
			final var baseAdn = new StringBuilder();
			while (cordY < dna.length) {
				baseAdn.append(matriz[cordX][cordY]);
				cordY++;
				cordX++;
			}

			if (findMutation(baseAdn.toString()))
				return true;

			y++;
			cordY = y;

		}

		// Obtener los valores de la linea en Y
		var x = 1;
		cordX = 1;

		while (x < dna.length - 3) {

			cordY = 0;
			final var baseAdn = new StringBuilder();
			while (cordX < dna.length) {
				baseAdn.append(matriz[cordX][cordY]);
				cordY++;
				cordX++;
			}

			if (findMutation(baseAdn.toString()))
				return true;

			x++;
			cordX = x;

		}

		return false;

	}

	/**
	 * Metodo encargado de recorrer el arreglo de las cadenas de ADN verticalmente y
	 * buscar 4 letras repetidas
	 *
	 * @param dna
	 * @return
	 */
	private static boolean validateVertically(String[] dna) {

		final var matriz = createMatrix(dna);

		for (var i = 0; i < matriz.length; i++) {

			final var base = new StringBuilder();
			for (final String[] element : matriz) {
				base.append(element[i]);
			}

			if (findMutation(base.toString()))
				return true;

		}

		return false;
	}

	/**
	 * Metodo encargado de recorrer el arreglo del ADN de forma horizontal buscando
	 * 4 caracteres repetidos
	 *
	 * @param dna
	 * @return true si alguna posicion del arreglo tiene 4 letras repetidas
	 */
	private static boolean validateHor(String[] dna) {

		for (final String cadena : dna) {

			if (findMutation(cadena))
				return true;

		}

		return false;
	}

	/**
	 * Metodo encargado de encontrar una mutacion en la palabra recibida
	 *
	 * @param cadena
	 * @return true si se encuentra repetida la palabra mas de 4 veces
	 */
	private static boolean findMutation(String cadena) {

		final var mutations = DNASequenceDto.MUTATIONS;

		for (final String mutation : mutations) {
			if (cadena.contains(mutation))
				return true;
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
		if (DNASequenceDto.ALLOW_VALUES.indexOf(charAt) < 0)
			throw new LetterInvalidException(charAt + " no es un caracter valido");
	}

}
