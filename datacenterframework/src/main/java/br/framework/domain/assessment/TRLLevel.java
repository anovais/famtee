package br.framework.domain.assessment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Representa um Nível específico do indicador TRL ( de um à nove )	
 */
@Entity
public class TRLLevel implements GenericEntity{

	
	@Id 
	private Long id;
	@Lob	
	private String description;
	
	//Nível de TRL
	private Integer level;
	private String name;
	
	public TRLLevel(Long level){
		this.level =  level.intValue();
		this.id = level;
	}
	
	public TRLLevel(){
		
	}
	
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
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


	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
