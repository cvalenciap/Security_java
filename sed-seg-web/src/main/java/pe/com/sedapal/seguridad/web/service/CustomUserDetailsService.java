package pe.com.sedapal.seguridad.web.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SeguridadService seguridadService;
	
//	inicio adecuacion seguridad2
	@Autowired
	private PerfilSistemaService perfilSistemaService;
	
	@Autowired
	private HttpServletRequest request;
//	fin adecuacion seguridad2

	public CustomUserDetailsService() {

	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		// TODO Auto-generated method stub
		Retorno retorno = null;
		UsuarioBean usuarioBean = null;
		List<String> permisos = null;
		List<GrantedAuthority> authorities = null;
//		inicio adecuacion seguridad2
		final Integer codSistema = (Integer) request.getSession().getAttribute("codSistema");
//		fin adecuacion seguridad2
		try {			
//			inicio adecuacion seguridad2
//			retorno = seguridadService.loadUserByUsername(username.toUpperCase());
			retorno = seguridadService.loadUserByUsernameAct(username.toUpperCase());
//			fin adecuacion seguridad2
			
			if (Constants.FAILURE.equals(retorno.getCodigo())) {
				if (Constants.ESTADO_INACTIVO_STR.equals(retorno.getTipo())) {
					throw new UsernameNotFoundException(retorno.getMensaje());
				} else if (Constants.ESTADO_BLOQUEADO_STR.equals(retorno.getTipo())) {
					throw new BadCredentialsException(retorno.getMensaje());
				}
			}	
			
//			inicio adecuacion seguridad2
			List<PerfilSistemaBean> listaPerfilesActivos = perfilSistemaService.obtenerPerfilesSistemaActivos(username.toUpperCase(), codSistema);
			if(listaPerfilesActivos == null || listaPerfilesActivos.size() == Constants.EMPTY_VALUE) {
				throw new BadCredentialsException("Usuario sin perfiles activos para el sistema.");
			}else {
				if (retorno.getFlagCambiarClave() != null
						&& new Integer(retorno.getFlagCambiarClave()).intValue() == Constants.ESTADO_ACTIVO) {
					permisos = new ArrayList<String>();
					permisos.add("/clave");
				}
				
				if(listaPerfilesActivos.size() == Constants.UNIQUE_VALUE){
					Integer perfilUnico = listaPerfilesActivos.get(0).getCodPerfil();
					permisos = seguridadService.obtenerListadoUsuarioAccionPorCodigoAct(username.toUpperCase(), codSistema, perfilUnico);
					request.getSession().setAttribute("codPerfil", perfilUnico);
					request.getSession().setAttribute("descripcionPerfil", listaPerfilesActivos.get(0).getDescripcion());
					request.getSession().setAttribute("flagNumeroPerfiles", Constants.SINGLE);
				}else {
					permisos = new ArrayList<String>();
					permisos.add("/formPerfilLogin");
					request.getSession().setAttribute("flagNumeroPerfiles", Constants.MULTIPLE);
				}
//				fin adecuacion seguridad2
			}
			
			authorities = buildUserAuthority(permisos);

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

		return buildUserForAuthentication(username.toUpperCase(),retorno.getClave(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<String> permisos) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		for (String permiso : permisos) {
			setAuths.add(new SimpleGrantedAuthority(permiso));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public User buildUserForAuthentication(String usuario, String clave , List<GrantedAuthority> authorities) {
		return new User(usuario, clave, authorities);
	}
	
	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}

}
