package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */
public interface SistemaModuloFormService {

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema,
			Serializable codModulo) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Serializable parametro,
			Serializable codUsuario) throws Exception;

	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Serializable parametro,
			Serializable codUsuario) throws Exception;

	SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;

	int grabarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception;

	void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception;

	void actualizarEstadoSistemaModuloForm(Serializable codSistema, Serializable codModulo, Serializable codFormulario,
			Serializable estado, Serializable usuario) throws Exception;

	int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex, String tipoBusqueda, String valorBusqueda,
			String sortColumn) throws Exception;
	
	List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;
	
//	inicio adecuacion seguridad2
	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoAct(Serializable codSistema,
			Serializable codUsuario, Serializable codPerfil) throws Exception;
	
	/*PROCESOS LDAP*/
	List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(Serializable codSistema,
			Serializable codUsuario, Serializable codPerfil) throws Exception;
//	fin adecuacion seguridad2
}
