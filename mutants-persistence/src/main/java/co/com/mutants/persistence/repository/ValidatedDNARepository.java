package co.com.mutants.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.mutants.persistence.entity.ValidatedDNA;

public interface ValidatedDNARepository extends MongoRepository<ValidatedDNA, String> {

	ValidatedDNA findByHash(String hash);

}
