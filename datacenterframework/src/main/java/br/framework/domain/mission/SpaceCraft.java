package br.framework.domain.mission;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: O veículo espacial descrito de forma única e especifica 	
 */
@Entity
@Component
public class SpaceCraft implements GenericEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;

	private String name;
	
	private String description;
	
	//Categoria do SpaceCraft
	@OneToOne(cascade=CascadeType.DETACH)
	private SpaceCraftCategory category;
	
	//Dimensão X.Y.Z e Peso
	@Embedded
	@Autowired
	private Dimension dimension;
	
	//peso
	private Double weight;
	
	
	
	public SpaceCraftCategory getCategory() {
		return category;
	}


	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}


	public void setCategory(SpaceCraftCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
}
