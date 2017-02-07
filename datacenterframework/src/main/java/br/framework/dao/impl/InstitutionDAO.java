package br.framework.dao.impl;

import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain Institution instituição}
 *
 */
@Repository
public class InstitutionDAO extends GenericDAO<Institution>{

	public InstitutionDAO() {
		super(Institution.class);
	}

	

	
}
