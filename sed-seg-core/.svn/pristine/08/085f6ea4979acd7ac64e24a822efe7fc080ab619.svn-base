package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 24/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface AccesoMBDAO {

	void grabarAcceso(AccesoBean accesoBean) throws Exception;

	AccesoBean obtenerAccesoSistema(Map<String, Object> parametrosDetalle) throws Exception;

	List<AccesoBean> obtenerUltimoAccesoSistema(Map<String, Object> parametrosBusqueda) throws Exception;
	
//	AccesoBean obtenerUltimoAccesoSistema(Map<String, Object> parametrosBusqueda) throws Exception;

	void actualizarAcceso(String token) throws Exception;
}
