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

public interface TrabajadorMBDAO {
	
	List<TrabajadorBean> obtenerListadoTrabajador(Map<String, Object> parametrosBusqueda) throws Exception;

	TrabajadorBean obtenerTrabajadorPorCodigo(Map<String, Object> parametrosCodigo) throws Exception;

	TrabajadorBean obtenerTrabajadorPorFicha(Map<String, Object> parametrosFicha) throws Exception;
	
//	inicio adecuacion seguridad2
	TrabajadorBean obtenerDatosTrabajadorWs(Map<String, Object> parametrosFicha) throws Exception;
//	fin adecuacion seguridad2
}
