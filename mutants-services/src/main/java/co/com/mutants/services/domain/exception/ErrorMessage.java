package co.com.mutants.services.domain.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage implements Serializable {

	private String id;
	private String url;
	private String description;
	private HttpStatus statusCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;

	public ErrorMessage(RuntimeException exception, HttpServletRequest request) {

		timestamp = LocalDateTime.now();
		id = request.getSession().getId();
		url = request.getRequestURL().toString();
		description = exception.getMessage();
		statusCode = HttpStatus.BAD_REQUEST;

	}

}
