package pe.com.sedapal.seguridad.web.controlador;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.sedapal.seguridad.core.service.AreaService;
import pe.com.sedapal.seguridad.core.service.CentroService;
import pe.com.sedapal.seguridad.core.service.ClaveService;
import pe.com.sedapal.seguridad.core.service.MenuService;
import pe.com.sedapal.seguridad.core.service.PerfilAccionService;
import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.service.AccesoService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 * 
 */

@Controller
public class IngresoController {

	private final Logger logger = LoggerFactory.getLogger(AplicacionController.class);

	@Autowired
	Environment env;

	@Autowired
	private SeguridadService seguridadService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CentroService centroService;
	
	@Autowired
	private AccesoService accesoService;
	
	@Autowired
	private ClaveService claveService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PerfilAccionService perfilAccionService;

	@Value("#{databaseobjects['ambiente']}")
	private String ambiente;

	@Value("#{databaseobjects['copy']}")
	private String copy;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("index()");

		model.addAttribute("ambiente", ambiente);
		model.addAttribute("copy", copy);

		if (SecurityUtil.isAuthenticated()) {

			return "forward:/menu";
		}

		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "msg", required = false) String msg) {
		
		logger.info("Metodo login, url /login");
		
		//cb
		try {
//			areaService.obtenerListadoArea();
//			areaService.obtenerAreaPorCodigo(41702);
//			List<AccesoBean> lista = accesoService.obtenerAccesoSistema("SEGADMIN", "0:0:0:0:0:0:0:1");
//			lista = accesoService.obtenerUltimoAccesoSistema("SEGADMIN");
//			centroService.obtenerListadoCentro();
//			centroService.obtenerCentroPorCodigo(12);
//			claveService.obtenerClaves("SEGADMIN");
//			menuService.obtenerListadoSistemaModuloFormularioMenu(53, 1);
//			menuService.obtenerSistemaModuloFormularioMenu(53, 4);
//			perfilAccionService.obtenerListadoPerfilAccionPorCodigo(3,53);
//			perfilAccionService.obtenerPerfilAccionPorCodigo(4699);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//fcb

		ModelAndView model = new ModelAndView();
		try {
			model.addObject("ambiente", ambiente);
			model.addObject("copy", copy);
			SecurityUtil.getSession().setAttribute("copy", copy);
			if (error != null) {
				switch (error) {
				case "1":
					model.addObject("error", msg);
					/* model.addObject("error", "Usuario y clave invalido!"); */
					break;

				default:
					model.addObject("error", "Ha ocurrido un error inesperado.");
					break;
				}

			}

			if (logout != null && !"".equals(logout)) {
				model.addObject("msg", "Usted ha salido del sistema satisfactoriamente.");
			}

			if (SecurityUtil.isAuthenticated()) {
				/* The user is logged in :) */
				model.setViewName("forward:/menu");
				// return "forward:/aplicaciones";
			} else {
				model.setViewName("login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return model;

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

		logger.info(request.getRequestedSessionId());
		logger.info(request.getSession().getId());

		if (SecurityUtil.getAuthentication() != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
			request.getSession().invalidate();
			try {
				seguridadService.actualizarAcceso(request.getRequestedSessionId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/login?logout";
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/cambio", method = RequestMethod.GET)
	public ModelAndView cambioClave() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("clave");
		return model;

	}

}
