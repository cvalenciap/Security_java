package pe.com.sedapal.seguridad.web.controlador;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.reflect.TypeToken;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.commons.BRespuestaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.SistemaService;
import pe.com.sedapal.seguridad.core.service.UsuarioService;
import pe.com.sedapal.seguridad.core.util.LDAPAutentication;
import pe.com.sedapal.seguridad.web.bean.Perfiles;
import pe.com.sedapal.seguridad.web.util.JsonUtil;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.UsuarioValidator;

@Controller
public class AsociacionLDAPController {
	
	private final Logger logger = LoggerFactory.getLogger(AsociacionLDAPController.class);
	
	private BRespuestaBean respuestaBean = new BRespuestaBean();

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SistemaService sistemaService;
	
	@Autowired
	private PerfilSistemaService perfilSistemaService;
	
	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private UsuarioValidator usuarioValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(usuarioValidator);
	}

	// metodo de carga formulario asociacionLDAP
	@RequestMapping(value = "/asociacionLDAP", method = RequestMethod.GET)
	public String formularioAsociacion(Model model, final RedirectAttributes redirectAttributes) {

		try {
			if (SecurityUtil.hasRole("/asociacionLDAP")) {
				
			} else {
				return "accesourl";
			}
			model.addAttribute("sistemasTotal", JsonUtil.convertirObjetoACadenaJson(seguridadService.obtenerListadoSistemasActivos()));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "asociacionLDAP/asociacionLDAPForm";

	}
	
//	validacion y retorno de usuario LDAP
	@RequestMapping(value = "/asociacionLDAP/getUsuarioLDAP", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String getUsuarioLDAP(String idRutaProg, HttpServletRequest request, Model model) throws RuntimeException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		UsuarioBean usuarioLDAP = new UsuarioBean();
		String loginDN = null;
		List<SistemaBean> sistemasAsociados = new ArrayList<SistemaBean>();
		List<PerfilSistemaBean> perfilesAsociados = new ArrayList<PerfilSistemaBean>();
		try {
			String codUsuarioLDAP = request.getParameter("codUsuarioLDAP").toUpperCase();
			usuarioLDAP.setCodUsuarioLDAP(codUsuarioLDAP);
			loginDN = LDAPAutentication.busquedaUsuarioLDAP(usuarioLDAP);
			if(loginDN != null) {
				usuarioLDAP = usuarioService.obtenerUsuarioPorCodUsuarioLDAP(codUsuarioLDAP);
//				usuarioLDAP = usuarioService.obtenerUsuarioPorCodUsuarioAct(codUsuarioLDAP);
				if(usuarioLDAP != null) {
					List<UsuarioPerfilBean> listUsuarioPerfilLDAP = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(usuarioLDAP.getCodUsuarioLDAP());	
					for (UsuarioPerfilBean usuarioPerfilLDAP : listUsuarioPerfilLDAP) {
						SistemaBean sistema = new SistemaBean();
						PerfilSistemaBean perfil = new PerfilSistemaBean();
						sistema.setCodSistema(usuarioPerfilLDAP.getCodSistema());
						sistema.setDescripcion(usuarioPerfilLDAP.getSistemaNombre());
						sistema.setAbreviatura(usuarioPerfilLDAP.getAbreviaturaSistema());
						perfil.setCodPerfil(usuarioPerfilLDAP.getCodPerfil());
						perfil.setDescripcion(usuarioPerfilLDAP.getPerfilNombre());
						perfil.setCodSistema(usuarioPerfilLDAP.getCodSistema());
						if(verificarSistema(sistemasAsociados, sistema.getCodSistema())){
							sistemasAsociados.add(sistema);
						}
						perfilesAsociados.add(perfil);
					}
					if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
						usuarioLDAP.setSistemasAsociados(sistemasAsociados);
					}
					if(perfilesAsociados.size() > Constants.EMPTY_VALUE) {
						usuarioLDAP.setPerfilesAsociados(perfilesAsociados);
					}
					if(listUsuarioPerfilLDAP != null && listUsuarioPerfilLDAP.size() != Constants.EMPTY_VALUE) {
						respuestaBean.setMensajeRespuesta("Usuario encontrado, con asociaciones.");
					}else {
						respuestaBean.setMensajeRespuesta("Usuario encontrado, sin asociaciones.");
					}
					mapResultado.put("modo", Constants.EDITAR);
				}else {
					usuarioLDAP = new UsuarioBean();
					usuarioLDAP.setCodUsuarioLDAP(codUsuarioLDAP);
					usuarioService.grabarUsuarioLDAP(usuarioLDAP);
					respuestaBean.setMensajeRespuesta("Usuario registrado");
					mapResultado.put("modo", Constants.NUEVO);
				}
				
				mapResultado.put("usuarioLDAP", usuarioLDAP);
				mapResultado.put("perfilesAsociados", perfilesAsociados);
				mapResultado.put("sistemasAsociados", sistemasAsociados);
				respuestaBean.setEstadoRespuesta(Constants.OK);
				respuestaBean.setParametros(mapResultado);
			}else {
				respuestaBean.setEstadoRespuesta(Constants.ERROR);
				respuestaBean.setMensajeRespuesta("Usuario no existe en directorio LDAP");
				respuestaBean.setParametros(mapResultado);
			}
		}catch(Exception e) {
			e.printStackTrace();
			respuestaBean.setMensajeRespuesta("Error en ejecución de método");
			respuestaBean.setEstadoRespuesta(Constants.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			throw new RuntimeException(e);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
//	grabacion de asociaciones de usuario LDAP
	@RequestMapping(value = "/asociacionLDAP/grabarUsuarioLDAP", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody String grabarUsuarioLDAP(String idRutaProg, HttpServletRequest request, Model model) throws RuntimeException {
		Map<String, Object> mapResultado = new HashMap<String, Object>();
		try {
			String codUsuarioLDAP = request.getParameter("codUsuarioLDAP").toUpperCase();
			String ltaPerfilesAsociados = request.getParameter("ltaPerfilesAsociados");
			String usuario = SecurityUtil.getAuthenticationName();
			Type listType = new TypeToken<List<UsuarioPerfilBean>>() {}.getType();
			List<UsuarioPerfilBean> asociaciones = JsonUtil.convertirCadenaJsonALista(ltaPerfilesAsociados, listType);
			List<UsuarioPerfilBean> upb = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(codUsuarioLDAP);
			if (upb != null && upb.size() != Constants.EMPTY_VALUE) {
				seguridadService.eliminarAsociacionesActualesLDAP(codUsuarioLDAP);	
			}
			for(UsuarioPerfilBean asociacion : asociaciones) {
				asociacion.setEstado(Constants.ESTADO_ACTIVO);
				asociacion.setUsuarioCreacion(usuario);
				asociacion.setCodUsuario(codUsuarioLDAP);
				seguridadService.grabarUsuarioPerfilSistemaLDAP(asociacion);
//				seguridadService.actualizarAuditoriaUsuarioLDAP(asociacion, usuariobkp);
			}
			respuestaBean.setEstadoRespuesta(Constants.OK);
			respuestaBean.setMensajeRespuesta("Asociaciones de Usuario LDAP guardadas con éxito.");
		}catch(Exception e) {
			e.printStackTrace();
			respuestaBean.setMensajeRespuesta("Error en ejecución de método.");
			respuestaBean.setEstadoRespuesta(Constants.ERROR);
			model.addAttribute("respuesta", respuestaBean);
			throw new RuntimeException(e);
		}
		return JsonUtil.convertirObjetoACadenaJson(respuestaBean);
	}
	
	private boolean verificarSistema(List<SistemaBean> sistemasAsociados, Integer codSistema) {
		boolean flag = true;
		if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
			for(SistemaBean sistema : sistemasAsociados) {
				if(sistema.getCodSistema() == codSistema) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	
	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}
	
}
