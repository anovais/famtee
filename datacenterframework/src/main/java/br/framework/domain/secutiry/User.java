package br.framework.domain.secutiry;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.framework.domain.GenericEntity;
import br.framework.domain.Institution;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Descreve um pesquisador, stakeholder, ou usuário do sistema em geral. <br>
 * Implementa a interface UserDetails do Spring security para autenticação<br>
 */
@Entity
@Component
public class User implements GenericEntity, UserDetails{
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	private String name;
	private String register;
	private String email;
	private String lattes;
	private String password;
	
	/*
	 * Papéis exercidos
	 * 
	 */
	@OneToMany(fetch=FetchType.EAGER)
	private List<Role> roles;
	
	
	@OneToOne
	@JoinColumn(name="institution_id")
	private Institution institution;
	
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
	 * @return the register
	 */
	public String getRegister() {
		return register;
	}
	/**
	 * @param register the register to set
	 */
	public void setRegister(String register) {
		this.register = register;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lattes
	 */
	public String getLattes() {
		return lattes;
	}
	/**
	 * @param lattes the lattes to set
	 */
	public void setLattes(String lattes) {
		this.lattes = lattes;
	}
	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}
	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}
	@Override
	public String getPassword() {
		return password.isEmpty() ? null : password;
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {return true;}
	@Override
	public boolean isAccountNonLocked() {return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	@Override
	public boolean isEnabled() {return true;}
	public void setPassword(String password) {
		this.password = password;
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
