package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;

public interface PerfilSistemaService {

	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception;

	int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception;

	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;

	PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception;

	int grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado,
			Serializable usuario) throws Exception;

	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema)
			throws Exception;
	
//	inicio adecuacion seguridad2
	List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigo(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	List<PerfilSistemaBean> obtenerPerfilesSistemaActivos(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	List<PerfilSistemaBean> obtenerDetallePerfilesUsuario(Serializable codUsuario) throws Exception;
	
	/*PROCESOS LDAP*/
	List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigoLDAP(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	List<PerfilSistemaBean> obtenerPerfilesSistemaActivosLDAP(Serializable codUsuario, Serializable codSistema) throws Exception;
	
//	fin adecuacion seguridad2
}
