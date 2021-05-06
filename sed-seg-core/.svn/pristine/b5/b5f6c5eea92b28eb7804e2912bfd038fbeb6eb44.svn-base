package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

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
public interface SistemaModuloFormDAO {
	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema,
			Serializable codModulo) throws Exception;

	int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) throws Exception;

	List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex, String tipoBusqueda, String valorBusqueda,
			String sortColumn) throws Exception;

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

	List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;
}
