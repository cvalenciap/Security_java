package pe.com.sedapal.seguridad.web.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.AplicacionValidator;

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
public class AplicacionController {

	private final Logger logger = LoggerFactory.getLogger(AplicacionController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private AplicacionValidator aplicacionValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(aplicacionValidator);
	}

	@RequestMapping(value = "/aplicaciones", method = RequestMethod.GET)
	public String listarAplicacion(Model model, final RedirectAttributes redirectAttributes) throws RuntimeException {

		logger.info("toda las aplicaciones()");
		try {
			if (SecurityUtil.hasRole("/aplicaciones")) {
				model.addAttribute("aplicaciones", this.seguridadService.obtenerListadoSistemas());
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
		}
		return "aplicaciones/lista";
	}

	@RequestMapping(value = "/aplicaciones/add", method = RequestMethod.GET)
	public String nuevoAplicacionForm(Model model, @RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) throws Exception {

		if (SecurityUtil.hasRole("/aplicaciones")) {
			SistemaBean aplicacionBean = new SistemaBean();
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar formulario()");
			aplicacionBean.setEstado(Constants.ESTADO_ACTIVO_SISTEMAS);
			populateDefaultModel(model);
			model.addAttribute("aplicacionForm", aplicacionBean);
			model.addAttribute("urlReturn", urlReturn);
			
		} else {
			return "accesourl";
		}

		return "aplicaciones/aplicacionForm";
	}

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/aplicaciones", method = RequestMethod.POST)
	public String grabarOActualizarAplicacion(@ModelAttribute("aplicacionForm") @Validated SistemaBean aplicacionBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws RuntimeException {

		logger.info("grabar o actualizar aplicacion() : {}", aplicacionBean);
		try {

			if (SecurityUtil.hasRole("/aplicaciones")) {
				if (result.hasErrors()) {
					aplicacionBean.setEstado(Constants.ESTADO_ACTIVO_SISTEMAS);
					populateDefaultModel(model);
					return "aplicaciones/aplicacionForm";
				} else {
					redirectAttributes.addFlashAttribute("css", "success");
					if (aplicacionBean.isNew()) {
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");
						aplicacionBean.setEstado(Constants.ESTADO_ACTIVO_SISTEMAS);
						aplicacionBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
//						inicio adecuacion seguridad2
//						this.seguridadService.grabarSistema(aplicacionBean);
						this.seguridadService.grabarSistemaAct(aplicacionBean);
//						fin adecuacion seguridad2
					} else {
						if(aplicacionBean.getFlagNivel() == null){
							aplicacionBean.setFlagNivel(this.seguridadService.obtenerSistemaPorId(aplicacionBean.getCodSistema()).getFlagNivel());
						}
						aplicacionBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
//						inicio adecuacion seguridad2
//						this.seguridadService.actualizarSistema(aplicacionBean);
						this.seguridadService.actualizarSistemaAct(aplicacionBean);
//						fin adecuacion seguridad2
						redirectAttributes.addFlashAttribute("msg", "Se Actualizó Satisfactoriamente!");
					}
				}
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			model.asMap().put("css", "danger");
			model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());
			populateDefaultModel(model);
			model.addAttribute("aplicacionForm", aplicacionBean);
			return "aplicaciones/aplicacionForm";
		}
		return "redirect:/aplicaciones";

	}

	@RequestMapping(value = "/aplicaciones/update/{id}", method = RequestMethod.GET)
	public String visualizarAplicacionForm(@ModelAttribute("aplicacionForm") SistemaBean aplicacionBean,
			@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {

		logger.info("showUpdateaplicacionForm() : {}", id);

		try {
			if (SecurityUtil.hasRole("/aplicaciones")) {
//				inicio adecuacion seguridad2
//				aplicacionBean = this.seguridadService.obtenerSistemaPorId(id);
				aplicacionBean = this.seguridadService.obtenerSistemaPorIdAct(id);
//				fin adecuacion seguridad2
				populateDefaultModel(model);
				model.addAttribute("aplicacionForm", aplicacionBean);
			} else {
				return "accesourl";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/aplicaciones";
		}
		return "aplicaciones/aplicacionForm";

	}

	@RequestMapping(value = "/aplicaciones/delete/{id}", method = RequestMethod.GET)
	public String eliminaAplicacion(@PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws RuntimeException {

		logger.info("deleteUser() : {}", id);

		try {
			if (SecurityUtil.hasRole("/aplicaciones")) {
				this.seguridadService.actualizarEstadoSistema(id, Constants.ESTADO_INACTIVO_SISTEMAS,
						SecurityUtil.getAuthenticationName());
				redirectAttributes.addFlashAttribute("css", "success");
				redirectAttributes.addFlashAttribute("msg", "El sistema ha sido deshabilitado!");
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/aplicaciones";
		}
		return "redirect:/aplicaciones";

	}

	// metodo que ejecuta acción de mostrar registro
	@RequestMapping(value = "/aplicaciones/{id}", method = RequestMethod.GET)
	public String mostrarAplicacion(@ModelAttribute("aplicacionForm") SistemaBean aplicacionBean,
			@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {

		logger.info("showUser() id: {}", id);

		try {
			if (SecurityUtil.hasRole("/aplicaciones")) {
//				inicio adecuacion seguridad2
//				aplicacionBean = seguridadService.obtenerSistemaPorId(id);
				aplicacionBean = seguridadService.obtenerSistemaPorIdAct(id);
//				fin adecuacion seguridad2
				if (aplicacionBean == null) {
					model.addAttribute("css", "danger");
					model.addAttribute("msg", "Sistema no encontrado");
				}
				populateDefaultModel(model);
				model.addAttribute("aplicacionForm", aplicacionBean);
			} else {
				return "accesourl";
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/aplicaciones";
		}

		return "aplicaciones/mostrar";

	}

	private void populateDefaultModel(Model model) throws RuntimeException {

		List<ParametroBean> parametrosEstados;
		List<ParametroBean> parametrosNiveles;
		Map<Integer, String> estados = new HashMap<Integer, String>();
		Map<Integer, String> niveles = new HashMap<Integer, String>();

		try {
			parametrosEstados = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO_SISTEMA);
			for (ParametroBean parametroBean : parametrosEstados) {
				estados.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("estados", estados);

			parametrosNiveles = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.NIVEL);
			for (ParametroBean parametroBean : parametrosNiveles) {
				niveles.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("niveles", niveles);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public SeguridadService getSeguridadService() {
		return seguridadService;
	}

	public void setSeguridadService(SeguridadService seguridadService) {
		this.seguridadService = seguridadService;
	}

}