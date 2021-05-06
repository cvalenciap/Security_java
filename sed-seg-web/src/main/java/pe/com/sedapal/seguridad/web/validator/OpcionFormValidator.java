package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;

@Component
public class OpcionFormValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return SistemaModuloFormBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SistemaModuloFormBean sistemaModuloFormBean = (SistemaModuloFormBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "descripcion", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "urlFormulario", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "codFormularioPadre", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nivelFormulario", "NotEmpty.inputForm.field");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "ordenFormulario", "NotEmpty.inputForm.field");
		
		if (sistemaModuloFormBean.getCodSistema() != null) {
			if (sistemaModuloFormBean.getCodSistema() == -1) {
				arg1.rejectValue("codSistema", "NotEmpty.inputForm.list");
			}
		}
		
		if (sistemaModuloFormBean.getCodModulo() != null) {
			if (sistemaModuloFormBean.getCodModulo() == -1) {
				arg1.rejectValue("codModulo", "NotEmpty.inputForm.list");
			}
		}
		
		if (sistemaModuloFormBean.getEstado() != null) {
			if (sistemaModuloFormBean.getEstado() == -1) {
				arg1.rejectValue("estado", "NotEmpty.inputForm.list");
			}
		}
		
		if (sistemaModuloFormBean.getDescripcion().length() > 40) {
			arg1.rejectValue("descripcion", "NotEmpty.inputForm.textmax");
		}
		
		//if (sistemaModuloFormBean.getEstado() == 0) {
			if (sistemaModuloFormBean.getAccion() == null) {
				arg1.rejectValue("accion", "NotEmpty.inputForm.acciones");
			}
		//}
		
		/*if (usuarioBean.getDescripcion().length() == 0 ) {
			arg1.rejectValue("descripcion", "NotEmpty.inputForm.field");
		}*/
		
	}

}
