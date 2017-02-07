package br.framework.core.context.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.framework.core.context.Context;
import br.framework.core.context.ConverterContext;
import br.framework.core.context.IActivity;
import br.framework.core.context.Result;
import br.framework.domain.mission.Column;
/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Uma atividade do contexto {@link ConverterContext} <br>
 * Utilizada para transformar campos de data em objetos do tipo {@link Date} <br>  
 *
 */
public class DateTelemetryConverterActivity implements IActivity<Column>{

	@Override
	public void execute(Column column, Context context) {
		convertDateFields(column.getKey(),column.getValue(), context);
	}
	

	private void convertDateFields(String column, Object value, Context context) {
		if(! (column.toLowerCase().contains("date") || column.toLowerCase().contains("time")) )
			return;
		Date d;
		Column c;
		try{
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS").parse(value.toString());
		}catch (ParseException e){
			try {
				d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());
			}catch (ParseException ex){
				try {
					d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value.toString());
				}catch (ParseException exx){
					return;
				}
			}
		}
		Result r = new Result();
		r.setResultEntity(new Column("Timestamp", d));
		context.setResult(r);
			
	}

}
