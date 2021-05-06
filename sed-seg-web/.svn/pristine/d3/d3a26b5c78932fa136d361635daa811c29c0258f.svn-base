package pe.com.sedapal.seguridad.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;

@Component
public class PerfilValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return PerfilSistemaBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		PerfilSistemaBean sistemaModuloBean = (PerfilSistemaBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "descripcion", "NotEmpty.inputForm.field");
		
		if (sistemaModuloBean.getCodSistema() != null) {
			if (sistemaModuloBean.getCodSistema() == -1) {
				arg1.rejectValue("codSistema", "NotEmpty.inputForm.list");
			}
		}
		
		if (sistemaModuloBean.getEstado() != null) {
			if (sistemaModuloBean.getEstado().equalsIgnoreCase("-1")) {
				arg1.rejectValue("estado", "NotEmpty.inputForm.list");
			}
		}
		
		if(!sistemaModuloBean.isUsuarioAccion()) {
			if (sistemaModuloBean.getDescripcion().length() > 30) {
				arg1.rejectValue("descripcion", "NotEmpty.inputForm.textmax");
			}	
		}
		
		
		
		/*if (usuarioBean.getDescripcion().length() == 0 ) {
			arg1.rejectValue("descripcion", "NotEmpty.inputForm.field");
		}*/
		
//		inicio adecuacion seguridad2
		if (sistemaModuloBean.getCodPerfil() != null) {
			if (sistemaModuloBean.getCodPerfil() == -1) {
				arg1.rejectValue("codPerfil", "NotEmpty.inputForm.list");
			}
		}
//		fin adecuacion seguridad2
		
	}

}
