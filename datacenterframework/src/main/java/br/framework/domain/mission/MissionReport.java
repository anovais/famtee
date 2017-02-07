package br.framework.domain.mission;

import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import br.framework.domain.GenericEntity;
import br.framework.domain.Status;


/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description:  	
 * Representa o relatório de status de uma missão em um dado periodo de tempo
 *
 */
@Entity
@Component
public class MissionReport implements GenericEntity {	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	//Data do staus
	@DateTimeFormat
	private Calendar date;	
	//Descrição do status
	private String description;
	//O tipo de relatório de status - Danificado, Funcionando, Debilitado, etc
	@OneToOne	
	private MissionState state;
	
	//Tipo de relatório, sucesso, erro, informação, etc
	@Embedded
	private Status status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public MissionState getState() {
		return state;
	}
	public void setState(MissionState state) {
		this.state = state;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	

}
