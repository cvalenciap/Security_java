package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;

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
public interface PerfilSistemaDAO {
	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception;
	
	int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception;
	
	List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, 
			String valueSearch, String sortColumn) throws Exception;

	PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception;

	int grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception;

	void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado,
			Serializable usuario) throws Exception;
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema) throws Exception;
}
