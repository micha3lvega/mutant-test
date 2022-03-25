package co.com.mercadolibre.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.com.mercado.libre.commons.exception.InvalidLengthException;
import co.com.mercado.libre.commons.exception.LetterInvalidException;

class ADNValidatorTest {

	@Test
	void testIsMutant() {
		final String[] adnMutant = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		final var isMutant = ADNValidator.isMutant(adnMutant);
		assertTrue(isMutant);
	}

	@Test
	void testNoMutant() {
		final String[] adnMutant = { "ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CACATA", "TCACTG" };
		final var isMutant = ADNValidator.isMutant(adnMutant);
		assertTrue(isMutant);
	}

	@Test
	void invalidLengthAdn() {
		final String[] adnMutant = { "ATGCGA" };
		assertThrows(InvalidLengthException.class, () -> ADNValidator.isMutant(adnMutant));
	}

	@Test
	void invalidBaseADN() {
		final String[] adnMutant = { "X" };
		assertThrows(LetterInvalidException.class, () -> ADNValidator.isMutant(adnMutant));
	}
}
