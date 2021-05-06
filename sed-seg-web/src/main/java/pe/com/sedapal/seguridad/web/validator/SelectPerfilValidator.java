package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;

@Component
public class SelectPerfilValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return PerfilSistemaBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		PerfilSistemaBean selectPerfilBean = (PerfilSistemaBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "codPerfil", "NotEmpty.inputForm.field");

		
		if (selectPerfilBean.getCodPerfil() != null) {
			if (selectPerfilBean.getCodPerfil() == -1) {
				arg1.rejectValue("codPerfil", "NotEmpty.inputForm.list");
			}
		}		
	}
	
}
