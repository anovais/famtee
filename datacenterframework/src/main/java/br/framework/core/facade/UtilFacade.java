package br.framework.core.facade;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.dao.GenericDAO;
import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description:  Fornece serviço de acesso ao banco de dados para tabelas de dominio<br> 
 *
 */
@Component
public class UtilFacade {

	@Autowired
	private GenericDAO genericDAO;
	
	
	public Collection<GenericEntity> getDomainTable(Class classe){
		genericDAO.setEntityClass(classe);
		return genericDAO.listAll();		
	}
	
	public GenericEntity getLazyId(Class classe, Long id){
		genericDAO.setEntityClass(classe);
		return genericDAO.findById(id);		
	}
}
