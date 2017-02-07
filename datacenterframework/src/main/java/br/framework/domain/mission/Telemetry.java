package br.framework.domain.mission;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Columns;
import org.springframework.web.multipart.MultipartFile;

import br.framework.domain.GenericEntity;
import br.framework.domain.util.Period;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Um objeto de telemetria para ser importado para o Centro de Dados com linhas representando os dados <br> 
 * 
 * */
public class Telemetry implements GenericEntity{

	private List<RowData> data = new ArrayList<RowData>();;

	@JsonIgnore
	private SpaceMission mission;
	@JsonIgnore
	private String sourceType;
	@JsonIgnore
	private MultipartFile file;
	@JsonIgnore
	private Period period;
	
	private List<String> columns;
	
	public void addRegister(RowData row){
		data.add(row);
	}
	
	public List<RowData> getData() {
		return data;
	}
	
	public void setData(List<RowData> data) {
		this.data = data;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public SpaceMission getMission() {
		return mission;
	}

	public void setMission(SpaceMission mission) {
		this.mission = mission;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}


	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	/*TODO Controle das colunas distintas de todos os registros
	 * Codigo precisa de refatoração na lógica. O código só é executado na primeira chamda <br>
	 * porque ele só é invocado na hora da apresentação. Solução perigosa.
	 */
	public List<String> getAllColumns() {
		if( columns != null)return columns;
		Set<String> distinct = new LinkedHashSet<String>();
		columns = new ArrayList<String>();
		distinct.add("Timestamp");
		for (RowData row : data)
			distinct.addAll(row.keySet());
		columns.addAll(distinct);
		return columns;
	}
	
}
