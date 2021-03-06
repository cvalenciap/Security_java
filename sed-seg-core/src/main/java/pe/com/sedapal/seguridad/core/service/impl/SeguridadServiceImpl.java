package pe.com.sedapal.seguridad.core.service.impl;

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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;
import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.bean.CorreoBean;
import pe.com.sedapal.seguridad.core.bean.MenuBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.AccesoService;
import pe.com.sedapal.seguridad.core.service.AccionFormularioService;
import pe.com.sedapal.seguridad.core.service.ClaveService;
import pe.com.sedapal.seguridad.core.service.CorreoService;
import pe.com.sedapal.seguridad.core.service.MenuService;
import pe.com.sedapal.seguridad.core.service.ParametroService;
import pe.com.sedapal.seguridad.core.service.PerfilAccionService;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.SistemaModuloFormService;
import pe.com.sedapal.seguridad.core.service.SistemaModuloService;
import pe.com.sedapal.seguridad.core.service.SistemaService;
import pe.com.sedapal.seguridad.core.service.TrabajadorService;
import pe.com.sedapal.seguridad.core.service.UsuarioFormularioAccionService;
import pe.com.sedapal.seguridad.core.service.UsuarioPerfilSistemaService;
import pe.com.sedapal.seguridad.core.service.UsuarioService;
import pe.com.sedapal.seguridad.core.util.Util;
import pe.com.sedapal.seguridad.core.util.JwtUtil;
import pe.com.sedapal.seguridad.core.util.LDAPAutentication;
import pe.com.sedapal.seguridad.core.util.RedisUtil;

@Service("seguridadService")
public class SeguridadServiceImpl implements SeguridadService {

	private final Logger logger = LoggerFactory.getLogger(SeguridadServiceImpl.class);

	@Autowired
	private SistemaService sistemaService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AccesoService accesoService;

	@Autowired
	private ParametroService parametroService;

	@Autowired
	private PerfilSistemaService perfilSistemaService;

	@Autowired
	private PerfilAccionService perfilAccionService;

	@Autowired
	private SistemaModuloService sistemaModuloService;

	@Autowired
	private SistemaModuloFormService sistemaModuloFormService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private ClaveService claveService;

	@Autowired
	private TrabajadorService trabajadorService;

	@Autowired
	private AccionFormularioService accionFormularioService;

	@Autowired
	private UsuarioPerfilSistemaService usuarioPerfilSistemaService;

	@Autowired
	private CorreoService correoService;

	@Autowired
	private UsuarioFormularioAccionService usuarioFormularioAccionService;
	
//	inicio adecuacion seguridad2
	@Autowired
	RedisUtil redis;
	
