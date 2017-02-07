package br.framework.domain.assessment.quiz;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.context.annotation.Lazy;

import br.framework.domain.GenericEntity;
import br.framework.domain.assessment.Artefact;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.util.Progress;

/**
 * Author: Andre Novais <br>
 * Date:  04/2016 <br>
 * Description: Resposta a uma{@linkplain TRLQuestion questão TRL} 	
 *
 */
@Entity
public class TRLAnswer implements GenericEntity{
	
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	@OneToOne
	@JoinColumn(name="question_id")
	private TRLQuestion question;
	
	private Double weight; 
	
	@Enumerated
	private Progress progress = Progress.NONE;
	
	private Boolean applicable = new Boolean( true );
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Artefact> artefacts = new ArrayList<Artefact>();
	
	public TRLAnswer(){
	}
	
	
	/**
	 * @param question pergunta a qual essa resposta se refere
	 */
	public TRLAnswer(TRLQuestion question) {
		this.question = question;		
	}
	/**
	 * @return the applicable
	 */
	public Boolean getApplicable() {
		return applicable;
	}
	/**
	 * @param applicable the applicable to set
	 */
	public void setApplicable(Boolean applicable) {
		this.applicable = applicable;
	}
	/**
	 * @return the question
	 */
	public TRLQuestion getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(TRLQuestion question) {
		this.question = question;
	}
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public void addArtefact(Artefact artefact){
		artefacts.add(artefact);
	}
	
	public TRLLevel getLevel(){
		return question.getLevel();
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


	public Progress getProgress() {
		return progress;
	}


	public void setProgress(Progress progress) {
		this.progress = progress;
	}


	public List<Artefact> getArtefacts() {
		return artefacts;
	}


	public void setArtefacts(List<Artefact> artefacts) {
		this.artefacts = artefacts;
	}
	

}
