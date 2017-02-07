package br.framework.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain LevelAssessment avaliação}
 *
 */
@Repository
public class TRLAssessmentDAO extends GenericDAO<LevelAssessment>{
	
	
	@Autowired
	private TRLAnswerDAO answerDAO;
	
	@Autowired
	private TRLLevelDAO levelDAO;
	
	public TRLLevelDAO getLevelDAO() {
		return levelDAO;
	}



	public void setLevelDAO(TRLLevelDAO levelDAO) {
		this.levelDAO = levelDAO;
	}



	TRLAssessmentDAO(){
		super(LevelAssessment.class);
	}
	
	
	
	//Carrega o quiz INICIAL para a tecnologia passada por parâmetro
	public void loadAppropriatedQuiz(Instrument instrument){
		
	}
	

	public TRLAnswerDAO getAnswerDAO() {
		return answerDAO;
	}

	public void setAnswerDAO(TRLAnswerDAO answerDAO) {
		this.answerDAO = answerDAO;
	}
	
	
	
	
}
