package co.com.mutants.services.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.commons.dto.DNASequence;
import co.com.mercadolibre.validator.ADNValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/validator/api/v1")
public class MutantsController {

	@PostMapping("/mutant")
	public ResponseEntity<Void> isMutant(@RequestBody DNASequence dnaSequence) {

		log.trace("[isMutant] dnaSequence: {}", dnaSequence);

		final var isMutant = ADNValidator.isMutant(dnaSequence.getDna());

		if (isMutant)
			return ResponseEntity.ok(null);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

	}

}
