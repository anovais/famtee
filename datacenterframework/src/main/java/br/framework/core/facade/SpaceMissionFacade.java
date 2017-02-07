package br.framework.core.facade;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.InsertSpaceMissionContext;
import br.framework.core.context.SearchTelemetryContext;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.dao.MongoDB;
import br.framework.dao.impl.SpaceMissionDAO;
import br.framework.domain.mission.SpaceMission;
import br.framework.domain.mission.Telemetry;
import br.framework.domain.util.Period;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description:  Classe de fachada para operações relativas à {@linkplain SpaceMission missão espacial} <br>
 * O upload de teletria é realizado usando um contexto de {@linkplain UploadTelemetryContext upload} que importa os dados para uma base de dados NoSQL.  
 *
 */
@Component
public class SpaceMissionFacade implements IFacade<SpaceMission>{

	@Autowired
	private SpaceMissionDAO dao;
	
	@Autowired
	private InsertSpaceMissionContext insertContext;
	
	@Autowired
	SearchTelemetryContext searchTelemetryContext;
	
	@Autowired
	private MongoDB mongo;
	
	@Autowired
	private UploadTelemetryContext uploadContext;
	
	
	@Override
	public void insert(SpaceMission target) {
		insertContext.insert(target);
	}

	@Override
	public void delete(SpaceMission target) {
		dao.remove(target);
	}

	@Override
	public void update(SpaceMission target) {
		dao.update(target);
	}

	@Override
	public Collection<SpaceMission> listAll() {
		return dao.listAll();
	}


	@Override
	public SpaceMission findById(Long id) {
		return dao.findById(id);
	}

	public SpaceMission findByName(String name) {
		return dao.findByName(name);
	}

	public void uploadTelemetry(Telemetry archive) {
		uploadContext.upload(archive);
	}

	public Iterable<String> getCollections(SpaceMission mission) {
		 return mongo.getDatabase(mission.getCode()).listCollectionNames();		 
		 
	}

	public Telemetry searchTelemetry(SpaceMission mission,String type,Period period){
		return searchTelemetryContext.search(mission, type, period);
	}
	
}
