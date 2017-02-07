package br.framework.dao.impl;

import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.DomainTable;
/**
 * Author: Andre
 * Date: 10/2016
 * Description: Acesso ao banco de dados relacional para tabelas de dominio da aplicação
 *
 */
@Repository
public class DomainTableDAO extends GenericDAO<DomainTable> {

	public DomainTableDAO(){
		super(DomainTable.class);
	}	
}
