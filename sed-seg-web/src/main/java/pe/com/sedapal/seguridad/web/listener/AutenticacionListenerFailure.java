package pe.com.sedapal.seguridad.web.listener;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.util.Util;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;

public class AutenticacionListenerFailure implements AuthenticationFailureHandler {

	@Autowired
	private SeguridadService seguridadService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse arg1, AuthenticationException arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String mensaje = arg2.getMessage();
		String clave = arg0.getParameter("password");
		String codUsuario = arg0.getParameter("username");
		String bloqueo = "";
		int cont = 1;
		String path = "";
		try {
			if (Constants.MSG_ERROR_CREDENCIALES.equals(mensaje)) {
				mensaje = "Credenciales erroneas";
			} else if (mensaje.contains("Conexión cerrada")) {
				mensaje = "Conexión a la base de datos no dispobible.";
			}/*if ("Credenciales erroneas".equals(mensaje)) {
				mensaje = "Usuario debe cambiar su clave.";
				path = "/";
			}*/
//			inicio adecuacion seguridad2
//			UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuario(codUsuario);
			UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
//			fin adecuacion seguridad2
			if(usuarioBean != null){
				if (!Util.compararClave(clave, usuarioBean.getPass())) {
					mensaje = "Error al ingresar su clave.";
					//
					try {
						bloqueo = seguridadService.obtenerParametroPorCodigoParametro(Constants.BLOQUEO).getValor();
						if (usuarioBean.getNroIntentos() != null) {
							cont = cont + usuarioBean.getNroIntentos();
						}

						if (cont >= new Integer(bloqueo).intValue()) {
//							inicio adecuacion seguridad2
//							seguridadService.actualizarUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(),
//									Constants.ESTADO_BLOQUEADO, usuarioBean.getCodUsuario(),usuarioBean.getCodSistema());
							seguridadService.actualizarUsuarioEstadoPorCodUsuarioAct(usuarioBean.getCodUsuario(),
									Constants.ESTADO_BLOQUEADO, usuarioBean.getCodUsuario());
							/*auditoria*/
							List<UsuarioPerfilBean> asociaciones = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(usuarioBean.getCodUsuario());
							for(UsuarioPerfilBean asociacion : asociaciones) {
								asociacion.setEstado(Constants.ESTADO_ACTIVO);
								asociacion.setUsuarioCreacion(codUsuario);
								asociacion.setCodUsuario(codUsuario);
								seguridadService.actualizarAuditoriaUsuario(asociacion, usuarioBean);								
							}
//							fin adecuacion seguridad2
							mensaje = "Error al ingresar su clave. Su usuario ha sido bloqueado por seguridad.";
						}
						seguridadService.actualizarUsuarioClavesErroneas(usuarioBean.getCodUsuario(), cont);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if ("".equals(path)) {
				path = arg0.getContextPath() + "/login?error=1&msg=" + mensaje;
			} /*else {
				path = arg0.getContextPath() + "/clave?error=1&msg=" + mensaje;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		arg1.sendRedirect(path);
	}

}
