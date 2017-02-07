package br.framework.core.context.activity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.core.context.SaveInstrumentContext;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.core.facade.TRLFacade;
import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Atividade do contexto {@link SaveInstrumentContext}<br>
 * Utilizada para inserir uma carregar as questões associadas a tecnologia para avaliação de maturidade<br>  
 *
 */
@Component
public class LoadFirstAssessmentActivity implements IActivity<Instrument>{

	@Autowired
	private TRLFacade trlFacade;
	@Override
	public void execute(Instrument instrument, Context context) {
		trlFacade.fillAnswersToInstrument(instrument);
	}

}
