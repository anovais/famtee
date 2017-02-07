package br.framework.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Criação de metadados para descrição das tecnologias. Assume-se que uma tecnologia possua outras informações não previstas que são relevates para sua caracterização.<br>
 * Esse campo permite descrever informações especificas sobre a tecnologia/instrumento   
 */
@Component
@Entity(name="metadata")
public class Metadata implements GenericEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	@Column(name="metadata_key")
	private String key;
	@Column(name="metadata_value")
	private  String value;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
