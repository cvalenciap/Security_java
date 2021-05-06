package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;

@Component
public class ParametroValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ParametroBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		ParametroBean parametroBean = (ParametroBean) arg0;
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "descripcion", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "valor", "NotEmpty.inputForm.field");
		/*
		if (parametroBean.getEstado() != null) {
			if (parametroBean.getEstado() == -1) {
				arg1.rejectValue("estado", "NotEmpty.inputForm.list");
			}
		}*/
	}

}
