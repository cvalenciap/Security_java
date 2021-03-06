package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;

public interface UsuarioPerfilSistemaService {
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema,
			Serializable codUsuario) throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;;

	UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario) throws Exception;

	void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable codUsuario,
			Serializable estado, Serializable usuario) throws Exception;

	int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;
	
//	inicio adecuacion seguridad2
	int obtenerListadoUsuarioSistemaPaginarTotalAct(String valueSearch) throws Exception;

//	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch,
//			String sortColumn) throws Exception;
	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch) throws Exception;
	
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(Serializable codUsuario) throws Exception;
	
	void eliminarAsociacionesActuales(Serializable codUsuario) throws Exception;
	
	void actualizarAuditoriaUsuario(UsuarioPerfilBean asociacion, UsuarioBean usuario) throws Exception;
	
	/*PROCESOS LDAP*/
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(Serializable codUsuario) throws Exception;
	
	void eliminarAsociacionesActualesLDAP(Serializable codUsuarioLDAP) throws Exception;
	
	void grabarUsuarioPerfilSistemaLDAP(UsuarioPerfilBean usuarioLDAPPerfilBean) throws Exception;
//	fin adecuacion seguridad2
}
