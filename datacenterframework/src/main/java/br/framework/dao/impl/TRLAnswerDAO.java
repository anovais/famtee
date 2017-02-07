package br.framework.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.assessment.quiz.TRLQuestion;
import br.framework.domain.instrument.TechnologyType;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain TRLAnswer resposta TRL}
 *
 */
@Repository
public class TRLAnswerDAO extends GenericDAO<TRLAnswer> {

	
	@Autowired
	private TRLQuestionDAO questionDAO;
	
	
	public TRLQuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(TRLQuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	public TRLAnswerDAO() {
		super(TRLAnswer.class);		
	}

	

	
}
