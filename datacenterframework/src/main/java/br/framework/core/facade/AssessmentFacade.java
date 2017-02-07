package br.framework.core.facade;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.framework.core.context.TRLCalculatorContext;
import br.framework.dao.impl.TRLAnswerDAO;
import br.framework.dao.impl.TRLAssessmentDAO;
import br.framework.domain.assessment.Artefact;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.secutiry.User;
import br.framework.domain.util.Progress;
import br.framework.helper.FilePathBuilder;
import br.framework.helper.FileSaver;
/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Classe de fachada para operações com objeto {@link Assessment} (avaliação) <br> 
 * Ele simplifica as operações e fornece as interfaces de metódos para a parte de controle.
 */
@Component
public class AssessmentFacade implements IFacade<LevelAssessment>{
	
	private Instrument instrument;

	private List<LevelAssessment> assessments;

	@Autowired
	private InstrumentFacade instrumentFacade;

	/**
	 * Algoritmo usado durante a avaliação de maturidade <br>
	 * Algoritmo baseado na ferramenta TRL CALCULATOR da AFRL
	 */
	@Autowired
	private TRLCalculatorContext calculator;

	
	@Autowired
	private FileSaver fileSaver;

	@Autowired
	private TRLFacade trlFacade;

	@Autowired
	private FilePathBuilder pathBuilder;



	public void forInstrument(Instrument instrument){
		this.instrument = instrument;
		assessments = instrument.getAssessments();
	}

	@Autowired	
	TRLAssessmentDAO dao;

	
	@Autowired
	TRLAnswerDAO answerDAO;

	@Override
	public void insert(LevelAssessment target) {dao.insert(target);}

	@Override
	public void delete(LevelAssessment target) {dao.remove(target);}

	@Override
	public void update(LevelAssessment target) {dao.update(target);}

	@Override
	public Collection<LevelAssessment> listAll() {
		return dao.listAll();
	}

	@Override
	public LevelAssessment findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * Atualiza uma resposta de avalição
	 * @param answerId 		identifica qual a resposta
	 * @param value			identifica o novo valor daquela resposta
	 * @return A questão totalmente preenchida
	 */
	public TRLAnswer answer(Long answerId, Integer value){
		TRLAnswer answer = getAnswer(answerId);
		answer.setProgress(Progress.build(value));
		return answer;
	}
	
	/**
	 * Atualiza uma resposta de avalição
	 * @param answerId 		identifica qual a resposta
	 * @param value			novo valor daquela resposta
	 * @param description	descrição para a resposta
	 * @return A questão totalmente preenchida
	 */
	public TRLAnswer answerQuestion(Long answerId, Integer value, String description) {
		TRLAnswer answer = answer(answerId, value);
		answer.setDescription(description);
		return answer;
	}

	/**
	 * Upload de um artefato relacionado a resposta de uma questão da avaliação
	 * @param id	id da questão à qual o artefato está atrelado
	 * @param artefact	o artefato a ser carregado
	 */
	public void uploadArtefact4Answer(Long id, Artefact artefact) {
		uploadArtefact(getAnswer(id), artefact);
	}

	/**
	 * Upload de um artefato relacionado a um nível de TRL
	 * @param level	numero do level TRL
	 * @param artefact	o artefato a ser carregado
	 */
	public void uploadArtefact4Assessment(Long level, Artefact artefact) {
		uploadArtefact(assessments.get(level.intValue()-1), artefact);
	}

	


	private void uploadArtefact(LevelAssessment assessment, Artefact artefact){
		assessment.addArtefact(processArtefact(instrument,artefact) );
	}

	private void uploadArtefact(TRLAnswer answer, Artefact artefact){
		answer.addArtefact(processArtefact(instrument,artefact) );
	}


	// Realiza o pré processamento do artefato
	private Artefact processArtefact(Instrument instrument,Artefact artefact){		
		String path = pathBuilder.artefactsDirectory(instrument);
		String extension = "";
		if(	artefact.getFile().getOriginalFilename().contains(".") )			
			extension = artefact.getFile().getOriginalFilename().substring(artefact.getFile().getOriginalFilename().lastIndexOf(".")+1);
		if(StringUtils.isEmpty(artefact.getCode()))
			getCodeFor(artefact);
		artefact.setExtension(extension);
		artefact.setResourceURL( fileSaver.write(path, artefact.getFile()) );
		artefact.setUploadDate(GregorianCalendar.getInstance());
		return artefact;
	}


	private void getCodeFor(Artefact artefact) {
		String params[] = artefact.getName().split(" ");
		StringBuilder builder = new StringBuilder();
		for (String s: params)
			builder.append(s.substring(0,1).toUpperCase());
		builder.append("-").append(artefact.getFile().hashCode()+"");
		artefact.setCode(builder.toString());
	}

	private TRLAnswer getAnswer(Long id){
		for (LevelAssessment assessment: assessments) 
			for (TRLAnswer answer : assessment.getQuizz()) 
				if(answer.getId().equals(id))
					return answer;
		return null;
	}

	public void submitAssessmentProcess(UserDetails user){
		
		Assessment tra = Assessment.getInstance().withAssessments(assessments).evaluetedBy((User)user);
		instrument.addTRA(tra);
		assessment(instrument);
	}


	
	public void assessment(Instrument instrument) {
		calculator.assessment(instrument);
		if(calculator.isSucceed())
			instrumentFacade.update(instrument);
	}


	public void removeArtefact(String code){
		for (LevelAssessment assessment : assessments) {
			//procurando em artefatos do nível TRL
			Iterator<Artefact> iterator = assessment.getArtefacts().iterator();
			while(iterator.hasNext())
				if( iterator.next().getCode().equals(code)){
					iterator.remove();
					return;
				}
			//procurando em artefatos de cada questão
			
			for (TRLAnswer answer : assessment.getQuizz()) {
			iterator = answer.getArtefacts().iterator();
			while(iterator.hasNext())
				if( iterator.next().getCode().equals(code)){
					iterator.remove();
					return;
				}
			}

		}
	}

	public Instrument getInstrument() {
		return instrument;
	}

}

