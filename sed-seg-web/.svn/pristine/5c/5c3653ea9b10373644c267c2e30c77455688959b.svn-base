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

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.MenuBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.web.bean.Modulo;
import pe.com.sedapal.seguridad.web.bean.TablaDatosBean;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.OpcionFormValidator;

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
public class OpcionFormController {

	private final Logger logger = LoggerFactory.getLogger(ModuloController.class);

	@Autowired
	private SeguridadService seguridadService;

	@Autowired
	private OpcionFormValidator opcionFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(opcionFormValidator);
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/opcionform", method = RequestMethod.GET)
	public String listarFormularioModulo(Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {

		try {
			logger.info("todo lass opciones de formulario de modulo()");
			if (SecurityUtil.hasRole("/opcionform")) {

			} else {
				return "accesourl";
			}
			// model.addAttribute("opcionforms",
			// seguridadService.obtenerListadoSistemaModuloFormPorCodigo(0, 0));
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}
		// return "opcionform/lista";
		return "opcionform/listaPaginacion";
	}

	// metodo que ejecuta acción de listado
	@RequestMapping(value = "/opcionform/getFormularioListado", method = RequestMethod.GET)
	public @ResponseBody TablaDatosBean getFormularioListado(HttpServletRequest request,
			@RequestParam Map<String,String> allRequestParams) throws RuntimeException {
		List<SistemaModuloFormBean> listDatos = new ArrayList<SistemaModuloFormBean>();

		JQueryDataTableParamBean.DTParameters param = DataTablesParamUtility.getParam(request);
		
		String tipoBusqueda="";
		String valorBusqueda="";
		
		if(allRequestParams.get("tipoBusqueda")!=null && !"".equals(allRequestParams.get("tipoBusqueda"))) {
			tipoBusqueda = allRequestParams.get("tipoBusqueda");
		}
		
		if(allRequestParams.get("valorBusqueda")!=null && !"".equals(allRequestParams.get("valorBusqueda"))) {
			valorBusqueda = allRequestParams.get("valorBusqueda");
		}

		int draw;
		int length;
		int start;
		String sortColumn = "";
//		String search = "";

		draw = param.getDraw();
		length = param.getLength();
		start = param.getStart();
//		search = param.getSearch();

		int pageIndex = start / length;
		TablaDatosBean tablaDatos = null;

		int recordsTotal = 0;
		int recordsFiltered = 0;
		StringBuffer botones = null;
		List<String> permisos;
		try {

			if (SecurityUtil.hasRole("/opcionform")) {
				permisos = (ArrayList<String>) request.getSession().getAttribute("permisos");
				logger.info("todo los perfiles()");
				recordsTotal = this.seguridadService.obtenerListadoSistemaModuloFormPaginarTotal(tipoBusqueda, valorBusqueda);

				recordsFiltered = recordsTotal;

				listDatos = this.seguridadService.obtenerListadoSistemaModuloFormPaginar(length, pageIndex, tipoBusqueda, valorBusqueda,
						sortColumn);

				tablaDatos = new TablaDatosBean();

				tablaDatos.setDraw(draw);
				tablaDatos.setRecordsFiltered(recordsFiltered);
				tablaDatos.setRecordsTotal(recordsTotal);
				// tablaDatos.setData(listDatos);

				String[][] datos = new String[listDatos.size()][11];
				int row = 0;

				for (SistemaModuloFormBean sistemaModuloForm : listDatos) {
					botones = new StringBuffer();
					datos[row][0] = sistemaModuloForm.getCodSistema().toString();
					datos[row][1] = sistemaModuloForm.getSistemaNombre();
					datos[row][2] = sistemaModuloForm.getCodModulo().toString();
					datos[row][3] = sistemaModuloForm.getModuloNombre();
					datos[row][4] = sistemaModuloForm.getCodFormulario().toString();
					datos[row][5] = sistemaModuloForm.getDescripcion();
					datos[row][6] = sistemaModuloForm.getEstadoNombre();
					datos[row][7] = sistemaModuloForm.getUrlFormulario();
					datos[row][8] = sistemaModuloForm.getUsuarioModificacion();
					datos[row][9] = (sistemaModuloForm.getFechaModificacion() == null ? ""
							: sistemaModuloForm.getFechaModificacion().toString());
					if (permisos.contains("/opcionformVISUALIZAR")) {
						botones.append(
								"<sed:seguridad accion=\"/opcionformVISUALIZAR\"><button class=\"close-image btn btn-default btn-view\" onclick=\"location.href=${verUrl}\"> <img src=\"resources/core/images/buscar.png\"></button></sed:seguridad>");
					}
					if (permisos.contains("/opcionformMODIFICAR")) {
						/*botones.append(
								"<sed:seguridad accion=\"/opcionformMODIFICAR\"><button class=\"close-image btn btn-default btn-update1\" onclick=\"location.href=${updateUrl}\"> <img src=\"resources/core/images/edit-icon.png\"></button>");*/
						botones.append(
								"<button class=\"close-image btn btn-default btn-update2\" onclick=\"location.href=${updateTreeUrl}\"> <img src=\"resources/core/images/edit-icon.png\"></button></sed:seguridad>");
					}
					if (permisos.contains("/opcionformELIMINAR") && sistemaModuloForm.getEstado().intValue() == 1) {
						botones.append(
								"<sed:seguridad accion=\"/opcionformELIMINAR\"><button class=\"close-image btn btn-default btn-delete\" onclick=\"location.href=${deleteUrl}\"> <img src=\"resources/core/images/eliminar.png\"></button></sed:seguridad>");
					}
					datos[row][10] = botones.toString();
					row++;
				}

				tablaDatos.setData(datos);
			} else {
				// return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return tablaDatos;
	}

	// metodo que ejecuta acción de nuevo registro
	@RequestMapping(value = "/opcionform/add", method = RequestMethod.GET)
	public String nuevoFormularioModuloForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) throws RuntimeException {

		if (SecurityUtil.hasRole("/opcionform")) {
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar opciones formulario()");

			SistemaModuloFormBean sistemaModuloFormBean = new SistemaModuloFormBean();
			sistemaModuloFormBean = new SistemaModuloFormBean();
			sistemaModuloFormBean.setEstado(Constants.ESTADO_ACTIVO);
			sistemaModuloFormBean.setActivarRoot(false);
			populateDefaultModel(model);
			model.addAttribute("opcionForm", sistemaModuloFormBean);
			model.addAttribute("urlReturn", urlReturn);
		} else {
			return "accesourl";
		}

		return "opcionform/opcionForm";
	}
	
	private boolean isFormPadreInactivo(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
		boolean retorno = false;
		
		String codSistema = String.valueOf(sistemaModuloFormBean.getCodSistema());
		String codModulo = String.valueOf(sistemaModuloFormBean.getCodModulo());	
	
		Integer codFormularioPadre = sistemaModuloFormBean.getCodFormularioPadre();
		System.out.println("############## ENTRO 1");
		if(codFormularioPadre == 0 ){	
			System.out.println("############## Grabo correctamente A");
			retorno = false;
			return retorno;
		}
		
		List<MenuBean> listaA = seguridadService.obtenerListadoSistemaModuloFormularioMenu(codSistema, codModulo);
				
		for (MenuBean menuBean : listaA) {			
			Integer codFormulario = menuBean.getCodFormulario();
			Integer estado = menuBean.getEstado();
			if(codFormularioPadre == codFormulario &&  estado!=1){				
				retorno = true;
				break;
			}else if(codFormularioPadre == codFormulario &&  estado==1){				
				retorno = false;
				break;
			}
		}		
		return retorno;
	}
	

	@RequestMapping(value = "/opcionform", method = RequestMethod.POST)
	public String grabarOActualizarFormulario(
			@ModelAttribute("opcionForm") @Validated SistemaModuloFormBean sistemaModuloFormBean, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) throws RuntimeException {

		logger.info("grabar o actualizar opciones form() : {}", sistemaModuloFormBean);
		List<SistemaModuloFormBean> lista;
		
		List<String> ids = new ArrayList<String>();
		List<String> idsAux = new ArrayList<String>();
		String in = "";
		
		if (result.hasErrors()) {
			sistemaModuloFormBean.setEstado(Constants.ESTADO_ACTIVO);
			populateDefaultModel(model);
			populateListaModulo(model, sistemaModuloFormBean.getCodSistema());
			model.addAttribute("opcionForm", sistemaModuloFormBean);
			return "opcionform/opcionForm";

		} else {

			if (SecurityUtil.hasRole("/opcionform")) {
				redirectAttributes.addFlashAttribute("selectCodSistema", sistemaModuloFormBean.getCodSistema());
				redirectAttributes.addFlashAttribute("selectCodModulo", sistemaModuloFormBean.getCodModulo());

				try {
					redirectAttributes.addFlashAttribute("css", "success");
					AccionFormularioBean accionFormularioBean;

					if (sistemaModuloFormBean.isNew()) {
						//jj, issue 5: 
						if(isFormPadreInactivo(sistemaModuloFormBean)){							
							model.addAttribute("msg", "No se puede crear el formulario porque el estado del formulario padre es inactivo");
							model.addAttribute("css", "danger");							
							populateDefaultModel(model);
							populateListaModulo(model, sistemaModuloFormBean.getCodSistema());
							model.addAttribute("opcionForm", sistemaModuloFormBean);
							return "opcionform/opcionForm";
						}						
						//jj, fin issue 5
						
						redirectAttributes.addFlashAttribute("msg", "Se Registró Satisfactoriamente!");

						sistemaModuloFormBean.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
						sistemaModuloFormBean.setEstado(Constants.ESTADO_ACTIVO);
						int identity = seguridadService.grabarSistemaModuloForm(sistemaModuloFormBean);

						if (sistemaModuloFormBean.getAccion() != null) {
							for (String accion : sistemaModuloFormBean.getAccion()) {
								accionFormularioBean = new AccionFormularioBean();
								accionFormularioBean.setCodSistema(sistemaModuloFormBean.getCodSistema());
								accionFormularioBean.setCodModulo(sistemaModuloFormBean.getCodModulo());
								accionFormularioBean.setCodFormulario(identity);
								accionFormularioBean.setCodParametro(Constants.ACCIONES);
								accionFormularioBean.setCodigo(Integer.parseInt(accion));
								accionFormularioBean.setEstado(Constants.ESTADO_ACTIVO);

								seguridadService.grabarAccionFormulario(accionFormularioBean);
							}
						}

					} else {
						
						//jj, issue 5: 
						if(isFormPadreInactivo(sistemaModuloFormBean)){							
							model.addAttribute("msg", "No se puede actualizar el formulario porque el estado del formulario padre es inactivo");
							model.addAttribute("css", "danger");							
							populateDefaultModel(model);
							populateListaModulo(model, sistemaModuloFormBean.getCodSistema());
							model.addAttribute("opcionForm", sistemaModuloFormBean);
							return "opcionform/opcionForm";
						}						
						//jj, fin issue 5
						
						redirectAttributes.addFlashAttribute("msg", "Se Actualizó Satisfactoriamente!");

						in = "" + sistemaModuloFormBean.getCodFormulario();
						idsAux.add( in);
						lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(sistemaModuloFormBean.getCodSistema(), sistemaModuloFormBean.getCodModulo(), in);
						while(!lista.isEmpty()){
							lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(sistemaModuloFormBean.getCodSistema(), sistemaModuloFormBean.getCodModulo(), in); 
							ids = new ArrayList<String>();
							for (SistemaModuloFormBean sistemaModuloFormBean1 : lista) {
								//System.out.println(sistemaModuloFormBean);
								ids.add("" + sistemaModuloFormBean1.getCodFormulario());
								idsAux.add("" + sistemaModuloFormBean1.getCodFormulario());
							}			
							in = "";			
							for (int i = 0; i < ids.size(); i++) {				
								in = in + ids.get(i) + ",";
								//System.out.println(in);
							}
							if(!in.equals("")){
								in = in.substring(0,in.length() - 1);
							}
							
						}
						
						sistemaModuloFormBean.setUsuarioModificacion(SecurityUtil.getAuthenticationName());
						seguridadService.actualizarSistemaModuloForm(sistemaModuloFormBean);
						
						for (int i = 0; i < idsAux.size(); i++) {															
							this.seguridadService.actualizarEstadoSistemaModuloForm(sistemaModuloFormBean.getCodSistema(), sistemaModuloFormBean.getCodModulo(),
									idsAux.get(i), sistemaModuloFormBean.getEstado(), SecurityUtil.getAuthenticationName());
						}
						
						
						sistemaModuloFormBean
								.setAccionFormulario(seguridadService.obtenerListadoAccionFormularioPorCodigo(
										sistemaModuloFormBean.getCodSistema(), sistemaModuloFormBean.getCodModulo(),
										sistemaModuloFormBean.getCodFormulario()));

						int codeAccion = 0;

						if (sistemaModuloFormBean.getAccion() != null) {

							if (sistemaModuloFormBean.getAccion().size() > 0) {
								boolean encontrado = false;

								for (AccionFormularioBean accionFormulario : sistemaModuloFormBean
										.getAccionFormulario()) {
									codeAccion = accionFormulario.getCodigo();

									for (String accion : sistemaModuloFormBean.getAccion()) {
										if (codeAccion == Integer.parseInt(accion)) {
											encontrado = true;
											break;
										}
									}

   									if (encontrado) {
										if (accionFormulario.getCodAccionFormulario() > 0) {
											if (accionFormulario.getEstado() == Constants.ESTADO_INACTIVO) {
												accionFormularioBean = new AccionFormularioBean();
												accionFormularioBean.setCodAccionFormulario(
														accionFormulario.getCodAccionFormulario());
												accionFormularioBean.setEstado(Constants.ESTADO_ACTIVO);
												seguridadService.actualizarAccionFormulario(accionFormularioBean);
											}
										} else {
											accionFormularioBean = new AccionFormularioBean();
											accionFormularioBean.setCodSistema(sistemaModuloFormBean.getCodSistema());
											accionFormularioBean.setCodModulo(sistemaModuloFormBean.getCodModulo());
											accionFormularioBean
													.setCodFormulario(sistemaModuloFormBean.getCodFormulario());
											accionFormularioBean.setCodParametro(Constants.ACCIONES);
											accionFormularioBean.setCodigo(codeAccion);
											accionFormularioBean.setEstado(Constants.ESTADO_ACTIVO);
											seguridadService.grabarAccionFormulario(accionFormularioBean);
										}

//									} else {
   									} else if(accionFormulario.getCodAccionFormulario() != null) {	
										if (accionFormulario.getCodAccionFormulario() > 0) {
											if (accionFormulario.getEstado() == Constants.ESTADO_ACTIVO) {
												accionFormularioBean = new AccionFormularioBean();
												accionFormularioBean.setCodAccionFormulario(
														accionFormulario.getCodAccionFormulario());
												accionFormularioBean.setEstado(Constants.ESTADO_INACTIVO);
												seguridadService.actualizarAccionFormulario(accionFormularioBean);
											}
										}
									}
									encontrado = false;
								}
							}

						} else {
							if (sistemaModuloFormBean.getEstado().intValue() != 1) {
								for (AccionFormularioBean accionFormulario : sistemaModuloFormBean
										.getAccionFormulario()) {
									if (accionFormulario.getCodAccionFormulario() > 0) {
										accionFormulario.setEstado(Constants.ESTADO_INACTIVO);
										seguridadService.actualizarAccionFormulario(accionFormulario);
									}
								}
							}

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
					model.asMap().put("css", "danger");
					model.asMap().put("msg", "Error al realizar la operación " + e.getMessage());
					populateDefaultModel(model);
					populateListaModulo(model, sistemaModuloFormBean.getCodSistema());
					return "opcionform/opcionForm";
					
				}
			} else {
				return "accesourl";
			}

			// return "redirect:/opcionform/add";
			return "redirect:/opcionform";
		}

	}

	// metodo que ejecuta acción de busqueda por Id o Código, para actualizar
	// registro
	@RequestMapping(value = "/opcionform/update/{codSistema},{codModulo},{id},{activar}", method = RequestMethod.GET)
	public String visualizarFormularioModuloForm(@PathVariable("codSistema") int codSistema,
			@PathVariable("codModulo") int codModulo, @PathVariable("id") int id,
			@PathVariable("activar") boolean activar, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {

		logger.info("visualizarOpcionForm() : {}", id);
		SistemaModuloFormBean sistemaModuloFormBean = null;
		try {
			if (SecurityUtil.hasRole("/opcionform")) {
				sistemaModuloFormBean = seguridadService.obtenerSistemaModuloFormPorCodigo(codSistema, codModulo, id);
				// Se carga lista de acciones de formulario todos
				List<AccionFormularioBean> listAcciones = seguridadService
						.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo, id);
				List<String> items = new ArrayList<String>();
				if (null != listAcciones) {
					for (AccionFormularioBean accionFormulario : listAcciones) {
						if (accionFormulario.getEstado() == 1) {
							items.add(accionFormulario.getCodigo().toString());
						}
					}
				}

				sistemaModuloFormBean.setAccion(items);

				redirectAttributes.addFlashAttribute("nombreSistema", sistemaModuloFormBean.getSistemaNombre());
				redirectAttributes.addFlashAttribute("nombreModulo", sistemaModuloFormBean.getModuloNombre());

				sistemaModuloFormBean.setActivarRoot(activar);

				model.addAttribute("opcionForm", sistemaModuloFormBean);

				model.addAttribute("activar", activar);

				populateDefaultModel(model);
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "opcionform/opcionForm";
	}

	// metodo que ejecuta acción de busqueda por Id o Código, para actualizar
	// registro
	@RequestMapping(value = "/opcionform/{codSistema},{codModulo},{id}", method = RequestMethod.GET)
	public String mostrarFormularioModulo(@PathVariable("codSistema") int codSistema,
			@PathVariable("codModulo") int codModulo, @PathVariable("id") int id, Model model,
			final RedirectAttributes redirectAttributes) throws RuntimeException {

		logger.info("visualizar Form con acciones() : {}", id);
		SistemaModuloFormBean sistemaModuloFormBean = null;
		try {
			if (SecurityUtil.hasRole("/opcionform")) {
				sistemaModuloFormBean = seguridadService.obtenerSistemaModuloFormPorCodigo(codSistema, codModulo, id);
				// Se carga lista de acciones de formulario todos
				List<AccionFormularioBean> listAcciones = seguridadService
						.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo, id);
				List<String> items = new ArrayList<String>();
				if (null != listAcciones) {
					for (AccionFormularioBean accionFormulario : listAcciones) {
						if (accionFormulario.getEstado() == 1) {
							items.add(accionFormulario.getCodigo().toString());
						}
					}
				}

				sistemaModuloFormBean.setAccion(items);

				redirectAttributes.addFlashAttribute("nombreSistema", sistemaModuloFormBean.getSistemaNombre());
				redirectAttributes.addFlashAttribute("nombreModulo", sistemaModuloFormBean.getModuloNombre());

				model.addAttribute("opcionForm", sistemaModuloFormBean);

				populateDefaultModel(model);
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
		}

		return "opcionform/mostrar";
	}

	@RequestMapping(value = "/opcionform/getModuloSistema", method = RequestMethod.GET)
	public @ResponseBody List<Modulo> getModuloSistemaList(@RequestParam("id") String codSistema)
			throws RuntimeException {

		List<SistemaModuloBean> lstModulo;
		List<Modulo> modulos = new ArrayList<Modulo>();
		try {
			lstModulo = seguridadService.obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(codSistema);

			for (SistemaModuloBean sistemaModuloBean : lstModulo) {
				modulos.add(
						new Modulo(sistemaModuloBean.getCodModulo().toString(), sistemaModuloBean.getDescripcion()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return modulos;
	}

	@RequestMapping(value = "/opcionform/getFormModuloSistema", method = RequestMethod.GET)
	public @ResponseBody SistemaModuloFormBean getFormularioModuloSistema(@RequestParam("value1") String codSistema,
			@RequestParam("value2") String codModulo, @RequestParam("value3") String codForm) throws RuntimeException {

		SistemaModuloFormBean formulario = null;
		try {
			formulario = seguridadService.obtenerSistemaModuloFormPorCodigo(codSistema, codModulo, codForm);

			formulario.setAccionFormulario(
					seguridadService.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo, codForm));

			// Se carga lista de acciones de formulario todos
			List<AccionFormularioBean> listAcciones = seguridadService
					.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo, codForm);
			List<String> items = new ArrayList<String>();
			if (null != listAcciones) {
				for (AccionFormularioBean accionFormulario : listAcciones) {
					if (accionFormulario.getEstado() == 1) {
						items.add(accionFormulario.getCodigo().toString());
					}
				}
			}

			formulario.setAccion(items);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return formulario;
	}

	private void populateListaModulo(Model model, int codSistema) throws RuntimeException {
		if (codSistema > 0) {
			List<SistemaModuloBean> lstModulo;
			Map<Integer, String> modulos = new HashMap<Integer, String>();

			try {
				lstModulo = seguridadService.obtenerListadoSistemaModuloPorCodigo(codSistema);
				for (SistemaModuloBean sistemaModuloBean : lstModulo) {
					modulos.put(sistemaModuloBean.getCodModulo(),
							sistemaModuloBean.getCodModulo() + " - " + sistemaModuloBean.getDescripcion());
				}

				model.addAttribute("modulos", modulos);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void populateDefaultModel(Model model) throws RuntimeException {
		List<SistemaBean> lstSistema;
		List<ParametroBean> lstEstados;
		List<ParametroBean> lstAcciones;
		List<Modulo> acciones = new ArrayList<Modulo>();
		Map<Integer, String> sistemas = new HashMap<Integer, String>();
		Map<Integer, String> estados = new HashMap<Integer, String>();

		try {

			lstSistema = seguridadService.obtenerListadoSistemasActivos();
			for (SistemaBean sistemaBean : lstSistema) {
				sistemas.put(sistemaBean.getCodSistema(),
						sistemaBean.getCodSistema() + " - " + sistemaBean.getDescripcion());
			}
			Map<Integer, String> treeMap = new TreeMap<Integer, String>(sistemas);
			model.addAttribute("sistemas", treeMap);

			lstAcciones = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ACCIONES);

			for (ParametroBean parametroBean : lstAcciones) {
				acciones.add(new Modulo(parametroBean.getCodigo().toString(), parametroBean.getDescripcion()));
			}

			model.addAttribute("acciones", acciones);

			lstEstados = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO);

			for (ParametroBean parametroBean : lstEstados) {
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

	// elimina registro por Id o Código
	@RequestMapping(value = "/opcionform/delete/{codSistema},{codModulo},{id}", method = RequestMethod.GET)
	public String eliminaModulo(@PathVariable("codSistema") int codSistema, @PathVariable("codModulo") int codModulo,
			@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes)
			throws RuntimeException {

		logger.info("elimina Formulario() : {}", id);
		List<SistemaModuloFormBean> lista = null;
		List<String> ids = new ArrayList<String>();
		List<String> idsAux = new ArrayList<String>();
		String in = "";
		//List<Integer> ids = new ArrayList<Integer>();
		//List<Integer> idsAux = new ArrayList<Integer>();
		//boolean continuar = false;
		try {
			if (SecurityUtil.hasRole("/opcionform")) {
				
				in = "" + id;
				idsAux.add( in);
				lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(codSistema, codModulo, in);
				while(!lista.isEmpty()){
					lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(codSistema, codModulo, in); 
					ids = new ArrayList<String>();
					for (SistemaModuloFormBean sistemaModuloFormBean : lista) {
						//System.out.println(sistemaModuloFormBean);
						ids.add("" + sistemaModuloFormBean.getCodFormulario());
						idsAux.add("" + sistemaModuloFormBean.getCodFormulario());
					}			
					in = "";			
					for (int i = 0; i < ids.size(); i++) {				
						in = in + ids.get(i) + ",";
						//System.out.println(in);
					}
					if(!in.equals("")){
						in = in.substring(0,in.length() - 1);
					}
					
				}
				
				for (int i = 0; i < idsAux.size(); i++) {								
					System.out.println(idsAux.get(i));
					this.seguridadService.actualizarEstadoSistemaModuloForm(codSistema, codModulo,
							idsAux.get(i), "0", SecurityUtil.getAuthenticationName());
				}
				
				
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operación");
			return "redirect:/opcionform";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Formulario Deshabilitado!");

		return "redirect:/opcionform";
	}

}
