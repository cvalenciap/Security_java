package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;

public interface PerfilAccionService {

	List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;

	PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception;

	void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception;

	void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception;
}
