package br.framework.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.activity.InsertSpaceMissionActivity;
import br.framework.core.context.activity.PhotoUploadActivity;
import br.framework.domain.mission.SpaceMission;

@Component
public class InsertSpaceMissionContext extends Context<SpaceMission> {

	
	@Autowired	
	public InsertSpaceMissionContext( InsertSpaceMissionActivity insert, PhotoUploadActivity photo){
		registerActivity(photo);
		registerActivity(insert);
		addParameter("defaultPhoto", "/resources/icons/satellite.png");
	}

	public void insert(SpaceMission target) {
		run(target);
		
	}
	
}
