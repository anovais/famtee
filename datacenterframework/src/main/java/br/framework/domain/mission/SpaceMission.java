package br.framework.domain.mission;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.framework.domain.PhotoEntity;
import br.framework.domain.GenericEntity;
import br.framework.domain.Institution;
import br.framework.domain.Metadata;
import br.framework.domain.assessment.Environment;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.secutiry.User;
import br.framework.domain.util.Period;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Representa uma missão espacial 	
 */
@Entity
@Component
public class SpaceMission extends Environment implements GenericEntity, PhotoEntity{
	
	/**
	 * identificação do veiculo espacial 
	 */
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Autowired
	private SpaceCraft spaceCraft;
	
	private String code;
	
	// Logs de reports do status da missão
	@OneToMany(fetch=FetchType.LAZY)
	private List<MissionReport> status;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Institution> institutions;
	
	@OneToMany(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	private List<User> researchers;
	
	@OneToMany(cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<Metadata> metadata;
	
	@Lob
	private String objective;
	
	//Payloads embarcadas
	@ManyToMany
	@JoinTable(name="mission_instrument")
	private List<Instrument> payloads = new ArrayList<Instrument>();
	
	@Embedded
	private Period period;
	@DateTimeFormat
	private Calendar launchDate;
	
	
	private String photoPath;
	
	public List<Institution> getInstitutions() {
		return institutions;
	}
	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
	public List<User> getResearchers() {
		return researchers;
	}
	public void setResearchers(List<User> researchers) {
		this.researchers = researchers;
	}
	public List<Metadata> getMetadata() {
		return metadata;
	}
	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public Calendar getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Calendar launchDate) {
		this.launchDate = launchDate;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public SpaceCraft getSpaceCraft() {
		return spaceCraft;
	}
	public void setSpaceCraft(SpaceCraft spaceCraft) {
		this.spaceCraft = spaceCraft;
	}
	public List<MissionReport> getStatus() {
		return status;
	}
	public void setStatus(List<MissionReport> status) {
		this.status = status;
	}
	public List<Instrument> getPayloads() {
		return payloads;
	}
	public void setPayloads(List<Instrument> payloads) {
		this.payloads = payloads;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
}
