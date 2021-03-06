package pe.com.sedapal.seguridad.web.controlador;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.reflect.TypeToken;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;
import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.ContratistaService;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.SistemaService;
import pe.com.sedapal.seguridad.web.bean.Perfiles;
import pe.com.sedapal.seguridad.web.bean.TablaDatosBean;
import pe.com.sedapal.seguridad.web.util.JsonUtil;
import pe.com.sedapal.seguridad.web.util.SecurityUtil;
import pe.com.sedapal.seguridad.web.validator.UsuarioValidator;

@Controller
public class UsuarioController {

	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

//	inicio adecuacion seguridad2
	@Autowired
	private SistemaService sistemaService;
	
	@Autowired
	private PerfilSistemaService perfilSistemaService;
//	fin adecuacion seguridad2
	
	@Autowired
	private SeguridadService seguridadService;
	
	@Autowired
	private ContratistaService contratistaService;

	@Autowired
	private UsuarioValidator usuarioValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(usuarioValidator);
	}

	// metodo que ejecuta acci?n de listado
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public String listarUsuario(Model model, final RedirectAttributes redirectAttributes) {

		try {
			if (SecurityUtil.hasRole("/usuario")) {
				
			} else {
				return "accesourl";
			}
			// model.addAttribute("usuarios",
			// seguridadService.obtenerListadoUsuarioSistema());
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
		}
		// return "usuario/lista";
		return "usuario/listaPaginacion";

	}

	// metodo que ejecuta acci?n de listado
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/usuario/getUsuarioListado", method = RequestMethod.GET)
	public @ResponseBody TablaDatosBean getUsuarioListado(HttpServletRequest request,
			@RequestParam Map<String,String> allRequestParams) throws RuntimeException {
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
		//search = param.getSearch();
		
		String strTipo="";
		String strSearch="";
		
		if(allRequestParams.get("cpTipo")!=null && !"".equals(allRequestParams.get("cpTipo"))) {
			strTipo = allRequestParams.get("cpTipo");
		}
		
		if(allRequestParams.get("cpSearch")!=null && !"".equals(allRequestParams.get("cpSearch"))) {
			strSearch = allRequestParams.get("cpSearch");
		}
		search = strTipo+"|"+strSearch;
		
		TablaDatosBean tablaDatos = null;

		int recordsTotal = 0;
		int recordsFiltered = 0;

		// Leer total de registros
		StringBuffer botones = null;
//		inicio adecuacion seguridad2
		StringBuffer enlaces = null;
//		fin adecuacion seguridad2
		List<String> permisos;
		try {
			if (SecurityUtil.hasRole("/usuario")) {
				int pageIndex = start / length;
//				inicio adecuacion seguridad2
//				recordsTotal = seguridadService.obtenerListadoUsuarioSistemaPaginarTotal(search);
				recordsTotal = seguridadService.obtenerListadoUsuarioSistemaPaginarTotalAct(search);
//				fin adecuacion seguridad2
				recordsFiltered = recordsTotal;
				logger.info("todo los perfiles()");

//				inicio adecuacion seguridad2
//				listDatos = seguridadService.obtenerListadoUsuarioSistemaPaginar(length, pageIndex, search, sortColumn);
				listDatos = seguridadService.obtenerListadoUsuarioSistemaPaginarAct(length, pageIndex, search);
//				fin adecuacion seguridad2

				tablaDatos = new TablaDatosBean();

				tablaDatos.setDraw(draw);
				tablaDatos.setRecordsFiltered(recordsFiltered);
				tablaDatos.setRecordsTotal(recordsTotal);
				permisos = (ArrayList<String>) request.getSession().getAttribute("permisos");
//				inicio adecuacion seguridad2
//				String[][] datos = new String[listDatos.size()][14];
				String[][] datos = new String[listDatos.size()][13];
//				fin adecuacion seguridad2
				int row = 0;

				for (UsuarioSistemaBean usuarioSistema : listDatos) {
					botones = new StringBuffer();
//					inicio adecuacion seguridad2
//					datos[row][0] = usuarioSistema.getCodSistema().toString();
//					datos[row][1] = usuarioSistema.getCodUsuario();
//					datos[row][2] = usuarioSistema.getSistemaNombre();
//					datos[row][3] = usuarioSistema.getDescripcion();
//					datos[row][4] = usuarioSistema.getCodFicha().toString();
//					datos[row][5] = usuarioSistema.getCodArea().toString();
//					datos[row][6] = usuarioSistema.getEstadoNombre().toString();
//					datos[row][7] = usuarioSistema.getAreaDescripcion().toString();
//					datos[row][8] = usuarioSistema.getAreaAbreviatura();
//					datos[row][9] = usuarioSistema.getCodAreaTrabajador().toString();
//					datos[row][10] = usuarioSistema.getNombrePerfil().toString();
//					datos[row][11] = usuarioSistema.getEstadoTrabajador().toString();
//					datos[row][12] = usuarioSistema.getCorreo().toString();
					
					datos[row][0] = usuarioSistema.getCodUsuario();
					datos[row][1] = usuarioSistema.getDescripcion();
					datos[row][2] = usuarioSistema.getCodFicha().toString();
					datos[row][3] = usuarioSistema.getCodArea().toString();
					datos[row][4] = usuarioSistema.getEstadoNombre().toString();
					datos[row][5] = usuarioSistema.getDesTipo();
					datos[row][6] = usuarioSistema.getAreaDescripcion().toString();
					datos[row][7] = usuarioSistema.getAreaAbreviatura();
					datos[row][8] = usuarioSistema.getCodAreaTrabajador().toString();
					datos[row][9] = usuarioSistema.getEstadoTrabajador().toString();
					datos[row][10] = usuarioSistema.getCorreo().toString();
//					fin adecuacion seguridad2
					
//					inicio adecuacion seguridad2
					enlaces = new StringBuffer();
					if (permisos.contains("/usuarioVISUALIZAR")) {
						enlaces.append(
								"<sed:seguridad accion=\"/usuarioVISUALIZAR\"><a class=\"close-image btn btn-default btn-detail\">Relaciones</a></sed:seguridad>");
//								"<sed:seguridad accion=\"/usuarioVISUALIZAR\"><a class=\"close-image btn\" onclick=\"verModalDetalleUsuario('hola')\">Relaciones</a></sed:seguridad>");
					}
					datos[row][11] = enlaces.toString();
//					fin adecuacion seguridad2

					if (permisos.contains("/usuarioVISUALIZAR")) {
						botones.append(
								"<sed:seguridad accion=\"/usuarioVISUALIZAR\"><button class=\"close-image btn btn-default btn-view\" onclick=\"location.href=${verUrl}\"> <img src=\"resources/core/images/buscar.png\"></button></sed:seguridad>");
					}
					if (permisos.contains("/usuarioMODIFICAR")) {
						botones.append(
								"<sed:seguridad accion=\"/usuarioMODIFICAR\"><button class=\"close-image btn btn-default btn-update\" onclick=\"location.href=${updateUrl}\"> <img src=\"resources/core/images/edit-icon.png\"></button></sed:seguridad>");
					}

					if (permisos.contains("/usuarioELIMINAR") && usuarioSistema.getEstado().intValue() == 1) {
						botones.append(
								"<sed:seguridad accion=\"/usuarioELIMINAR\"><button class=\"close-image btn btn-default btn-delete\" onclick=\"location.href=${deleteUrl}\"> <img src=\"resources/core/images/eliminar.png\"></button></sed:seguridad>");
					}

					if (usuarioSistema.getEstado().equals(Constants.ESTADO_BLOQUEADO)
							&& permisos.contains("/usuarioDESBLOQUEAR")) {
						botones.append(
								"<button class=\"close-image btn btn-default btn-blocked\" onclick=\"location.href=${desbloquearUrl}\"> <img src=\"resources/core/images/blocked.jpg\"></button>");
					} else if (usuarioSistema.getEstado().equals(Constants.ESTADO_ACTIVO)) {
						botones.append(
								"<button class=\"close-image btn btn-default btn-active\" onclick=\"location.href=#\"> <img src=\"resources/core/images/active.jpg\"></button>");
					} else if (usuarioSistema.getEstado().equals(Constants.ESTADO_INACTIVO)
							&& permisos.contains("/usuarioACTIVAR")) {
						botones.append(
								"<button class=\"close-image btn btn-default btn-disabled\" onclick=\"location.href=${activarUrl}\"> <img src=\"resources/core/images/disabled.png\"></button>");
					}
					/*
					 * botones.append(
					 * "<button class=\"close-image btn btn-default btn-acciones\" onclick=\"location.href=${accionesUrl}\"> <img src=\"resources/core/images/acciones.png\"></button>"
					 * );
					 */
//					inicio adecuacion seguridad2
//					datos[row][13] = botones.toString();
					datos[row][12] = botones.toString();
//					fin adecuacion seguridad2
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
	
	// metodo que ejecuta acci?n de grabar o actualizar
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public String grabarOActualizarUsuario(@ModelAttribute("usuarioForm") @Validated UsuarioBean usuarioBean,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) throws RuntimeException {
		// ,@RequestParam("trabajador-datos") String datos) {

		logger.info("grabar o actualizar aplicacion() : {}", usuarioBean);
		UsuarioBean usuariobkp = null;
		TrabajadorBean trabajadorBean;
		boolean isUpdate = Boolean.FALSE;
		if (result.hasErrors()) {
			populateDefaultModel(model);
//			inicio adecuacion seguridad2
//			populateListaPerfil(model, usuarioBean.getCodSistema());
			adicionParametrosModel(model, usuarioBean.getCodUsuario());
//			fin adecuacion seguridad2
			usuarioBean.setEstado(Constants.ESTADO_ACTIVO);
			model.addAttribute("usuarioBean", usuarioBean);
			return "usuario/usuarioForm";
		} else {
			if (SecurityUtil.hasRole("/usuario")) {
				try {
					redirectAttributes.addFlashAttribute("css", "success");
					String usuario = SecurityUtil.getAuthenticationName();
//					inicio adecuacion seguridad2
//					usuariobkp = seguridadService.obtenerUsuarioPorCodUsuario(usuarioBean.getCodUsuario());
					usuariobkp = seguridadService.obtenerUsuarioPorCodUsuarioAct(usuarioBean.getCodUsuario());
					Type listType = new TypeToken<List<UsuarioPerfilBean>>() {}.getType();
					List<UsuarioPerfilBean> asociaciones = JsonUtil.convertirCadenaJsonALista(usuarioBean.getSistemasPerfilesAsociadosJSON(), listType);
//					fin adecuacion seguridad2
					
					if (usuarioBean.isNew()) {
						redirectAttributes.addFlashAttribute("msg", "Se Registr? Satisfactoriamente!");
						usuarioBean.setResponsable(SecurityUtil.getAuthenticationName());
						usuarioBean.setCodUsuario(usuarioBean.getIdUsuario());
						usuarioBean.setEstado(Constants.ESTADO_ACTIVO);
						usuarioBean.setNombreDoc(file.getOriginalFilename());
						usuarioBean.setBytesDoc(file.getBytes());
						
						double bytes = file.getSize();
						double kilobytes = (bytes / 1024);
						//double megabytes = (kilobytes / 1024);
						
						if(kilobytes > 2048){
							redirectAttributes.addFlashAttribute("css", "danger");
							redirectAttributes.addFlashAttribute("msg",
									"Error al realizar la operaci?n. Tama?o del archivo sobrepasa el permitido 2M.");
							model.asMap().put("css", "danger");
							model.asMap().put("msg", "Error al realizar la operaci?n. Tama?o del archivo sobrepasa el permitido 2M.");							
							if (!isUpdate) {
								usuarioBean.setCodUsuario(null);
							}							
							populateDefaultModel(model);
//							inicio adecuacion seguridad2
//							populateListaPerfil(model, usuarioBean.getCodSistema());
							adicionParametrosModel(model, usuarioBean.getCodUsuario());
//							fin adecuacion seguridad2
							return "usuario/usuarioForm";
						} else if((new Double(kilobytes)).intValue() == 0){
							redirectAttributes.addFlashAttribute("css", "danger");
							redirectAttributes.addFlashAttribute("msg",
									"Error al realizar la operaci?n. Debe adjuntar un archivo.");
							model.asMap().put("css", "danger");
							model.asMap().put("msg", "Error al realizar la operaci?n. Debe adjuntar un archivo.");							
							if (!isUpdate) {
								usuarioBean.setCodUsuario(null);
							}							
							populateDefaultModel(model);
//							inicio adecuacion seguridad2
//							populateListaPerfil(model, usuarioBean.getCodSistema());
							adicionParametrosModel(model, usuarioBean.getCodUsuario());
//							fin adecuacion seguridad2
							return "usuario/usuarioForm";
						}
						
						if (usuariobkp != null) {
							redirectAttributes.addFlashAttribute("css", "danger");
							redirectAttributes.addFlashAttribute("msg",
									"Error al realizar la operaci?n. Usuario ingresado ya existe.");
							populateDefaultModel(model);
//							inicio adecuacion seguridad2
//							populateListaPerfil(model, usuarioBean.getCodSistema());
							adicionParametrosModel(model, usuarioBean.getCodUsuario());
//							fin adecuacion seguridad2
							return "usuario/usuarioForm";
						}
//						inicio adecuacion seguridad2
//						seguridadService.grabarUsuario(usuarioBean);
						seguridadService.grabarUsuarioAct(usuarioBean, asociaciones);
						
//						UsuarioPerfilBean usuarioPerfilBean = new UsuarioPerfilBean();
//						usuarioPerfilBean.setEstado(Constants.ESTADO_ACTIVO);
//						usuarioPerfilBean.setUsuarioCreacion(usuario);
//						usuarioPerfilBean.setCodSistema(usuarioBean.getCodSistema());
//						usuarioPerfilBean.setCodPerfil(usuarioBean.getCodPerfil());
//						usuarioPerfilBean.setCodUsuario(usuarioBean.getCodUsuario());
//						seguridadService.grabarUsuarioPerfilSistema(usuarioPerfilBean);
						
						for(UsuarioPerfilBean asociacion : asociaciones) {
							asociacion.setEstado(Constants.ESTADO_ACTIVO);
							asociacion.setUsuarioCreacion(usuario);
							asociacion.setCodUsuario(usuarioBean.getCodUsuario());
							seguridadService.grabarUsuarioPerfilSistema(asociacion);
							seguridadService.actualizarAuditoriaUsuario(asociacion, usuarioBean);
						}
//						fin adecuacion seguridad2
					} else {
						redirectAttributes.addFlashAttribute("msg", "Se Actualiz? Satisfactoriamente!");
						isUpdate = Boolean.TRUE;
//						inicio adecuacion seguridad2
//						usuariobkp = seguridadService.obtenerUsuarioPorCodUsuario(usuarioBean.getCodUsuario());
						usuariobkp = seguridadService.obtenerUsuarioPorCodUsuarioAct(usuarioBean.getCodUsuario());
//						fin adecuacion seguridad2
						usuariobkp.setEstado(usuarioBean.getEstado());
						if(usuarioBean.getCodFicha() !=null && usuarioBean.getCodFicha().intValue() !=0){
							trabajadorBean = seguridadService.obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
							usuariobkp.setCodArea(trabajadorBean.getnCodArea());
							usuariobkp.setDescripcion(trabajadorBean.getvApePaterno() + " " + trabajadorBean.getvApeMaterno() + " " + trabajadorBean.getvNombres());
						}
						
						usuariobkp.setCodFicha(usuarioBean.getCodFicha());
						usuariobkp.setResponsable(SecurityUtil.getAuthenticationName());
						usuariobkp.setSustentacion(usuarioBean.getSustentacion());
						usuariobkp.setDoc(usuarioBean.getDoc());
						usuariobkp.setNombreDoc(file.getOriginalFilename());
						usuariobkp.setBytesDoc(file.getBytes());
						
						/*add tipo usuario parameters*/
						usuariobkp.setCodEmpresa(usuarioBean.getCodEmpresa());
						usuariobkp.setNombreExterno(usuarioBean.getNombreExterno());
						usuariobkp.setDniExterno(usuarioBean.getDniExterno());
						usuariobkp.setEmailExterno(usuarioBean.getEmailExterno());
						usuariobkp.setFlagBloqueo(usuarioBean.getFlagBloqueo());
						/**/
						
						double bytes = file.getSize();
						double kilobytes = (bytes / 1024);
						//double megabytes = (kilobytes / 1024);
						File archivo = new File(seguridadService.obtenerParametroPorCodigoParametro(Constants.RUTADOC).getValor() + usuariobkp.getDoc());
						
						if(!archivo.exists()){
							if(kilobytes > 2048){
								redirectAttributes.addFlashAttribute("css", "danger");
								redirectAttributes.addFlashAttribute("msg",
										"Error al realizar la operaci?n. Tama?o del archivo sobrepasa el permitido 2M.");
								model.asMap().put("css", "danger");
								model.asMap().put("msg", "Error al realizar la operaci?n. Tama?o del archivo sobrepasa el permitido 2M.");							
								if (!isUpdate) {
									usuarioBean.setCodUsuario(null);
								}							
								populateDefaultModel(model);
//								inicio adecuacion seguridad2
//								populateListaPerfil(model, usuarioBean.getCodSistema());
								adicionParametrosModel(model, usuarioBean.getCodUsuario());
//								fin adecuacion seguridad2
								return "usuario/usuarioForm";
							} else if((new Double(kilobytes)).intValue() == 0){
								redirectAttributes.addFlashAttribute("css", "danger");
								redirectAttributes.addFlashAttribute("msg",
										"Error al realizar la operaci?n. Debe adjuntar un archivo.");
								model.asMap().put("css", "danger");
								model.asMap().put("msg", "Error al realizar la operaci?n. Debe adjuntar un archivo.");							
								if (!isUpdate) {
									usuarioBean.setCodUsuario(null);
								}							
								populateDefaultModel(model);
//								inicio adecuacion seguridad2
//								populateListaPerfil(model, usuarioBean.getCodSistema());
								adicionParametrosModel(model, usuarioBean.getCodUsuario());
//								fin adecuacion seguridad2
								return "usuario/usuarioForm";
							}
						}
						
//						inicio adecuacion seguridad2
//						seguridadService.actualizarUsuario(usuariobkp);
						seguridadService.actualizarUsuarioAct(usuariobkp);
						
//						UsuarioPerfilBean upb = seguridadService.obtenerUsuarioPerfilSistemaPorCodigo(
//								usuariobkp.getCodPerfil(), usuariobkp.getCodSistema(), usuariobkp.getCodUsuario());
						List<UsuarioPerfilBean> upb = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(usuariobkp.getCodUsuario());
						if (upb != null && upb.size() != Constants.EMPTY_VALUE) {
							seguridadService.eliminarAsociacionesActuales(usuariobkp.getCodUsuario());
//							seguridadService.actualizarEstadoUsuarioPerfilSistema(usuarioBean.getCodPerfil(),
//									usuarioBean.getCodSistema(), usuarioBean.getCodUsuario(), 1, usuario);

						} 
//						else {
//							UsuarioPerfilBean usuarioPerfilBean = new UsuarioPerfilBean();
//							usuarioPerfilBean.setEstado(Constants.ESTADO_ACTIVO);
//							usuarioPerfilBean.setUsuarioCreacion(usuario);
//							usuarioPerfilBean.setCodSistema(usuarioBean.getCodSistema());
//							usuarioPerfilBean.setCodPerfil(usuarioBean.getCodPerfil());
//							usuarioPerfilBean.setCodUsuario(usuarioBean.getCodUsuario());
//							seguridadService.grabarUsuarioPerfilSistema(usuarioPerfilBean);
//						}
						for(UsuarioPerfilBean asociacion : asociaciones) {
							asociacion.setEstado(Constants.ESTADO_ACTIVO);
							asociacion.setUsuarioCreacion(usuario);
							asociacion.setCodUsuario(usuarioBean.getCodUsuario());
							seguridadService.grabarUsuarioPerfilSistema(asociacion);
							seguridadService.actualizarAuditoriaUsuario(asociacion, usuariobkp);
						}
//						fin adecuacion seguridad2
					}
				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("css", "danger");
					redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n.");
					model.asMap().put("css", "danger");
					model.asMap().put("msg", "Error al realizar la operaci?n " + e.getMessage());
					populateDefaultModel(model);
//					inicio adecuacion seguridad2
//					populateListaPerfil(model, usuarioBean.getCodSistema());
					adicionParametrosModel(model, usuarioBean.getCodUsuario());
//					fin adecuacion seguridad2
					if (!isUpdate) {
						usuarioBean.setCodUsuario(null);
					}
					model.addAttribute("usuarioBean", usuarioBean);
					return "usuario/usuarioForm";
				}
			} else {
				return "accesourl";
			}

			return "redirect:/usuario";
		}

	}

	// metodo que ejecuta acci?n de nuevo registro
	@RequestMapping(value = "/usuario/add", method = RequestMethod.GET)
	public String nuevoUsuarioForm(Model model, //
			@RequestParam(value = "url", defaultValue = "") String urlReturn,
			final RedirectAttributes redirectAttributes) {

		if (SecurityUtil.hasRole("/usuario")) {
			redirectAttributes.addFlashAttribute("css", "");
			redirectAttributes.addFlashAttribute("msg", "");
			logger.info("agregar formulario()");

			UsuarioBean usuarioBean = new UsuarioBean();
			usuarioBean.setIndicador(Constants.ESTADO_INDICADOR_D);
			usuarioBean.setEstado(Constants.ESTADO_ACTIVO);
			populateDefaultModel(model);
			model.addAttribute("usuarioForm", usuarioBean);
			adicionParametrosModel(model, usuarioBean.getCodUsuario());
			
		} else {
			return "accesourl";
		}

		return "usuario/usuarioForm";
	}

	// metodo que ejecuta acci?n de busqueda por Id o C?digo, para actualizar
	// registro
//	inicio adecuacion seguridad2
//	@RequestMapping(value = "/usuario/update/{codigo},{id}", method = RequestMethod.GET)
//	public String visualizarUsuarioPerfilForm(@PathVariable("codigo") int codSistema, @PathVariable("id") String id,
//			Model model, final RedirectAttributes redirectAttributes) {
//
//		logger.info("visualizarUsuarioPerfilForm() : {}", id);
//
//		try {
//			if (SecurityUtil.hasRole("/usuario")) {
//				List<UsuarioPerfilBean> listUsuarioPerfil = seguridadService
//						.obtenerListadoUsuarioPerfilSistemaPorUsuario(codSistema, id);
//
//				int sistema = 0;
//				String sistemaNombre = null;
//
//				UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioCodSistema(id, codSistema);
//				if (usuarioBean != null) {
//					usuarioBean.setIdUsuario(usuarioBean.getCodUsuario());
//
//					for (UsuarioPerfilBean usuarioPerfil : listUsuarioPerfil) {
//
//						sistema = usuarioPerfil.getCodSistema();
//						sistemaNombre = usuarioPerfil.getSistemaNombre();
//						usuarioBean.setCodPerfil(usuarioPerfil.getCodPerfil());
//						usuarioBean.setPerfilNombre(usuarioPerfil.getPerfilNombre());
//					}
//					if(sistema != 0){
//						usuarioBean.setCodSistema(sistema);	
//					}
//					if(sistemaNombre != null ){
//						usuarioBean.setSistemaNombre(sistemaNombre);	
//					}
//					
//					
//				} else {
//					usuarioBean = new UsuarioBean();
//				}
//
//				populateDefaultModel(model);
//				populateListaPerfil(model, usuarioBean.getCodSistema());
//				model.addAttribute("usuarioForm", usuarioBean);
//			} else {
//				return "accesourl";
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("css", "danger");
//			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
//		}
//
//		return "usuario/usuarioForm";
//	}
	@RequestMapping(value = "/usuario/update/{codUsuario}", method = RequestMethod.GET)
	public String visualizarUsuarioPerfilForm(@PathVariable("codUsuario") String codUsuario, Model model, final RedirectAttributes redirectAttributes) {

		logger.info("editarUsuarioForm() : {}", codUsuario);

		try {
			if (SecurityUtil.hasRole("/usuario")) {
				List<UsuarioPerfilBean> listUsuarioPerfil = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
				
				List<SistemaBean> sistemasAsociados = new ArrayList<SistemaBean>();
				List<PerfilSistemaBean> perfilesAsociados = new ArrayList<PerfilSistemaBean>();
				
				UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
				if (usuarioBean != null) {
					usuarioBean.setIdUsuario(usuarioBean.getCodUsuario());

					for (UsuarioPerfilBean usuarioPerfil : listUsuarioPerfil) {
						SistemaBean sistema = new SistemaBean();
						PerfilSistemaBean perfil = new PerfilSistemaBean();
						sistema.setCodSistema(usuarioPerfil.getCodSistema());
						sistema.setDescripcion(usuarioPerfil.getSistemaNombre());
						sistema.setAbreviatura(usuarioPerfil.getAbreviaturaSistema());
						perfil.setCodPerfil(usuarioPerfil.getCodPerfil());
						perfil.setDescripcion(usuarioPerfil.getPerfilNombre());
						perfil.setCodSistema(usuarioPerfil.getCodSistema());
						if(verificarSistema(sistemasAsociados, sistema.getCodSistema())){
							sistemasAsociados.add(sistema);
						}
						perfilesAsociados.add(perfil);
					}
					if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
						usuarioBean.setSistemasAsociados(sistemasAsociados);
					}
					if(perfilesAsociados.size() > Constants.EMPTY_VALUE) {
						usuarioBean.setPerfilesAsociados(perfilesAsociados);
					}
					model.addAttribute("modo", JsonUtil.convertirObjetoACadenaJson(Constants.EDITAR));
				}else {
					usuarioBean = new UsuarioBean();
					model.addAttribute("modo", JsonUtil.convertirObjetoACadenaJson(Constants.NUEVO));
				}
				model.addAttribute("usuarioForm", usuarioBean);
				model.addAttribute("sistemasAsociados", JsonUtil.convertirObjetoACadenaJson(sistemasAsociados));
				model.addAttribute("perfilesAsociados", JsonUtil.convertirObjetoACadenaJson(perfilesAsociados));
				model.addAttribute("sistemasTotal", JsonUtil.convertirObjetoACadenaJson(seguridadService.obtenerListadoSistemasActivos()));
				populateDefaultModel(model);
				populateListaPerfil(model, usuarioBean.getCodSistema());
			} else {
				return "accesourl";
			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
		}

		return "usuario/usuarioForm";
	}

	private void adicionParametrosModel(Model model, String codUsuario) throws RuntimeException {
		List<SistemaBean> sistemasAsociados = new ArrayList<SistemaBean>();
		List<PerfilSistemaBean> perfilesAsociados = new ArrayList<PerfilSistemaBean>();
		try {
			
			List<UsuarioPerfilBean> listUsuarioPerfil = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
			UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
			if (usuarioBean != null) {
				usuarioBean.setIdUsuario(usuarioBean.getCodUsuario());

				for (UsuarioPerfilBean usuarioPerfil : listUsuarioPerfil) {
					SistemaBean sistema = new SistemaBean();
					PerfilSistemaBean perfil = new PerfilSistemaBean();
					sistema.setCodSistema(usuarioPerfil.getCodSistema());
					sistema.setDescripcion(usuarioPerfil.getSistemaNombre());
					sistema.setAbreviatura(usuarioPerfil.getAbreviaturaSistema());
					perfil.setCodPerfil(usuarioPerfil.getCodPerfil());
					perfil.setDescripcion(usuarioPerfil.getPerfilNombre());
					perfil.setCodSistema(usuarioPerfil.getCodSistema());
					if(verificarSistema(sistemasAsociados, sistema.getCodSistema())){
						sistemasAsociados.add(sistema);
					}
					perfilesAsociados.add(perfil);
				}
				if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
					usuarioBean.setSistemasAsociados(sistemasAsociados);
				}
				if(perfilesAsociados.size() > Constants.EMPTY_VALUE) {
					usuarioBean.setPerfilesAsociados(perfilesAsociados);
				}
				model.addAttribute("modo", JsonUtil.convertirObjetoACadenaJson(Constants.EDITAR));
			}else {
				usuarioBean = new UsuarioBean();
				model.addAttribute("modo", JsonUtil.convertirObjetoACadenaJson(Constants.NUEVO));
			}
			model.addAttribute("sistemasAsociados", JsonUtil.convertirObjetoACadenaJson(sistemasAsociados));
			model.addAttribute("perfilesAsociados", JsonUtil.convertirObjetoACadenaJson(perfilesAsociados));
			model.addAttribute("sistemasTotal", JsonUtil.convertirObjetoACadenaJson(seguridadService.obtenerListadoSistemasActivos()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
//	fin adecuacion seguridad2

	// elimina registro por Id o C?digo
//	inicio adecuacion seguridad2
//	@RequestMapping(value = "/usuario/delete/{codigo},{id}", method = RequestMethod.GET)
//	public String eliminaUsuarioPerfilForm(@PathVariable("codigo") int codSistema, @PathVariable("id") String id,
//			Model model, final RedirectAttributes redirectAttributes) {
	@RequestMapping(value = "/usuario/delete/{codUsuario}", method = RequestMethod.GET)
	public String eliminaUsuarioPerfilForm(@PathVariable("codUsuario") String codUsuario, Model model, final RedirectAttributes redirectAttributes) {	
//	fin adecuacion seguridad2
		logger.info("elimina Usuario() : {}", codUsuario);

		try {
			if (SecurityUtil.hasRole("/usuario")) {
//				inicio adecuacion seguridad2
//				seguridadService.actualizarUsuarioEstadoPorCodUsuario(id, Constants.ESTADO_INACTIVO,
//						SecurityUtil.getAuthenticationName(), codSistema);
				seguridadService.actualizarUsuarioEstadoPorCodUsuarioAct(codUsuario, Constants.ESTADO_INACTIVO, SecurityUtil.getAuthenticationName());
				UsuarioBean usuario = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
				List<UsuarioPerfilBean> asociaciones = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
				for(UsuarioPerfilBean asociacion : asociaciones) {
					asociacion.setEstado(Constants.ESTADO_ACTIVO);
					asociacion.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
					asociacion.setCodUsuario(codUsuario);
					seguridadService.actualizarAuditoriaUsuario(asociacion, usuario);
				}
//				fin adecuacion seguridad2
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
			return "redirect:/usuario";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Usuario Deshabilitado!");

		return "redirect:/usuario";
	}

	// bloquea registro por Id o C?digo
//	inicio adecuacion seguridad2
	@RequestMapping(value = "/usuario/desbloquear/{id},{codSistema}", method = RequestMethod.GET)
	public String bloqueUsuarioForm(@PathVariable("id") String id, @PathVariable("codSistema") String codSistema,
			Model model, final RedirectAttributes redirectAttributes) {
//	@RequestMapping(value = "/usuario/desbloquear/{codUsuario}", method = RequestMethod.GET)
//	public String bloqueUsuarioForm(@PathVariable("codUsuario") String codUsuario, Model model, final RedirectAttributes redirectAttributes) {
//	fin adecuacion seguridad2

		logger.info("bloquea Usuario() : {}", id);

		try {
			if (SecurityUtil.hasRole("/usuario")) {
//				inicio adecuacion seguridad2
				seguridadService.bloqueoUsuarioEstadoPorCodUsuario(id, Constants.ESTADO_ACTIVO, Constants.ESTADO_INACTIVO,
						SecurityUtil.getAuthenticationName(), codSistema);
////				seguridadService.bloqueoUsuarioEstadoPorCodUsuarioAct(codUsuario, Constants.ESTADO_ACTIVO, Constants.ESTADO_INACTIVO,
//						SecurityUtil.getAuthenticationName());
//				fin adecuacion seguridad2
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
			return "redirect:/usuario";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Usuario desbloqueado!");

		return "redirect:/usuario";
	}

	// activa registro por Id o C?digo
//	inicio adecuacion seguridad2
//	@RequestMapping(value = "/usuario/activar/{id},{codSistema}", method = RequestMethod.GET)
//	public String activaUsuarioForm(@PathVariable("id") String id, @PathVariable("codSistema") String codSistema,
//			Model model, final RedirectAttributes redirectAttributes) {
	@RequestMapping(value = "/usuario/activar/{codUsuario}", method = RequestMethod.GET)
	public String activaUsuarioForm(@PathVariable("codUsuario") String codUsuario, Model model, final RedirectAttributes redirectAttributes) {	
//	fin adecuacion seguridad2	

		logger.info("activar Usuario() : {}", codUsuario);

		try {
			if (SecurityUtil.hasRole("/usuario")) {
//				inicio adecuacion seguridad2
//				seguridadService.actualizarUsuarioEstadoPorCodUsuario(id, Constants.ESTADO_ACTIVO,
//						SecurityUtil.getAuthenticationName(), codSistema);
				seguridadService.actualizarUsuarioEstadoPorCodUsuarioAct(codUsuario, Constants.ESTADO_ACTIVO,
						SecurityUtil.getAuthenticationName());
				UsuarioBean usuario = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
				List<UsuarioPerfilBean> asociaciones = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
				for(UsuarioPerfilBean asociacion : asociaciones) {
					asociacion.setEstado(Constants.ESTADO_ACTIVO);
					asociacion.setUsuarioCreacion(SecurityUtil.getAuthenticationName());
					asociacion.setCodUsuario(codUsuario);
					seguridadService.actualizarAuditoriaUsuario(asociacion, usuario);
				}
//				fin adecuacion seguridad2
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
			return "redirect:/usuario";
		}

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Usuario Activado!");

		return "redirect:/usuario";
	}

	// metodo que ejecuta acci?n de mostrar registro
//	inicio adecuacion seguridad2
//	@RequestMapping(value = "/usuario/{codigo},{id}", method = RequestMethod.GET)
//	public String mostrarUsuario(@PathVariable("codigo") int codSistema, @PathVariable("id") String id, Model model,
//			final RedirectAttributes redirectAttributes) {
	@RequestMapping(value = "/usuario/{codigo}", method = RequestMethod.GET)
	public String mostrarUsuario(@PathVariable("codigo") String codUsuario, Model model, final RedirectAttributes redirectAttributes) {
		logger.info("mostrarModulo() codUsuario: {}", codUsuario);
//		fin adecuacion seguridad2
		try {
			if (SecurityUtil.hasRole("/usuario")) {
//				inicio adecuacion seguridad2
//				List<UsuarioPerfilBean> listUsuarioPerfil = seguridadService
//						.obtenerListadoUsuarioPerfilSistemaPorUsuario(codSistema, id);
				List<UsuarioPerfilBean> listUsuarioPerfil = seguridadService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
				
//				int sistema = 0;
//				String sistemaNombre = null;
				List<SistemaBean> sistemasAsociados = new ArrayList<SistemaBean>();
				List<PerfilSistemaBean> perfilesAsociados = new ArrayList<PerfilSistemaBean>();

//				UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuario(id);
				UsuarioBean usuarioBean = seguridadService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
//				fin adecuacion seguridad2
				usuarioBean.setIdUsuario(usuarioBean.getCodUsuario());

				for (UsuarioPerfilBean usuarioPerfil : listUsuarioPerfil) {
//					inicio adecuacion seguridad2
//					sistema = usuarioPerfil.getCodSistema();
//					sistemaNombre = usuarioPerfil.getSistemaNombre();
//					usuarioBean.setCodPerfil(usuarioPerfil.getCodPerfil());
					SistemaBean sistema = new SistemaBean();
					PerfilSistemaBean perfil = new PerfilSistemaBean();
					sistema.setCodSistema(usuarioPerfil.getCodSistema());
					sistema.setDescripcion(usuarioPerfil.getSistemaNombre());
					sistema.setAbreviatura(usuarioPerfil.getAbreviaturaSistema());
					perfil.setCodPerfil(usuarioPerfil.getCodPerfil());
					perfil.setDescripcion(usuarioPerfil.getPerfilNombre());
					perfil.setCodSistema(usuarioPerfil.getCodSistema());
					if(verificarSistema(sistemasAsociados, sistema.getCodSistema())){
						sistemasAsociados.add(sistema);
					}
					perfilesAsociados.add(perfil);
				}

//				if(sistema != 0){
//					usuarioBean.setCodSistema(sistema);	
//				}
//				if(sistemaNombre != null ){
//					usuarioBean.setSistemaNombre(sistemaNombre);	
//				}
				if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
					usuarioBean.setSistemasAsociados(sistemasAsociados);
				}
				
				if(perfilesAsociados.size() > Constants.EMPTY_VALUE) {
					usuarioBean.setPerfilesAsociados(perfilesAsociados);
				}
				
//				fin adecuacion seguridad2
				model.addAttribute("usuarioForm", usuarioBean);
				model.addAttribute("sistemasAsociados", JsonUtil.convertirObjetoACadenaJson(sistemasAsociados));
				model.addAttribute("perfilesAsociados", JsonUtil.convertirObjetoACadenaJson(perfilesAsociados));
				populateDefaultModel(model);
//				inicio adecuacion seguridad2
//				populateListaPerfil(model, usuarioBean.getCodSistema());
//				fin adecuacion seguridad2
			} else {
				return "accesourl";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "Error al realizar la operaci?n");
		}

		return "usuario/mostrar";
	}
	
//	inicio adecuacion seguridad2
	private boolean verificarSistema(List<SistemaBean> sistemasAsociados, Integer codSistema) {
		boolean flag = true;
		if(sistemasAsociados.size() > Constants.EMPTY_VALUE) {
			for(SistemaBean sistema : sistemasAsociados) {
				if(sistema.getCodSistema() == codSistema) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
//	fin adecuacion seguridad2

	public class CboSistemas {
		private String id;
		private String value;
		private String label;

		public CboSistemas() {
		}

		public CboSistemas(String id, String value, String label) {
			this.id = id;
			this.value = value;
			this.label = label;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	}

	private void populateDefaultModel(Model model) throws RuntimeException {

		List<SistemaBean> lstSistema;
		Map<Integer, String> estados = new HashMap<Integer, String>();
		Map<Integer, String> tipos = new HashMap<Integer, String>();
		Map<Integer, String> bloqueos = new HashMap<Integer, String>();
		List<ParametroBean> parametros;
		try {
			/*adicion de sistemas*/
			lstSistema = seguridadService.obtenerListadoSistemasActivos();
			List<CboSistemas> selectItems = new ArrayList<CboSistemas>();
			for (SistemaBean sistemaBean : lstSistema) {
				selectItems.add(new CboSistemas(sistemaBean.getAbreviatura(), sistemaBean.getCodSistema().toString(),
						sistemaBean.getCodSistema().toString() + " - " + sistemaBean.getDescripcion()));
			}
			model.addAttribute("sistemas", selectItems);
			/*adicion de estados*/
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.ESTADO);
			for (ParametroBean parametroBean : parametros) {
				estados.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("estados", estados);
			/*adicion de tipos de usuario*/			
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.TIPO_USUARIO);
			for (ParametroBean parametroBean : parametros) {
				tipos.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("tipos", tipos);
			/*adicion de flag bloqueo*/
			parametros = seguridadService.obtenerListadoParametrosPorCodigoParametro(Constants.FLAG_BLOQUEO);
			for (ParametroBean parametroBean : parametros) {
				bloqueos.put(parametroBean.getId(), parametroBean.getDescripcion());
			}
			model.addAttribute("bloqueos", bloqueos);
			/*adicion lista contratistas*/
			model.addAttribute("listaContratistas", contratistaService.listarContratistas());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	private void populateListaPerfil(Model model, int codSistema) throws RuntimeException {
		try {
			List<PerfilSistemaBean> lstPerfil;
			Map<Integer, String> perfiles = new HashMap<Integer, String>();
			if (codSistema > 0) {

				lstPerfil = seguridadService.obtenerListadoPerfilSistemaPorCodigo(codSistema);

				for (PerfilSistemaBean perfilSistemaBean : lstPerfil) {
					perfiles.put(perfilSistemaBean.getCodPerfil(),
							perfilSistemaBean.getCodPerfil() + " - " + perfilSistemaBean.getDescripcion() + " - " +  perfilSistemaBean.getEstadoNombre());
				}

				model.addAttribute("perfiles", perfiles);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/usuario/getPerfilSistema", method = RequestMethod.GET)
	public @ResponseBody List<Perfiles> getPerfilSistemaList(@RequestParam("id") String codSistema)
			throws RuntimeException {

		List<PerfilSistemaBean> lstPerfil;
		List<Perfiles> perfiles = new ArrayList<Perfiles>();
		try {
			if ("-1".equals(codSistema)) {
				codSistema = null;
			}
			lstPerfil = seguridadService.obtenerListadoPerfilSistemaPorCodigoActivos(codSistema);
			for (PerfilSistemaBean perfilBean : lstPerfil) {
				perfiles.add(new Perfiles(perfilBean.getCodPerfil().toString(), perfilBean.getDescripcion()));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return perfiles;
	}

	@RequestMapping(value = "/usuario/getFichaTrabajador", method = RequestMethod.GET)
	public @ResponseBody TrabajadorBean getFichaTrabajador(@RequestParam("id") String codFicha)
			throws RuntimeException {

		TrabajadorBean trabajador = null;
		String urlValor = "";
		byte[] data = null;
		URL url = null;
		String credentials;
		//String usuario;
		//String clave;
		try {
			trabajador = seguridadService.obtenerTrabajadorPorFicha(codFicha);
			if (trabajador != null) {
				urlValor = seguridadService.obtenerParametroPorCodigoParametro(Constants.RUTAFOTOS).getValor();
				//usuario = seguridadService.obtenerParametroPorCodigoParametro(Constants.USUARIO_SERVIDOR).getValor();
				//clave = seguridadService.obtenerParametroPorCodigoParametro(Constants.CLAVE_SERVIDOR).getValor();
				//credentials = usuario + ":" + clave;
				urlValor = urlValor + trabajador.getvDni() + ".jpg";
				url = new URL(urlValor);
				//String encoding = new String(Base64.encodeBase64(credentials.getBytes("UTF-8")));
				URLConnection uc = url.openConnection();
				//uc.setRequestProperty("Authorization", String.format("Basic %s", encoding));
				data = IOUtils.toByteArray(uc);
				trabajador.setData(data);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return trabajador;
	}
	
//	inicio adecuacion seguridad2
	@RequestMapping(value = "/cargarVentanaVerModalDetalleUsuario", method = { RequestMethod.POST, RequestMethod.GET })
	public String cargarVentanaVerModalDetalleUsuario(HttpServletRequest request, Model model) throws RuntimeException {
		String[] arrayBean = null;
		UsuarioBean usuarioBean = new UsuarioBean();
		try {
			String extraido = request.getParameter("usuarioBean");
			arrayBean  = JsonUtil.convertirCadenaJsonAObjeto(extraido, String[].class);
			if(arrayBean != null) {
				usuarioBean.setCodUsuario(arrayBean[0]);
				usuarioBean.setDescripcion(arrayBean[1]);
				usuarioBean.setCodFicha(Integer.valueOf(arrayBean[2]));
				usuarioBean.setCodArea(Integer.valueOf(arrayBean[3]));
				List<SistemaBean> listaSistemas = sistemaService.obtenerDetalleSistemasUsuario(usuarioBean.getCodUsuario());
				List<PerfilSistemaBean> listaPerfiles = perfilSistemaService.obtenerDetallePerfilesUsuario(usuarioBean.getCodUsuario());
				model.addAttribute("listaSistemas", JsonUtil.convertirObjetoACadenaJson(listaSistemas));
				model.addAttribute("listaPerfiles", JsonUtil.convertirObjetoACadenaJson(listaPerfiles));
				model.addAttribute("codUsuario", usuarioBean.getIdUsuario());
				model.addAttribute("usuarioBean", JsonUtil.convertirObjetoACadenaJson(usuarioBean));				
			}else {
				
			}
			model.addAttribute("mensajeRespuesta", request.getParameter("mensajeRespuesta"));
			model.addAttribute("estadoRespuesta", request.getParameter("estadoRespuesta"));
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return "usuario/verModalDetalleUsuario";
	}
//fin adecuacion seguridad2	
}

