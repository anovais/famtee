package br.framework.domain.assessment;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.framework.domain.GenericEntity;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.util.Period;
import br.framework.domain.util.Progress;

/**
 * Author: Andre
 * Date: 04/2016
 * Description: Classe que carrega o conceito de uma avaliação de um nível de maturidade.<br>
 * Cada instância é um nível de TRL, carregando atributos que expressam seu resultado. <br>
 *
 */
@Entity
public class LevelAssessment implements GenericEntity, Cloneable{

	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Lob	
	private String description;

	//Level a que se refere	
	@OneToOne
	@JoinColumn(name="level_id")	
	private TRLLevel level;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)	
	private List<Artefact> artefacts = new ArrayList<Artefact>();
	
	//Armazena o resultado da avaliação em porcentagem de atendimento aos criterios
	@Enumerated(EnumType.STRING)
	private Progress progress;
	
	//Descrição do escopo em que a avaliação foi feita
	@Embedded
	private Scope scope;
	
	//Data da avaliação
	@Embedded
	private Period period;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="assessment_id")
	private List<TRLAnswer> quizz;
		
	public  LevelAssessment(){
		setProgress(Progress.NONE);
	}
	

	public LevelAssessment(TRLLevel level){
		this.level = level;
		setProgress(Progress.NONE);
	}

	
	
	public void addArtefact(Artefact artefact){
		artefacts.add(artefact);
	}
	
	
	/**
	 * @return the scope
	 */
	public Scope getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	/**
	 * @return the date
	 */
	public Period getPeriod() {
		return period;
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
	 * @param period the period to set
	 */
	public void setPeriod(Period period) {
		this.period = period;
	}

	/**
	 * @return the progress
	 */
	public Progress getProgress() {
		return progress;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public List<Artefact> getArtefacts() {
		return artefacts;
	}

	public void setArtefacts(List<Artefact> artefacts) {
		this.artefacts = artefacts;
	}


	public List<TRLAnswer> getQuizz() {
		return quizz;
	}


	public void setQuizz(List<TRLAnswer> quizz) {
		this.quizz = quizz;
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

	@Override
	public LevelAssessment clone() throws CloneNotSupportedException {
		return (LevelAssessment) super.clone();
	}
	
}
