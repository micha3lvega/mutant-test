package co.com.mutants.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercado.libre.commons.dto.DNAstatisticsDto;
import co.com.mutants.persistence.repository.ValidatedDNARepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StaticsDNAServices {

	@Autowired
	private ValidatedDNARepository dnaRepository;

	/**
	 * Metodo encargado de calculas las estadisticas del ADN validado
	 *
	 * @return - total de humanos, total de mutantes, ratio
	 */
	public DNAstatisticsDto stats() {

		log.trace("[[start]]");

		final var statics = new DNAstatisticsDto();

		// Obtener total de adn's validados
		final Long total = dnaRepository.count();
		log.trace("total: {}", total);
		statics.setHumans(total);

		// Obtener adn's con mutacion
		final var mutants = dnaRepository.findByMutant(true) != null ? dnaRepository.findByMutant(true).size() : 0L;
		statics.setMutants(mutants);
		log.trace("mutants: {}", mutants);

		// Calcular el ratio
		Double ratio = 0D;
		if (total > 0) {
			ratio = (double) mutants / total;
		}

		log.trace("ratio: {}", ratio);
		statics.setRatio(ratio);

		return statics;
	}

}
