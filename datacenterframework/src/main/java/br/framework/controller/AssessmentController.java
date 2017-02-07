package br.framework.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import br.framework.core.facade.AssessmentFacade;
import br.framework.core.facade.InstrumentFacade;
import br.framework.domain.assessment.Artefact;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Invoca as operações de {@link Assessment} usando {@linkplain AssessmentFacade sua fachada}.
 */
@Controller
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@SessionAttributes({"facade"})
@RequestMapping("/assessment")
@Transactional
public class AssessmentController {

	@Autowired
	InstrumentFacade instrumentFacade;
	@Autowired
	AssessmentFacade facade;
	@RequestMapping(value="/evaluate/{code}")
	public ModelAndView assessment(@PathVariable("code") String code){
		ModelAndView model = new ModelAndView();
		model = new ModelAndView("instrument/instrument-assessment");
		Instrument instrument = instrumentFacade.findByCode(code);
		facade.forInstrument(instrument);		
		model.addObject("instrument", instrument);
		return model;
	}
	
	
	@RequestMapping(value = "/answer", method = RequestMethod.POST,  produces={"application/json; charset=UTF-8"})
	public @ResponseBody String answer(Long answer, Integer value, String description) {		
		return facade.answerQuestion(answer,value,description).getProgress().getAlternativeIcon();
	}

	
	@RequestMapping(value = "/artefact", method = RequestMethod.POST,  produces={"application/json; charset=UTF-8"})
	public @ResponseBody String upload(MultipartHttpServletRequest   request, 
			HttpServletResponse response, String name, String description, Long id, String type) {                 

		Artefact artefact = new Artefact();
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		try {
			artefact.setFile(mpf);
			artefact.setName( new String(name.getBytes(),"UTF-8" ) );
			artefact.setDescription(new String(description.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(type.equals("answer"))
			facade.uploadArtefact4Answer(id, artefact);
		else
			facade.uploadArtefact4Assessment(id, artefact);
		
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder r = new StringBuilder(f.format( artefact.getUploadDate().getTime() ))
		.append(",").append(artefact.getResourceURL())
		.append(",").append(artefact.getExtension())
		.append(",").append(artefact.getCode());
		return r.toString();
	}
	
	
	@RequestMapping(value="/removeArtefact", method = RequestMethod.POST,  produces={"application/json; charset=UTF-8"})
	public @ResponseBody String removeArtefact(String code){
		facade.removeArtefact(code);
		return "succes";
	}

	
	@RequestMapping(value="/submit",method=RequestMethod.POST)
	public ModelAndView submitAssessment(){
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		facade.submitAssessmentProcess(user);
		return new ModelAndView("redirect:/instrument/profile/" + facade.getInstrument().getCode());
	}

	
}
