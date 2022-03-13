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

			// Recorrer las letra de cada base del ADN
			final var letters = base.toCharArray();
			for (final char letter : letters) {

				// Validar que la letra no sea invalida
				validLetter(letter);

			}

		}

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

}
