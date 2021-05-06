package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;

@Component
public class ClaveValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return ClaveBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ClaveBean claveBean = (ClaveBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "pass", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "passNuevo", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "passConfirmar", "NotEmpty.inputForm.field");
		
	}

}
