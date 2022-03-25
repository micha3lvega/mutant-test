package co.com.mutants.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mercado.libre.commons.dto.DNAstatisticsDto;
import co.com.mercadolibre.validator.ADNValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MutantsService {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Metodo encargado de validar si existe o no una mutacion en el adn y luego
	 * guardalo en base de datoss
	 *
	 * @param dnaSequence
	 * @return
	 */
	public boolean isMutant(DNASequenceDto dnaSequence) {

		final var wacth = new StopWatch();
		wacth.start();

		try {

			final var isMutant = ADNValidator.isMutant(dnaSequence.getDna());
			log.info("isMutant: {}", isMutant);

			dnaSequence.setMutants(isMutant);

			insertAsync(dnaSequence);

			return dnaSequence.isMutants();

		} finally {
			wacth.stop();
			log.info("El metodo tardo {} milisegundos", wacth.getTotalTimeMillis());
		}

	}

	public DNAstatisticsDto stats() {

		return restTemplate.getForObject("http://localhost:8081/persitence/api/v1/stats", DNAstatisticsDto.class);

	}

	/**
	 * Metodo asincrono que envia una solictud para guardar el adn en la base de
	 * datos
	 *
	 * @param dnaSequence
	 */
	@Async("asyncExecutor")
	public void insertAsync(DNASequenceDto dnaSequence) {

		restTemplate.postForObject("http://localhost:8082/queue/api/v1", dnaSequence, DNASequenceDto.class);

	}

}
