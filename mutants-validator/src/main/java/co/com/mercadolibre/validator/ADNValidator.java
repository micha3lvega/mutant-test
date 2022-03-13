package co.com.mercadolibre.validator;

import co.com.mercado.libre.commons.exception.InvalidLengthException;

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
		validateLength(dna);

		return false;
	}

	/**
	 * Metodo que valida que la cadena sea de nXn
	 *
	 * @param dna
	 */
	private static void validateLength(String[] dna) {

		// Obtener el tamano de la cadena
		final var arrayLength = dna.length;

		// Recorrer la cadena comparando el tamaño de cada fila
		for (final String base : dna) {

			if (base.length() != arrayLength)
				throw new InvalidLengthException();

		}

	}

}
