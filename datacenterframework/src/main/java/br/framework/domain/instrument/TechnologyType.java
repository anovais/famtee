package br.framework.domain.instrument;

import javax.persistence.Entity;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Descreve um tipo de tecnologia, STW HWD BOTH 
 *
 */

public enum TechnologyType {

	SOFTWARE("S","Software"), HARDWARE("H","Hardware"), BOTH("B","Software/Hardware");
	
	private String code;
	private String name;
	
	private TechnologyType(String code,String name){
		this.code = code;
		this.name = name;
	}
	

	/**
	 * @param code código do tipo
	 * @return um tipo de tecnologia de acordo com o código informado
	 */
	public static TechnologyType getType(String code) {
		
		if( code.equalsIgnoreCase("S") )		
			return SOFTWARE;			
		if( code.equalsIgnoreCase("H") )			
			return HARDWARE;			
		if( code.equalsIgnoreCase("B") )
			return BOTH;	
					
		return null;
		
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