	@Autowired
	JwtUtil Jwtutil;
//	fin adecuacion seguridad2

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistema(SistemaBean sistemaBean) throws Exception {
		// TODO Auto-generated method stub
		this.sistemaService.grabarSistema(sistemaBean);
		// throw new Exception("Probando null");
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistema(SistemaBean sistemaBean) throws Exception {
		this.sistemaService.actualizarSistema(sistemaBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemas() throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaService.obtenerListadoSistemas();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaBean obtenerSistemaPorId(Serializable idSistema) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaService.obtenerSistemaPorId(idSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistema(Serializable idSistema, Serializable estado, Serializable usuario)
			throws Exception {
		// TODO Auto-generated method stub
		this.sistemaService.actualizarEstadoSistema(idSistema, estado, usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	// TODO Auto-generated method stub
	public UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception {
		return this.usuarioService.obtenerUsuarioPorCodUsuario(codUsuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarAcceso(AccesoBean accesoBean) throws Exception {
		// TODO Auto-generated method stub
		this.accesoService.grabarAcceso(accesoBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccesoBean> obtenerAccesoSistema(Serializable usuario, Serializable ip) throws Exception {
		// TODO Auto-generated method stub
		return this.accesoService.obtenerAccesoSistema(usuario, ip);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarAcceso(Serializable token) throws Exception {
		// TODO Auto-generated method stub
		this.accesoService.actualizarAcceso(token);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Serializable codParametro) throws Exception {
		// TODO Auto-generated method stub
		return this.parametroService.obtenerListadoParametrosPorCodigoParametro(codParametro);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Serializable codParametro, Serializable codigo)
			throws Exception {
		// TODO Auto-generated method stub
		return this.parametroService.obtenerParametroPorCodigoParametroPorCodigo(codParametro, codigo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ParametroBean> obtenerListadoParametroUnicos() throws Exception {
		// TODO Auto-generated method stub
		return this.parametroService.obtenerListadoParametroUnicos();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarParametro(ParametroBean parametroBean) throws Exception {
		// TODO Auto-generated method stub
		this.parametroService.actualizarParametro(parametroBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerListadoUsuario() throws Exception {
		return usuarioService.obtenerListadoUsuario();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,
			Serializable codSistema) throws Exception {
		this.usuarioService.actualizarUsuarioEstadoPorCodUsuario(codUsuario, estado, usuario, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado,
			Serializable nrointentos, Serializable usuario, Serializable codSistema) throws Exception {
		this.usuarioService.bloqueoUsuarioEstadoPorCodUsuario(codUsuario, estado, nrointentos, usuario, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloService.obtenerListadoSistemaModuloPorCodigo(codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloBean obtenerSistemaModuloPorCodigo(Serializable codSistema, Serializable codModulo)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloService.obtenerSistemaModuloPorCodigo(codSistema, codModulo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
		this.sistemaModuloService.grabarSistemaModulo(sistemaModuloBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
		this.sistemaModuloService.actualizarSistemaModulo(sistemaModuloBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistemaModulo(Serializable codSistema, Serializable codModulo, Serializable estado,
			Serializable usuario) throws Exception {
		this.sistemaModuloService.actualizarEstadoSistemaModulo(codSistema, codModulo, estado, usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception {
		return this.perfilSistemaService.obtenerListadoPerfilSistemaPorCodigo(codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception {
		return this.perfilSistemaService.obtenerPerfilSistemaPorCodigo(codPerfil, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
		int codPerfil = this.perfilSistemaService.grabarPerfilSistema(perfilSistemaBean);

		if (codPerfil > 0) {
			PerfilAccionBean perfilAccion;

			try {
				this.perfilAccionService.eliminarPerfilAccion(codPerfil, perfilSistemaBean.getCodSistema());

				for (String perfil : perfilSistemaBean.getPerfilAccion()) {
					perfilAccion = new PerfilAccionBean();

					perfilAccion.setCodPerfil(codPerfil);
					perfilAccion.setCodSistema(perfilSistemaBean.getCodSistema());
					perfilAccion.setCodAccionFormulario(Integer.parseInt(perfil));
					this.perfilAccionService.grabarPerfilAccion(perfilAccion);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
		this.perfilSistemaService.actualizarPerfilSistema(perfilSistemaBean);

		PerfilAccionBean perfilAccion;

		try {
//			ESTA LINEA DE CODIGO SE ENCONTRABA DENTRO DEL IF, LA CUAL SE A REUBICADO 
			this.perfilAccionService.eliminarPerfilAccion(perfilSistemaBean.getCodPerfil(),
					perfilSistemaBean.getCodSistema());

			if (perfilSistemaBean.getPerfilAccion() != null && !perfilSistemaBean.getPerfilAccion().isEmpty()) {

				for (String perfil : perfilSistemaBean.getPerfilAccion()) {
					perfilAccion = new PerfilAccionBean();

					perfilAccion.setCodPerfil(perfilSistemaBean.getCodPerfil());
					perfilAccion.setCodSistema(perfilSistemaBean.getCodSistema());
					perfilAccion.setCodAccionFormulario(Integer.parseInt(perfil));
					this.perfilAccionService.grabarPerfilAccion(perfilAccion);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado,
			Serializable usuario) throws Exception {
		this.perfilSistemaService.actualizarEstadoPerfilSistema(codPerfil, codSistema, estado, usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public TrabajadorBean obtenerTrabajadorPorFicha(Serializable codFicha) throws Exception {
		// TODO Auto-generated method stub
		return this.trabajadorService.obtenerTrabajadorPorFicha(codFicha);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioClavesErroneas(Serializable codUsuario, int nroIntentos) throws Exception {
		// TODO Auto-generated method stub
		this.usuarioService.actualizarUsuarioClavesErroneas(codUsuario, nroIntentos);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno autenticacionUsuario(String codUsuario, int codSistema, String clave) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = null;
		// String nroSesiones = null;
		UsuarioBean usuarioBean;
		ClaveBean claveBean = null;
		TrabajadorBean trabajadorBean = null;
		// AccesoBean accesoBean = null;
		String bloqueo = "";
		int cont = 1;
		SistemaBean sistemaBean = null;
		PerfilSistemaBean perfilSistemaBean;
		try {
			retorno = new Retorno();
			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			if (usuarioBean == null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no existe.");
				return retorno;
			}
			
			//################## ALEX - SE AGREGO VALIDACION DE PERFIL - INICIO ##################
			perfilSistemaBean = perfilSistemaService.obtenerPerfilSistemaPorCodigo(usuarioBean.getCodPerfil(), usuarioBean.getCodSistema());
			
			if (perfilSistemaBean != null && perfilSistemaBean.getEstado().equals(Constants.P_PERFIL_ESTADO_INACTIVO)){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_PERFIL_NO_ACTIVO);
				retorno.setMensaje("Perfil de usuario se encuentra inactivo.");
				return retorno;
			}
			//################## ALEX - SE AGREGO VALIDACION DE PERFIL - FIN ##################
			
			usuarioBean = obtenerUsuarioPorCodUsuarioCodSistema(codUsuario, codSistema);
			if (usuarioBean == null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no tiene asignado el sistema.");
				return retorno;
			}
			retorno = new Retorno();
			retorno.setCodigo(Constants.SUCCESS);

			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			claveBean = obtenerUltimaClave(codUsuario);
			if (claveBean != null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_CLAVE_TEMPORAL_INVALIDA);
				retorno.setMensaje("Clave temporal invalida.");
				return retorno;
			}

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
				retorno.setMensaje("Usuario Inactivo.");
				return retorno;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
				retorno.setMensaje("Usuario bloqueado.");
				return retorno;
			}

			if (!usuarioBean.getCodArea().toString().equals(trabajadorBean.getnCodArea().toString())) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
				retorno.setMensaje("el usuario no puede ingresar al sistema actual por cambio en su area ");
				return retorno;
			}

			if (!Util.compararClave(clave, usuarioBean.getPass())) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
				retorno.setMensaje("Error al ingresar su clave. ");

				bloqueo = obtenerParametroPorCodigoParametro(Constants.BLOQUEO).getValor();
				if (usuarioBean.getNroIntentos() != null) {
					cont = cont + usuarioBean.getNroIntentos();
				}

				if (cont >= new Integer(bloqueo).intValue()) {
					actualizarUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_BLOQUEADO,
							usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());

					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_ERROR_MAX_NRO_CLAVES);
					retorno.setMensaje("Error al ingresar su clave. Su usuario ha sido bloqueado por seguridad. ");
					return retorno;
				}
				actualizarUsuarioClavesErroneas(usuarioBean.getCodUsuario(), cont);
				return retorno;
			}

			if (usuarioBean.getFlagCambiarClave() != null && "1".equals(usuarioBean.getFlagCambiarClave())) {
				retorno.setCodigo(Constants.SUCCESS);
				retorno.setTipo("" + Constants.SUCCESS);
				retorno.setMensaje("El usuario debe cambiar su Clave. ");
				retorno.setClave(usuarioBean.getPass());
				retorno.setUsuario(usuarioBean.getCodUsuario());
				return retorno;
			}
			
			sistemaBean = sistemaService.obtenerSistemaPorId(usuarioBean.getCodSistema());
			if (sistemaBean != null && sistemaBean.getEstado() == Constants.ESTADO_INACTIVO_SISTEMAS){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_ERROR_SISTEMA_NO_ACTIVO);
				retorno.setMensaje("El sistema se encuentra inactivo.");
				return retorno;
			}

			// retorno.setPermisos(obtenerListadoUsuarioAccionPorCodigo(codUsuario));
			// retorno.setMenu(obtenerListadoSistemaModuloFormInicioPorCodigov2(usuarioBean.getCodSistema(),
			// codUsuario));		
			
			retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
			retorno.setUsuario(usuarioBean.getCodUsuario());
			retorno.setClave(usuarioBean.getPass());
			retorno.setMensaje("Autenticación Satisfactoria");

		} catch (RuntimeException e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw e;
		} catch (Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw new RuntimeException(e);
		}

		return retorno;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public String autenticacionPb(String codUsuario, int codSistema, String clave) {

		// TODO Auto-generated method stub
		String trama;
		AccesoBean accesoBean;
		// String nroSesiones = null;
		UsuarioBean usuarioBean;
		ClaveBean claveBean = null;
		TrabajadorBean trabajadorBean = null;
		String bloqueo = "";
		// AccesoBean accesoBean = null;
		int cont = 1;
		try {

			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			if (usuarioBean == null) {
				trama = Constants.FAILURE + "#Usuario no existe#2";
				return trama;
			}
			usuarioBean = obtenerUsuarioPorCodUsuarioCodSistema(codUsuario, codSistema);
			if (usuarioBean == null) {
				trama = Constants.FAILURE + "#Usuario no tiene asignado el sistema.#2";
				return trama;
			}
			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			claveBean = obtenerUltimaClave(codUsuario);
			if (claveBean != null) {
				trama = Constants.FAILURE + "#Clave temporal invalida.#2";
				return trama;
			}

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				trama = Constants.FAILURE + "#Usuario Inactivo.#2";
				return trama;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				trama = Constants.FAILURE + "#Usuario bloqueado.#2";
				return trama;
			}

			if (!usuarioBean.getCodArea().toString().equals(trabajadorBean.getnCodArea().toString())) {
				trama = Constants.FAILURE + "#El usuario no puede ingresar al sistema actual por cambio en su area.#2";
				return trama;
			}

			if (!Util.compararClave(clave, usuarioBean.getPass())) {
				trama = Constants.FAILURE + "#Error al ingresar su clave.#2";

				bloqueo = obtenerParametroPorCodigoParametro(Constants.BLOQUEO).getValor();
				if (usuarioBean.getNroIntentos() != null) {
					cont = cont + usuarioBean.getNroIntentos();
				}

				if (cont >= new Integer(bloqueo).intValue()) {
					actualizarUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_BLOQUEADO,
							usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());
					trama = Constants.FAILURE
							+ "#Error al ingresar su clave. Su usuario ha sido bloqueado por seguridad.#2";
					return trama;
				}
				actualizarUsuarioClavesErroneas(usuarioBean.getCodUsuario(), cont);
				return trama;
			}

			if (usuarioBean.getFlagCambiarClave() != null && "1".equals(usuarioBean.getFlagCambiarClave())) {
				trama = Constants.SUCCESS + "#El usuario debe cambiar su Clave.#" + usuarioBean.getFlagCambiarClave();
				return trama;
			}
			
			/* inicio JJ -  */
			
			/*
			accesoBean = new AccesoBean(null, usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					"", usuarioBean.getCodArea(), "A", "I", 0, 1, usuarioBean.getCodUsuario().toUpperCase(),
					Util.fechaActualTimeStamp(), usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					usuarioBean.getPass());

			grabarAcceso(accesoBean);*/
			
			bloqueoUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, 0,
					usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());			
			/* fin JJ */

			trama = Constants.SUCCESS + "#Autenticación Satisfactoria.#2";

		} catch (RuntimeException e) {
			trama = Constants.FAILURE + "#Error en la aplicacion.#2";
			throw e;
		} catch (Exception e) {
			trama = Constants.FAILURE + "#Error en la aplicacion.#2";
			throw new RuntimeException(e);
		}

		return trama;

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno autenticacionUsuarioCompleta(String codUsuario, String ip, String token, int codSistema)
			throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = null;
		// String nroSesiones = null;
		UsuarioBean usuarioBean;
		AccesoBean accesoBean = null;
		try {
			retorno = new Retorno();
			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			
			accesoBean = new AccesoBean(null, usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					ip, usuarioBean.getCodArea(), "A", "I", 0, 1, usuarioBean.getCodUsuario().toUpperCase(),
					Util.fechaActualTimeStamp(), usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					token);

			grabarAcceso(accesoBean);
			
			String ultimoAcceso = "";
			List<AccesoBean> accesos =   this.accesoService.obtenerUltimoAccesoSistema(usuarioBean.getCodUsuario());
			if (accesos != null && !accesos.isEmpty()) {
				ultimoAcceso = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(accesos.get(0).getFecFecha());
			}	
			retorno.setUltimoAcceso(ultimoAcceso);
			retorno.setCodigo(Constants.SUCCESS);
			retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
			retorno.setUsuario(usuarioBean.getCodUsuario());
			retorno.setClave(usuarioBean.getPass());
			retorno.setMensaje("Autenticacion Satisfactoria");
			
			/*accesoBean = new AccesoBean(null, usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					ip, usuarioBean.getCodArea(), "A", "I", 0, 1, usuarioBean.getCodUsuario().toUpperCase(),
					Util.fechaActualTimeStamp(), usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					token);

			grabarAcceso(accesoBean);*/

			bloqueoUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, 0,
					usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());

			/*
			 * 
			 * List<AccesoBean> accesos = obtenerAccesoSistema(codUsuario, ip);
			 * nroSesiones =
			 * obtenerParametroPorCodigoParametro(Constants.NUMERO_SESSIONES).
			 * getValor(); if (accesos != null && !accesos.isEmpty()) { if
			 * (accesos.size() >= new Integer(nroSesiones).intValue()) {
			 * retorno.setCodigo(Constants.FAILURE);
			 * retorno.setTipo(Constants.FAILURE_MAXIMO_SESSIONES);
			 * retorno.setMensaje("Ud tienes " + nroSesiones +
			 * " sessiones activas."); return retorno; } }
			 */

		} catch (RuntimeException e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw e;
		} catch (Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw new RuntimeException(e);
		}

		return retorno;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno attemptAuthentication(String codUsuario, String ip, int codSistema) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno();
		retorno.setCodigo(Constants.SUCCESS);
		String nroSesiones = null;
		String tiempoSession = null;
		UsuarioBean usuarioBean;
		ClaveBean claveBean = null;
		TrabajadorBean trabajadorBean;
		SistemaBean sistemaBean = null; 
		PerfilSistemaBean perfilSistemaBean;
		try {
			
			
			
			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			if (usuarioBean == null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no existe.");
				return retorno;
			}
			
			//################## ALEX - SE AGREGO VALIDACION DE PERFIL - INICIO ##################
			
			perfilSistemaBean = perfilSistemaService.obtenerPerfilSistemaPorCodigo(usuarioBean.getCodPerfil(), usuarioBean.getCodSistema());
			
			if (perfilSistemaBean != null && perfilSistemaBean.getEstado().equals(Constants.P_PERFIL_ESTADO_INACTIVO)){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_PERFIL_NO_ACTIVO);
				retorno.setMensaje("Perfil de usuario se encuentra inactivo.");
				return retorno;
			}
			//################## ALEX - SE AGREGO VALIDACION DE PERFIL - FIN ##################
			

			usuarioBean = obtenerUsuarioPorCodUsuarioCodSistema(codUsuario, codSistema);
			if (usuarioBean == null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no tiene asignado el sistema.");
				return retorno;
			}
			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			claveBean = obtenerUltimaClave(codUsuario);
			if (claveBean != null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_CLAVE_TEMPORAL_INVALIDA);
				retorno.setMensaje("Clave temporal invalida.");
				return retorno;
			}

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
				retorno.setMensaje("Usuario Inactivo.");
				return retorno;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
				retorno.setMensaje("Usuario bloqueado.");
				return retorno;
			}

			if (trabajadorBean != null && !usuarioBean.getCodArea().toString().equals(trabajadorBean.getnCodArea().toString())) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
				retorno.setMensaje("el usuario no puede ingresar al sistema actual por cambio en su area ");
				return retorno;
			}

			List<AccesoBean> accesos = obtenerAccesoSistema(codUsuario, ip);
			nroSesiones = obtenerParametroPorCodigoParametro(Constants.NUMERO_SESSIONES).getValor();
			tiempoSession = obtenerParametroPorCodigoParametro(Constants.SESSION).getValor();
			if (accesos != null && !accesos.isEmpty()) {
				if (accesos.size() >= new Integer(nroSesiones).intValue()) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_MAXIMO_SESSIONES);
					retorno.setMensaje("Ud tiene " + nroSesiones + " sessiones activas. Intente nuevamente en " + tiempoSession + " minuto(s).");
					return retorno;
				}
			}
			
			sistemaBean = sistemaService.obtenerSistemaPorId(usuarioBean.getCodSistema());
			if (sistemaBean != null && sistemaBean.getEstado() == Constants.ESTADO_INACTIVO_SISTEMAS){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_ERROR_SISTEMA_NO_ACTIVO);
				retorno.setMensaje("El sistema se encuentra inactivo.");
				return retorno;
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retorno;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno loadUserByUsername(String codUsuario) throws RuntimeException {

		Retorno retorno = new Retorno();
		SistemaBean sistemaBean = null;
		logger.info("PRUEBA ENTRADA2");
		try {
			retorno.setCodigo(Constants.SUCCESS);
			UsuarioBean usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
				retorno.setMensaje("Usuario Inactivo.");
				return retorno;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
				retorno.setMensaje("Usuario Bloqueado.");
				return retorno;
			}
			
			retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
			retorno.setClave(usuarioBean.getPass());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retorno;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
//	inicio adecuacion seguridad2
//	public Retorno onAuthenticationSuccess(String username, String ip, String token) throws RuntimeException {
	public Retorno onAuthenticationSuccess(String username, String ip, String token, Integer codSistema) throws RuntimeException {		
//	fin adecuacion seguridad2
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno();
		retorno.setCodigo(Constants.SUCCESS);
		AccesoBean accesoBean = null;
		UsuarioBean usuarioBean = null;
		logger.info("PRUEBA ENTRADA1");
		try {
//			inicio adecuacion seguridad2
//			usuarioBean = obtenerUsuarioPorCodUsuario(username);
			usuarioBean = obtenerUsuarioPorCodUsuarioAct(username);
			usuarioBean.setCodSistema(codSistema);
//			fin adecuacion seguridad2

			accesoBean = new AccesoBean(null, usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					ip, usuarioBean.getCodArea(), "A", "I", 0, 1, usuarioBean.getCodUsuario().toUpperCase(),
					Util.fechaActualTimeStamp(), usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
					token);

			grabarAcceso(accesoBean);
//			inicio adecuacion seguridad2
//			bloqueoUsuarioEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, 0,
//					usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());
//			retorno.setClave(usuarioBean.getPass());
//			fin adecuacion seguridad2			
			retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
			retorno.setUsuario(usuarioBean.getCodUsuario());
			retorno.setMensaje("Satisfactorio");
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retorno;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String actualizarClavePb(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		// TODO Auto-generated method stub
		UsuarioBean usuarioBean = null;
		List<ClaveBean> clavesAnteriores = null;
		String ultimas = "";
		TrabajadorBean trabajadorBean;
		String trama = "";
		try {
			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			if (usuarioBean != null) {
				if (!Util.compararClave(claveActual, usuarioBean.getPass())) {
					trama = Constants.FAILURE + "#Error al ingresar su clave.#2";
					return trama;

				}

				clavesAnteriores = obtenerClaves(codUsuario);
				ultimas = obtenerParametroPorCodigoParametro(Constants.ANTERIOR).getValor();
				trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
				String valor = Util.validateNewPass(nuevaClave, nuevaClaveR, codUsuario, clavesAnteriores, ultimas,
						trabajadorBean.getvNombres(), trabajadorBean.getvApePaterno(), trabajadorBean.getvApeMaterno());
				if (!"Success".equals(valor)) {
					trama = Constants.FAILURE + "#" + valor + "#2";
					return trama;

				}

				String vpass = Util.generarClave(nuevaClave);
				usuarioBean.setPass(vpass);
				usuarioBean.setFlagCambiarClave(Constants.ESTADO_INACTIVO_STR);
				this.usuarioService.actualizarUsuario(usuarioBean);
				mantenimientoClaves(codUsuario, vpass, Constants.ESTADO_INACTIVO_STR);
				trama = Constants.SUCCESS + "#Se cambio su clave satisfactoriamente.#2";

				// actualizar clave
			} else {
				trama = Constants.FAILURE + "#Ususario no Existe.#2";
				return trama;
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return trama;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno actualizarClave(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException {
		// TODO Auto-generated method stub
		UsuarioBean usuarioBean = null;
		List<ClaveBean> clavesAnteriores = null;
		Retorno retorno = new Retorno();
		retorno.setCodigo(Constants.SUCCESS);
		String ultimas = "";
		TrabajadorBean trabajadorBean;
		try {
			usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario);
			if (usuarioBean != null) {
				if (!Util.compararClave(claveActual, usuarioBean.getPass())) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_ERROR_INGRESANDO_CLAVE);
					retorno.setMensaje("Error al ingresar su clave.");
					return retorno;

				}

				clavesAnteriores = obtenerClaves(codUsuario);
				ultimas = obtenerParametroPorCodigoParametro(Constants.ANTERIOR).getValor();
				trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
				String nombres = "";
				String apellidoPaterno = "";
				String apellidoMaterno = "";
				if (trabajadorBean != null) {
					nombres = trabajadorBean.getvNombres();
					apellidoPaterno = trabajadorBean.getvApePaterno();
					apellidoMaterno = trabajadorBean.getvApeMaterno();
				}
				String valor = Util.validateNewPass(nuevaClave, 
													nuevaClaveR, 
													codUsuario, 
													clavesAnteriores, 
													ultimas,
													nombres, 
													apellidoPaterno, 
													apellidoMaterno);
				if (!"Success".equals(valor)) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_ERROR_VALIDANDO_CLAVE);
					retorno.setMensaje(valor);
					return retorno;

				}

				String vpass = Util.generarClave(nuevaClave);
				usuarioBean.setPass(vpass);
				usuarioBean.setFlagCambiarClave(Constants.ESTADO_INACTIVO_STR);
				this.usuarioService.actualizarUsuario(usuarioBean);
				mantenimientoClaves(codUsuario, vpass, Constants.ESTADO_INACTIVO_STR);
				retorno.setCodigo(Constants.SUCCESS);
				retorno.setMensaje("Se cambio su clave satisfactoriamente.");

				// actualizar clave

				return retorno;
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retorno;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema,
			Serializable codModulo) throws Exception {
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormPorCodigo(codSistema, codModulo);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Serializable parametro,
			Serializable codUsuario) throws Exception {
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormInicioPorCodigo(parametro, codUsuario);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Serializable parametro,
			Serializable codUsuario) throws Exception {
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormInicioPorCodigov2(parametro, codUsuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception {
		return this.sistemaModuloFormService.obtenerSistemaModuloFormPorCodigo(codSistema, codModulo, codFormulario);

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int grabarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
		return this.sistemaModuloFormService.grabarSistemaModuloForm(sistemaModuloFormBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
		this.sistemaModuloFormService.actualizarSistemaModuloForm(sistemaModuloFormBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistemaModuloForm(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario, Serializable estado, Serializable usuario) throws Exception {
		this.sistemaModuloFormService.actualizarEstadoSistemaModuloForm(codSistema, codModulo, codFormulario, estado,
				usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception {
		return this.menuService.obtenerListadoSistemaModuloFormularioMenu(codSistema, codModulo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception {
		return this.menuService.obtenerSistemaModuloFormularioMenu(codSistema, codModulo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ClaveBean> obtenerClaves(String usuario) throws RuntimeException {
		// TODO Auto-generated method stub
		try {
			return this.claveService.obtenerClaves(usuario);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void mantenimientoClaves(String usuario, String vpass, String flagTemp) throws RuntimeException {
		// TODO Auto-generated method stub
		try {

			this.claveService.mantenimientoClaves(usuario, vpass, flagTemp);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void ejecutarProcesos() throws RuntimeException {
		// TODO Auto-generated method stub
		try {
			this.claveService.ejecutarProcesos();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
		this.accionFormularioService.grabarAccionFormulario(accionFormularioBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
		this.accionFormularioService.actualizarAccionFormulario(accionFormularioBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario) throws Exception {
		return this.accionFormularioService.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo,
				codFormulario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception {
		return this.accionFormularioService.obtenerListadoAccionFormularioPerfilPorCodigo(codSistema, codModulo,
				codFormulario, codPerfil);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuario(UsuarioBean usuarioBean) throws Exception {
		String vpass = Util.generadorPasswordTemporal();
		usuarioBean.setPass(Util.generarClave(vpass));
		usuarioBean.setFlagCambiarClave(Constants.ESTADO_ACTIVO_STR);
		if (usuarioBean.getBytesDoc() != null && usuarioBean.getBytesDoc().length != 0
				&& !"".equals(usuarioBean.getNombreDoc())) {
			String ruta = Util.escribirDoc(usuarioBean.getBytesDoc(),
					obtenerParametroPorCodigoParametro(Constants.RUTADOC).getValor(), usuarioBean.getNombreDoc());
			usuarioBean.setDoc(usuarioBean.getNombreDoc());
		}
		this.usuarioService.grabarUsuario(usuarioBean);
		mantenimientoClaves(usuarioBean.getCodUsuario(), usuarioBean.getPass(), Constants.ESTADO_ACTIVO_STR);
//		enviarCorreoNuevoUsuario(usuarioBean, vpass);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuario(UsuarioBean usuarioBean) throws Exception {
		if (usuarioBean.getBytesDoc() != null && usuarioBean.getBytesDoc().length != 0
				&& !"".equals(usuarioBean.getNombreDoc())) {
			String ruta = Util.escribirDoc(usuarioBean.getBytesDoc(),
					obtenerParametroPorCodigoParametro(Constants.RUTADOC).getValor(), usuarioBean.getNombreDoc());
			usuarioBean.setDoc(usuarioBean.getNombreDoc());
		}
		this.usuarioService.actualizarUsuario(usuarioBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioPerfilSistema();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistema();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil,
			Serializable codSistema) throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioPerfilSistemaPorCodigo(codPerfil, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema,
			Serializable codUsuario) throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioPerfilSistemaPorUsuario(codSistema, codUsuario);
	}
	//

	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario) throws Exception {
		return this.usuarioPerfilSistemaService.obtenerUsuarioPerfilSistemaPorCodigo(codPerfil, codSistema, codUsuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
		this.usuarioPerfilSistemaService.grabarUsuarioPerfilSistema(usuarioPerfilBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
		this.usuarioPerfilSistemaService.actualizarUsuarioPerfilSistema(usuarioPerfilBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception {
		this.usuarioPerfilSistemaService.actualizarEstadoUsuarioPerfilSistema(codPerfil, codSistema, codUsuario, estado,
				usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception {
		// TODO Auto-generated method stub
		return this.accesoService.obtenerUltimoAccesoSistema(usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void enviarCorreoNuevoUsuario(UsuarioBean usuarioBean, String clave, List<UsuarioPerfilBean> asociaciones) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder mensage = new StringBuilder();
		TrabajadorBean trabajadorBean;
		String nombreEnvio = "";
		String correoEnvio = "";
		try {
			String from = obtenerParametroPorCodigoParametro(Constants.FROM).getValor();
			String ccto = obtenerParametroPorCodigoParametro(Constants.CCTO).getValor();
			String bccto = obtenerParametroPorCodigoParametro(Constants.BCCTO).getValor();
			String titulo = obtenerParametroPorCodigoParametro(Constants.ASUNTO_USUARIO).getValor();
			
			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			
			if(usuarioBean.getCodTipo().intValue() == Constants.TIPO_USUARIO_INTERNO.intValue()) {
				nombreEnvio = trabajadorBean.getvApePaterno() + " " + trabajadorBean.getvApeMaterno() + " " + trabajadorBean.getvNombres();
				correoEnvio = trabajadorBean.getvDirelectronica();
			} else if(usuarioBean.getCodTipo().intValue() == Constants.TIPO_USUARIO_EXTERNO.intValue()) {
				nombreEnvio = usuarioBean.getNombreExterno();
				correoEnvio = usuarioBean.getEmailExterno();
			}
			
			for(UsuarioPerfilBean asociacion : asociaciones) {
				mensage.append("Sistema:  " + obtenerSistemaPorId(asociacion.getCodSistema()).getDescripcion() + " con Perfil: " + "Perfil:  " + obtenerPerfilSistemaPorCodigo(asociacion.getCodPerfil(), asociacion.getCodSistema()).getDescripcion() + "\n");
			}
			mensage.append("\n");
			mensage.append("Estimada(o): " + nombreEnvio + "\n");
			
			if(usuarioBean.getCodTipo().intValue() == Constants.TIPO_USUARIO_INTERNO.intValue()) {
				mensage.append("Ficha:  " + usuarioBean.getCodFicha() + "\n");
				mensage.append("Equipo: " + trabajadorBean.getDescArea() + " \n");
			}
			
			mensage.append("Le informamos que sus datos de ingreso al aplicativo son los siguientes:" + "\n");
			mensage.append("Usuario:  " + usuarioBean.getCodUsuario() + "\n");
			mensage.append("Password:  " + clave + "\n");

			mensage.append(
					"Asimismo, le comunico a usted, que el usuario y clave asignada es personal e intransferible, estando bajo su responsabilidad el uso de la misma."
							+ "\n");
			mensage.append("Sustento:  " + usuarioBean.getSustentacion() + "\n");

			mensage.append("Atentamente," + "\n");
			mensage.append("Administrador del Aplicativo de Control Accesos" + "\n");

			mensage.append("No responder este Email" + "\n");

			CorreoBean correoBean = new CorreoBean(from, correoEnvio, titulo, mensage, ccto, bccto);

			enviarCorreo(correoBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
//	public void enviarCorreoNuevaClave(TrabajadorBean trabajadorBean, String email, String clave) throws Exception {
	public void enviarCorreoNuevaClave(String nombre, String email, String clave) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder mensage = new StringBuilder();

		try {
			String titulo = obtenerParametroPorCodigoParametro(Constants.ASUNTO_CLAVE).getValor();
			String from = obtenerParametroPorCodigoParametro(Constants.FROM).getValor();
//			mensage.append("El usuario " + trabajadorBean.getvApePaterno() + " " + trabajadorBean.getvApeMaterno() + " "
//					+ trabajadorBean.getvNombres() + " solicito contraseña y se le envió el ");
			mensage.append("El usuario " + nombre + " solicito contraseña y se le envió el ");
			mensage.append("correo de forma satisfactoria." + "\n");
			mensage.append("nueva clave:" + clave + "\n");
			mensage.append("No responder este Email" + "\n");

			// seguridadService.enviarCorreo(correoBean);
			// Set up the SMTP server.
			CorreoBean correoBean = new CorreoBean(from, email, titulo, mensage, "", "");
			enviarCorreo(correoBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno olvidarClaveWs(String codUsuario) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno(Constants.SUCCESS, "Se envió el correo con su nueva clave");
		try {
			this.olvidarClave(codUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE_ERROR_OLVIDO_CLAVE);
			retorno.setMensaje("Error al enviar nueva clave." + e.getMessage());
		}
		return retorno;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String olvidarClavePb(String codUsuario) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno(Constants.SUCCESS, "Se envió el correo con su nueva clave");
		try {
			this.olvidarClave(codUsuario);
		} catch (Exception e) {
			e.printStackTrace();
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE_ERROR_OLVIDO_CLAVE);
			retorno.setMensaje("Error al enviar nueva clave." + e.getMessage());
		}
		StringBuffer sb = new StringBuffer (retorno.getCodigo()).append("#")
				.append(retorno.getMensaje()).append("#")
				.append(retorno.getTipo());		
		return sb.toString();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void olvidarClave(String codUsuario) throws Exception {
		// TODO Auto-generated method stub
		String vpass = Util.generadorPasswordTemporal();
		UsuarioBean usuarioBean = obtenerUsuarioPorCodUsuario(codUsuario.toUpperCase());
		TrabajadorBean trabajadorBean;
		String correoEnvio = "";
		String nombreEnvio = "";
		if (usuarioBean != null) {
			String generado = Util.generarClave(vpass);
			usuarioBean.setPass(generado);
			usuarioBean.setFlagCambiarClave(Constants.ESTADO_ACTIVO_STR);
			actualizarUsuario(usuarioBean);
			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			mantenimientoClaves(codUsuario, usuarioBean.getPass(), Constants.ESTADO_ACTIVO_STR);
			/*adecuacion usuarios externo*/
			if(usuarioBean.getCodTipo() != null && usuarioBean.getCodTipo().intValue() == Constants.TIPO_USUARIO_INTERNO.intValue()) {
				correoEnvio = trabajadorBean.getvDirelectronica();
				nombreEnvio = trabajadorBean.getvApePaterno() + " " + trabajadorBean.getvApeMaterno() + " " + trabajadorBean.getvNombres();
			} else if (usuarioBean.getCodTipo() != null && usuarioBean.getCodTipo().intValue() == Constants.TIPO_USUARIO_EXTERNO.intValue()) {
				correoEnvio = usuarioBean.getEmailExterno();
				nombreEnvio = usuarioBean.getNombreExterno();
			}
//			enviarCorreoNuevaClave(trabajadorBean, trabajadorBean.getvDirelectronica(), vpass);
			enviarCorreoNuevaClave(nombreEnvio, correoEnvio, vpass);
			/*actualizar usuario bloqueado*/
			if(usuarioBean.getEstado().intValue() == Constants.ESTADO_BLOQUEADO) {
				actualizarUsuarioEstadoPorCodUsuarioAct(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, usuarioBean.getCodUsuario());
			}
			
		} else {
			throw new Exception("Usuario no existe");
		}

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception {
		return this.perfilAccionService.obtenerListadoPerfilAccionPorCodigo(codPerfil, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception {
		return this.perfilAccionService.obtenerPerfilAccionPorCodigo(codPerfilAccion);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
		this.perfilAccionService.grabarPerfilAccion(perfilAccionBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
		this.perfilAccionService.actualizarPerfilAccion(perfilAccionBean);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception {
		this.perfilAccionService.eliminarPerfilAccion(codPerfil, codSistema);
	}

	@Override
	public void enviarCorreo(CorreoBean correoBean) {
		// TODO Auto-generated method stub

		logger.info("enviando correo .....");
		// String host = null;
		try {

			this.correoService.enviarCorreo(correoBean);

			/*
			 * this.sender = new JavaMailSenderImpl(); host =
			 * obtenerParametroPorCodigoParametro(Constants.SERVER_CORREO).
			 * getValor(); this.sender.setHost("smtp.gmail.com");
			 * this.sender.setPort(587);
			 * this.sender.setUsername("gluiscastro@gmail.com");
			 * this.sender.setPassword("Sebas2811"); Properties
			 * javaMailProperties = new Properties();
			 * javaMailProperties.setProperty("mail.smtp.auth", "false");
			 * javaMailProperties.setProperty("mail.smtp.starttls.enable",
			 * "true"); this.sender.setJavaMailProperties(javaMailProperties);
			 * 
			 * MimeMessage message = this.sender.createMimeMessage();
			 * MimeMessageHelper helper = new MimeMessageHelper(message);
			 * helper.setFrom(correoBean.getvFrom());
			 * helper.setTo(correoBean.getvTo());
			 * 
			 * if (correoBean.getvCcto() != null &&
			 * !"".equals(correoBean.getvCcto())) {
			 * helper.setCc(correoBean.getvCcto().split(";")); }
			 * 
			 * if (correoBean.getvBccto() != null &&
			 * !"".equals(correoBean.getvBccto())) {
			 * helper.setBcc(correoBean.getvBccto().split(";")); }
			 * 
			 * helper.setSubject(correoBean.getvSubject());
			 * helper.setText(correoBean.getvMessage().toString());
			 * 
			 * sender.send(message);
			 */
			logger.info("se envio el correo .....");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error al enviar el correo .....");
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public ParametroBean obtenerParametroPorCodigoParametro(Serializable codParametro) throws Exception {
		// TODO Auto-generated method stub
		return this.parametroService.obtenerParametroPorCodigoParametro(codParametro);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public ClaveBean obtenerUltimaClave(String usuario) throws Exception {
		// TODO Auto-generated method stub
		return this.claveService.obtenerUltimaClave(usuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception {
		return this.usuarioFormularioAccionService.obtenerListadoUsuarioAccionPorCodigo(codUsuario);		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception {
		return this.accionFormularioService.obtenerListadoAccionFormularioPerfilPorUsuario(codSistema, codModulo,
				codFormulario, codPerfil);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch) throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistemaPaginarTotal(valueSearch);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistemaPaginar(pageSize, pageIndex, valueSearch,
				sortColumn);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Serializable abreviatura) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaService.obtenerListadoSistemasPorAbreviatura(abreviatura);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception {
		// TODO Auto-generated method stub
		return this.perfilSistemaService.obtenerListadoPerfilSistemaPaginarTotal(valueSearch);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception {
		// TODO Auto-generated method stub
		return this.perfilSistemaService.obtenerListadoPerfilSistemaPaginar(pageSize, pageIndex, valueSearch,
				sortColumn);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormPaginarTotal(tipoBusqueda, valorBusqueda);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex,
			String tipoBusqueda, String valorBusqueda, String sortColumn) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormPaginar(pageSize, pageIndex, tipoBusqueda, valorBusqueda,
				sortColumn);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno actualizarClaveWs(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return this.actualizarClave(codUsuario, claveActual, nuevaClave, nuevaClaveR);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemasActivos() throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaService.obtenerListadoSistemasActivos();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloService.obtenerListadoSistemaModuloPorCodigoActivos(codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return this.perfilSistemaService.obtenerListadoPerfilSistemaPorCodigoActivos(codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloService.obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario, Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioService.obtenerUsuarioPorCodUsuarioCodSistema(codUsuario, codSistema);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloBean obtenerSistemaModuloPorNombre(Serializable codSistema, Serializable nombreModulo)
			throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloService.obtenerSistemaModuloPorNombre(codSistema, nombreModulo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario) {
		// TODO Auto-generated method stub
		StringBuilder retorno = new StringBuilder();
		try {
			List<String> valores = this.usuarioFormularioAccionService
					.obtenerListadoUsuarioAccionPorCodigoPb(codUsuario);
			for (String valor : valores) {
				retorno.append(valor);
				retorno.append("#");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno.toString();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerMenuWs(Serializable codUsuario, Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return obtenerListadoSistemaModuloFormInicioPorCodigov2(codSistema, codUsuario);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaModuloFormService.obtenerSistemaModuloFormPorCodigoHijos(codSistema, codModulo,
				codFormulario);
	}
	
//	inicio adecuacion seguridad2
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno attemptAuthenticationAct(String codUsuario, String ip, int codSistema) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno();
		retorno.setCodigo(Constants.SUCCESS);
		String nroSesiones = null;
		String tiempoSession = null;
		UsuarioBean usuarioBean;
		ClaveBean claveBean = null;
		TrabajadorBean trabajadorBean;
		SistemaBean sistemaBean = null;
		try {
			
			
			
			usuarioBean = obtenerUsuarioPorCodUsuarioAct(codUsuario);
			if (usuarioBean == null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no existe.");
				return retorno;
			}
			
			List<PerfilSistemaBean> listaPerfilesSistema = perfilSistemaService.obtenerPerfilesSistemaPorCodigo(codUsuario, codSistema);
			
			if(listaPerfilesSistema == null || listaPerfilesSistema.size() == Constants.EMPTY_VALUE) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_SIN_PERFILES);
				retorno.setMensaje("El usuario no tiene ningun perfil asociado al sistema.");
				return retorno;
			}else {
				Integer flag = 0;
				for(PerfilSistemaBean perfilSistema : listaPerfilesSistema){
					if(perfilSistema.getEstado().equals(Constants.P_PERFIL_ESTADO_ACTIVO)) {
						flag = 1;
						break;
					}
				}
				if(flag == 0) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_PERFIL_NO_ACTIVO);
					retorno.setMensaje("El usuario no tiene perfiles activos.");
					return retorno;
				}
			}			

			List<UsuarioBean> asociacionesUsuario = obtenerUsuarioPorCodUsuarioCodSistemaAct(codUsuario, codSistema);
			if (asociacionesUsuario == null || asociacionesUsuario.size() == Constants.EMPTY_VALUE) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
				retorno.setMensaje("Usuario no tiene asignado el sistema.");
				return retorno;
			}
			
			trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
			claveBean = obtenerUltimaClave(codUsuario);
			if (claveBean != null) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_CLAVE_TEMPORAL_INVALIDA);
				retorno.setMensaje("Clave temporal invalida.");
				return retorno;
			}

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
				retorno.setMensaje("Usuario Inactivo.");
				return retorno;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
				retorno.setMensaje("Usuario bloqueado.");
				return retorno;
			}

			if (trabajadorBean != null && !usuarioBean.getCodArea().toString().equals(trabajadorBean.getnCodArea().toString())) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
				retorno.setMensaje("el usuario no puede ingresar al sistema actual por cambio en su area ");
				return retorno;
			}

			List<AccesoBean> accesos = obtenerAccesoSistema(codUsuario, ip);
			nroSesiones = obtenerParametroPorCodigoParametro(Constants.NUMERO_SESSIONES).getValor();
			tiempoSession = obtenerParametroPorCodigoParametro(Constants.SESSION).getValor();
			if (accesos != null && !accesos.isEmpty()) {
				if (accesos.size() >= new Integer(nroSesiones).intValue()) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_MAXIMO_SESSIONES);
					retorno.setMensaje("Ud tiene " + nroSesiones + " sessiones activas. Intente nuevamente en " + tiempoSession + " minuto(s).");
					return retorno;
				}
			}
			
			sistemaBean = sistemaService.obtenerSistemaPorId(codSistema);
			if (sistemaBean != null && sistemaBean.getEstado() == Constants.ESTADO_INACTIVO_SISTEMAS){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_ERROR_SISTEMA_NO_ACTIVO);
				retorno.setMensaje("El sistema se encuentra inactivo.");
				return retorno;
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retorno;
	}	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	// TODO Auto-generated method stub
	public UsuarioBean obtenerUsuarioPorCodUsuarioAct(Serializable codUsuario) throws Exception {
		return this.usuarioService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno loadUserByUsernameAct(String codUsuario) throws RuntimeException {

		Retorno retorno = new Retorno();
		SistemaBean sistemaBean = null;
		try {
			retorno.setCodigo(Constants.SUCCESS);
			UsuarioBean usuarioBean = obtenerUsuarioPorCodUsuarioAct(codUsuario);

			if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
				retorno.setMensaje("Usuario Inactivo.");
				return retorno;
			} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
				retorno.setMensaje("Usuario Bloqueado.");
				return retorno;
			}
			
			retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
			retorno.setClave(usuarioBean.getPass());
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return retorno;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoAct(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception {
		return this.usuarioFormularioAccionService.obtenerListadoUsuarioAccionPorCodigoAct(codUsuario, codSistema, codPerfil);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaAct(Serializable codUsuario, Serializable codSistema)throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioService.obtenerUsuarioPorCodUsuarioCodSistemaAct(codUsuario, codSistema);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void bloqueoUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado,
			Serializable nrointentos, Serializable usuario) throws Exception {
		this.usuarioService.bloqueoUsuarioEstadoPorCodUsuarioAct(codUsuario, estado, nrointentos, usuario);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoAct(Serializable codSistema,
			Serializable codUsuario, Serializable codPerfil) throws Exception {
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormInicioPorCodigoAct(codSistema, codUsuario, codPerfil);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoUsuarioSistemaPaginarTotalAct(String valueSearch) throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistemaPaginarTotalAct(valueSearch);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch) throws Exception {
		// TODO Auto-generated method stub
//		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistemaPaginarAct(pageSize, pageIndex, valueSearch,
//				sortColumn);
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioSistemaPaginarAct(pageSize, pageIndex, valueSearch);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(Serializable codUsuario) throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioAct(UsuarioBean usuarioBean) throws Exception {
		if (usuarioBean.getBytesDoc() != null && usuarioBean.getBytesDoc().length != 0
				&& !"".equals(usuarioBean.getNombreDoc())) {
			String ruta = Util.escribirDoc(usuarioBean.getBytesDoc(),
					obtenerParametroPorCodigoParametro(Constants.RUTADOC).getValor(), usuarioBean.getNombreDoc());
			usuarioBean.setDoc(usuarioBean.getNombreDoc());
		}
		this.usuarioService.actualizarUsuarioAct(usuarioBean);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarAsociacionesActuales(Serializable codUsuario) throws Exception {
		this.usuarioPerfilSistemaService.eliminarAsociacionesActuales(codUsuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarAuditoriaUsuario(UsuarioPerfilBean asociacion, UsuarioBean usuario) throws Exception{
		this.usuarioPerfilSistemaService.actualizarAuditoriaUsuario(asociacion, usuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioAct(UsuarioBean usuarioBean, List<UsuarioPerfilBean> asociaciones) throws Exception {
		String vpass = Util.generadorPasswordTemporal();
		usuarioBean.setPass(Util.generarClave(vpass));
		usuarioBean.setFlagCambiarClave(Constants.ESTADO_ACTIVO_STR);
		if (usuarioBean.getBytesDoc() != null && usuarioBean.getBytesDoc().length != 0
				&& !"".equals(usuarioBean.getNombreDoc())) {
			String ruta = Util.escribirDoc(usuarioBean.getBytesDoc(),
					obtenerParametroPorCodigoParametro(Constants.RUTADOC).getValor(), usuarioBean.getNombreDoc());
			usuarioBean.setDoc(usuarioBean.getNombreDoc());
		}
		this.usuarioService.grabarUsuarioAct(usuarioBean);
		mantenimientoClaves(usuarioBean.getCodUsuario(), usuarioBean.getPass(), Constants.ESTADO_ACTIVO_STR);
		enviarCorreoNuevoUsuario(usuarioBean, vpass, asociaciones);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception {
		this.usuarioService.actualizarUsuarioEstadoPorCodUsuarioAct(codUsuario, estado, usuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistemaAct(SistemaBean sistemaBean) throws Exception {
		this.sistemaService.grabarSistemaAct(sistemaBean);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaAct(SistemaBean sistemaBean) throws Exception {
		this.sistemaService.actualizarSistemaAct(sistemaBean);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaBean obtenerSistemaPorIdAct(Serializable idSistema) throws Exception {
		// TODO Auto-generated method stub
		return this.sistemaService.obtenerSistemaPorIdAct(idSistema);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno autenticacionUsuarioAct(String codUsuario, int codSistema, String clave) throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = null;
		// String nroSesiones = null;
		UsuarioBean usuarioBean = null;
		ClaveBean claveBean = null;
		TrabajadorBean trabajadorBean = null;
		// AccesoBean accesoBean = null;
		String bloqueo = "";
		int cont = 1;
		SistemaBean sistemaBean = null;
		try {
			retorno = new Retorno();
			/*OBTENER DATOS DE SISTEMA*/
			sistemaBean = sistemaService.obtenerSistemaPorIdAct(codSistema);
			/*validación de sistema activo*/
			if (sistemaBean != null && sistemaBean.getEstado() == Constants.ESTADO_INACTIVO_SISTEMAS){
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo("" + Constants.FAILURE_ERROR_SISTEMA_NO_ACTIVO);
				retorno.setMensaje("El sistema se encuentra inactivo.");
				return retorno;
			}
			/*set tipo de autenticacion : TABLAS*/
			if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_TABLAS) {
				/*obtener datos de usuario*/
				usuarioBean = obtenerUsuarioPorCodUsuarioAct(codUsuario);
				/*validacion usuario*/
				if (usuarioBean == null) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
					retorno.setMensaje("Usuario no existe.");
					return retorno;
				}
				/*obtener perfiles de usuario*/
				List<PerfilSistemaBean> listaPerfilesSistema = perfilSistemaService.obtenerPerfilesSistemaPorCodigo(codUsuario, codSistema);
				/*validacion de perfiles de usuario*/
				if(listaPerfilesSistema == null || listaPerfilesSistema.size() == Constants.EMPTY_VALUE) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_SIN_PERFILES);
					retorno.setMensaje("El usuario no tiene ningun perfil asociado al sistema.");
					return retorno;
				}else {
					Integer flag = 0;
					for(PerfilSistemaBean perfilSistema : listaPerfilesSistema){
						if(perfilSistema.getEstado().equals(Constants.P_PERFIL_ESTADO_ACTIVO)) {
							flag = 1;
							break;
						}
					}
					if(flag == 0) {
						retorno.setCodigo(Constants.FAILURE);
						retorno.setTipo(Constants.FAILURE_PERFIL_NO_ACTIVO);
						retorno.setMensaje("El usuario no tiene perfiles activos.");
						return retorno;
					}
				}
				
				/*obtener asociacion de usuario con sistema*/
				List<UsuarioBean> asociacionesUsuario = obtenerUsuarioPorCodUsuarioCodSistemaAct(codUsuario, codSistema);
				/*validacion de existencia de asociacion usuario - sistema*/
				if (asociacionesUsuario == null || asociacionesUsuario.size() == Constants.EMPTY_VALUE) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
					retorno.setMensaje("Usuario no tiene asignado el sistema.");
					return retorno;
				}
				retorno = new Retorno();
				retorno.setCodigo(Constants.SUCCESS);
				
				/*validacion de clave temporal*/
				trabajadorBean = obtenerTrabajadorPorFicha(usuarioBean.getCodFicha());
				claveBean = obtenerUltimaClave(codUsuario);
				if (claveBean != null) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_CLAVE_TEMPORAL_INVALIDA);
					retorno.setMensaje("Clave temporal invalida.");
					return retorno;
				}
				
				/*validacion de estado de usuario*/
				if (usuarioBean.getEstado() == Constants.ESTADO_INACTIVO) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_USUARIO_NOACTIVO);
					retorno.setMensaje("Usuario Inactivo.");
					return retorno;
				} else if (usuarioBean.getEstado() == Constants.ESTADO_BLOQUEADO) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_USUARIO_BLOQUEADO);
					retorno.setMensaje("Usuario bloqueado.");
					return retorno;
				}
				
				/*validacion de area de usuario - trabajador*/
				if (!usuarioBean.getCodArea().toString().equals(trabajadorBean.getnCodArea().toString())) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
					retorno.setMensaje("el usuario no puede ingresar al sistema actual por cambio en su area. ");
					return retorno;
				}
				
				/*Validación de estado de trabajador*/
				if(trabajadorBean.getvCodEstado().equals(Constants.TRABAJADOR_BAJA)) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_TRABAJADOR_BAJA);
					retorno.setMensaje("el usuario no puede ingresar al sistema por trabajador de baja. ");
					return retorno;
				}
				
				/*validacion de credenciales*/
				if (!Util.compararClave(clave, usuarioBean.getPass())) {
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_AREAS_DIFERENTES);
					retorno.setMensaje("Error al ingresar su clave. ");
						
					/*evaluacion flag bloqueo*/
					if(usuarioBean.getFlagBloqueo().intValue() == Constants.FLAG_BLOQUEO_SI) {
						bloqueo = obtenerParametroPorCodigoParametro(Constants.BLOQUEO).getValor();
						if (usuarioBean.getNroIntentos() != null) {
							cont = cont + usuarioBean.getNroIntentos();
						}

						if (cont >= new Integer(bloqueo).intValue()) {
							actualizarUsuarioEstadoPorCodUsuarioAct(usuarioBean.getCodUsuario(), Constants.ESTADO_BLOQUEADO,
									usuarioBean.getCodUsuario());
							/*auditoria*/
							List<UsuarioPerfilBean> asociaciones = obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(codUsuario);
							for(UsuarioPerfilBean asociacion : asociaciones) {
								asociacion.setEstado(Constants.ESTADO_ACTIVO);
								asociacion.setUsuarioCreacion(codUsuario);
								asociacion.setCodUsuario(codUsuario);
								actualizarAuditoriaUsuario(asociacion, usuarioBean);
							}	
							retorno.setCodigo(Constants.FAILURE);
							retorno.setTipo("" + Constants.FAILURE_ERROR_MAX_NRO_CLAVES);
							retorno.setMensaje("Error al ingresar su clave. Su usuario ha sido bloqueado por seguridad. ");
							return retorno;
						}
						actualizarUsuarioClavesErroneas(usuarioBean.getCodUsuario(), cont);
						return retorno;
					}
					return retorno;
				}
				
				/*validacion de estado de clave*/
				if (usuarioBean.getFlagCambiarClave() != null && Constants.FLAG_CAMBIAR_CLAVE.equals(usuarioBean.getFlagCambiarClave())) {
					retorno.setCodigo(Constants.SUCCESS);
					retorno.setTipo("" + Constants.SUCCESS);
					retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
					retorno.setMensaje("El usuario debe cambiar su Clave. ");
					retorno.setUsuario(usuarioBean.getCodUsuario());
					return retorno;
				}
				
				/*pass --- autenticacion correcta*/
				retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
				retorno.setUsuario(usuarioBean.getCodUsuario());
				retorno.setAutenticacion(Constants.AUTH_TABLAS_STR);
				retorno.setMensaje("Autenticación Satisfactoria");
				
				/*ADICION DE PERFILES ACTIVOS EN RESPUESTA*/
				List<PerfilSistemaBean> perfilesActivos = perfilSistemaService.obtenerPerfilesSistemaActivos(codUsuario, codSistema);
				retorno.setNumPerfilesAct(perfilesActivos.size());
				retorno.setPerfilesAct(perfilesActivos);
				
			}else if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_LDAP) {
				/*obtener datos de usuario LDAP*/
				usuarioBean = obtenerUsuarioPorCodUsuarioLDAP(codUsuario);
				/*validacion usuario LDAP*/
				if (usuarioBean == null) {
					retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_USUARIO_LDAP_NO_ASOCIA);
					retorno.setMensaje("Usuario LDAP no tiene asociaciones.");
					return retorno;
				}
				
				/*validacion de existencia de usuario LDAP*/
				String loginDN = busquedaUsuarioLDAP(usuarioBean);
				if (loginDN == null) {
					retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_USUARIO_LDAP_NO_VALID);
					retorno.setMensaje("No existe usuario LDAP.");
					return retorno;
				}
				
				/*obtener perfiles de usuario LDAP*/
				List<PerfilSistemaBean> listaPerfilesSistema = perfilSistemaService.obtenerPerfilesSistemaPorCodigoLDAP(codUsuario, codSistema);
				/*validacion de perfiles de usuario LDAP*/
				if(listaPerfilesSistema == null || listaPerfilesSistema.size() == Constants.EMPTY_VALUE) {
					retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_SIN_PERFILES);
					retorno.setMensaje("El usuario no tiene ningun perfil asociado al sistema.");
					return retorno;
				}else {
					Integer flag = 0;
					for(PerfilSistemaBean perfilSistema : listaPerfilesSistema){
						if(perfilSistema.getEstado().equals(Constants.P_PERFIL_ESTADO_ACTIVO)) {
							flag = 1;
							break;
						}
					}
					if(flag == 0) {
						retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
						retorno.setCodigo(Constants.FAILURE);
						retorno.setTipo(Constants.FAILURE_PERFIL_NO_ACTIVO);
						retorno.setMensaje("El usuario no tiene perfiles activos.");
						return retorno;
					}
				}
				
				/*obtener asociacion de usuario LDAP con sistema*/
				List<UsuarioBean> asociacionesUsuario = obtenerUsuarioPorCodUsuarioCodSistemaLDAP(codUsuario, codSistema);
				/*validacion de existencia de asociacion usuario LDAP - sistema*/
				if (asociacionesUsuario == null || asociacionesUsuario.size() == Constants.EMPTY_VALUE) {
					retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo(Constants.FAILURE_USUARIO_NO_EXISTE);
					retorno.setMensaje("Usuario LDAP no tiene asignado el sistema.");
					return retorno;
				}
				retorno = new Retorno();
				retorno.setCodigo(Constants.SUCCESS);
								
				/*validacion de credenciales LDAP*/
				boolean conexionLDAP = validarConectividadLDAP(loginDN, clave);
				if (!conexionLDAP) {
					retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
					retorno.setCodigo(Constants.FAILURE);
					retorno.setTipo("" + Constants.FAILURE_ERROR_INGRESANDO_CLAVE);
					retorno.setMensaje("Error al ingresar su clave. ");
					
					/*VERIFY REQ INCLUDE BLOQ LDAP USER*/
//					bloqueo = obtenerParametroPorCodigoParametro(Constants.BLOQUEO).getValor();
//					if (usuarioBean.getNroIntentos() != null) {
//						cont = cont + usuarioBean.getNroIntentos();
//					}
//
//					if (cont >= new Integer(bloqueo).intValue()) {
//						actualizarUsuarioLDAPEstadoPorCodUsuario(usuarioBean.getCodUsuario(), Constants.ESTADO_BLOQUEADO,
//								usuarioBean.getCodUsuario(), usuarioBean.getCodSistema());
//						/*auditoria*/
//						List<UsuarioPerfilBean> asociaciones = obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(codUsuario);
//						for(UsuarioPerfilBean asociacion : asociaciones) {
//							asociacion.setEstado(Constants.ESTADO_ACTIVO);
//							asociacion.setUsuarioCreacion(codUsuario);
//							asociacion.setCodUsuario(codUsuario);
//							actualizarAuditoriaUsuario(asociacion, usuarioBean);
//						}
//						retorno.setCodigo(Constants.FAILURE);
//						retorno.setTipo("" + Constants.FAILURE_ERROR_MAX_NRO_CLAVES);
//						retorno.setMensaje("Error al ingresar su clave. Su usuario LDAP ha sido bloqueado por seguridad. ");
//						return retorno;
//					}
//					actualizarUsuarioLDAPClavesErroneas(usuarioBean.getCodUsuario(), cont);
					return retorno;
				}
				
				/*pass --- autenticacion LDAP correcta*/
				retorno.setUsuario(usuarioBean.getCodUsuarioLDAP());
				retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
				retorno.setMensaje("Autenticación Satisfactoria");
				
				/*ADICION DE PERFILES ACTIVOS EN RESPUESTA*/
				List<PerfilSistemaBean> perfilesActivosLDAP = perfilSistemaService.obtenerPerfilesSistemaActivosLDAP(codUsuario, codSistema);
				retorno.setNumPerfilesAct(perfilesActivosLDAP.size());
				retorno.setPerfilesAct(perfilesActivosLDAP);
				
			}
			
			/*creación y devolución token redis*/
			if(retorno.getCodigo().equals(Constants.SUCCESS)) {
				String token = Jwtutil.generateJwtToken(codUsuario);
				redis.save(token, usuarioBean);
				retorno.setToken(token);
			}
		} catch (RuntimeException e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw e;
		} catch (Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw new RuntimeException(e);
		}

		return retorno;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno autenticacionUsuarioCompletaAct(String codUsuario, String ip, String token, int codSistema, int codPerfil)
			throws RuntimeException {
		// TODO Auto-generated method stub
		Retorno retorno = null;
		// String nroSesiones = null;
		UsuarioBean usuarioBean;
		SistemaBean sistemaBean;
		AccesoBean accesoBean = null;
		try {
			retorno = new Retorno();
			sistemaBean = sistemaService.obtenerSistemaPorIdAct(codSistema);
			
			if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_TABLAS) {
				usuarioBean = obtenerUsuarioPorCodUsuarioAct(codUsuario);
				accesoBean = new AccesoBean(null, usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
						ip, usuarioBean.getCodArea(), "A", "I", 0, 1, usuarioBean.getCodUsuario().toUpperCase(),
						Util.fechaActualTimeStamp(), usuarioBean.getCodUsuario().toUpperCase(), Util.fechaActualTimeStamp(),
						token);

				grabarAcceso(accesoBean);
				
				String ultimoAcceso = "";
				List<AccesoBean> accesos = this.accesoService.obtenerUltimoAccesoSistema(usuarioBean.getCodUsuario());
				if (accesos != null && !accesos.isEmpty()) {
					ultimoAcceso = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(accesos.get(0).getFecFecha());
				}	
				retorno.setUltimoAcceso(ultimoAcceso);
				retorno.setCodigo(Constants.SUCCESS);
				retorno.setFlagCambiarClave(usuarioBean.getFlagCambiarClave());
				retorno.setUsuario(usuarioBean.getCodUsuario());
				retorno.setClave(usuarioBean.getPass());
				retorno.setAutenticacion(Constants.AUTH_TABLAS_STR);
				retorno.setMensaje("Autenticacion Satisfactoria");
				
				bloqueoUsuarioEstadoPorCodUsuarioAct(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, 0,
						usuarioBean.getCodUsuario());
			}else if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_LDAP) {
				/*VERIFY REQ INCLUDE REGISTER ACCES LDAP USER*/
				usuarioBean = obtenerUsuarioPorCodUsuarioLDAP(codUsuario);
				accesoBean = new AccesoBean(null, usuarioBean.getCodUsuarioLDAP().toUpperCase(), Util.fechaActualTimeStamp(),
						ip, 0, "A", "I", 0, 1, usuarioBean.getCodUsuarioLDAP().toUpperCase(),
						Util.fechaActualTimeStamp(), usuarioBean.getCodUsuarioLDAP().toUpperCase(), Util.fechaActualTimeStamp(),
						token);

				grabarAcceso(accesoBean);
				
				String ultimoAcceso = "";
				List<AccesoBean> accesos = this.accesoService.obtenerUltimoAccesoSistema(usuarioBean.getCodUsuarioLDAP());
				if (accesos != null && !accesos.isEmpty()) {
					ultimoAcceso = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(accesos.get(0).getFecFecha());
				}	
				retorno.setUltimoAcceso(ultimoAcceso);
				retorno.setCodigo(Constants.SUCCESS);
				retorno.setUsuario(usuarioBean.getCodUsuarioLDAP());
				retorno.setAutenticacion(Constants.AUTH_LDAP_STR);
				retorno.setMensaje("Autenticacion Satisfactoria");
//				
//				bloqueoUsuarioEstadoPorCodUsuarioAct(usuarioBean.getCodUsuario(), Constants.ESTADO_ACTIVO, 0,
//						usuarioBean.getCodUsuario(), codSistema, codPerfil);
			}
			
		} catch (RuntimeException e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw e;
		} catch (Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw new RuntimeException(e);
		}

		return retorno;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerMenuActWs(Serializable codSistema, Serializable codUsuario, Serializable codPerfil)
			throws Exception {
		SistemaBean sistemaBean;
		List<SistemaModuloOpcionBean> listaMenu = null;
		sistemaBean = sistemaService.obtenerSistemaPorIdAct(codSistema);
		
		if(sistemaBean != null) {
			if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_TABLAS) {
				listaMenu = obtenerListadoSistemaModuloFormInicioPorCodigoAct(codSistema, ((String) codUsuario).toUpperCase(), codPerfil);
			}else if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_LDAP) {
				listaMenu = obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(codSistema, ((String) codUsuario).toUpperCase(), codPerfil);
			}
		}
		return listaMenu;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerPermisosActWS(Serializable codUsuario, Integer codSistema, Integer codPerfil)
			throws Exception {
		SistemaBean sistemaBean;
		List<String> listaPermisos = null;
		sistemaBean = sistemaService.obtenerSistemaPorIdAct(codSistema);
		
		if(sistemaBean != null) {
			if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_TABLAS) {
				listaPermisos = obtenerListadoUsuarioAccionPorCodigoAct(((String) codUsuario).toUpperCase(), codSistema, codPerfil);
			}else if(sistemaBean.getAutenticacion() == Constants.AUTENTICACION_LDAP) {
				listaPermisos = obtenerListadoUsuarioAccionPorCodigoLDAP(((String) codUsuario).toUpperCase(), codSistema, codPerfil);
			}
		}
		return listaPermisos;
	}
	
	/*PROCESOS LDAP*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	// TODO Auto-generated method stub
	public UsuarioBean obtenerUsuarioPorCodUsuarioLDAP(Serializable codUsuario) throws Exception {
		return this.usuarioService.obtenerUsuarioPorCodUsuarioLDAP(codUsuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaLDAP(Serializable codUsuario, Serializable codSistema)
			throws Exception {
		// TODO Auto-generated method stub
		return this.usuarioService.obtenerUsuarioPorCodUsuarioCodSistemaLDAP(codUsuario, codSistema);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String busquedaUsuarioLDAP(UsuarioBean usuarioLDAP) throws Exception {
		// TODO Auto-generated method stub
		return LDAPAutentication.busquedaUsuarioLDAP(usuarioLDAP);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean validarConectividadLDAP(String loginDN, String clave) throws Exception {
		// TODO Auto-generated method stub
		return LDAPAutentication.validarConectividadLDAP(loginDN, clave);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(Serializable codSistema,
			Serializable codUsuario, Serializable codPerfil) throws Exception {
		return this.sistemaModuloFormService.obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(codSistema, codUsuario, codPerfil);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoLDAP(Serializable codUsuario,
			Integer codSistema, Integer codPerfil) throws Exception {
		return this.usuarioFormularioAccionService.obtenerListadoUsuarioAccionPorCodigoLDAP(codUsuario, codSistema, codPerfil);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(Serializable codUsuario) throws Exception {
		return this.usuarioPerfilSistemaService.obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(codUsuario);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarAsociacionesActualesLDAP(Serializable codUsuarioLDAP) throws Exception {
		this.usuarioPerfilSistemaService.eliminarAsociacionesActualesLDAP(codUsuarioLDAP);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioPerfilSistemaLDAP(UsuarioPerfilBean usuarioLDAPPerfilBean) throws Exception {
		this.usuarioPerfilSistemaService.grabarUsuarioPerfilSistemaLDAP(usuarioLDAPPerfilBean);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TrabajadorBean obtenerTrabajadorWs(String codUsuario) throws Exception {
		UsuarioBean usuario;
		TrabajadorBean trabajador = null;
		try {
			usuario = usuarioService.obtenerUsuarioPorCodUsuarioAct(codUsuario);
			if(usuario != null && usuario.getCodFicha() != null) {
				trabajador = trabajadorService.obtenerDatosTrabajadorWs(usuario.getCodFicha());
			}
		}catch(Exception e) {
			throw new Exception();
		}
		return trabajador;
	}
	
	/*PROCESOS REDIS*/
	@SuppressWarnings("null")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Retorno validarTokenWs(String token) throws RuntimeException {
		Retorno retorno = null;
		UsuarioBean usuario = null;
		try {
			retorno = new Retorno();
			usuario = (UsuarioBean) redis.findByToken(token);
			if(usuario != null) {
				retorno.setCodigo(Constants.SUCCESS);
				retorno.setMensaje("Token Encontrado");
				retorno.setUsuario(usuario.getCodUsuario());
			}else {
				retorno.setCodigo(Constants.FAILURE);
				retorno.setTipo(Constants.FAILURE_TOKEN_INVALID);
				retorno.setMensaje("El token no esta registrado en servidor Redis.");
			}
		} catch (RuntimeException e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw e;
		} catch (Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setTipo(Constants.FAILURE);
			retorno.setMensaje("Error en la aplicacion.");
			throw new RuntimeException(e);
		}
		return retorno;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarTokenRedisWs(String token) throws RuntimeException {
		try {
			redis.deleteKeyToken(token);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
//	fin adecuacion seguridad2

}