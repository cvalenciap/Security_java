package pe.com.sedapal.seguridad.web.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.ModuloValidator;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 28/12/2016.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */
@Controller
public class ModuloController {

	private final Logger logger = LoggerFactory.getLogger(ModuloController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private ModuloValidator moduloValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(moduloValidator);
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/modulo", method = RequestMethod.GET)
	public String listarModulo(Model model, final RedirectAttributes redirectAttributes) throws Exception {
		try {
			logger.info("todo los modulos()");
			if (SecurityUtil.hasRole("/modulo")) {
				model.addAttribute("modulos", this.seguridadService.obtenerListadoSistemaModuloPorCodigoActivos(0));
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "modulo/lista";

	}

	// metodo que ejecuta acción de nuevo registro
	@RequestMapping(value = "/modulo/add", method = RequestMethod.GET)
	public String nuevoModuloForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) throws Exception {

		if (SecurityUtil.hasRole("/modulo")) {
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar formulario()");

			SistemaModuloBean sistemaModuloBean = new SistemaModuloBean();
			sistemaModuloBean.setEstado(Constants.ESTADO_ACTIVO);

			model.addAttribute("moduloForm", sistemaModuloBean);
			model.addAttribute("urlReturn", urlReturn);

			populateDefaultModel(model);
		} else {
			return "accesourl";
		}

		return "modulo/moduloForm";
	}

	

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/modulo", method = RequestMethod.POST)
	public String grabarOActualizarModulo(@ModelAttribute("moduloForm") @Validated SistemaModuloBean sistemaModuloBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws Exception {

		logger.info("grabar o actualizar modulo() : {}", sistemaModuloBean);

		try {

			if (result.hasErrors()) {
				sistemaModuloBean.setEstado(Constants.ESTADO_ACTIVO);
				populateDefaultModel(model);
				return "modulo/moduloForm";
			} else {

				if (SecurityUtil.hasRole("/modulo")) {
					redirectAttributes.addFlashAttribute("css", "success");

					if (sistemaModuloBean.isNew()) {
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");
						sistemaModuloBean.setEstado(Constants.ESTADO_ACTIVO);
						sistemaModuloBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						seguridadService.grabarSistemaModulo(sistemaModuloBean);
					} else {
						sistemaModuloBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						seguridadService.actualizarSistemaModulo(sistemaModuloBean);
						redirectAttributes.addFlashAttribute("msg", "Se Actualizó Satisfactoriamente!");
					}
				} else {
					return "accesourl";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			model.asMap().put("css", "danger");
			model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());
			model.addAttribute("moduloForm", sistemaModuloBean);
			populateDefaultModel(model);
			return "modulo/moduloForm";
		}
		return "redirect:/modulo";
	}

	// metodo que ejecuta acción de busqueda por Id o Código, para actualizar
	// registro
	@RequestMapping(value = "/modulo/update/{codigo},{id}", method = RequestMethod.GET)
	public String visualizarModuloForm(@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws Exception {

		logger.info("visualizarModuloForm() : {}", id);
		SistemaModuloBean sistemaModuloBean = null;
		try {

			if (SecurityUtil.hasRole("/modulo")) {
				redirectAttributes.addFlashAttribute("css", "success");
				sistemaModuloBean = seguridadService.obtenerSistemaModuloPorCodigo(codigo, id);
				model.addAttribute("moduloForm", sistemaModuloBean);
				populateDefaultModel(model);

			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/modulo";
		}

		return "modulo/moduloForm";
	}

	// elimina registro por Id o Código
	@RequestMapping(value = "/modulo/delete/{codigo},{id}", method = RequestMethod.GET)
	public String eliminaModulo(@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws Exception {

		logger.info("eliminaModulo() : {}", id);

		try {
			if (SecurityUtil.hasRole("/modulo")) {
				seguridadService.actualizarEstadoSistemaModulo(codigo, id, Constants.ESTADO_INACTIVO,
						SecurityUtil.getAuthenticationName());
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/modulo";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Módulo Deshabilitado!");

		return "redirect:/modulo";
	}

	// metodo que ejecuta acción de mostrar registro
	@RequestMapping(value = "/modulo/{codigo},{id}", method = RequestMethod.GET)
	public String mostrarModulo(@ModelAttribute("moduloForm") SistemaModuloBean sistemaModuloBean,
			@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws Exception {

		logger.info("mostrarModulo() id: {}", id);

		try {
			if (SecurityUtil.hasRole("/modulo")) {
				sistemaModuloBean = seguridadService.obtenerSistemaModuloPorCodigo(codigo, id);
				if (sistemaModuloBean == null) {
					model.addAttribute("css", "danger");
					model.addAttribute("msg", "Módulo no encontrado");
				}
				populateDefaultModel(model);
				model.addAttribute("moduloForm", sistemaModuloBean);
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/modulo";
		}

		return "modulo/mostrar";
	}

	private void populateDefaultModel(Model model) throws RuntimeException {
		List<SistemaBean> lstSistema;
		Map<Integer, String> sistemas = new HashMap<Integer, String>();
		Map<Integer, String> estados = new HashMap<Integer, String>();
		List<ParametroBean> parametros;
		try {

			lstSistema = seguridadService.obtenerListadoSistemasActivos();
			for (SistemaBean sistemaBean : lstSistema) {
				sistemas.put(sistemaBean.getCodSistema(),
						sistemaBean.getCodSistema() + " - " + sistemaBean.getDescripcion());
			}
			Map<Integer, String> treeMap = new TreeMap<Integer, String>(sistemas);
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO);
			for (ParametroBean parametroBean : parametros) {
				if (parametroBean.getId().intValue() != 2) {
					estados.put(parametroBean.getId(), parametroBean.getDescripcion());
				}
			}
			model.addAttribute("estados", estados);
			model.addAttribute("sistemas", treeMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
