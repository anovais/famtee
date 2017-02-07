package br.framework.domain.assessment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Descreve o contexto em que a avaliação é válida.<br>
 * Um nível de TRL avalia a tecnologia sobre circunstâncias específicas, 
 * o que pode valer para determinada situação pode não ser verdade em outras.
 */
@Embeddable
public class Scope implements  GenericEntity{
	
	
	@Column(name="scope_description")
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
