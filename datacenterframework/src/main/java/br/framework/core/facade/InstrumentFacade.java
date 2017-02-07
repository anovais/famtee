package br.framework.core.facade;

import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.framework.core.context.SaveInstrumentContext;
import br.framework.dao.impl.InstrumentDAO;
import br.framework.dao.impl.TRLAnswerDAO;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.instrument.Instrument;
import br.framework.helper.FilePathBuilder;
import br.framework.helper.FileSaver;


/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description:  Classe de fachada para operações com objeto {@linkplain Instrument instrumento}  <br> 
 *
 */
@Component
public class InstrumentFacade implements IFacade<Instrument>  {

	@Autowired
	private InstrumentDAO dao;
	
	@Autowired
	private TRLAnswerDAO answerDAO;

	@Autowired
	private FileSaver fileSaver;

	@Autowired
	private TRLFacade trlFacade;

	@Autowired
	private FilePathBuilder pathBuilder;
	
	@Autowired
	private SaveInstrumentContext context;

	/**
	 * Roda um contexto de inserção de novo instrumento.
	 * Cria a estrutura interna de pastas para armazenamento de artefatos.
	 * @param instrument	novo instrumento a ser inserido na base de dados
	 */
	public void insert(Instrument instrument) {
		insert(instrument, null);
	}

	/**
	 * Roda um contexto de inserção de novo instrumento.
	 * Cria a estrutura interna de pastas para armazenamento de artefatos. 
	 * @param instrument	novo instrumento a ser inserido na base de dados
	 * @param photo			uma photo para identificação do instrumento
	 */
	public void insert(Instrument instrument, MultipartFile photo) {
		context.addParameter("photo", photo);
		context.save(instrument);
		dao.insert(instrument);
	}

	public void delete(Instrument object) {
		dao.remove(object);		
	}

	public void update(Instrument object) {
		dao.update(object);
	}
	
	public void merge(Instrument object) {
		dao.merge(object);
	}

	public Collection<Instrument> listAll() {
		return dao.listAll();
	}

	public Instrument findById(Long id) {
		Instrument instrument = dao.findById(id);
		return initialize(instrument);
	}

	private Instrument initialize(Instrument instrument){
		Hibernate.initialize(instrument.getTRAs());
		Hibernate.initialize(instrument.getMissions());
		dao.getManager().detach(instrument);
		return instrument;
	}

	public Instrument findByCode(String code) {
		Instrument instrument = dao.findByCode(code);
		return initialize(instrument);
	}


}



