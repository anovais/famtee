package br.framework.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.activity.TelemetrySearchActivity;
import br.framework.domain.mission.SpaceMission;
import br.framework.domain.mission.Telemetry;
import br.framework.domain.util.Period;

@Component
public class SearchTelemetryContext extends Context{

	
	@Autowired
	public SearchTelemetryContext(TelemetrySearchActivity s){
		registerActivity(s);
	}
	
	
	
	
	public Telemetry search(SpaceMission mission, String type, Period period){
		Telemetry t = new Telemetry();
		t.setMission(mission);
		t.setPeriod(period);
		t.setSourceType(type);
		run(t);
		return t;
		
	}
}
