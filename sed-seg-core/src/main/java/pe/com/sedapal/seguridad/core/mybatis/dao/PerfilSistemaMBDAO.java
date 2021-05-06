package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 30/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface PerfilSistemaMBDAO {

	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;
	
	void obtenerListadoPerfilSistemaPaginarTotal(Map<String, Object> parametrosPagina) throws Exception;
	
	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(Map<String, Object> parametrosPaginar) throws Exception;

	PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Map<String, Object> parametrosDetalle) throws Exception;

	int grabarPerfilSistema(Map<String, Object> parametrosGrabar) throws Exception;

	void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarEstadoPerfilSistema(Map<String, Object> parametrosActualizar) throws Exception;
	
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Map<String, Object> parametrosActivos) throws Exception;
	
//	inicio adecuacion seguridad2
	
	List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigo(Map<String, Object> parametrosDetalleLista) throws Exception;
	
	List<PerfilSistemaBean> obtenerPerfilesSistemaActivos(Map<String, Object> parametrosDetalleLista) throws Exception;
	
	List<PerfilSistemaBean> obtenerDetallePerfilesUsuario(Map<String, Object> detallePerfiles) throws Exception;
	
	/*PROCESOS LDAP*/
	List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigoLDAP(Map<String, Object> parametrosDetalleLista) throws Exception;
		
	List<PerfilSistemaBean> obtenerPerfilesSistemaActivosLDAP(Map<String, Object> parametrosDetalleLista) throws Exception;
	
//	fin adecuacion seguridad2
}
