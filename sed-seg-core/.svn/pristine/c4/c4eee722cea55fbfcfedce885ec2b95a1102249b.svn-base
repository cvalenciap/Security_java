package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.MenuBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 29/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface ParametroMBDAO {

	List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Map<String, Object> parametrosBusqueda) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Map<String, Object> parametrosDetalle) throws Exception;

	List<ParametroBean> obtenerListadoParametroUnicos(Map<String, Object> parametrosUnicos) throws Exception;

	void actualizarParametro(ParametroBean parametroBean) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametro(Map<String, Object> parametrosCodigo) throws Exception;
}
