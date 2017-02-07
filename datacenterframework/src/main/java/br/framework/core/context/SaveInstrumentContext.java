package br.framework.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.activity.LoadFirstAssessmentActivity;
import br.framework.core.context.activity.PhotoUploadActivity;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.Column;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Um contexto de várias atividades {@linkplain IActivity atividades} responsável por armazenar um {@linkplain Instrument instrumento} no tipo de dado apropriado.
 *   
 */
@Component
public class SaveInstrumentContext extends Context<Instrument>{


	@Autowired
	public SaveInstrumentContext(PhotoUploadActivity photoUploadActivity,LoadFirstAssessmentActivity firstAssessmentActivity){
		registerActivity(photoUploadActivity);
		registerActivity(firstAssessmentActivity);
		addParameter("defaultPhoto", "/resources/icons/instrument.png");
	}
	
	
	public void save(Instrument instrument){
		run(instrument);
	}
}
