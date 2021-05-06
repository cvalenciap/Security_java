package pe.com.sedapal.seguridad.ws.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;

public interface IntegracionService {

	/*
	 * Retorno attemptAuthentication(String codUsuario, String ip) throws
	 * RuntimeException;
	 * 
	 * Retorno loadUserByUsername(String username) throws RuntimeException;
	 * 
	 * Retorno onAuthenticationSuccess(String username,String ip,String token)
	 * throws RuntimeException;
	 */
	Retorno autenticacionUsuarioWs(String usuario, int codSistema,String clave) throws RuntimeException;

	Retorno olvidarClaveWs(String codUsuario) throws RuntimeException;

	Retorno actualizarClaveWs(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException;

	public Retorno autenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema)
			throws RuntimeException;
	
	String autenticacionPb(String codUsuario, int codSistema,String clave);
	
	String actualizarClavePb(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR);
	
	String menuPb(String codUsuario);

	List<SistemaModuloOpcionBean> obtenerMenuWs(Serializable codUsuario,Serializable codSistema) throws Exception;
	
	Retorno logoutWs(String token) throws Exception;

	List<String> obtenerPermisosWs(String codUsuario) throws Exception;

	String obtenerPermisosPb(String codUsuario);

	String olvidarClavePb(String codUsuario) throws RuntimeException;
	
//	inicio adecuacion seguridad2
	Retorno autenticacionUsuarioActWs(String usuario, int codSistema,String clave) throws RuntimeException;
	
	public Retorno autenticacionUsuarioCompletaActWs(String usuario, String ip, String token, int codSistema, int codPerfil)
			throws RuntimeException;
	
	List<SistemaModuloOpcionBean> obtenerMenuActWs(Serializable codSistema, Serializable codUsuario, Serializable codPerfil) throws Exception;
	
	List<String> obtenerPermisosActWs(String codUsuario, Integer codSistema, Integer codPerfil) throws Exception;
	
	TrabajadorBean obtenerTrabajadorWs(String codUsuario) throws Exception;
	
	/*PROCESOS REDIS*/
	public Retorno validarTokenWs(String token) throws RuntimeException;
//	fin adecuacion seguridad2
}
