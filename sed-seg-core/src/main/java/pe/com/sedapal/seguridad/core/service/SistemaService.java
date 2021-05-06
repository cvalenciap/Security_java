package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 *  
 */

public interface SistemaService {

	void grabarSistema(SistemaBean sistemaBean) throws Exception;

	//AplicacionBean actualizarAplicacion(AplicacionBean aplicacionBean) throws Exception;
	void actualizarSistema(SistemaBean sistemaBean) throws Exception;

	List<SistemaBean> obtenerListadoSistemas() throws Exception; 

	SistemaBean obtenerSistemaPorId(Serializable idSistema) throws Exception;

	void actualizarEstadoSistema(Serializable idSistema,Serializable estado,Serializable usuario) throws Exception;
	
	List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Serializable abreviatura) throws Exception;
	
	public List<SistemaBean> obtenerListadoSistemasActivos() throws Exception;
	
//	inicio adecuacion seguridad2
	public List<SistemaBean> obtenerDetalleSistemasUsuario(Serializable codUsuario) throws Exception;
	
	void grabarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	void actualizarSistemaAct(SistemaBean sistemaBean) throws Exception;
	
	SistemaBean obtenerSistemaPorIdAct(Serializable idSistema) throws Exception;
//	fin adecuacion seguridad2
}
