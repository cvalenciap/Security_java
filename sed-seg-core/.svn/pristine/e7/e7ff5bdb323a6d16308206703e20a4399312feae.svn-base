package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 30/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface SistemaModuloMBDAO {
	
	List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;

	SistemaModuloBean obtenerSistemaModuloPorCodigo(Map<String, Object> parametrosCodigo) throws Exception;

	void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarEstadoSistemaModulo(Map<String, Object> parametrosActualizar) throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Map<String, Object> parametrosActivos) throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Map<String, Object> parametrosCodigoActivos) throws Exception;
	
	SistemaModuloBean obtenerSistemaModuloPorNombre(Map<String, Object> parametrosNombre) throws Exception;
}
