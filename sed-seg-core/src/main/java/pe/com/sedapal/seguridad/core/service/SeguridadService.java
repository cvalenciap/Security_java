package pe.com.sedapal.seguridad.core.service;

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
import java.util.List;

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

public interface SeguridadService {

	// SISTEMA
	void grabarSistema(SistemaBean sistemaBean) throws Exception;

	// sistemaBean actualizarSistema(sistemaBean sistemaBean) throws Exception;
	void actualizarSistema(SistemaBean sistemaBean) throws Exception;

	List<SistemaBean> obtenerListadoSistemas() throws Exception;

	SistemaBean obtenerSistemaPorId(Serializable idSistema) throws Exception;

	void actualizarEstadoSistema(Serializable idSistema, Serializable estado, Serializable usuario) throws Exception;

	List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception;

	// USUARIO
	UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception;
	
	UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario,Serializable codSistema) throws Exception;

	List<UsuarioBean> obtenerListadoUsuario() throws Exception;

	void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,Serializable codSistema)
			throws Exception;

	void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable nrointentos,
			Serializable usuario,Serializable codSistema) throws Exception;

	void actualizarUsuarioClavesErroneas(Serializable codUsuario, int nroIntentos) throws Exception;

	void grabarUsuario(UsuarioBean usuarioBean) throws Exception;

	void actualizarUsuario(UsuarioBean usuarioBean) throws Exception;

	// USUARIO PERFIL SISTEMA
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema,
			Serializable codUsuario) throws Exception;

	//

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;

	UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario) throws Exception;

	void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable codUsuario,
			Serializable estado, Serializable usuario) throws Exception;

	// ACCESO
	void grabarAcceso(AccesoBean accesoBean) throws Exception;

	List<AccesoBean> obtenerAccesoSistema(Serializable usuario, Serializable ip) throws Exception;

	void actualizarAcceso(Serializable token) throws Exception;

	// PARAMETRO
	List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Serializable codParametro) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Serializable codParametro, Serializable codigo)
			throws Exception;

	List<ParametroBean> obtenerListadoParametroUnicos() throws Exception;

	void actualizarParametro(ParametroBean parametroBean) throws Exception;

	// PERFIL ACCION
	List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;

	PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception;

	void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception;

	// MODULO
	List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Serializable codSistema) throws Exception;

	SistemaModuloBean obtenerSistemaModuloPorCodigo(Serializable codSistema, Serializable codModulo) throws Exception;
	
	SistemaModuloBean obtenerSistemaModuloPorNombre(Serializable codSistema, Serializable nombreModulo) throws Exception;

	void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarEstadoSistemaModulo(Serializable codSistema, Serializable codModulo, Serializable estado,
			Serializable usuario) throws Exception;

	// MODULO FORM
	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema,
			Serializable codModulo) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Serializable parametro,
			Serializable codUsuario) throws Exception;

	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Serializable parametro,
			Serializable codUsuario) throws Exception;

	SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;

	int grabarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception;

	void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception;

	void actualizarEstadoSistemaModuloForm(Serializable codSistema, Serializable codModulo, Serializable codFormulario,
			Serializable estado, Serializable usuario) throws Exception;

	// ACCION FORMULARIO
	void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception;

	List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception;

	// MENU
	List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception;

	StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo) throws Exception;

	// PERFIL SISTEMA
	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception;

	PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception;

	void grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado,
			Serializable usuario) throws Exception;

	TrabajadorBean obtenerTrabajadorPorFicha(Serializable codFicha) throws Exception;

	// LOGICA DE LOG IN, CLAVES

	Retorno autenticacionUsuario(String codUsuario, int codSistema,String clave) throws RuntimeException;

	Retorno attemptAuthentication(String codUsuario, String ip,int codSistema) throws RuntimeException;

	Retorno loadUserByUsername(String username) throws RuntimeException;
	
//	inicio adecuacion seguridad2
//	Retorno onAuthenticationSuccess(String username, String ip, String token) throws RuntimeException;
	Retorno onAuthenticationSuccess(String username, String ip, String token, Integer codSistema) throws RuntimeException;
