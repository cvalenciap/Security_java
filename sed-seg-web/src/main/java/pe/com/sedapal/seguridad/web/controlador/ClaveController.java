package pe.com.sedapal.seguridad.web.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.ClaveValidator;

@Controller
public class ClaveController {

	private final Logger logger = LoggerFactory.getLogger(ClaveController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private ClaveValidator claveValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(claveValidator);
	}

	// metodo que ejecuta acción de nuevo registro
	@RequestMapping(value = "/clave", method = RequestMethod.GET)
	public String nuevoClaveForm(Model model, final RedirectAttributes redirectAttributes) throws RuntimeException{

		redirectAttributes.addFlashAttribute("css", "");
		redirectAttributes.addFlashAttribute("msg", "");
		logger.info("agregar formulario()");

		ClaveBean claveBean = new ClaveBean();

		model.addAttribute("claveForm", claveBean);
		// model.addAttribute("urlReturn", urlReturn);

		return "clave/cambiarClaveForm";

	}

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/clave", method = RequestMethod.POST)
	public String grabarOActualizarClave(@ModelAttribute("claveForm") @Validated ClaveBean claveBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes)throws RuntimeException {

		logger.info("grabar o actualizar clave() : {}", claveBean);
		List<String> permisos = null;
		Retorno retorno = null;
		if (result.hasErrors()) {
			return "clave/cambiarClaveForm";
		} else {
			try {
				retorno = seguridadService.actualizarClave(SecurityUtil.getAuthenticationName(), claveBean.getPass(),
						claveBean.getPassNuevo(), claveBean.getPassConfirmar());

				if (Constants.FAILURE.equals(retorno.getCodigo())) {									
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", retorno.getMensaje());
					return "redirect:/clave";
				}
				
				redirectAttributes.addFlashAttribute("css", "success");
				redirectAttributes.addFlashAttribute("msg", retorno.getMensaje());
				Authentication au = SecurityUtil.getAuthentication();
				
				Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//				inicio adecuacion seguridad2
				permisos = seguridadService.obtenerListadoUsuarioAccionPorCodigo(SecurityUtil.getAuthenticationName());
//				permisos = seguridadService.obtenerListadoUsuarioAccionPorCodigoAct(SecurityUtil.getAuthenticationName(), Constants.CODIGO_SISTEMA);
//				fin adecuacion seguridad2
				
				SimpleGrantedAuthority authority = null;
				List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)au.getAuthorities();
				for (String permiso : permisos) {					
					authority = new SimpleGrantedAuthority(permiso);	
					updatedAuthorities.add(authority);
				}
				
				updatedAuthorities.addAll(oldAuthorities);

				SecurityContextHolder.getContext().setAuthentication(
				        new UsernamePasswordAuthenticationToken(
				                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
				                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
				                updatedAuthorities)
				);																		

			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
				model.asMap().put("css", "danger");
				model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());
				return "clave/cambiarClaveForm";
			}

			return "redirect:/menu";
		}

	}
	
	
	
}
