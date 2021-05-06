package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;

public interface AccesoDAO {

	void grabarAcceso(AccesoBean accesoBean) throws Exception;

	List<AccesoBean> obtenerAccesoSistema(Serializable usuario, Serializable ip) throws Exception;

	List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception;

	void actualizarAcceso(Serializable token) throws Exception;
}
