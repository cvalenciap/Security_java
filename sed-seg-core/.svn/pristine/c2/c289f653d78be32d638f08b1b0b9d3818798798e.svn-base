package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

/*
 * 
 * #Proyecto: Implementación ORM.
 * #Fecha Creación: 30/10/2018.
 * #Autor: Carlos Valencia.
 * #Empresa: CANVIA.
 */

public interface SistemaModuloFormMBDAO {
	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Map<String, Object> parametrosBusqueda) throws Exception;

	void obtenerListadoSistemaModuloFormPaginarTotal(Map<String, Object> parametrosPaginaTotal) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(Map<String, Object> parametrosPaginar) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Map<String, Object> parametrosDetalle) throws Exception;

	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Map<String, Object> parametrosDetalle2) throws Exception;

	SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Map<String, Object> parametrosCodigo) throws Exception;

	int grabarSistemaModuloForm(Map<String, Object> parametrosGrabar) throws Exception;

	void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception;

	void actualizarEstadoSistemaModuloForm(Map<String, Object> parametrosActualizar) throws Exception;

	List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Map<String, Object> parametrosCodigoHijo) throws Exception;
	
//	inicio adecuacion seguridad2
	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoAct(Map<String, Object> parametrosDetalleLista) throws Exception;
	
	/*PROCESOS LDAP*/
	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(Map<String, Object> parametrosDetalleLista) throws Exception;
	
//	fin adecuacion seguridad2
}
