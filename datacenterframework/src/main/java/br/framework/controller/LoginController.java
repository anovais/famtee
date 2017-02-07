package br.framework.controller;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.framework.core.facade.AssessmentFacade;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.secutiry.User;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Realiza o login e identificação do {@linkplain User usuário}.
 */
@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class LoginController {
	
	
	
	@RequestMapping(value="/userLogin", method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView m = new ModelAndView("login");
		return m;
	}
	
	
	
	

	 
}
