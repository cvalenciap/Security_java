package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 30/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface SistemaMBDAO {
	
	void grabarSistema(SistemaBean sistemaBean) throws Exception;

	void actualizarSistema(SistemaBean sistemaBean) throws Exception;

	List<SistemaBean> obtenerListadoSistemas(Map<String, Object> parametrosBusqueda) throws Exception;

	SistemaBean obtenerSistemaPorId(Map<String, Object> parametrosDetalle) throws Exception;

	void actualizarEstadoSistema(Map<String, Object> parametrosActualizar) throws Exception;
	
	List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Map<String, Object> parametrosAbreviatura) throws Exception;
	
	public List<SistemaBean> obtenerListadoSistemasActivos(Map<String, Object> parametrosActivos) throws Exception;
	
//	inicio adecuacion seguridad2
	public List<SistemaBean> obtenerDetalleSistemasUsuario(Map<String, Object> sistemasDetalle) throws Exception;
	
	void grabarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	void actualizarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	SistemaBean obtenerSistemaPorIdAct(Map<String, Object> parametrosDetalle) throws Exception;
//	fin adecuacion seguridad2
}
