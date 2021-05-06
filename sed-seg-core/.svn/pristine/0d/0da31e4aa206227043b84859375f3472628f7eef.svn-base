package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */
public interface SistemaModuloService {

	List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Serializable codSistema) throws Exception;

	SistemaModuloBean obtenerSistemaModuloPorCodigo(Serializable codSistema, Serializable codModulo) throws Exception;

	void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception;

	void actualizarEstadoSistemaModulo(Serializable codSistema, Serializable codModulo, Serializable estado,
			Serializable usuario) throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Serializable codSistema) throws Exception;
	
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Serializable codSistema) throws Exception;
	
	SistemaModuloBean obtenerSistemaModuloPorNombre(Serializable codSistema, Serializable nombreModulo) throws Exception;

//	SistemaModuloBean obtenerSistemaModuloPorNombre1(Serializable codSistema, Serializable c) throws Exception;
}
