package co.com.mutants.services.exception.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

import co.com.mercado.libre.commons.exception.InvalidLengthException;
import co.com.mercado.libre.commons.exception.LetterInvalidException;
import co.com.mutants.services.domain.exception.ErrorMessage;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler({ InvalidLengthException.class, LetterInvalidException.class })
	public ResponseEntity<ErrorMessage> invalidADNExceptionHandler(RuntimeException ex, HttpServletRequest request) {

		final var error = new ErrorMessage(ex, request);
		return new ResponseEntity<>(error, error.getStatusCode());

	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(RuntimeException ex, HttpServletRequest request) {

		final var error = new ErrorMessage(ex, request);
		error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(error, error.getStatusCode());

	}

	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ErrorMessage> jsonMappingException(JsonMappingException ex, HttpServletRequest request) {

		final var error = new ErrorMessage();

		error.setTimestamp(LocalDateTime.now());
		error.setId(request.getSession().getId());
		error.setStatusCode(HttpStatus.BAD_REQUEST);
		error.setDescription(ex.getOriginalMessage());
		error.setUrl(request.getRequestURL().toString());

		return new ResponseEntity<>(error, error.getStatusCode());

	}

}
