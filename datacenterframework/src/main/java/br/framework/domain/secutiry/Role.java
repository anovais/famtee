package br.framework.domain.secutiry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016
 * Description: Papel de um usuário no sistema.
 * Usado principalmente pelo Spring Secutity para determinar políticas de acesso.
 */
@Component
@Entity
public class Role implements GenericEntity,GrantedAuthority{

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getAuthority() {
		return getDescription();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
