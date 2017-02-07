package br.framework.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.framework.core.facade.AssessmentFacade;
import br.framework.dao.impl.UserDAO;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.secutiry.User;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Cadastro de novos usuários do sistema.
 */
@Controller
@Transactional
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class UserController {
	
	@RequestMapping("/ola")
	public ModelAndView testeJQuery(){
		
		ModelAndView m = new ModelAndView("teste/ajax");
		
		return m;
	}
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView addUser(User user){
		ModelAndView model;
		try{
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userDAO.insert(user);
		}catch(Exception e){
			
			throw e;
		}
		model = new ModelAndView("redirect:/instrument/listAll");
		return model;
	}

	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView register(User user){
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("user", new User());
		return mv;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}



	
}
