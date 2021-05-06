package pe.com.sedapal.seguridad.web.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.bean.TablaDatosBean;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.PerfilValidator;

@Controller
@SessionAttributes("lstOpciones")
public class PerfilController {

	private final Logger logger = LoggerFactory.getLogger(PerfilController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private PerfilValidator perfilValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(perfilValidator);
	}

	private String descripcionTemporal ;//VARIABLE TEMPORAL , PARA VALIDACION DE PERFIL DUPLICADOS, SE CREO CON SU GET Y SET - ALEX
	
	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String listarModulo(Model model, final RedirectAttributes redirectAttributes) {

		try {
			logger.info("todo los perfiles()");
			if (SecurityUtil.hasRole("/perfil")) {
				model.addAttribute("perfiles", seguridadService.obtenerListadoPerfilSistemaPorCodigo(0));
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		// return "perfil/lista";
		return "perfil/listaPaginacion";
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/perfil/getPerfilListado", method = RequestMethod.GET)
	public @ResponseBody TablaDatosBean getPerfilListado(HttpServletRequest request,@RequestParam Map<String,String> allRequestParams) throws RuntimeException {
		List<PerfilSistemaBean> listDatos = new ArrayList<PerfilSistemaBean>();

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
		logger.info("todo los perfiles()");
		try {
			recordsTotal = seguridadService.obtenerListadoPerfilSistemaPaginarTotal(search);
			recordsFiltered = recordsTotal;
			listDatos = seguridadService.obtenerListadoPerfilSistemaPaginar(length, pageIndex, search, sortColumn);
			permisos = (ArrayList<String>) request.getSession().getAttribute("permisos");
			tablaDatos = new TablaDatosBean();

			tablaDatos.setDraw(draw);
			tablaDatos.setRecordsFiltered(recordsFiltered);
			tablaDatos.setRecordsTotal(recordsTotal);

			String[][] datos = new String[listDatos.size()][8];
			int row = 0;

			for (PerfilSistemaBean perfilSistema : listDatos) {
				botones = new StringBuffer();
				datos[row][0] = perfilSistema.getCodSistema().toString();
				datos[row][1] = perfilSistema.getSistemaNombre();
				datos[row][2] = perfilSistema.getCodPerfil().toString();
				datos[row][3] = perfilSistema.getDescripcion();
				datos[row][4] = perfilSistema.getEstadoNombre();
				datos[row][5] = perfilSistema.getUsuarioModificacion();
				datos[row][6] = (perfilSistema.getFechaModificacion() == null ? ""
						: perfilSistema.getFechaModificacion().toString());
				// datos[row][7] = perfilSistema.getCodSistema().toString();
				if (permisos.contains("/perfilVISUALIZAR")) {
					botones.append(
							"<sed:seguridad accion=\"/perfilVISUALIZAR\"><button class=\"close-image btn btn-default btn-view\" onclick=\"location.href=${verUrl}\"> <img src=\"resources/core/images/buscar.png\"></button></sed:seguridad>");
				}
				if (permisos.contains("/perfilMODIFICAR")) {
					botones.append(
							"<sed:seguridad accion=\"/perfilMODIFICAR\"><button class=\"close-image btn btn-default btn-update\" onclick=\"location.href=${updateUrl}\"> <img src=\"resources/core/images/edit-icon.png\"></button>");
				}
				if (permisos.contains("/perfilELIMINAR") && "1".equals(perfilSistema.getEstado())) {
					botones.append(
							"<sed:seguridad accion=\"/perfilELIMINAR\"><button class=\"close-image btn btn-default btn-delete\" onclick=\"location.href=${deleteUrl}\"> <img src=\"resources/core/images/eliminar.png\"></button></sed:seguridad>");
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

	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/usuario/acciones/", method = RequestMethod.POST)
	public String grabarOActualizarUsuarioAcciones(
			@ModelAttribute("perfilForm") @Validated PerfilSistemaBean perfilSistemaBean, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		logger.info("grabar o actualizar perfil() : {}", perfilSistemaBean);

		if (result.hasErrors()) {
			
			perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);
			populateDefaultModel(model);
			model.addAttribute("perfilForm", perfilSistemaBean);
			return "perfil/perfilForm";
		} else {

			if (SecurityUtil.hasRole("/usuario")) {
				perfilSistemaBean.setDescripcion(perfilSistemaBean.getDescripcion().toUpperCase());
				//
				try {
					redirectAttributes.addFlashAttribute("css", "success");
					if (perfilSistemaBean.isNew()) {
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");
						perfilSistemaBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						// seguridadService.grabarPerfilSistema(perfilSistemaBean);
					} else {
						perfilSistemaBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						seguridadService.actualizarPerfilSistema(perfilSistemaBean);
						// redirectAttributes.addFlashAttribute("msg", "Se
						// Actualizó Satisfactoriamente!");
					}

				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
					populateDefaultModel(model);
				}
			} else {
				return "accesourl";
			}

			//

			return "redirect:/usuarios";
		}

	}

	@RequestMapping(value = "/usuario/acciones/{codUsuario}", method = RequestMethod.GET)
	public String accionesUsuario(@PathVariable("codUsuario") String codUsuario, Model model,
			final RedirectAttributes redirectAttributes) {

		try {

			logger.info("visualizarUsuarioPerfilForm() : {}", codUsuario);

			if (SecurityUtil.hasRole("/usuario")) {
				model.addAttribute("codUsuario", codUsuario);
				PerfilSistemaBean perfilSistemaBean = new PerfilSistemaBean();
				perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);
				perfilSistemaBean.setUsuarioAccion(Boolean.TRUE);
				populateDefaultModel(model);
				model.addAttribute("perfilForm", perfilSistemaBean);
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "usuario/accionesusuario";
	}

	// metodo que ejecuta acción de nuevo registro
	@RequestMapping(value = "/perfil/add", method = RequestMethod.GET)
	public String nuevoPerfilForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) {

		if (SecurityUtil.hasRole("/perfil")) {
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar formulario()");

			PerfilSistemaBean perfilSistemaBean = new PerfilSistemaBean();
			perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);

			model.addAttribute("perfilForm", perfilSistemaBean);
			model.addAttribute("urlReturn", urlReturn);

			populateDefaultModel(model);
		} else {
			return "accesourl";
		}

		return "perfil/perfilForm";
	}
	
	
	private boolean isPerfilDuplicado(PerfilSistemaBean perfilSistemaBean) throws Exception {
		
		boolean retorno = false;
		List<PerfilSistemaBean> listaPerfil = new ArrayList<>();
		listaPerfil = seguridadService.obtenerListadoPerfilSistemaPorCodigo(0);
		Integer codSistBD = perfilSistemaBean.getCodSistema();
		String descBD = perfilSistemaBean.getDescripcion();
		
		if(descripcionTemporal!=null && descripcionTemporal.equals(descBD)){
			retorno = false; 
			return retorno;	
		}
		
		for (PerfilSistemaBean currentperfil : listaPerfil) {
			Integer codSist = currentperfil.getCodSistema();
			String desc = currentperfil.getDescripcion();
			if(codSist == codSistBD && desc.equalsIgnoreCase(descBD)){
				retorno = true; 
				return retorno;				
			}
		}
		
		return retorno;		
	}
	


	// metodo que ejecuta acción de grabar o actualizar
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public String grabarOActualizarPerfil(@ModelAttribute("perfilForm") @Validated PerfilSistemaBean perfilSistemaBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		logger.info("grabar o actualizar perfil() : {}", perfilSistemaBean);

		if (result.hasErrors()) {
			perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);
			populateDefaultModel(model);
			model.addAttribute("perfilForm", perfilSistemaBean);			
			return "perfil/perfilForm";
		} else {

			if (SecurityUtil.hasRole("/perfil")) {
				perfilSistemaBean.setDescripcion(perfilSistemaBean.getDescripcion().toUpperCase());
				//
				try {
					redirectAttributes.addFlashAttribute("css", "success");
					if (perfilSistemaBean.isNew()) {
						
						if(isPerfilDuplicado(perfilSistemaBean)){
							model.addAttribute("msg", "Nombre de perfil ya existe");
							model.addAttribute("css", "danger");
							populateDefaultModel(model);
							perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);
							model.addAttribute("perfilForm", perfilSistemaBean);	
							return "perfil/perfilForm";
						}						
						
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");
						perfilSistemaBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						perfilSistemaBean.setEstado(Constants.ESTADO_ACTIVO_STR);
						seguridadService.grabarPerfilSistema(perfilSistemaBean);
					} else {
						

						if(isPerfilDuplicado(perfilSistemaBean)){
							model.addAttribute("msg", "Nombre de perfil ya existe");
							model.addAttribute("css", "danger");
							populateDefaultModel(model);
							model.addAttribute("perfilForm", perfilSistemaBean);	
							return "perfil/perfilForm";
						}	
						
						perfilSistemaBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						seguridadService.actualizarPerfilSistema(perfilSistemaBean);
						redirectAttributes.addFlashAttribute("msg", "Se Actualizó Satisfactoriamente!");
					}

				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
					populateDefaultModel(model);
				}
			} else {
				return "accesourl";
			}

			//

			return "redirect:/perfil";
		}

	}

	// metodo que ejecuta acción de busqueda por Id o Código, para actualizar
	// registro
	@RequestMapping(value = "/perfil/update/{codigo},{id}", method = RequestMethod.GET)
	public String visualizarPerfilForm(@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.info("visualizarPerfilForm() : {}", id);
		PerfilSistemaBean perfilSistemaBean = null;
		try {
			
			if (SecurityUtil.hasRole("/perfil")) {
				perfilSistemaBean = seguridadService.obtenerPerfilSistemaPorCodigo(codigo, id);
				setDescripcionTemporal(perfilSistemaBean.getDescripcion());//se agrego para validar - ALEX 
				model.addAttribute("perfilForm", perfilSistemaBean);
				populateDefaultModel(model);
			} else {
				return "accesourl";
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "perfil/perfilForm";
	}

	// elimina registro por Id o Código
	@RequestMapping(value = "/perfil/delete/{codigo},{id}", method = RequestMethod.GET)
	public String eliminaModulo(@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.info("eliminaPerfil() : {}", id);

		try {
			if (SecurityUtil.hasRole("/perfil")) {
				seguridadService.actualizarEstadoPerfilSistema(codigo, id, "0", SecurityUtil.getAuthenticationName());	
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
			return "redirect:/perfil";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Perfil Deshabilitado!");

		return "redirect:/perfil";
	}

	// metodo que ejecuta acción de mostrar registro
	@RequestMapping(value = "/perfil/{codigo},{id}", method = RequestMethod.GET)
	public String mostrarPerfil(@PathVariable("codigo") int codigo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.info("mostrarPerfil() id: {}", id);
		PerfilSistemaBean perfilSistemaBean = null;
		try {
			if (SecurityUtil.hasRole("/perfil")) {
				perfilSistemaBean = seguridadService.obtenerPerfilSistemaPorCodigo(codigo, id);
				if (perfilSistemaBean == null) {
					model.addAttribute("css", "danger");
					model.addAttribute("msg", "Perfil no encontrado");
				}
				populateDefaultModel(model);
				model.addAttribute("perfilForm", perfilSistemaBean);	
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "perfil/mostrar";
	}

	@RequestMapping(value = "/perfil/getAccionFormModuloSistema", method = RequestMethod.GET)
	public @ResponseBody List<ParametroBean> getAccionModuloSistema(@RequestParam("id") int codSistema)
			throws RuntimeException {
		List<ParametroBean> lstParametros;
		List<ParametroBean> parametros = new ArrayList<ParametroBean>();

		try {

			lstParametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ACCIONES);

			parametros.add(new ParametroBean("", 0, "NOMBRE DEL FORMULARIO", 1, 0, ""));

			for (ParametroBean parametroBean : lstParametros) {
				parametros.add(parametroBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return parametros;
	}

	@RequestMapping(value = "/perfil/getOpcionFormModuloSistema", method = RequestMethod.GET)
	public @ResponseBody List<SistemaModuloFormBean> getModuloSistemaList(@RequestParam("codsistema") int codSistema,
			@RequestParam("codperfil") int codPerfil) throws RuntimeException {
		List<SistemaModuloFormBean> lstOpciones;
		List<SistemaModuloFormBean> opcion = new ArrayList<SistemaModuloFormBean>();

		try {

			lstOpciones = seguridadService.obtenerListadoSistemaModuloFormPorCodigo(codSistema, 0);

			for (SistemaModuloFormBean sistemaModuloForm : lstOpciones) {
				logger.info("cod sistema " + sistemaModuloForm.getCodSistema());
				logger.info("cod modulo " + sistemaModuloForm.getCodModulo());
				logger.info("cod formulario " + sistemaModuloForm.getCodFormulario());
				logger.info("cod perfil " + codPerfil);
				List<AccionFormularioBean> accionFormulario = seguridadService.obtenerListadoAccionFormularioPerfilPorCodigo(sistemaModuloForm.getCodSistema(),sistemaModuloForm.getCodModulo(), sistemaModuloForm.getCodFormulario(), codPerfil);
				if(accionFormulario != null && !accionFormulario.isEmpty()){
					sistemaModuloForm.setAccionFormulario(accionFormulario);
					opcion.add(sistemaModuloForm);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return opcion;
	}

	@Autowired
	private HttpSession httpSession;

	@RequestMapping(value = "/perfil/getOpcionFormModuloInicio", method = RequestMethod.GET)
	public @ResponseBody List<SistemaModuloFormBean> getModuloSistemaInicioList(@RequestParam("id") String paramSistema,
			@RequestParam("codigo") String codUsuario) throws RuntimeException {
		List<SistemaModuloFormBean> lstOpciones = null;
		try {
			lstOpciones = seguridadService.obtenerListadoSistemaModuloFormInicioPorCodigo(paramSistema, codUsuario);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lstOpciones;
	}

	@RequestMapping(value = "/perfil/getOpcionModuloInicio", method = RequestMethod.GET)

	public @ResponseBody List<SistemaModuloOpcionBean> getModuloSistemaOpcion(@RequestParam("id") String paramSistema,
//			inicio adecuacion seguridad2
//			@RequestParam("codigo") String codUsuario) throws RuntimeException {
			@RequestParam("codigo") String codUsuario, @RequestParam("perfil") String codPerfil) throws RuntimeException {
//			fin adecuacion seguridad2
		List<SistemaModuloOpcionBean> lstOpciones = null;
		List<String> lista = new ArrayList<>();
		try {
//			inicio adecuacion seguridad2
//			lstOpciones = seguridadService.obtenerListadoSistemaModuloFormInicioPorCodigov2(paramSistema, codUsuario);
			lstOpciones = seguridadService.obtenerListadoSistemaModuloFormInicioPorCodigoAct(paramSistema, codUsuario, codPerfil);
//			fin adecuacion seguridad2
			
			for (SistemaModuloOpcionBean sistemaModuloOpcionBean : lstOpciones) {
				logger.info(sistemaModuloOpcionBean.toString());
				lista.add(sistemaModuloOpcionBean.getUrlFormulario());
			}
			httpSession.setAttribute("menu", lista);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lstOpciones;
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
			model.addAttribute("sistemas", treeMap);
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

	public String getDescripcionTemporal() {
		return descripcionTemporal;
	}

	public void setDescripcionTemporal(String descripcionTemporal) {
		this.descripcionTemporal = descripcionTemporal;
	}

}
