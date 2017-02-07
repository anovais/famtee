package br.framework.helper.details;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.domain.Institution;
import br.framework.domain.assessment.Artefact;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.SpaceMission;
import br.framework.domain.secutiry.User;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016
 * Description: Usado para simplificar a apresentação dos dados de uma {@linkplain SpaceMission missão espacial} na camada de visualização.
 */
@Component
public class SpaceMissionDetails {

	
	@Autowired
	private List<Instrument> payloads;
	
	@Autowired
	private List<User> researchers;
	
	@Autowired
	private List<Institution> institutions;

	public List<Instrument> getPayloads() {
		return payloads;
	}

	public void setPayloads(List<Instrument> payloads) {
		this.payloads = payloads;
	}

	public List<User> getResearchers() {
		return researchers;
	}

	public void setResearchers(List<User> researchers) {
		this.researchers = researchers;
	}

	public List<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
	
	
	
	
	
}
