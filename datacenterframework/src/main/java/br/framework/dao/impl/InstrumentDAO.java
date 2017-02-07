package br.framework.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.SQLUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain Instrument instrumento}
 *
 */
@Repository
public class InstrumentDAO extends GenericDAO<Instrument>{


	
	@Autowired
	private TRLAssessmentDAO assessmentDAO;
	


	public InstrumentDAO(){
		super(Instrument.class);
	}
	
	
	public void insert(Instrument t) {
		super.insert(t);
	}
	

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}


	public TRLAssessmentDAO getAssessmentDAO() {
		return assessmentDAO;
	}


	public void setAssessmentDAO(TRLAssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}


	public Instrument findByCode(String code) {
		TypedQuery<Instrument> query = manager.createQuery("select i from Instrument i where i.code = :code",Instrument.class);
		query.setParameter("code", code);
		return query.getSingleResult();
	}

	

	
	
	

	
}
