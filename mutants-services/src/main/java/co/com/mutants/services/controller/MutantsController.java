package co.com.mutants.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mutants.services.services.MutantsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/validator/api/v1")
@Api(value = "Servicios mutantes", produces = "application/json")
public class MutantsController {

	@Autowired
	private MutantsService service;

	@PostMapping("/mutant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se encontro una mutacion en el ADN recibido"),
			@ApiResponse(code = 403, message = "No se encontro una mutacion en el ADN recibido") })
	@ApiOperation(value = "Validacion ADN", notes = "Valida si una persona es o no mutante dependiendo de la cadena de ADN recibida")
	public ResponseEntity<Void> isMutant(@NonNull @RequestBody(required = true) DNASequenceDto dnaSequence) {

		log.trace("[isMutant] dnaSequence: {}", dnaSequence);

		final var isMutant = service.isMutant(dnaSequence);

		if (isMutant)
			return ResponseEntity.ok(null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

}
