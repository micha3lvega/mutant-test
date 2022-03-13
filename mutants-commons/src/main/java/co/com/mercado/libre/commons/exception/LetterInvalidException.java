package co.com.mercado.libre.commons.exception;

public class LetterInvalidException extends RuntimeException {

	public LetterInvalidException() {
		super("La cadena de ADN contiene valores invalidos");
	}

	public LetterInvalidException(String message) {
		super(message);
	}

	public LetterInvalidException(Throwable cause) {
		super(cause);
	}

	public LetterInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

}
