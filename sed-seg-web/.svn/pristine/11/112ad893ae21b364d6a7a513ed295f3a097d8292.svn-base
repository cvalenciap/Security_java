package pe.com.sedapal.seguridad.web.validator;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.google.common.reflect.TypeToken;

import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.UsuarioService;
import pe.com.sedapal.seguridad.web.util.JsonUtil;
import pe.com.sedapal.seguridad.web.util.ValidatorEmailUtil;

@Component
public class UsuarioValidator implements Validator {

	@Autowired
	private SeguridadService seguridadService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public boolean supports(Class<?> arg0) {
		return UsuarioBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		UsuarioBean usuarioBean = (UsuarioBean) arg0;
		
		try {
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "sustentacion", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "doc", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idUsuario", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "codFicha", "NotEmpty.inputForm.field");
			ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "flagBloqueo", "NotEmpty.inputForm.field");
			
//			inicio adecuacion seguridad2
//			if (usuarioBean.getCodSistema() != null) {
//				if (usuarioBean.getCodSistema() == -1) {
//					arg1.rejectValue("codSistema", "NotEmpty.inputForm.list");
//				}
//			}
//
//			if (usuarioBean.getCodPerfil() == null) {
//				arg1.rejectValue("codPerfil", "NotEmpty.inputForm.list");
//			}	
//			//JJ: se agrega validacion cuando se ha seleccionado del combo el campo por defecto --Select
//			if (usuarioBean.getCodPerfil() != null && usuarioBean.getCodPerfil() == -1 ) {
//				arg1.rejectValue("codPerfil", "NotEmpty.inputForm.list");
//			}
//			
//			//sólo se valida el trabajador en el crear, en el update no se valida si tiene email
//			if (usuarioBean.getCodUsuario() == null){
//				if(usuarioBean.getEmailTrabajador()==null || usuarioBean.getEmailTrabajador().trim().equals("")){
//					arg1.rejectValue("codFicha", "NotEmpty.inputForm.trabajador.email");
//				}
//			}
			
			/*VALIDACION DE CAMPO JSON*/
			if(usuarioBean.getSistemasPerfilesAsociadosJSON() == null || usuarioBean.getSistemasPerfilesAsociadosJSON() == Constants.EMPTY_VALUE_ARR_JSON) {
				arg1.rejectValue("sistemasAsociados", "NotEmpty.inputForm.list");
			}
			Type listType = new TypeToken<List<UsuarioPerfilBean>>() {}.getType();
			List<UsuarioPerfilBean> asociaciones = JsonUtil.convertirCadenaJsonALista(usuarioBean.getSistemasPerfilesAsociadosJSON(), listType);
			if(asociaciones.size() == Constants.EMPTY_VALUE) {
				arg1.rejectValue("sistemasAsociados", "NotEmpty.inputForm.list");
			}
//			fin adecuacion seguridad2
			
			if (usuarioBean.getEstado() != null) {
				if (usuarioBean.getEstado() == -1) {
					arg1.rejectValue("estado", "NotEmpty.inputForm.list");
				}
			}

			if (usuarioBean.getCodUsuario() == null
					&& this.seguridadService.obtenerUsuarioPorCodUsuario(usuarioBean.getIdUsuario().toUpperCase()) != null) {
				arg1.rejectValue("idUsuario", "NotEmpty.usuario.existe");
			}
			
			if (usuarioBean.getDoc()!= null && usuarioBean.getDoc().length() > 50 ) {
				arg1.rejectValue("doc", "NotEmpty.inputForm.vdoc");
			}
			
			if (usuarioBean.getIdUsuario() != null && usuarioBean.getIdUsuario().length() > 15 ) {
				arg1.rejectValue("idUsuario", "NotEmpty.inputForm.idUsuario");
			}
			
			/*validaciones tipo externo*/
			if (usuarioBean.getCodTipo() == null || usuarioBean.getCodTipo() == -1) {
				arg1.rejectValue("codTipo", "NotEmpty.inputForm.list");
			}
			
			if(usuarioBean.getCodTipo() == Constants.TIPO_USUARIO_EXTERNO) {
				ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "nombreExterno", "NoEmpty.inputForm.nombreExterno");
				ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "dniExterno", "NoEmpty.inputForm.dniExterno");
				ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "emailExterno", "NoEmpty.inputForm.emailExterno");
				
				if (usuarioBean.getCodEmpresa() == null || usuarioBean.getCodEmpresa() == -1) {
					arg1.rejectValue("codEmpresa", "NotEmpty.inputForm.list");
				}
				
				if(usuarioBean.getEmailExterno() != null) {
					if(!ValidatorEmailUtil.emailValidator(usuarioBean.getEmailExterno()) || !ValidatorEmailUtil.validateEmail(usuarioBean.getEmailExterno())) {
						arg1.rejectValue("emailExterno", "Invalid.inputForm.email");
					}
				}
				
				if(!usuarioService.validacionUsuarioExterno(usuarioBean.getIdUsuario().toUpperCase(), usuarioBean.getCodEmpresa(), usuarioBean.getDniExterno())) {
					arg1.rejectValue("dniExterno", "NotEmpty.dniExterno.existe");
				}
			}
			
			/**/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
