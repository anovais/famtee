package br.framework.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.Institution;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.SpaceMission;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para a entidade de {@linkplain SpaceMission missão espacial}
 *
 */
@Repository
public class SpaceMissionDAO extends GenericDAO<SpaceMission>{

	public SpaceMissionDAO() {
		super(SpaceMission.class);
	}

	public SpaceMission findByName(String name) {
		TypedQuery<SpaceMission> query = manager.createQuery("select s from SpaceMission s where s.name = :name",SpaceMission.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

	
}
