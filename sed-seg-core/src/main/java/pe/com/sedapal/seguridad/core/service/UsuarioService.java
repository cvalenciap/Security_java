package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;

public interface UsuarioService {
	List<UsuarioBean> obtenerListadoUsuario() throws Exception;

	UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception;

	void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,Serializable codSistema)
			throws Exception;
	
	void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable nrointentos, Serializable usuario, Serializable codSistema)
			throws Exception;
	
	void actualizarUsuarioClavesErroneas(Serializable codUsuario,int nroIntentos) throws Exception;
	
	void grabarUsuario(UsuarioBean usuarioBean) throws Exception;
	
	void actualizarUsuario(UsuarioBean usuarioBean) throws Exception;
	
	UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario,Serializable codSistema) throws Exception;
	
//	inicio adecuacion seguridad2
	
	UsuarioBean obtenerUsuarioPorCodUsuarioAct(Serializable codUsuario) throws Exception;
	
	List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaAct(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	void bloqueoUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable nrointentos, Serializable usuario) throws Exception;
	
	void actualizarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	
	void grabarUsuarioAct(UsuarioBean usuarioBean) throws Exception;
	
	void actualizarUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception;
	
	/*PROCESOS LDAP*/
	UsuarioBean obtenerUsuarioPorCodUsuarioLDAP(Serializable codUsuario) throws Exception;
		
	List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaLDAP(Serializable codUsuario, Serializable codSistema) throws Exception;
	
	void grabarUsuarioLDAP(UsuarioBean usuarioLDAPBean) throws Exception;
//	fin adecuacion seguridad2
	
	boolean validacionUsuarioExterno(String codUsuario, Integer codEmpresa, String dniExterno) throws Exception;
}
