package br.framework.core.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.framework.core.context.IActivity;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.Column;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Validador de campos de {@linkplain Instrument instrumento}
 */
public class InstrumentValidator implements Validator{

	@Override
	public boolean supports(Class<?> type) {
		return Instrument.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "objective", "error.field.required");
		
		
	}
	
	

}
