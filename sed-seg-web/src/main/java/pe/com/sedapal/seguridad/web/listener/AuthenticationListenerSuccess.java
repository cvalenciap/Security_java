package pe.com.sedapal.seguridad.web.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;

public class AuthenticationListenerSuccess implements AuthenticationSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(AuthenticationListenerSuccess.class);

	@Autowired
	private SeguridadService seguridadService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// insertar un registro en la tabla accesos
		UsuarioBean usuarioBean = null;

		Retorno retorno = null;
		final String usuario = request.getParameter("username");
//		inicio adecuacion seguridad2
		final Integer codSistema = (Integer) request.getSession().getAttribute("codSistema");
		final String flagNumeroPerfiles = (String) request.getSession().getAttribute("flagNumeroPerfiles");
//		fin adecuacion seguridad2
		String ip = "";
		String token = "";
		String path = "";
		ParametroBean parametroBean;
		try {
			Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
			if (details instanceof WebAuthenticationDetails)
				ip = ((WebAuthenticationDetails) details).getRemoteAddress();

			token = request.getRequestedSessionId();
			logger.info(token);
			
//			inicio adecuacion seguridad2
			retorno = seguridadService.onAuthenticationSuccess(usuario.toUpperCase(), ip, token, codSistema);
			if(flagNumeroPerfiles.equals(Constants.SINGLE)) {
				Integer codPerfil = (Integer) request.getSession().getAttribute("codPerfil");
				
				if (Constants.SUCCESS.equals(retorno.getCodigo())) {				
					if (retorno.getFlagCambiarClave() != null
							&& new Integer(retorno.getFlagCambiarClave()).intValue() == Constants.ESTADO_ACTIVO) {
						path = request.getContextPath() + "/clave";
					} else {
						path = request.getContextPath() + "/menu";
					}
				} else {
					path = request.getContextPath() + "/error";
				}
				request.getSession().setAttribute("permisos", seguridadService.obtenerListadoUsuarioAccionPorCodigoAct(retorno.getUsuario(), codSistema, codPerfil));
				seguridadService.bloqueoUsuarioEstadoPorCodUsuarioAct(usuario.toUpperCase(), Constants.ESTADO_ACTIVO, 0,
						usuario.toUpperCase());
			}else if(flagNumeroPerfiles.equals(Constants.MULTIPLE)){
				path = request.getContextPath() + "/selectPerfil";
			}
			request.getSession().setAttribute("codUsuario", usuario.toUpperCase());
			parametroBean = seguridadService.obtenerParametroPorCodigoParametro(Constants.SESSION);			
			request.getSession().setAttribute("usuarioBean", usuarioBean);
			request.getSession().setMaxInactiveInterval(new Integer(parametroBean.getValor()) * 60);
			
//			fin adecuacion seguridad2
			response.sendRedirect(path);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
