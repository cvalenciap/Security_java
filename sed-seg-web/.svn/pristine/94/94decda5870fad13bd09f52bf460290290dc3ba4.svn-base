package pe.com.sedapal.seguridad.web.controlador;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.bean.MenuBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;

@Controller
public class MenuController {

	private final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private SeguridadService seguridadService;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showMain(Locale locale, Model model, final RedirectAttributes redirectAttributes) throws Exception {

		List<AccesoBean> accesos;
		ParametroBean parametroBean;
		UsuarioBean usuario;
		try {
			logger.info("Bienvenido! El cliente local es {}.", locale);

			String ultimoAcceso = "";
			accesos = seguridadService
					.obtenerUltimoAccesoSistema(SecurityUtil.getAuthenticationName());
			if (accesos != null && !accesos.isEmpty()) {
				ultimoAcceso = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(accesos.get(0).getFecFecha());
				SecurityUtil.getSession().setAttribute("ultimoAcceso", ultimoAcceso);
//				inicio adecuacion seguridad2
//				parametroBean = seguridadService.obtenerParametroPorCodigoParametro(Constants.PREFIJO);
//				SecurityUtil.getSession().setAttribute("abreviatura", parametroBean.getValor());
				final Integer codSistema =(Integer) SecurityUtil.getSession().getAttribute("codSistema");
				final Integer codPerfil =(Integer) SecurityUtil.getSession().getAttribute("codPerfil");
				SecurityUtil.getSession().setAttribute("codSistema", codSistema);
				SecurityUtil.getSession().setAttribute("codPerfil", codPerfil);
//				fin adecuacion seguridad2
				SecurityUtil.getSession().setAttribute("usuario", SecurityUtil.getAuthenticationName());
				//model.addAttribute("valorSEG", parametro.getValor());
				model.addAttribute("serverTime", ultimoAcceso);
			}		
			usuario = this.seguridadService.obtenerUsuarioPorCodUsuario(SecurityUtil.getAuthenticationName());
			if(usuario != null && "1".equals(usuario.getFlagCambiarClave())){
				return "forward:/logout";
			}
			

			logger.info("toda las opciones de menu()");

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "menu/principal";
	}

	// list page
	@RequestMapping(value = "/menu/list/{codSistema},{id}", method = RequestMethod.GET)
	public String showAllMenu(@PathVariable("codSistema") int codSistema, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws Exception {

		try {
			logger.info("toda las opciones de menu()");
			model.addAttribute("formularioMenu",
					seguridadService.obtenerListadoSistemaModuloFormularioMenu(codSistema, id));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "menu/lista";
	}

	// list perfil
	@RequestMapping(value = "/menu/perfil/{codSistema},{id}", method = RequestMethod.GET)
	public String showMenuPerfil(@PathVariable("codSistema") int codSistema, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws Exception {

		StringBuilder sb = new StringBuilder();

		try {
			logger.info("toda las opciones de menu()");

			List<MenuBean> lista = seguridadService.obtenerListadoSistemaModuloFormularioMenu(codSistema, id);
			this.MenuFormularioModulo(sb, lista, 0);

			model.addAttribute("formularioMenu", sb);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "menu/perfil";
	}

	private void MenuFormularioModulo(StringBuilder sb, List<MenuBean> listMenu, int id) {
		for (MenuBean menu : listMenu) {
			if (menu.getCodFormularioPadre() == 0) {
				sb.append(
						"<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/carpetclose.gif\"/></span>");
				sb.append(menu.getDescripcion());
				sb.append("<b class=\"caret\"></b></a>");
				itemMenu(sb, listMenu, menu.getCodFormulario());
				sb.append("</li>");
			}
		}
	}

	private void itemMenu(StringBuilder sb, List<MenuBean> listMenu, int idNode) {
		StringBuilder sb1 = new StringBuilder();

		for (MenuBean bean : listMenu) {
			if (bean.getCodFormularioPadre() == idNode) {
				sb1.append("<li><a href=\"#\">");
				sb1.append(bean.getDescripcion());
				sb1.append("</a></li>");
				itemSubMenu(sb1, listMenu, bean.getCodFormulario());
			}
		}
		if (sb1.length() > 0) {
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append(sb1.toString());
			sb.append("</ul>");
		}
	}

	private void itemSubMenu(StringBuilder sb, List<MenuBean> listMenu, int idNodeChild) {
		StringBuilder sb2 = new StringBuilder();

		for (MenuBean bean : listMenu) {
			if (bean.getCodFormularioPadre() == idNodeChild) {
				sb2.append("<li><a href=\"#\">");
				sb2.append(bean.getDescripcion());
				sb2.append("</a></li>");
			}
		}
		if (sb2.length() > 0) {
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append(sb2.toString());
			sb.append("</ul>");
		}

	}

	@RequestMapping(value = "/menu/getFormularioModulo", method = RequestMethod.GET)
	public @ResponseBody List<MenuBean> getFormularioModulo(@RequestParam("value1") String codSistema,
			@RequestParam("value2") String codFicha) {

		List<MenuBean> lista = null;
		StringBuilder sb = new StringBuilder();

		try {
			lista = seguridadService.obtenerListadoSistemaModuloFormularioMenu(codSistema, codFicha);
			sb.append("<ul></ul>");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lista;
	}

}
