package br.framework.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.framework.core.facade.AssessmentFacade;
import br.framework.core.facade.InstrumentFacade;
import br.framework.core.facade.SpaceMissionFacade;
import br.framework.dao.impl.DomainTableDAO;
import br.framework.dao.impl.InstitutionDAO;
import br.framework.dao.impl.SpaceMissionDAO;
import br.framework.dao.impl.TRLLevelDAO;
import br.framework.dao.impl.TRLQuestionDAO;
import br.framework.domain.Institution;
import br.framework.domain.Metadata;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.assessment.quiz.TRLQuestion;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.instrument.TechnologyType;
import br.framework.domain.mission.Dimension;
import br.framework.domain.mission.SpaceCraft;
import br.framework.domain.mission.SpaceCraftCategory;
import br.framework.domain.mission.SpaceMission;
import br.framework.helper.PropertieManager;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Camada de controle. Realiza a comunicação entre a camada lógica de visualização e de dominio. <br>
 * Invoca inicialização do banco de dados com dados de exemplo.
 */
@Transactional
@Controller
@Scope(value=WebApplicationContext.SCOPE_APPLICATION)
public class InitDataConfig {

	
	@Autowired
	private TRLLevelDAO trlDAO;
	@Autowired
	private InstrumentFacade instrumentFacade;
	@Autowired
	private InstitutionDAO institutionDAO;
	@Autowired
	private SpaceMissionFacade missionFacade;
	@Autowired
	private TRLQuestionDAO trlQuestionDAO;
	
	@Autowired
	private DomainTableDAO dao;
	

	@RequestMapping(value="/initAllModel", method=RequestMethod.GET)
	public ModelAndView initAllModel(){
		//Tabelas de dominio
		loadTrlDefinitions();		
		loadDoDQuestions();
		loadSpaceCraftCategories();
		
		initInstitutions();
		initInstruments();
		initMissions();
		ModelAndView m = new ModelAndView("init-form");
		m.addObject("message","adicionado com sucesso");
		return m;
		
	}

	@RequestMapping(value="/initDataBaseModel", method=RequestMethod.GET)
	public ModelAndView form(){
		return new ModelAndView("init-form"); 
	}


	public void initInstitutions(){
			for (Institution i : institutions()) 
				institutionDAO.insert(i);
	}



	public void loadTrlDefinitions() {
		TRLLevel level;
			for (int i = 1; i < 10; i++) {
				String name = PropertieManager.getMessage("level.name."+i);
				String description = PropertieManager.getMessage("level.description."+i);
				level = new TRLLevel((long)i);
				level.setName(name);
				level.setDescription(description);
				trlDAO.insert(level);
			}
	}


	public void loadDoDQuestions() {
		TRLQuestion question;
		//TODO teste
		Random r = new Random();
		String type,line,description;
		Integer weight,artefact;
		String params[];
			for (int i = 1; i < 10; i++) {
				Scanner s = new Scanner(PropertieManager.getMessage("question.level."+i));
				while(s.hasNext()){
					s.useDelimiter("\\|");
					params = s.next().split("-");					
					type = params[0];
					weight = Integer.parseInt(params[1]);
					artefact = Integer.parseInt( params[2] );
					description = params[3];
					question = new TRLQuestion().withLevel(i).applicableTo(TechnologyType.getType(type))
							.weight(weight)
							.setArtefactRequired(artefact==2?true:false)
							.withDescription(description);
					
				trlQuestionDAO.merge(question);
				}
			}

	}

