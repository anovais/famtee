package br.framework.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.activity.TelemetryCSVConverterActivity;
import br.framework.core.context.activity.TelemetryInsertActivity;
import br.framework.domain.mission.Column;
import br.framework.domain.mission.Telemetry;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Um contexto de várias atividades {@linkplain IActivity atividades} para upload de telemetria.
 * É um contexto executado em cima da entidade {@link Telemetry}
 *   
 */
@Component
public class UploadTelemetryContext extends Context<Telemetry>{
	
	

	@Autowired
	public UploadTelemetryContext(TelemetryInsertActivity insertActivity,TelemetryCSVConverterActivity activity){
		registerActivity(activity);
		registerActivity(insertActivity);
	}
	
	public void upload(Telemetry archive){
		run(archive);
	}
}
