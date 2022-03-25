package co.com.mutants.persistence.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mongounit.MongoUnitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mutants.persistence.repository.ValidatedDNARepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@MongoUnitTest(name = "test statidistc")
class StaticsDNAServicesTest {

	@Autowired
	private StaticsDNAServices dnaServices;

	@Autowired
	private ValidatedDNARepository validatedDNARepository;

	@Autowired
	private ValidatedDNAService service;

	@Test
	@DisplayName("Validar que no exista ningun registro")
	void initTest() {

		var stats = dnaServices.stats();
		log.info("######################## stats: {}", stats);

		assertNotNull(stats);

		// Vaidar humanos
		assertEquals(0, stats.getHumans());

		// Validar mutantes
		assertEquals(0, stats.getMutants());

		// Validar ratio
		assertEquals(0d, stats.getRatio());

		final String[] adnMutant = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		final var dna = new DNASequenceDto();
		dna.setDna(adnMutant);
		dna.setMutants(true);

		final var newAdn = service.insert(dna);
		stats = dnaServices.stats();
		log.info("######################## stats: {}", stats);
	}

	@Test
	@DisplayName("Valida la insersion de un adn mutado")
	void testInsertADNMutant() {

		final String[] adnMutant = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		final var dna = new DNASequenceDto();
		dna.setDna(adnMutant);
		dna.setMutants(true);

		final var newAdn = service.insert(dna);
		log.info("newAdn: {}", newAdn);

		assertNotNull(newAdn);
		assertNotNull(newAdn.getId());
		assertNotNull(newAdn.getHash());
		assertTrue(newAdn.getMutant());

	}

	@Test
	@DisplayName("Validar que no exista ningun registro")
	void initTest1() {

		var stats = dnaServices.stats();
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> stats: {}", stats);

		assertNotNull(stats);
		final String[] adnMutant = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };

		final var dna = new DNASequenceDto();
		dna.setDna(adnMutant);
		dna.setMutants(true);

		final var newAdn = service.insert(dna);

		stats = dnaServices.stats();
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> stats: {}", stats);

	}

}
