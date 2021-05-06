package pe.com.sedapal.seguridad.web.filter;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.SQLNestedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;

public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private SeguridadService seguridadService;

	private final Logger logger = LoggerFactory.getLogger(ExUsernamePasswordAuthenticationFilter.class);

	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationSuccessHandler(successHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Retorno retorno = null;
		final String usuario = request.getParameter("username");
		final String password = request.getParameter("password");
		String ip = request.getRemoteAddr();
		int codSistema = 0;
		logger.info("usuario = " + usuario);
		logger.info("ip = " + ip);
		try {

			if (usuario == null || usuario.isEmpty()) {
				throw new UsernameNotFoundException("Campo usuario vacio");
			}
			if (password == null || password.isEmpty()) {
				throw new UsernameNotFoundException("Campo clave vacio");
			}

			codSistema = seguridadService
					.obtenerListadoSistemasPorAbreviatura(
							seguridadService.obtenerParametroPorCodigoParametro(Constants.PREFIJO).getValor())
					.get(0).getCodSistema();

//			inicio adecuacion seguridad2
			request.getSession().setAttribute("codSistema", codSistema);
//			retorno = seguridadService.attemptAuthentication(usuario.toUpperCase(), ip, codSistema);
			retorno = seguridadService.attemptAuthenticationAct(usuario.toUpperCase(), ip, codSistema);
//			fin adecuacion seguridad2
			if (Constants.FAILURE.equals(retorno.getCodigo())) {
				if (Constants.FAILURE_USUARIO_NO_EXISTE.equals(retorno.getTipo())
						|| Constants.FAILURE_USUARIO_NOACTIVO.equals(retorno.getTipo())
						|| Constants.FAILURE_USUARIO_BLOQUEADO.equals(retorno.getTipo())) {
					throw new UsernameNotFoundException(retorno.getMensaje());
				} else if (Constants.FAILURE_MAXIMO_SESSIONES.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				} else if (Constants.FAILURE_CLAVE_TEMPORAL_INVALIDA.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				} else if (Constants.FAILURE_AREAS_DIFERENTES.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				} else if (Constants.FAILURE_ERROR_SISTEMA_NO_ACTIVO.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				}else if (Constants.FAILURE_PERFIL_NO_ACTIVO.equals(retorno.getTipo())){
					throw new BadCredentialsException(retorno.getMensaje());
				}
//				inicio adecuacion seguridad2
				else if (Constants.FAILURE_SIN_PERFILES.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				}
//				fin adecuacion seguridad2
			}	
			


		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof UsernameNotFoundException)
				throw new UsernameNotFoundException(e.getMessage());
			else if (e instanceof BadCredentialsException)
				throw new BadCredentialsException(e.getMessage());
			else if (e instanceof SQLException) {
				throw new AccessDeniedException(e.getMessage());
			} else {
				throw new AccessDeniedException(e.getMessage());
			}

		}
		return super.attemptAuthentication(request, response);
	}

}