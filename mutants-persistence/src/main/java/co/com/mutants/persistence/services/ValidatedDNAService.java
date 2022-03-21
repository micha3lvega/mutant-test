package co.com.mutants.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mutants.persistence.entity.ValidatedDNA;
import co.com.mutants.persistence.repository.ValidatedDNARepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidatedDNAService {

	@Autowired
	private ValidatedDNARepository dnaRepository;

	/**
	 * Metodo que inserta en la base de datos un ADN solo si este no existe
	 *
	 * @param sequence
	 * @return la entidad creada
	 */
	public ValidatedDNA insert(DNASequenceDto sequence) {

		log.trace("[insert] sequence: {}", sequence);

		// Genera el hash
		final var hash = generateHash(sequence);

		// Busca si la secuencia de ADN ya existe o no
		final var exitsSequence = validateExistHash(hash);
		if (exitsSequence != null) // Si ya existe no se inserta y se retorna la entidad existente
			return exitsSequence;

		// Generar objecyo
		final var dna = new ValidatedDNA();
		dna.setMutant(sequence.isMutants());
		dna.setDna(sequence.getDna());
		dna.setHash(hash);

		// Insertar en base de datos
		return dnaRepository.insert(dna);

	}

	/**
	 * Metodo que busca un {@link ValidatedDNA} por su hash
	 *
	 * @param hash
	 * @return la entidad {@link ValidatedDNA} encontrada
	 */
	public ValidatedDNA validateExistHash(String hash) {

		return dnaRepository.findByHash(hash);
	}

	/**
	 * metodo encargado de generar un hash con cada uno de los items del arreglo
	 *
	 * @param sequence
	 * @return
	 */
	private String generateHash(DNASequenceDto sequence) {

		final var builder = new StringBuilder();

		if (sequence.getDna() != null) {
			for (final String sequenceADN : sequence.getDna()) {
				builder.append(sequenceADN);
			}
		}

		return builder.toString();
	}
}
