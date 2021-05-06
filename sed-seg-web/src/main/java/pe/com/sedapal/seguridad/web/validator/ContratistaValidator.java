package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;

@Component
public class ContratistaValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return ContratistaBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		ContratistaBean contratistaBean = (ContratistaBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "desRazon", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "desRazonCorta", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "direccion", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nroDocRazon", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nroDocRepresentante", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "correo", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "telefono", "NotEmpty.inputForm.field");
		
		if (contratistaBean.getCodDocRazon() != null) {
			if (contratistaBean.getCodDocRazon() == -1) {
				arg1.rejectValue("codDocRazon", "NotEmpty.inputForm.list");
			}
		}
		
		if (contratistaBean.getCodDocRepresentante() != null) {
			if (contratistaBean.getCodDocRepresentante() == -1) {
				arg1.rejectValue("codDocRepresentante", "NotEmpty.inputForm.list");
			}
		}
		
	}
	
	

}
