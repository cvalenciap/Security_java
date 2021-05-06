package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

public interface UsuarioFormularioAccionService {
	

	List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception;

	List<String> obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario) throws Exception;
	
//	inicio adecuacion seguridad2
	List<String> obtenerListadoUsuarioAccionPorCodigoAct(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception;
	
	List<String> obtenerListadoUsuarioAccionPorCodigoPbAct(Serializable codUsuario, Integer codSistema) throws Exception;
	
	/*PROCESOS LDAP*/
	List<String> obtenerListadoUsuarioAccionPorCodigoLDAP(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception;
	
//	fin adecuacio seguridad2
}

