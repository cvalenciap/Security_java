package pe.com.sedapal.seguridad.web.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.service.SeguridadService;

@Component
public class AplicacionValidator implements Validator {

	@Autowired
	private SeguridadService seguridadService;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return SistemaBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		SistemaBean aplicacionBean = (SistemaBean) arg0;
		List<SistemaBean> sistemas = null;
		try {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "descripcion", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "abreviatura", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "programa", "NotEmpty.inputForm.field");
//			inicio adecuacion seguridad2
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "autenticacion", "NotEmpty.inputForm.field");
//			fin adecuacion seguridad2

			if (aplicacionBean.getFlagNivel() != null) {
				if (aplicacionBean.getFlagNivel().equalsIgnoreCase("-1")) {
					arg1.rejectValue("flagNivel", "NotEmpty.inputForm.list");
				}
			}

			if (aplicacionBean.getEstado() != null) {
				if (aplicacionBean.getEstado() == -1) {
					arg1.rejectValue("estado", "NotEmpty.inputForm.list");
				}
			}

			if (aplicacionBean.getDescripcion().length() > 40) {
				arg1.rejectValue("descripcion", "NotEmpty.inputForm.textmax");
			}

			if (aplicacionBean.getAbreviatura().length() > 7) {
				arg1.rejectValue("abreviatura", "NotEmpty.inputForm.textmax");
			}
			if (aplicacionBean.getPrograma().length() > 50) {
				arg1.rejectValue("programa", "NotEmpty.inputForm.textmax");
			}

			sistemas = seguridadService.obtenerListadoSistemasPorAbreviatura(aplicacionBean.getAbreviatura());
			if (aplicacionBean.getCodSistema() == null && (sistemas!=null && !sistemas.isEmpty())) {
				arg1.rejectValue("abreviatura", "NotEmpty.abreviatura.sistema");
			} else {
				for (SistemaBean sistemaBean : sistemas) {
					if (sistemaBean.getCodSistema().intValue() != aplicacionBean.getCodSistema().intValue()) {
						arg1.rejectValue("abreviatura", "NotEmpty.abreviatura.sistema");
					}
				}
			}
			
//			inicio adecuacion seguridad2
			if (aplicacionBean.getAutenticacion() == null) {
				arg1.rejectValue("autenticacion", "NotEmpty.inputForm.list");
			}
//			fin adecuacion seguridad2
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
