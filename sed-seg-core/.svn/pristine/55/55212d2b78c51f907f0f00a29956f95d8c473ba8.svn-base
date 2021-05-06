package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 25/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface AccionFormularioMBDAO {
	void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Map<String, Object> parametrosDetalle) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;

	List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Map<String, Object> parametrosUsuario) throws Exception;

}
