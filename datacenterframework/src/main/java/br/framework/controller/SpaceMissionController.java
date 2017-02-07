/**
 * 
 */
package br.framework.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import br.framework.core.facade.InstrumentFacade;
import br.framework.core.facade.SpaceMissionFacade;
import br.framework.core.facade.UtilFacade;
import br.framework.domain.GenericEntity;
import br.framework.domain.Institution;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.SpaceCraftCategory;
import br.framework.domain.mission.SpaceMission;
import br.framework.domain.mission.Telemetry;
import br.framework.domain.util.Period;
import br.framework.domain.util.Progress;
import br.framework.helper.details.SpaceMissionDetails;
/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Invoca as operações de {@link SpaceMission} usando {@linkplain SpaceMissionFacade sua fachada}.
 */
@Controller
@Transactional
@SessionAttributes
@Scope(value=WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/mission")
public class SpaceMissionController implements GenericEntity {

	
	@Autowired
	private SpaceMission mission;
	
	@Autowired
	private SpaceMissionFacade facade;
	
	@Autowired
	private InstrumentFacade instrumentFacade;
	
	@Autowired
	private UtilFacade utilFacade;

	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public ModelAndView form(SpaceMission newMission){
	ModelAndView model = new ModelAndView("mission/mission-form");
	 model.addObject("spaceCategoryList",utilFacade.getDomainTable(SpaceCraftCategory.class));
	 this.mission = newMission;
	 model.addObject("mission",mission);
	 return model;
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public ModelAndView addSpaceMission(SpaceMission mission){
		this.mission = mission;
		facade.insert(mission);
		ModelAndView mv = new ModelAndView("mission/mission-form-details");
		mv.addObject("details",new SpaceMissionDetails());
		mv.addObject("payloads",utilFacade.getDomainTable(Instrument.class));
		mv.addObject("institutions",utilFacade.getDomainTable(Institution.class));
		return mv;
	}

	

	@RequestMapping(value = "/telemetry", method = RequestMethod.POST,  produces={"application/json; charset=UTF-8"})
	public @ResponseBody String upload(MultipartHttpServletRequest   request, 
			HttpServletResponse response, String missionCode, String type) {                 

		Telemetry archive = new Telemetry();
		Iterator<String> itr =  request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		try {
			archive.setFile(mpf);
			archive.setMission( mission );
			archive.setSourceType(new String(type.getBytes(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		facade.uploadTelemetry(archive);
		
		return "success";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchTelemetry(Date startDate, Date endDate,String typeTelemetry) {
		Telemetry t = facade.searchTelemetry(mission, typeTelemetry, new Period(startDate,endDate));
		ModelAndView mv = 	profile(mission.getCode());
		mv.addObject("telemetry",t);
		return mv;
	}
	
	@RequestMapping(value="/form-details", method=RequestMethod.POST)
	public ModelAndView addSpaceMissionDetails(SpaceMissionDetails details){
		mission.setInstitutions(details.getInstitutions());
		mission.setPayloads(details.getPayloads());
		mission.setResearchers(details.getResearchers());
		facade.update(mission);
		return new ModelAndView("redirect:mission/profile?id=" + mission.getId());
	}
	
	
	@RequestMapping(value="/profile/{name}", method=RequestMethod.GET)
	public ModelAndView profile(@PathVariable("name") String name){
		ModelAndView mv = new ModelAndView("mission/mission-profile");
		Collection<SpaceMission> missions = facade.listAll();
		mission = facade.findByName(name);
		for (SpaceMission spaceMission : missions) {
			Hibernate.initialize(spaceMission.getPayloads());
		}
		
		Iterator<String> it = facade.getCollections(mission).iterator();
		StringBuilder s = new StringBuilder();
		List<String> collections = new ArrayList<String>();
		while(it.hasNext()){
			String t = it.next();
			collections.add(t);
			s.append(",").append(t);
		}
		mv.addObject("collections",s.toString().replaceFirst(",",""));
		mv.addObject("fileTypes",collections);
		mv.addObject("list",missions);
		mv.addObject("mission", mission);
		return mv;
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public ModelAndView listInstruments(){
		ModelAndView view = new ModelAndView("/list");
		view.addObject("list",facade.listAll());
		view.addObject("type","mission");
		view.addObject("title",new String("Lista de Missões"));
		return view;
	}
	
	
	
	 	@InitBinder
	 	@Scope(value=WebApplicationContext.SCOPE_APPLICATION)
	 	protected void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(List.class, "payloads", new CustomCollectionEditor(List.class)
	          {
	            @Override
	            protected Object convertElement(Object element)
	            {
	                Long id = null;

	                if(element instanceof String && !((String)element).equals("")){
	                    //From the JSP 'element' will be a String
	                    try{
	                        id = Long.parseLong((String) element);
	                    }
	                    catch (NumberFormatException e) {
	                        System.out.println("Element was " + ((String) element));
	                        e.printStackTrace();
	                    }
	                }
	                else if(element instanceof Long) {
	                    //From the database 'element' will be a Long
	                    id = (Long) element;
	                }

	                return id != null ? utilFacade.getLazyId(Instrument.class, id) : null;
	            }
	          });
	        binder.registerCustomEditor(List.class, "quizz", new CustomCollectionEditor(List.class)
	          {
	            @Override
	            protected Object convertElement(Object element)
	            {
	                Long id = null;

	                if(element instanceof String && !((String)element).equals("")){
	                    //From the JSP 'element' will be a String
	                    try{
	                        id = Long.parseLong((String) element);
	                    }
	                    catch (NumberFormatException e) {
	                        System.out.println("Element was " + ((String) element));
	                        e.printStackTrace();
	                    }
	                }
	                else if(element instanceof Long) {
	                    //From the database 'element' will be a Long
	                    id = (Long) element;
	                }

	                return Progress.NONE;
	            }
	          });
	        binder.registerCustomEditor(List.class, "institutions", new CustomCollectionEditor(List.class)
	          {
	            @Override
	            protected Object convertElement(Object element)
	            {
	                Long id = null;

	                if(element instanceof String && !((String)element).equals("")){
	                    //From the JSP 'element' will be a String
	                    try{
	                        id = Long.parseLong((String) element);
	                    }
	                    catch (NumberFormatException e) {
	                        System.out.println("Element was " + ((String) element));
	                        e.printStackTrace();
	                    }
	                }
	                else if(element instanceof Long) {
	                    //From the database 'element' will be a Long
	                    id = (Long) element;
	                }

	                return id != null ? utilFacade.getLazyId(Institution.class, id) : null;
	            }
	          });
	 }
	

}
