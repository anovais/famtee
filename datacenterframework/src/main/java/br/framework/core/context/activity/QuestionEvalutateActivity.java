package br.framework.core.context.activity;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.core.context.TRLCalculatorContext;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.domain.assessment.Assessment;
import br.framework.domain.assessment.LevelAssessment;
import br.framework.domain.assessment.quiz.TRLAnswer;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.util.Progress;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Uma atividade do contexto {@link TRLCalculatorContext}<br>
 * Executa o algoritmo de classificação de nível TRL pelas respostas que o instrumento obteve no ultimo {@link Assessment}
 *
 */
@Component
public class QuestionEvalutateActivity implements IActivity<Instrument>{

	@Override
	public void execute(Instrument instrument, Context context) {
		int scoreLevel, totalScoreLevel;
		boolean pastLevel = true;
		for (LevelAssessment assessment : instrument.getAssessments()) {
			totalScoreLevel = scoreLevel = 0;
			for (TRLAnswer answer : assessment.getQuizz()) {
				double weight =  answer.getWeight() != null ?  answer.getWeight() : 1;
				totalScoreLevel += weight; 
				scoreLevel += answer.getProgress().equals(Progress.GREEN) ? weight : 0;
			}

			if(scoreLevel >= totalScoreLevel* (Progress.GREEN.getValue()/100d))
				assessment.setProgress(Progress.GREEN);				

			else if(scoreLevel >= totalScoreLevel*(Progress.YELLOW.getValue()/100d))
				assessment.setProgress(Progress.YELLOW);
			else if(scoreLevel > 0)
				assessment.setProgress(Progress.RED);
			else
				assessment.setProgress(Progress.NONE);

			if(assessment.getProgress() == Progress.GREEN && !pastLevel)
				assessment.setProgress(Progress.YELLOW);

			if(assessment.getProgress() !=  Progress.GREEN)
				pastLevel = false;
			else
				pastLevel = true;

		}
	}

}
