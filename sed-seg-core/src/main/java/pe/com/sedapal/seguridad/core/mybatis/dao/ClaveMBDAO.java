package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 24/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface ClaveMBDAO {

	List<ClaveBean> obtenerClaves(Map<String, Object> parametrosBusqueda) throws Exception;

	void mantenimientoClaves(Map<String, Object> parametrosMantenimiento) throws Exception;

//	void ejecutarProcesos() throws Exception;
	
	void ejecutarProcesosTemporal(Map<String, Object> parametrosProcesos) throws Exception;
	
	void ejecutarProcesosObligatorio(Map<String, Object> parametrosProcesos) throws Exception;
	
	ClaveBean obtenerUltimaClave(Map<String, Object> parametrosBusqueda) throws Exception;
}
