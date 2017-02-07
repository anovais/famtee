package br.framework.core.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.activity.QuestionEvalutateActivity;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.Column;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Um contexto de várias atividades {@linkplain IActivity atividades} responsável por iniciar o algoritmo de classficação de nível TRL. <br>
 * A principal {@link IActivity} desse contexto é a {@link QuestionEvalutateActivity}.
 */
@Component
public class TRLCalculatorContext extends Context<Instrument>{
	

	@Autowired
	public TRLCalculatorContext(QuestionEvalutateActivity activity){
		registerActivity(activity);
	}
	
	public void assessment(Instrument instrument){
		run(instrument);
	}
		
	
}
