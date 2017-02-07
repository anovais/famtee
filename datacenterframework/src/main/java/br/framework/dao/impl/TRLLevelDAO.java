package br.framework.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.framework.conf.JPAConfiguration;
import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.assessment.TRLLevel;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain TRLLevel level TRL}
 *
 */
@Repository
public class TRLLevelDAO extends GenericDAO<TRLLevel>{

	public TRLLevelDAO() {
		super(TRLLevel.class);
	}
	
	/* (non-Javadoc)
	 * @see br.framework.dao.GenericDAO#findById(java.lang.Integer)
	 */
	public TRLLevel findByLevel(Integer id) {
		
		
		String jpql = "select tl from TRLLevel tl where tl.level = :level";
		TypedQuery<TRLLevel> query =
				manager.createQuery(jpql, TRLLevel.class);
		
		query.setParameter("level", id);
		TRLLevel level = query.getSingleResult();
		return level;
	}

}
