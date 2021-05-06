package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.DatosBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;

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
public interface PerfilAccionDAO {
	List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;

	PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception;

	void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception;

	List<DatosBean> obtenerListadoPerfilAccion(int pageSize, int pageIndex, String sortColumn) throws Exception;

}