//	fin adecuacion seguridad2

	Retorno actualizarClave(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException;

	List<ClaveBean> obtenerClaves(String usuario) throws RuntimeException;

	void mantenimientoClaves(String usuario, String vpass, String flagTemp) throws RuntimeException;

	void ejecutarProcesos() throws RuntimeException;

	List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception;

//	void enviarCorreoNuevoUsuario(UsuarioBean usuarioBean, String clave) throws Exception;
	void enviarCorreoNuevoUsuario(UsuarioBean usuarioBean, String clave, List<UsuarioPerfilBean> asociaciones) throws Exception;

//	void enviarCorreoNuevaClave(TrabajadorBean trabajadorBean, String email, String clave) throws Exception;
	void enviarCorreoNuevaClave(String nombre, String email, String clave) throws Exception;

	void olvidarClave(String codUsuario) throws Exception;

	void enviarCorreo(CorreoBean correoBean) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametro(Serializable codParametro) throws Exception;

	ClaveBean obtenerUltimaClave(String usuario) throws Exception;

	int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;

	List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Serializable abreviatura) throws Exception;

	int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception;

	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;

	int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex, String tipoBusqueda, String valorBusqueda,
			String sortColumn) throws Exception;

	Retorno olvidarClaveWs(String codUsuario) throws RuntimeException;

	Retorno actualizarClaveWs(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException;
	
	public List<SistemaBean> obtenerListadoSistemasActivos() throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Serializable codSistema) throws Exception;
	
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema) throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Serializable codSistema) throws Exception;
	
	public Retorno autenticacionUsuarioCompleta(String codUsuario,String ip, String token,int codSistema) throws RuntimeException ;
	
	String autenticacionPb(String codUsuario, int codSistema,String clave);
	
	String actualizarClavePb(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR);
	
	String obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario);
	
	List<SistemaModuloOpcionBean> obtenerMenuWs(Serializable codUsuario,Serializable codSistema) throws Exception;
	
	List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;

	String olvidarClavePb(String codUsuario) throws RuntimeException;
	
//	inicio adecuacion seguridad2
	Retorno attemptAuthenticationAct(String codUsuario, String ip,int codSistema) throws RuntimeException;
	
	UsuarioBean obtenerUsuarioPorCodUsuarioAct(Serializable codUsuario) throws Exception;
	
	Retorno loadUserByUsernameAct(String username) throws RuntimeException;
	
	List<String> obtenerListadoUsuarioAccionPorCodigoAct(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception;
	
	List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaAct(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	void bloqueoUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable nrointentos,
			Serializable usuario) throws Exception;
	
	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoAct(Serializable codSistema,
			Serializable codUsuario, Serializable codPerfil) throws Exception;
	
	int obtenerListadoUsuarioSistemaPaginarTotalAct(String valueSearch) throws Exception;

//	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch,
//			String sortColumn) throws Exception;
	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch) throws Exception;
	
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(Serializable codUsuario) throws Exception;
	
	void actualizarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	
	void eliminarAsociacionesActuales(Serializable codUsuario) throws Exception;
	
	void actualizarAuditoriaUsuario(UsuarioPerfilBean asociacion, UsuarioBean usuario) throws Exception;
	
//	void grabarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	void grabarUsuarioAct(UsuarioBean usuarioBean, List<UsuarioPerfilBean> asociaciones) throws Exception;
	
	void actualizarUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception;
	
	void grabarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	void actualizarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	SistemaBean obtenerSistemaPorIdAct(Serializable idSistema) throws Exception;
	
	Retorno autenticacionUsuarioAct(String codUsuario, int codSistema,String clave) throws RuntimeException;
	
	public Retorno autenticacionUsuarioCompletaAct(String codUsuario,String ip, String token, int codSistema, int codPerfil) throws RuntimeException ;
	
	List<SistemaModuloOpcionBean> obtenerMenuActWs(Serializable codSistema, Serializable codUsuario, Serializable codPerfil) throws Exception;
	
	List<String> obtenerPermisosActWS(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception;
	
	/*PROCESOS LDAP*/
	UsuarioBean obtenerUsuarioPorCodUsuarioLDAP(Serializable codUsuario) throws Exception;
		
	List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaLDAP(Serializable codUsuario,Serializable codSistema) throws Exception;
	
	String busquedaUsuarioLDAP(UsuarioBean usuarioLDAP) throws Exception;
	
	boolean validarConectividadLDAP(String loginDN, String clave) throws Exception;
	
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(Serializable codUsuario) throws Exception;
	
	void eliminarAsociacionesActualesLDAP(Serializable codUsuarioLDAP) throws Exception;
	
	void grabarUsuarioPerfilSistemaLDAP(UsuarioPerfilBean usuarioPerfilBean) throws Exception;
	
	TrabajadorBean obtenerTrabajadorWs(String codUsuario) throws Exception;
	
	/*PROCESOS REDIS*/
	Retorno validarTokenWs(String token) throws RuntimeException;
	
	void eliminarTokenRedisWs(String token) throws RuntimeException;
//	fin adecuacion seguridad2
	
}
