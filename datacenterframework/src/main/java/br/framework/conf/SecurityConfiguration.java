package br.framework.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.framework.helper.FilePathBuilder;
/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Configuração exigida pelo componente de segurança do sistema: SPRING Security <br>
 * É realizado o controle de acesso a partes específicas da aplicação. 
 * 
 */
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private UserDetailsService users;
	
	/**
	 * Configuração de acesso ao sistema pelo protocolo HTTPS
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.antMatchers("/register").permitAll()
		.anyRequest().authenticated()			
		.and().formLogin().permitAll();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {	
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**").antMatchers("/"+FilePathBuilder.BASE_DIRECTORY+"/**");
			
	}
	
	
}
