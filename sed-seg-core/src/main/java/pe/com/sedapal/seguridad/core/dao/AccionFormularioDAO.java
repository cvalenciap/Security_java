package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;

public interface AccionFormularioDAO {
	void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception;

	List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception;

	List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception;
}
