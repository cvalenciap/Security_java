package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;

public interface AccesoService {

	void grabarAcceso(AccesoBean accesoBean) throws Exception;

	List<AccesoBean> obtenerAccesoSistema(Serializable usuario, Serializable ip) throws Exception;

	void actualizarAcceso(Serializable token) throws Exception;

	List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception;
}
