package pe.com.sedapal.seguridad.web.controlador;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.validator.OlvidoValidator;

@Controller
public class OlvidoController {

	private final Logger logger = LoggerFactory.getLogger(OlvidoController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private OlvidoValidator olvidoValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(olvidoValidator);
	}

	@RequestMapping(value = "/olvido", method = RequestMethod.GET)
	public String olvidoClave(Model model, final RedirectAttributes redirectAttributes) throws Exception {

		redirectAttributes.addFlashAttribute("css", "");
		redirectAttributes.addFlashAttribute("msg", "");
		logger.info("agregar formulario()");

		ClaveBean claveBean = new ClaveBean();

		model.addAttribute("claveForm", claveBean);

		return "olvido/olvidoClaveForm";

	}

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/olvido", method = RequestMethod.POST)
	public String olvidoClave(@ModelAttribute("claveForm") @Validated ClaveBean claveBean, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) throws Exception {

		logger.info("olvidar clave clave() : {}", claveBean);

		if (result.hasErrors()) {
			return "olvido/olvidoClaveForm";
		} else {
			try {
				seguridadService.olvidarClave(claveBean.getCodUsuario());
				redirectAttributes.addFlashAttribute("css", "success");
				redirectAttributes.addFlashAttribute("msg", "Se envio el correo con su nueva clave");

			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("css", "danger");
				redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
				model.asMap().put("css", "danger");
				model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());

				return "redirect:/olvido";
			}

			return "redirect:/login";
		}

	}

}
