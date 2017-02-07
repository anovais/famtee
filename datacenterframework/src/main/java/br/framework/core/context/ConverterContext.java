package br.framework.core.context;

import org.springframework.stereotype.Component;

import br.framework.core.context.activity.DateTelemetryConverterActivity;
import br.framework.core.context.activity.StringTelemetryConverterActivity;
import br.framework.domain.mission.Column;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Um contexto de várias atividades {@linkplain IActivity atividades} responsável por converter uma {@linkplain Column coluna} no tipo de dado apropriado.
 * O arquivo CSV é apenas texto, portanto é preciso identificar o tipo de dado daquela coluna e para armazená-lo apropriadamente.
 *   
 */
@Component
public class ConverterContext extends Context<Column> {


	public ConverterContext() {
			registerActivity(new DateTelemetryConverterActivity());
			registerActivity(new StringTelemetryConverterActivity());
	}
	
	
	public void converter(Column c){
		run(c);
	}
	
}
