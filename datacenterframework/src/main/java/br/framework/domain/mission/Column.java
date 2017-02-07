package br.framework.domain.mission;

import br.framework.domain.GenericEntity;
import br.framework.domain.assessment.quiz.TRLQuestion;


/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Uma coluna de um {@linkplain Telemetry arquivo de telemetria}. 	
 */
public class Column implements GenericEntity{

	private String key;
	private Object value;
	
	
	public Column(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
