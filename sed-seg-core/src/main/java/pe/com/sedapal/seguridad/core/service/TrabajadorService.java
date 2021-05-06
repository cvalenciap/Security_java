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

import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;

public interface TrabajadorService {
	
	List<TrabajadorBean> obtenerListadoTrabajador() throws Exception;

	TrabajadorBean obtenerTrabajadorPorCodigo(Serializable codTrabajador) throws Exception;

	TrabajadorBean obtenerTrabajadorPorFicha(Serializable codFicha) throws Exception;
	
//	inicio adecuacion seguridad2
	TrabajadorBean obtenerDatosTrabajadorWs(Integer codFicha) throws Exception;
//	fin adecuacion seguridad2
}
