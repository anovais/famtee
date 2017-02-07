package br.framework.domain.util;

import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.quiz.TRLAnswer;

/**
 * Author: Andre Novais <br>
 * Date: 03/12/2015 <br>
 * Progress.java
 * Description:  Determina o progresso de {@linkplain LevelAssessment nível TRL} e de {@linkplain TRLAnswer resposta TRL}
 */

public enum Progress {

	GREEN(80,"green.png","checked.png"), YELLOW(60,"yellow.png","half.png"), RED(1,"red.png","blank.png"), NONE(0,"none.png","blank.png");
	
	private Integer value;
	private String icon;
	private String alternativeIcon;

	/**
	 * @param value
	 */
	private Progress(Integer value, String icon, String alternativeIcon) {
		this.value = value;
		this.icon = icon;
		this.alternativeIcon = alternativeIcon;
	}

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	
	public static Progress build(Integer rate){
		if(rate >= GREEN.getValue())
			return GREEN;
		else if(rate >= YELLOW.getValue())
			return YELLOW;
		else if(rate >= RED.getValue())
			return RED;
		else
			return NONE;
	
	}

	public String getAlternativeIcon() {
		return alternativeIcon;
	}

	
	
		
}
	
