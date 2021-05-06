package pe.com.sedapal.seguridad.web.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.bean.TablaDatosBean;

@Controller
public class AccionPerfilController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(AccionPerfilController.class);

	@Autowired
	private SeguridadService seguridadService;

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/accionperfil", method = RequestMethod.GET)
	public String listarModulo(Model model, final RedirectAttributes redirectAttributes) throws RuntimeException {

		try {
			logger.info("todo los perfiles()");

			// model.addAttribute("pagetest",
			// perfilAccionDAO.obtenerListadoPerfilAccion());

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		return "accionperfil/lista";
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/accionperfil/getPerfilListado", method = RequestMethod.GET)
	public @ResponseBody TablaDatosBean getPerfilListado(HttpServletRequest request) throws RuntimeException {
		List<UsuarioSistemaBean> listDatos = new ArrayList<UsuarioSistemaBean>();

		JQueryDataTableParamBean.DTParameters param = DataTablesParamUtility.getParam(request);

		int draw;
		int length;
		int start;
		String sortColumn = "";
		String search = "";

		draw = param.getDraw();
		length = param.getLength();
		start = param.getStart();
		search = param.getSearch();

		int pageIndex = start / length;

		TablaDatosBean tablaDatos = null;

		int recordsTotal = 0;
		int recordsFiltered = 0;
		// Leer total de registros

		try {
			logger.info("todo los perfiles()");

			recordsTotal = this.seguridadService.obtenerListadoUsuarioSistemaPaginarTotal(search);
			recordsFiltered = recordsTotal;

			listDatos = this.seguridadService.obtenerListadoUsuarioSistemaPaginar(length, pageIndex, search,
					sortColumn);

			tablaDatos = new TablaDatosBean();

			tablaDatos.setDraw(draw);
			tablaDatos.setRecordsFiltered(recordsFiltered);
			tablaDatos.setRecordsTotal(recordsTotal);
			tablaDatos.setUsuarioSistemaBeans(listDatos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tablaDatos;
	}

	// metodo que ejecuta acción de nuevo registro
	@RequestMapping(value = "/accionperfil/add", method = RequestMethod.GET)
	public String nuevoAplicacionForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) throws RuntimeException {

		redirectAttributes.addFlashAttribute("css", "");
		redirectAttributes.addFlashAttribute("msg", "");
		logger.info("agregar accion()");

		PerfilAccionBean accionBean = new PerfilAccionBean();

		model.addAttribute("accionForm", accionBean);
		model.addAttribute("urlReturn", urlReturn);

		// populateDefaultModel(model);
		return "accionperfil/accionForm";
	}

}
