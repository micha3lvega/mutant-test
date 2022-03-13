package co.com.mercado.libre.commons.dto;

/**
 * Excepcion lanzada cuando una cadena de ADN no tiene el mismo tamaño de
 * columnas y filas
 *
 * @author Michael Vega
 *
 */
public class InvalidLengthException extends RuntimeException {

	public InvalidLengthException() {
		super("Tamano del arreglo es invalido");
	}

	public InvalidLengthException(String message) {
		super(message);
	}

	public InvalidLengthException(Throwable cause) {
		super(cause);
	}

	public InvalidLengthException(String message, Throwable cause) {
		super(message, cause);
	}

}
