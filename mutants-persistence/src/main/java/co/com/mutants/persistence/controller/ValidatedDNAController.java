package co.com.mutants.persistence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mercado.libre.commons.dto.DNAstatisticsDto;
import co.com.mutants.persistence.services.StaticsDNAServices;
import co.com.mutants.persistence.services.ValidatedDNAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/persitence/api/v1")
@Api(value = "Servicios de base de datos de los ADN previamente validado", produces = "application/json")
public class ValidatedDNAController {

	@Autowired
	private ValidatedDNAService service;

	@Autowired
	private StaticsDNAServices staticsDNAServices;

	@GetMapping("/stats")
	@ApiOperation(value = "Obtiene las estadisticas sobre las cadenas de ADN", notes = "Deevuelve un Json con las estadísticas de las verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}")
	public DNAstatisticsDto stats() {
		return staticsDNAServices.stats();
	}

	@PostMapping("/mutant")
	@ApiOperation(value = "Insertar una cadena de ADN", notes = "Inserta una cadena de ADN solo si no existe")
	public DNASequenceDto insert(@NonNull @RequestBody(required = true) DNASequenceDto dnaSequence) {

		log.trace("[insert] dnaSequence: {}", dnaSequence);

		final var insertDna = service.insert(dnaSequence);
		dnaSequence.setId(insertDna.getId());

		return dnaSequence;
	}

}
