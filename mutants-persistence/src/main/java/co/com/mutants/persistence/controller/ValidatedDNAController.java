package co.com.mutants.persistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.commons.dto.DNASequence;
import co.com.mutants.persistence.entity.ValidatedDNA;
import co.com.mutants.persistence.services.ValidatedDNAService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/persitence/api/v1")
@Api(value = "Servicios de base de datos de los ADN previamente validado", produces = "application/json")
public class ValidatedDNAController {

	@Autowired
	private ValidatedDNAService service;

	@PostMapping("/mutant")
	public ValidatedDNA insert(@NonNull @RequestBody(required = true) DNASequence dnaSequence) {

		log.trace("[insert] dnaSequence: {}", dnaSequence);

		return service.insert(dnaSequence);

	}

}
