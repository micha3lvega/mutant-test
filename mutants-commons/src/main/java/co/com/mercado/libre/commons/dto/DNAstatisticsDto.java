package co.com.mercado.libre.commons.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DNAstatisticsDto implements Serializable {

	@JsonProperty("count_human_dna")
	private Long humans;

	@JsonProperty("count_mutant_dna")
	private Long mutants;

	@JsonProperty("ratio")
	private Double ratio;

}
