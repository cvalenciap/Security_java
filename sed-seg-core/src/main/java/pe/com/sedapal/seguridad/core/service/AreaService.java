package pe.com.sedapal.seguridad.core.service;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 06/01/2017.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 *  
 */

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.AreaBean;

public interface AreaService {

//	List<AreaBean> obtenerListadoArea() throws Exception;
//
//	AreaBean obtenerAreaPorCodigo(Serializable codArea) throws Exception;
	
//	metodos mybatis
	List<AreaBean> obtenerListadoArea() throws Exception;

	AreaBean obtenerAreaPorCodigo(Serializable codArea) throws Exception;
//	fin metodos mybatis
}
