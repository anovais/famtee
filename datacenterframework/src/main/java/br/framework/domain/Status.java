package br.framework.domain;

import javax.persistence.Embeddable;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: um status genérico - success, alert, warning, info, 
 *
 */
@Embeddable
public enum Status {
	
	SUCCESS(1),ALERT(2),INFO(3),WARNING(4),DANGER(5),ERROR(6);
	
	private int code;
	
	private Status(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
