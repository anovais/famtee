package br.framework.core.context.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.Context;
import br.framework.core.context.ConverterContext;
import br.framework.core.context.IActivity;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.domain.mission.Column;
import br.framework.domain.mission.Telemetry;
import br.framework.domain.mission.RowData;


/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Uma atividade de conversão do arquivo sendo carregado pelo contexto {@link UploadTelemetryContext} <br>
 * Essa classe é responsável pela leitura dos bytes que compõe o arquivo de telemetria<br>
 * Ele identifica as colunas e linhas que compõe o arquivo CSV e inicia o contexto de conversão ( @{link ConverterContext} ) 
 * para cada coluna que lê.  
 */
@Component
public class TelemetryCSVConverterActivity implements IActivity<Telemetry>{

	private static final String SEPARATOR = ";";

	// Um contexto com activities que verificam o tipo do dado
	//TODO Melhorar o contexto de conversão com mais atividades verificativas de conversão  
	@Autowired
	private ConverterContext converterContext;
	
	
	@Override
	public void execute(Telemetry archive, Context context) {
		readAndConverter(archive);
	}

	
	/**
	 * Realiza a leitura e conversão dos dados de <br>
	 * telemetria  em documento do tipo JSON <br>
	 * @param archive
	 */
	private void readAndConverter(Telemetry archive){
			
		String line, params[],columns[];
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(archive.getFile().getInputStream()));
			columns = reader.readLine().split(SEPARATOR);
			while( (line = reader.readLine()) != null ){
				params = line.split(SEPARATOR);
				RowData data = new RowData();
				for (int i = 0; i < columns.length; i++) {
					// chamar a navegação de atividades para descobrir o tipo de dado
					converterContext.setResult(null);
					converterContext.converter(new Column(columns[i],params[i]));
					data.addColumn((Column)converterContext.getResult().getResultEntity());
				}
				archive.addRegister(data);
			}
		} catch (Exception e) {
		}
	}
	
}
