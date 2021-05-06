package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;

public interface UsuarioPerfilSistemaMBDAO {
	
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema(Map<String, Object> parametrosBusqueda) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistema(Map<String, Object> parametrosDetalle) throws Exception;

	void obtenerListadoUsuarioSistemaPaginarTotal(Map<String, Object> parametrosPagina) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(Map<String, Object> parametrosPaginar) throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Map<String, Object> parametrosCodigo) throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Map<String, Object> parametrosUsuario) throws Exception;

	UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Map<String, Object> parametrosUserCodigo) throws Exception;

	void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarEstadoUsuarioPerfilSistema(Map<String, Object> parametrosActualizar) throws Exception;
	
//	inicio adecuacion seguridad2
	void obtenerListadoUsuarioSistemaPaginarTotalAct(Map<String, Object> parametrosPagina) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(Map<String, Object> parametrosPaginar) throws Exception;
	
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(Map<String, Object> parametrosAsociados) throws Exception;
	 
	void eliminarAsociacionesActuales(Map<String, Object> parametrosEliminarAsociacion) throws Exception;
	
	void actualizarAuditoriaUsuario(Map<String, Object> parametrosAuditoria) throws Exception;
	
	/*PROCESOS LDAP*/
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(Map<String, Object> parametrosAsociados) throws Exception;
	
	void eliminarAsociacionesActualesLDAP(Map<String, Object> parametrosEliminarAsociacion) throws Exception;
	
	void grabarUsuarioPerfilSistemaLDAP(UsuarioPerfilBean usuarioPerfilBean) throws Exception;
	
//	fin adecuacion seguridad2
}
