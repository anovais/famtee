package br.framework.controller;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import br.framework.domain.assessment.quiz.TRLQuestion;
import br.framework.domain.instrument.TechnologyType;
import br.framework.helper.PropertieManager;

public class TesteAPAGAR {

	public static void main(String[] args) {
		TRLQuestion question;
		Stack<String> stack = new Stack<String>();
		//TODO teste
		Random r = new Random();
		String type,line,description;
		Integer weight,artefact;
		String params[];
			for (int i = 1; i < 10; i++) {
				Scanner s = new Scanner(PropertieManager.getMessage("question.level."+i));
				while(s.hasNext()){
					s.useDelimiter("\\|");
					line = s.next();
					stack.push(line);
					params = line.split("-");					
					type = params[0];
					weight = Integer.parseInt(params[1]);
					artefact = Integer.parseInt( params[2] );
					description = params[3];
					question = new TRLQuestion().withLevel(i).applicableTo(TechnologyType.getType(type))
							.weight(weight)
							.setArtefactRequired(artefact==2?true:false)
							.withDescription(description);
					
				}
				while(!stack.isEmpty())
					System.out.print(stack.pop()+"|");
				System.out.println();
			}
	}
}
