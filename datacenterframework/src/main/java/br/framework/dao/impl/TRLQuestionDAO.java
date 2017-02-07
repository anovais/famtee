package br.framework.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.assessment.quiz.TRLQuestion;
import br.framework.domain.instrument.TechnologyType;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain TRLQuestion questão TRL}
 *
 */
@Repository
public class TRLQuestionDAO extends GenericDAO<TRLQuestion>{

	
	
	public TRLQuestionDAO() {
		super(TRLQuestion.class);
	}

	public List<TRLQuestion> loadQuestionByLevelAndType(TRLLevel level, TechnologyType type){
		
		String jpql = "select q from TRLQuestion q where q.level= :level ";
		Query query = null;
		if(type == TechnologyType.BOTH){
			query = manager.createQuery(jpql, TRLQuestion.class);
			query.setParameter("level", level);			
		}else {
		
			jpql += " and q.type != :type";			
			query = manager.createQuery(jpql, TRLQuestion.class);
			query.setParameter("level", level);
			
			if(type == TechnologyType.HARDWARE)
				query.setParameter("type", TechnologyType.SOFTWARE);
			if(type == TechnologyType.SOFTWARE)
				query.setParameter("type", TechnologyType.HARDWARE);
		}
		return query.getResultList();
	}
	
}
