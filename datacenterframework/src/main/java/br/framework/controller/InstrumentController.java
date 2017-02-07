package br.framework.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.framework.core.facade.InstrumentFacade;
import br.framework.dao.impl.TRLAnswerDAO;
import br.framework.domain.GenericEntity;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.instrument.TechnologyType;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Invoca as operações de {@link Instrument} usando {@linkplain InstrumentFacade sua fachada}.
 */
@Controller
@Transactional
@RequestMapping("/instrument")
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@SessionAttributes("instrument")
public class InstrumentController implements GenericEntity{

	@Autowired
	private InstrumentFacade facade;

	@Autowired
	private TRLAnswerDAO answerDAO;

	//usado como cache
	private Instrument instrument;
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public ModelAndView formLink(Instrument instrument){
		this.instrument = instrument;
		ModelAndView mv = new ModelAndView("instrument/instrument-form", "instrument", instrument);
		mv.addObject("mapTypes", TechnologyType.values());
		return mv;
	}

	@RequestMapping(value="/form", method=RequestMethod.POST, name="newInstrument")
	public ModelAndView addInstrument(@Valid Instrument instrument,
			MultipartFile photo,
			BindingResult resultValidations, 
			RedirectAttributes attributes){
		
		//Validações
		if(resultValidations.hasErrors()){
			return new ModelAndView("instrument/instrument-form");
		}
		facade.insert(instrument, photo);
		//Mensagem de feedback
		attributes.addFlashAttribute("msg", "Adicionado com sucesso");
		attributes.addFlashAttribute("result","ok" );
		this.instrument = instrument;
		return new ModelAndView("redirect:listAll");
	}


	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public ModelAndView listInstruments(){
		ModelAndView view = new ModelAndView("/list");
		view.addObject("list",facade.listAll());
		view.addObject("type","instrument");
		view.addObject("title",new String("Lista de Instrumentos"));
		return view;
	}

	@RequestMapping("/profile/{name}")
	public ModelAndView profile(@PathVariable("name") String name){		 
		ModelAndView view = new ModelAndView("instrument/instrument-profile");
		instrument = facade.findByCode(name);		
		view.addObject("instrument", instrument);
		return view;
	}
	

}
