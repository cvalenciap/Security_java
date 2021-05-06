package pe.com.sedapal.seguridad.web.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.util.SecurityUtils;
import pe.com.sedapal.seguridad.web.controlador.UsuarioController.CboSistemas;
import pe.com.sedapal.seguridad.web.service.CustomUserDetailsService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.ClaveValidator;
import pe.com.sedapal.seguridad.web.validator.PerfilValidator;
import pe.com.sedapal.seguridad.web.validator.SelectPerfilValidator;

@Controller
public class SelectPerfilController {
	private final Logger logger = LoggerFactory.getLogger(ClaveController.class);

	@Autowired
	private SeguridadService seguridadService;
	
	@Autowired
	private PerfilSistemaService perfilSistemaService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SelectPerfilValidator selectPerfilValidator;
	
	@Autowired
	private CustomUserDetailsService customUserDetailService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(selectPerfilValidator);
	}
	
	// metodo que ejecuta acción de nuevo registro
		@RequestMapping(value = "/selectPerfil", method = RequestMethod.GET)
		public String selectPerfil(Model model, final RedirectAttributes redirectAttributes) throws RuntimeException{
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar formulario()");

			PerfilSistemaBean perfilBean = new PerfilSistemaBean();
			
			populateDefaultModel(model);
			model.addAttribute("perfilForm", perfilBean);
			return "selectPerfil/formPerfilLogin";

		}

		// metodo que ejecuta acción de grabar o actualizar
		@RequestMapping(value = "/selectPerfil", method = RequestMethod.POST)
		public String grabarOActualizarPerfil(@ModelAttribute("perfilForm") @Validated PerfilSistemaBean perfilBean,
				BindingResult result, Model model, final RedirectAttributes redirectAttributes)throws RuntimeException {
			logger.info("seleccionar perfil() : {}", perfilBean);
			List<String> permisos = null;
			UsuarioBean  usuarioBean = null;
			if (result.hasErrors()) {
				populateDefaultModel(model);
				return "selectPerfil/formPerfilLogin";
			} else {
				try {				
					request.getSession().setAttribute("codPerfil", perfilBean.getCodPerfil());
					final Integer codSistema = (Integer) request.getSession().getAttribute("codSistema");
					final Integer codPerfil = (Integer) request.getSession().getAttribute("codPerfil");
					perfilBean = perfilSistemaService.obtenerPerfilSistemaPorCodigo(perfilBean.getCodPerfil(), codSistema);
					request.getSession().setAttribute("descripcionPerfil", perfilBean.getDescripcion());
					
					redirectAttributes.addFlashAttribute("css", "success");
					redirectAttributes.addFlashAttribute("msg", "Ingreso con perfil " + perfilBean.getDescripcion());
										
					permisos = seguridadService.obtenerListadoUsuarioAccionPorCodigoAct(SecurityUtil.getAuthenticationName(), codSistema, codPerfil);
					request.getSession().setAttribute("permisos", permisos);
					
					//adicionar authorites a autenticacion Auth
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
					SimpleGrantedAuthority authority = null;
					for (String permiso : permisos) {					
						authority = new SimpleGrantedAuthority(permiso);	
						updatedAuthorities.add(authority);
					}
					
					User principalUpdated = customUserDetailService.buildUserForAuthentication(SecurityUtil.getAuthenticationName(), "", updatedAuthorities);
					
					Authentication newAuth = new UsernamePasswordAuthenticationToken(principalUpdated, auth.getCredentials(), updatedAuthorities);
					SecurityContextHolder.getContext().setAuthentication(newAuth);
					
					//grabar acceso
					seguridadService.bloqueoUsuarioEstadoPorCodUsuarioAct(SecurityUtil.getAuthenticationName(), Constants.ESTADO_ACTIVO, 0,
							SecurityUtil.getAuthenticationName());				
					
					//seleccion de redireccionamiento
					usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioAct(SecurityUtil.getAuthenticationName());
					
					if (usuarioBean != null) {				
						if (usuarioBean.getFlagCambiarClave() != null
								&& new Integer(usuarioBean.getFlagCambiarClave()).intValue() == Constants.ESTADO_ACTIVO) {
							return "redirect:/clave";
						} else {
							return "redirect:/menu";
						}
					} else {
						return "redirect:/error";
					}
				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
					model.asMap().put("css", "danger");
					model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());
					populateDefaultModel(model);
					return "selectPerfil/formPerfilLogin";
				}
			}

		}
		
		public class CboPerfiles {
			private String id;
			private String value;
			private String label;

			public CboPerfiles() {
			}

			public CboPerfiles(String id, String value, String label) {
				this.id = id;
				this.value = value;
				this.label = label;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			public String getLabel() {
				return label;
			}

			public void setLabel(String label) {
				this.label = label;
			}

		}
		
		private void populateDefaultModel(Model model) throws RuntimeException {

			List<PerfilSistemaBean> lstPerfil;
			final String codUsuario = (String) request.getSession().getAttribute("codUsuario");
			final Integer codSistema = (Integer) request.getSession().getAttribute("codSistema");
			try {
				lstPerfil = perfilSistemaService.obtenerPerfilesSistemaActivos(codUsuario , codSistema);
				List<CboPerfiles> selectItems = new ArrayList<CboPerfiles>();

				for (PerfilSistemaBean perfilBean : lstPerfil) {
					selectItems.add(new CboPerfiles(perfilBean.getDescripcion(), perfilBean.getCodPerfil().toString(),
							perfilBean.getCodPerfil().toString() + " - " + perfilBean.getDescripcion()));
				}
				model.addAttribute("perfiles", selectItems);

			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		}
}
