package br.framework.domain.assessment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.framework.domain.GenericEntity;

/**
 * 
 * @author Andre Novais <br>
 * Description: Tabela de dominio para os diferentes tipos de artefatos de maturidade <br>
 */
@Entity
public class ArtefactType implements GenericEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