	public void initInstruments() {
			for (Instrument instrument : createInstruments()) 
				instrumentFacade.insert(instrument);
	}

	
	
	
	public void loadSpaceCraftCategories(){
		SpaceCraftCategory s = new SpaceCraftCategory();
		s.setName("Picossatélite");
		s.setDescription("10g - 1kg");
		dao.insert(s);
		s = new SpaceCraftCategory();
		s.setName("Nanossatelite");
		s.setDescription("1kg - 10kg");		
		dao.insert(s);
		s = new SpaceCraftCategory();
		s.setName("Balão estratosférico");
		s.setDescription("Até 60 km de altitude");
		dao.insert(s);
	}

	
	public SpaceCraft loadSpaceCraft(){
		SpaceCraft s = new SpaceCraft();
		s.setName("NANOSATC-BR1");
		Dimension d = new Dimension();
		d.setHeight(1.33);
		d.setWidth(1D);
		d.setDepth(1D);		
		s.setDimension(d);
		s.setWeight(3D);
		s.setDescription("1kg - 10kg");
		SpaceCraftCategory category = new SpaceCraftCategory();
		category.setId(2l);
		s.setCategory(category);		
		return s;
	}
	
	public void initMissions(){
			SpaceMission mission = new SpaceMission();
			mission.setName("NANOSATC-BR1");
			mission.setCode("NANOSATC-BR1");
			mission.setObjective("O NANOSATC-BR1 tem a missão científica de coletar dados do Campo Magnético Terrestre principalmente na região da Anomalia Magnética da América do Sul – AMAS e do setor Brasileiro do Eletrojato Equatorial Ionosférico. Para isso utilizará um magnetômetro de três eixos da empresa holandesa XI - Xensor Integration (www.xensor.nl), modelo XEN-1210 com resolução de 15nT.");
			mission.setInstitutions(institutionDAO.listAll());
			mission.setPayloads((List<Instrument>)instrumentFacade.listAll());
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 19);
			c.set(Calendar.MONTH, 05);
			c.set(Calendar.YEAR, 2014);
			mission.setLaunchDate(c);
			mission.setSpaceCraft(loadSpaceCraft());
			missionFacade.insert(mission);
	}


	private List<Instrument> createInstruments(){
		List<Instrument> instruments = new ArrayList<Instrument>();
		Instrument sensor = new Instrument();
		sensor.setName("Sensor de Estrelas");		
		sensor.setType(TechnologyType.HARDWARE);
		sensor.setDescription("O Sensor de Estrelas é um equipamento eletro-óptico usado na determinação de atitude de plataformas espaciais, cujos dados são utilizados essencialmente na recalibração de giroscópios através da correção dos erros causados pela deriva nas medidas desses equipamentos inerciais.");
		sensor.setCode("SENSOR-I500");
		sensor.setObjective("Dentro do Programa de Capacitação em Novos Satélites da Coordenadoria Geral de Engenharia e Tecnologia Espacial (ETE), o projeto de Desenvolvimento de Sensores Eletro-Ópticos (SELOP) objetiva aumentar a capacitação da divisão em sensores para aplicação em futuros satélites. Dentre estes projetos o Sensor de Estrelas foi um subprojeto do SELOP. Foi desenvolvido um protótipo de aboratório, que voou a bordo de um balão estratosférico dentro da missão do telescópio MASCO.");
		List<Metadata> metadata = new ArrayList<Metadata>();
		Metadata m = new Metadata();
		m.setKey("Campo de visada");
		m.setValue("5graus x 6,7graus");
		metadata.add(m);
		m = new Metadata();
		m.setKey("Detector");
		m.setValue("CCD (288 x 384)");
		sensor.setMetadata(metadata);
		instruments.add(sensor);
		return instruments;
	}
	public List<Institution> institutions(){
		List<Institution> institutions = new ArrayList<Institution>();
		Institution i = new Institution();
		i.setDescription("Instituto Nacional de Pesquisas Espaciais");
		i.setName("INPE");
		i.setWebsite("http://www.inpe.br");
		institutions.add(i);
		i = new Institution();
		i.setDescription("Universidade Federam de Santa Maria");
		i.setName("UFSM");
		i.setWebsite("http://site.ufsm.br");		
		institutions.add(i);
		return institutions;
	}


}
