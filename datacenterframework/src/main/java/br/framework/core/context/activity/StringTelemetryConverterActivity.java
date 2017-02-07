package br.framework.core.context.activity;

import java.util.Date;

import br.framework.core.context.Context;
import br.framework.core.context.ConverterContext;
import br.framework.core.context.IActivity;
import br.framework.core.context.Result;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.domain.mission.Column;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Uma atividade do contexto {@link ConverterContext}<br>
 * Converte as colunas de texto do arquivo sendo carregado <br>  
 *
 */
public class StringTelemetryConverterActivity implements IActivity<Column>{

	@Override
	public void execute(Column column, Context context) {
		if( !context.hasResult() ){
			Result r = new Result();
			r.setResultEntity(column);
			context.setResult(r);
		}
	}
	
}
