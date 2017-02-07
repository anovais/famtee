package br.framework.domain.instrument;

import java.util.ArrayList;
import java.util.Calendar;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.framework.core.context.NavigableEntity;
import br.framework.domain.PhotoEntity;
import br.framework.domain.GenericEntity;
import br.framework.domain.Metadata;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.mission.SpaceMission;
import br.framework.domain.secutiry.User;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016
 * Description: Representa uma tecnologia,instrumento ou experimento espacial em fase qualificação. <br> 	
 * Esta é a entidade que sofrerá avaliações e pode manter um histórico sobre a evolução de {@linkplain Assessment avaliações} pela qual passou.
 * Ela gerencia seu histórico por meio de uma {@link ArrayList} o que permite que sejam armazenadas na ordem em que foram feitas.
 */
@Entity
@Component
public class Instrument implements GenericEntity, NavigableEntity, PhotoEntity{

	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	//Nome do instrumento
	@NotBlank //campo obrigatório
	private String name;	
	//Todo o histórico de TRA ( avaliações de TRL )
	@OneToMany(cascade=CascadeType.ALL , fetch=FetchType.LAZY)
	@JoinColumn(name="instrument_id")
	@Autowired
	private List<Assessment> TRAs;
	//Todos os pesquisadores envolvidos
	@OneToMany(fetch=FetchType.LAZY)
	private List<User> responsible;
	
	//Caracterização do objetivo do desenvolvimento da tecnologia
	@NotBlank
	@Lob	
	private String objective;
	
	//Um código identificador
	@NotBlank
	private String code;
	
	//url de foto
	private  String photoPath;	
	
	
	@OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<Metadata> metadata;
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="payloads")	
	private List<SpaceMission> missions;
	
	/**
	 * Data do inicio do projeto
	 */
	@DateTimeFormat
	private Calendar initDate;
	
		
	@Enumerated
	private TechnologyType type;
	
	
	
	public TechnologyType getType() {
		return type;
	}

	public void setType(TechnologyType type) {
		this.type = type;
	}

	public List<SpaceMission> getSpaceMissionsFlew(){		
		return missions;		
	}
	
	public Integer getCountSpaceMissionsFlew(){
		return missions.size();
	}

	
	public LevelAssessment getAssessment(Integer level){
		for (LevelAssessment assessment : getCurrentAssessment().getAssessments()) {
			if(assessment.getLevel().getLevel().equals(level))
				return assessment;
		}
		return null;
	}
	
	
	public List<TRLAnswer> getTRLQuiz(){
		List<TRLAnswer> list = new ArrayList<TRLAnswer>();
		for (LevelAssessment assessment : getCurrentAssessment().getAssessments()) 
			list.addAll(assessment.getQuizz());
		return list;
	}
	
	
	public Assessment getCurrentAssessment(){
		return TRAs.get(TRAs.size()-1);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the responsible
	 */
	public List<User> getResponsible() {
		return responsible;
	}

	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(List<User> responsible) {
		this.responsible = responsible;
	}

	/**
	 * @return the objective
	 */
	public String getObjective() {
		return objective;
	}

	
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	/**
	 * @param objective the objective to set
	 */
	public void setObjective(String objective) {
		this.objective = objective;
	}






	public List<Metadata> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}

	
	@Override
	public String toString() {
	
		return name + "\n" + getDescription() + "\n" + getObjective() + "\n" + getCode()+ "\n";
	}

	public Calendar getInitDate() {
		return initDate;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;

	
	}

	public List<LevelAssessment> getAssessments() {
		return getCurrentAssessment().getAssessments();
	}


	public List<SpaceMission> getMissions() {
		return missions;
	}

	public void setMissions(List<SpaceMission> missions) {
		this.missions = missions;
	}

	public void addTRA(Assessment tra) {
			TRAs.add(tra);
	}


	public List<Assessment> getTRAs() {
		return TRAs;
	}

	public void setTRAs(List<Assessment> tRAs) {
		TRAs = tRAs;
	}
	
}

