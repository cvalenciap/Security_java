package pe.com.sedapal.seguridad.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.service.SeguridadService;

@Component
public class ModuloValidator implements Validator {
	
	@Autowired
	private SeguridadService seguridadService;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return SistemaModuloBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		SistemaModuloBean sistemaModuloBean = (SistemaModuloBean) arg0;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "descripcion", "NotEmpty.inputForm.field");
		SistemaModuloBean sistemaModuloBean1 = null;
		try {
						
			if (sistemaModuloBean.getCodSistema() != null) {
				if (sistemaModuloBean.getCodSistema() == -1) {
					arg1.rejectValue("codSistema", "NotEmpty.inputForm.list");
				}
			}
			
			if (sistemaModuloBean.getEstado() != null) {
				if (sistemaModuloBean.getEstado() == -1) {
					arg1.rejectValue("estado", "NotEmpty.inputForm.list");
				}
			}
			
			if (sistemaModuloBean.getDescripcion().length() > 40) {
				arg1.rejectValue("descripcion", "NotEmpty.inputForm.textmax");
			}
			
			sistemaModuloBean1 = seguridadService.obtenerSistemaModuloPorNombre(sistemaModuloBean.getCodSistema(), sistemaModuloBean.getDescripcion());

			if(sistemaModuloBean.getCodModulo() == null) {
				if(sistemaModuloBean1 != null){
					arg1.rejectValue("descripcion", "NotEmpty.inputForm.modulodupli");
				}
			} 
			
			
			
			/*if (usuarioBean.getDescripcion().length() == 0 ) {
				arg1.rejectValue("descripcion", "NotEmpty.inputForm.field");
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
