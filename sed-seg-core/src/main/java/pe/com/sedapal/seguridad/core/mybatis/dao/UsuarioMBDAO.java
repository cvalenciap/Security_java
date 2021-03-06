package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;

public interface UsuarioMBDAO {
	
	List<UsuarioBean> obtenerListadoUsuario(Map<String, Object> parametrosBusqueda) throws Exception;

	UsuarioBean obtenerUsuarioPorCodUsuario(Map<String, Object> parametrosDetalle) throws Exception;

	void actualizarUsuarioEstadoPorCodUsuario(Map<String, Object> parametrosActualizar) throws Exception;

	void bloqueoUsuarioEstadoPorCodUsuario(Map<String, Object> parametrosBloqueo) throws Exception;

	void actualizarUsuarioClavesErroneas(Map<String, Object> parametrosActClave) throws Exception;

	void grabarUsuario(UsuarioBean usuarioBean) throws Exception;

	void actualizarUsuario(UsuarioBean usuarioBean) throws Exception;
	
	UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Map<String, Object> parametrosUsuarioCod) throws Exception;
	
//	inicio adecuacion seguridad2
	
	UsuarioBean obtenerUsuarioPorCodUsuarioAct(Map<String, Object> parametrosDetalle) throws Exception;
	
	void bloqueoUsuarioEstadoPorCodUsuarioAct(Map<String, Object> parametrosBloqueoLista) throws Exception;
	
	void actualizarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	
	void grabarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	
	void actualizarUsuarioEstadoPorCodUsuarioAct(Map<String, Object> parametrosActualizarAct) throws Exception;
	
	/*PROCESOS LDAP*/
	UsuarioBean obtenerUsuarioPorCodUsuarioLDAP(Map<String, Object> parametrosDetalle) throws Exception;
		
	List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaLDAP(Map<String, Object> parametrosUsuarioCod) throws Exception;
	
	void grabarUsuarioLDAP(UsuarioBean usuarioLDAPBean) throws Exception;
//	fin adecuacion seguridad2
		
	UsuarioBean validacionUsuarioExterno(Map<String, Object> parametrosValidExterno) throws Exception;
}

