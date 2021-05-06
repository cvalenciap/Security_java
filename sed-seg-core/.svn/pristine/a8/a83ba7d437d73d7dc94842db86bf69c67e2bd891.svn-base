package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;

public interface UsuarioDAO {
	List<UsuarioBean> obtenerListadoUsuario() throws Exception;

	UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception;

	void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,Serializable codSistema)
			throws Exception;

	void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable nrointentos,
			Serializable usuario,Serializable codSistema) throws Exception;

	void actualizarUsuarioClavesErroneas(Serializable codUsuario, int nroIntentos) throws Exception;

	void grabarUsuario(UsuarioBean usuarioBean) throws Exception;

	void actualizarUsuario(UsuarioBean usuarioBean) throws Exception;
	
	UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario,Serializable codSistema) throws Exception;

}
