package co.com.mutants.persistence.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "validated-dna")
public class ValidatedDNA implements Serializable {

	@Id
	private String id;

	private String[] dna;

	@Indexed(direction = IndexDirection.ASCENDING, name = "index_unique_hash", unique = true)
	private String hash;

	private Boolean mutant;

}
