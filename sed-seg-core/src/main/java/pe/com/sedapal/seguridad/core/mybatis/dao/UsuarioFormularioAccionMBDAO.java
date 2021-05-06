package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 31/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface UsuarioFormularioAccionMBDAO {
	
	List<String> obtenerListadoUsuarioAccionPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;
	
	List<String> obtenerListadoUsuarioAccionPorCodigoPb(Map<String, Object> parametrosDetalle) throws Exception;
	
//	inicio adecuacion seguridad2
	List<String> obtenerListadoUsuarioAccionPorCodigoAct(Map<String, Object> parametrosBusquedaLista) throws Exception;
	
	List<String> obtenerListadoUsuarioAccionPorCodigoLDAP(Map<String, Object> parametrosBusquedaLista) throws Exception;
//	fin adecuacion seguridad2
}
