package br.framework.domain.assessment.quiz;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import br.framework.domain.GenericEntity;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.instrument.TechnologyType;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description:Descreve uma questão adaptada do  TRL Calculator   	
 */
@Entity
public class TRLQuestion implements GenericEntity{

	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	@OneToOne(cascade=CascadeType.DETACH)
	private TRLLevel level;
	@Enumerated(EnumType.STRING)
	private TechnologyType type;
	//se a questão demanda resposta descritiva
	private boolean descriptive;
	//se a questão demanda upload de artefato
	private boolean artefactRequired;
	
	private Integer weight;

	
	
	
	public TRLQuestion(){
		
	}
	
	public TRLQuestion withLevel(int level){
		Long l = (long) level;		
		setLevel(new TRLLevel(l));
		return this;
	}
	public TRLQuestion withDescription(String description){
		setDescription(description);
		return this;
	}

	public TRLQuestion applicableTo(TechnologyType type){
		setType(type);
		return this;
	}
	
	public TRLQuestion(String description){
		setDescription(description);
	}
	
	/**
	 * @return the level
	 */
	public TRLLevel getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(TRLLevel level) {
		this.level = level;
	}
	/**
	 * @return the type
	 */
	public TechnologyType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TechnologyType type) {
		this.type = type;
	}


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

	public boolean isDescriptive() {
		return descriptive;
	}

	public void setDescriptive(boolean descriptive) {
		this.descriptive = descriptive;
	}

	public boolean isArtefactRequired() {
		return artefactRequired;
	}

	public TRLQuestion setArtefactRequired(boolean artefactRequired) {
		this.artefactRequired = artefactRequired;
		return this;
	}


	public Integer getWeight() {
		return weight;
	}

	public TRLQuestion weight(Integer weight) {
		this.weight = weight;
		return this;
	}
		
}
