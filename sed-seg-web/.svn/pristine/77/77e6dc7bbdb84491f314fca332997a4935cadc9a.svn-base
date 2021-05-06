package pe.com.sedapal.seguridad.web.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;
import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.ContratistaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.bean.TablaDatosBean;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.ContratistaValidator;
import pe.com.sedapal.seguridad.web.validator.PerfilValidator;

@Controller
public class ContratistaController {
	
	private final Logger logger = LoggerFactory.getLogger(PerfilController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private ContratistaService contratistaService;

	@Autowired
	private ContratistaValidator contratistaValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(contratistaValidator);
	}
	
	
	@RequestMapping(value = "/contratista", method = RequestMethod.GET)
	public String listarModulo(Model model, final RedirectAttributes redirectAttributes) {
        
		try {
			
			if (SecurityUtil.hasRole("/contratista")) {
				//model.addAttribute("contratista", this.seguridadService.obtenerListadoSistemas());
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		// return "perfil/lista";
		return "contratista/listaPaginacion";
	}
	
	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/contratista/getContratistaListado", method = RequestMethod.GET)
	public @ResponseBody TablaDatosBean getContratistaListado(HttpServletRequest request,@RequestParam Map<String,String> allRequestParams) throws RuntimeException {
		List<ContratistaBean> listDatos = new ArrayList<ContratistaBean>();

		JQueryDataTableParamBean.DTParameters param = DataTablesParamUtility.getParam(request);

		int draw;
		int length;
		int start;
		String sortColumn = "";
		String search = "";

		draw = param.getDraw();
		length = param.getLength();
		start = param.getStart();
		String strTipo="";
		String strSearch="";
		
		if(allRequestParams.get("cpTipo")!=null && !"".equals(allRequestParams.get("cpTipo"))) {
			strTipo = allRequestParams.get("cpTipo");
		}
		
		if(allRequestParams.get("cpSearch")!=null && !"".equals(allRequestParams.get("cpSearch"))) {
			strSearch = allRequestParams.get("cpSearch");
		}
		
		search = strTipo+"|"+strSearch; 
//		search = param.getSearch();

		int pageIndex = start / length;

		TablaDatosBean tablaDatos = null;

		int recordsTotal = 0;
		int recordsFiltered = 0;
		List<String> permisos;
		StringBuffer botones = null;
		
		try {
			recordsTotal = contratistaService.obtenerListadoContratistaPaginarTotal(search);
			recordsFiltered = recordsTotal;
			System.out.println(length);
			listDatos = contratistaService.obtenerListadoContratistaPaginar(length, pageIndex, search, sortColumn);
			permisos = (ArrayList<String>) request.getSession().getAttribute("permisos");
			tablaDatos = new TablaDatosBean();

			tablaDatos.setDraw(draw);
			tablaDatos.setRecordsFiltered(recordsFiltered);
			tablaDatos.setRecordsTotal(recordsTotal);

			String[][] datos = new String[listDatos.size()][8];
			int row = 0;

			for (ContratistaBean contratista : listDatos) {
				botones = new StringBuffer();
				datos[row][0] = contratista.getIdContratista().toString();
				datos[row][1] = contratista.getDesRazon();
				datos[row][2] = contratista.getNroDocRazon().toString();
				datos[row][3] = contratista.getDesEstado();
				datos[row][4] = contratista.getDireccion();
				datos[row][5] = contratista.getDesRepresentante();
				datos[row][6] = contratista.getTelefono();
				// datos[row][7] = perfilSistema.getCodSistema().toString();
				if (permisos.contains("/contratistaVISUALIZAR")) {
					botones.append(
							"<sed:seguridad accion=\"/contratistaVISUALIZAR\"><button class=\"close-image btn btn-default btn-view\" onclick=\"location.href=${verUrl}\"> <img src=\"resources/core/images/buscar.png\"></button></sed:seguridad>");
				}
				if (permisos.contains("/contratistaMODIFICAR")) {
					botones.append(
							"<sed:seguridad accion=\"/contratistaMODIFICAR\"><button class=\"close-image btn btn-default btn-update\" onclick=\"location.href=${updateUrl}\"> <img src=\"resources/core/images/edit-icon.png\"></button>");
				}
				if (permisos.contains("/contratistaELIMINAR") && "1".equals(contratista.getCodEstado())) {
					botones.append(
							"<sed:seguridad accion=\"/contratistaELIMINAR\"><button class=\"close-image btn btn-default btn-delete\" onclick=\"location.href=${deleteUrl}\"> <img src=\"resources/core/images/eliminar.png\"></button></sed:seguridad>");
				}
				datos[row][7] = botones.toString();
				row++;
			}

			tablaDatos.setData(datos);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return tablaDatos;
	}
	
	// metodo que renderiza jsp de creación de registro
	@RequestMapping(value = "/contratista/add", method = RequestMethod.GET)
	public String nuevoContratistaForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) {

		if (SecurityUtil.hasRole("/contratista")) {
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar contratista()");
			ContratistaBean contratistaBean = new ContratistaBean();
			contratistaBean.setCodEstado(Constants.ESTADO_ACTIVO_STR);
			populateDefaultModel(model);
			System.out.println(contratistaBean);
			model.addAttribute("contratistaForm", contratistaBean);
			model.addAttribute("urlReturn", urlReturn);
		} else {
			return "accesourl";
		}

		return "contratista/contratistaForm";
	}
	
	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/contratista", method = RequestMethod.POST)
	public String grabarOActualizarContratista(HttpServletRequest request, @ModelAttribute("contratistaForm") @Validated ContratistaBean contratistaBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws Exception, RuntimeException {

		logger.info("grabar o actualizar contratista() : {}", contratistaBean);
		try {
			if (SecurityUtil.hasRole("/contratista")) {
				if (result.hasErrors()) {
					contratistaBean.setCodEstado(Constants.ESTADO_ACTIVO_STR);
					populateDefaultModel(model);
					return "contratista/contratistaForm";
				} else {
					redirectAttributes.addFlashAttribute("css", "success");
					if (contratistaBean.isNew()) {
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");
						contratistaBean.setCodEstado(Constants.ESTADO_ACTIVO_STR);
						contratistaBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						contratistaBean.setIdTermCrea(request.getRemoteAddr());
						contratistaService.grabarContratista(contratistaBean);
					} else {
						contratistaBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						contratistaBean.setIdTermModi(request.getRemoteAddr());
						contratistaService.actualizarContratista(contratistaBean);
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
			model.addAttribute("contratistaForm", contratistaBean);
			return "contratista/contratistaForm";
		}
		return "redirect:/contratista";
	}
	
	// metodo que renderiza jsp de actualización de registro
	@RequestMapping(value = "/contratista/update/{id}", method = RequestMethod.GET)
	public String visualizarContratistaForm(@ModelAttribute("contratistaForm") ContratistaBean contratistaBean,
			@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {
		logger.info("showUpdatecontratistaForm() : {}", id);
		try {
			if (SecurityUtil.hasRole("/contratista")) {
				contratistaBean = contratistaService.obtenerContratistaPorId(id);
				System.out.println(contratistaBean);
				populateDefaultModel(model);
				model.addAttribute("contratistaForm", contratistaBean);
			} else {
				return "accesourl";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/contratista";
		}
		return "contratista/contratistaForm";

	}
	
	// metodo que renderiza jsp de actualización de registro
	@RequestMapping(value = "/contratista/delete/{id}", method = RequestMethod.GET)
	public String eliminarContratistaForm(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {
		logger.info("EliminarContratista() : {}", id);
		try {
			if (SecurityUtil.hasRole("/contratista")) {
				contratistaService.eliminarContratista(id);
			} else {
				return "accesourl";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/contratista";
		}
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Contratista Deshabilitado!");

		return "redirect:/contratista";

	}
	
	// metodo que renderiza jsp de visualizar dato de contratista
	@RequestMapping(value = "/contratista/{id}", method = RequestMethod.GET)
	public String visualizarContratistaFormId(@ModelAttribute("contratistaForm") ContratistaBean contratistaBean,
			@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {
		logger.info("showUpdatecontratistaForm() : {}", id);
		try {
			if (SecurityUtil.hasRole("/contratista")) {
				contratistaBean = contratistaService.obtenerContratistaPorId(id);
				System.out.println(contratistaBean);
				populateDefaultModel(model);
				model.addAttribute("contratistaForm", contratistaBean);
			} else {
				return "accesourl";
			}			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación " + e.getMessage());
			return "redirect:/contratista";
		}
		return "contratista/mostrar";

	}
	
	private void populateDefaultModel(Model model) throws RuntimeException {
		Map<Integer, String> estados = new HashMap<Integer, String>();
		Map<Integer, String> tiposDocumento = new HashMap<Integer, String>();
		List<ParametroBean> parametros;
		try {
			/*adicion de estados*/
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO);
			for (ParametroBean parametroBean : parametros) {
				estados.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("estados", estados);
			/*adicion de tipos documento*/
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.TIPO_DOC);
			for (ParametroBean parametroBean : parametros) {
				tiposDocumento.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("tiposDocumento", tiposDocumento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}