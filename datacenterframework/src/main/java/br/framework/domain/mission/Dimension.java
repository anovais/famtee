package br.framework.domain.mission;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Dimensões de um satélite 	
 */
@Embeddable
@Component
public class Dimension implements GenericEntity{

	private Double height;
	private Double width;
	private Double depth;

	@Override
	public String toString() {
	
		return height + " x "+width+ " x " +  depth;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}
	
	
	
		
	
}
