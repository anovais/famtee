package br.framework.dao.impl;

import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.mission.SpaceCraftCategory;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain SpaceCraftCategory categoria de veiculo espacial}
 *
 */
@Repository
public class SpaceCraftCategoryDAO extends GenericDAO<SpaceCraftCategory>{

	public SpaceCraftCategoryDAO() {
		super(SpaceCraftCategory.class);
	}
}
