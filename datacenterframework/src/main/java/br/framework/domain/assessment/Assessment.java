package br.framework.domain.assessment;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.secutiry.User;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Representa a classe de avaliação um TRA - um processo de avaliação de maturidade tecnológica completo.
 * Fornece informações sobre o responsável pela avaliação e resultados.<br>
 * Cada momento de avalição TRA, gera uma instância diferente e única a ser armazenado na base de dados.
 */
@Entity
@Component
public class Assessment {

	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	// Uma lista das avaliaçãoes pela qual a tecnologia passou	
	@OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	@JoinColumn(name="assessment_id")
	private List<LevelAssessment> assessments;

	@OneToOne
	private User responsible;
	
	@DateTimeFormat
	private Calendar date;
	
	private String description;

	
	
	
	public static Assessment getInstance(){
		Assessment tra = new Assessment();
		tra.setDate(Calendar.getInstance());
		return tra;
	}
	

	public Assessment withAssessments(List<LevelAssessment> assessments){
		for (LevelAssessment trlAssessment : assessments) {
			trlAssessment.setId(null);	
			for (Artefact artefact : trlAssessment.getArtefacts()) 
				artefact.setId(null);
			for (TRLAnswer trlAnswer : trlAssessment.getQuizz()) {
				trlAnswer.setId(null);
				for (Artefact artefact : trlAnswer.getArtefacts()) 
					artefact.setId(null);
			}
		}
		this.assessments = assessments;
		return this;
	}
	
	
	public Assessment evaluetedBy(User user){
		responsible = user;
		return this;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LevelAssessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<LevelAssessment> assessments) {
		this.assessments = assessments;
	}

	public User getResponsible() {
		return responsible;
	}

	public void setResponsible(User responsible) {
		this.responsible = responsible;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
