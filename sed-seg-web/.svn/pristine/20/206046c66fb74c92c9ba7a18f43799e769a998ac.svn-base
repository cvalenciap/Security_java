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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.ParametroValidator;

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
public class ParametroController {

	private final Logger logger = LoggerFactory.getLogger(ModuloController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private ParametroValidator parametroValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(parametroValidator);
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/parametro", method = RequestMethod.GET)
	public String listarParametro(Model model, final RedirectAttributes redirectAttributes) {

		try {
			logger.info("todos los parametros()");
			if (SecurityUtil.hasRole("/parametro")) {
				model.addAttribute("parametros", seguridadService.obtenerListadoParametroUnicos());
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "parametro/lista";

	}

	// metodo que ejecuta acción de busqueda por Id o Código, para actualizar
	// registro
	@RequestMapping(value = "/parametro/update/{codigo},{id}", method = RequestMethod.GET)
	public String visualizarParametroForm(@PathVariable("codigo") String codigo, @PathVariable("id") int id,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.info("visualizarParametroForm() : {}", id);
		ParametroBean parametroBean = null;
		try {
			if (SecurityUtil.hasRole("/parametro")) {
				parametroBean = seguridadService.obtenerParametroPorCodigoParametroPorCodigo(codigo, id);
				model.addAttribute("parametroForm", parametroBean);
				populateDefaultModel(model);
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "parametro/parametroForm";
	}

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/parametro", method = RequestMethod.POST)
	public String grabarOActualizarModulo(@ModelAttribute("parametroForm") @Validated ParametroBean parametroBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.info("grabar o actualizar parametro() : {}", parametroBean);

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "parametro/parametroForm";
		} else {

			try {

				if (SecurityUtil.hasRole("/parametro")) {
					redirectAttributes.addFlashAttribute("css", "success");
					if (parametroBean.isNew()) {
						// redirectAttributes.addFlashAttribute("msg", "Se
						// Registró
						// Satisfactoriamente!");
						redirectAttributes.addFlashAttribute("css", "danger");
						redirectAttributes.addFlashAttribute("msg", "Operación no permitida");
						parametroBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						// sistemaModuloService.grabarModulo(parametroBean);
					} else {
						parametroBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						seguridadService.actualizarParametro(parametroBean);
						redirectAttributes.addFlashAttribute("msg", "Se Actualizó Satisfactoriamente!");
					}
				} else {
					return "accesourl";
				}

			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
			}

			return "redirect:/parametro";
		}

	}

	// metodo que ejecuta acción de mostrar registro
	@RequestMapping(value = "/parametro/{codigo},{id}", method = RequestMethod.GET)
	public String mostrarParametro(@PathVariable("codigo") String codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.info("mostrarParametro() id: {}", id);
		ParametroBean parametroBean = null;
		try {
			
			if (SecurityUtil.hasRole("/parametro")) {
				parametroBean = seguridadService.obtenerParametroPorCodigoParametroPorCodigo(codigo, id);
				if (parametroBean == null) {
					model.addAttribute("css", "danger");
					model.addAttribute("msg", "Parametro no encontrado");
				}
				populateDefaultModel(model);
				model.addAttribute("parametroForm", parametroBean);

			} else {
				return "accesourl";
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "parametro/mostrar";
	}

	private void populateDefaultModel(Model model) throws RuntimeException {
		List<ParametroBean> parametros;
		Map<Integer, String> estados = new HashMap<Integer, String>();
		try {
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO);
			for (ParametroBean parametroBean : parametros) {
				if (parametroBean.getId().intValue() != 2) {
					estados.put(parametroBean.getId(), parametroBean.getDescripcion());
				}
			}
			model.addAttribute("estados", estados);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
