/**
 *
 */
package co.com.mercado.libre.commons.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objecto que representa la cadena de una persona
 *
 * @author Michael Vega
 *
 */
@Data
@NoArgsConstructor
public class DNASequence implements Serializable {

	private List<String> dna;

}
