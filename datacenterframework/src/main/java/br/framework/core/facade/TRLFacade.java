package br.framework.core.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.dao.impl.TRLLevelDAO;
import br.framework.dao.impl.TRLQuestionDAO;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.TRLLevel;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.assessment.quiz.TRLQuestion;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.instrument.TechnologyType;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description:  Classe de fachada para operações com {linkplain TRLLevel nível TRL} <br> 
 *
 */
@Component
public class TRLFacade {

	
	@Autowired
	private TRLQuestionDAO questionDAO;
	
	@Autowired
	private TRLLevelDAO levelDAO;
	
	public void fillAnswersToInstrument(Instrument instrument){
		
		List<TRLLevel> levels =  levelDAO.listAll();
		List<LevelAssessment> assessments = new ArrayList<LevelAssessment>();
		for (TRLLevel level : levels) {
			LevelAssessment a = new LevelAssessment(level);			
			a.setQuizz( initializeAnswers(level, instrument.getType()) );
			assessments.add(a);
		}
		Assessment tra = new Assessment();
		tra.setAssessments(assessments);
		instrument.setTRAs(new ArrayList<Assessment>());
		instrument.addTRA(tra);
	}
	
	private List<TRLAnswer> initializeAnswers(TRLLevel level, TechnologyType type){
		List<TRLAnswer> answers = new ArrayList<TRLAnswer>();
		List<TRLQuestion> questions = questionDAO.loadQuestionByLevelAndType(level, type);		
		TRLAnswer answer;
		for (TRLQuestion trlQuestion : questions) {
			answer = new TRLAnswer();
			answer.setQuestion(trlQuestion);
			answers.add(answer);	
		}
		return answers;
	}
	
	
	
}
