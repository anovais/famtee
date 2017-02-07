package br.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.framework.core.facade.AssessmentFacade;
import br.framework.domain.assessment.Assessment;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Atende a requisição inicial da aplicação
 */
@Controller
public class HomeController {

	
	@RequestMapping("/")
	public String index(){		
		System.out.println("Está na index do controller");
		return "index";
	}
}
