package br.framework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Representa toda e qualquer tabela de Dominio.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class DomainTable implements GenericEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;
	private String name;
	private String description;
	
	@Override
	public String toString() {	
		return name;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	
}
