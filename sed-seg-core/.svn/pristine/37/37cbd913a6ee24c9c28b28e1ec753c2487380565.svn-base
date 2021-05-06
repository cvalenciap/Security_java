package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;

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
public interface ParametroDAO {
	List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Serializable codParametro) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Serializable codParametro, Serializable codigo) throws Exception;

	List<ParametroBean> obtenerListadoParametroUnicos() throws Exception;

	void actualizarParametro(ParametroBean parametroBean) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametro(Serializable codParametro) throws Exception;
}
