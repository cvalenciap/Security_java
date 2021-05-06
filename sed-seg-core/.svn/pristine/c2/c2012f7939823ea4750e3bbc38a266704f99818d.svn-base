package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.bean.DatosBean;
import pe.com.sedapal.seguridad.core.bean.MenuBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 29/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface PerfilAccionMBDAO {

	List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;

	PerfilAccionBean obtenerPerfilAccionPorCodigo(Map<String, Object> parametrosDetalle) throws Exception;

	void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void eliminarPerfilAccion(Map<String, Object> parametrosEliminar) throws Exception;

//	no se encuentra en el paquete PCK_SEGURIDAD
//	List<DatosBean> obtenerListadoPerfilAccion(int pageSize, int pageIndex, String sortColumn) throws Exception;
}
