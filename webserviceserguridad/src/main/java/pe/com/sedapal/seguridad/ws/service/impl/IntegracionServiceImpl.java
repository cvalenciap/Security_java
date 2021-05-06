package pe.com.sedapal.seguridad.ws.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.ws.service.IntegracionService;

@Service("integracionService")
public class IntegracionServiceImpl implements IntegracionService {

	@Autowired
	private SeguridadService seguridadService;
	
	
	@Override
	public Retorno autenticacionUsuarioWs(String usuario,int codSistema,String clave) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.autenticacionUsuario(usuario, codSistema,clave);
	}
	
	@Override
	public Retorno autenticacionUsuarioCompletaWs(String usuario, String ip, String token,int codSistema) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.autenticacionUsuarioCompleta(usuario, ip, token, codSistema);
	}

	@Override
	public Retorno olvidarClaveWs(String codUsuario) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.olvidarClaveWs(codUsuario);
	}
	
	@Override
	public String olvidarClavePb(String codUsuario) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.olvidarClavePb(codUsuario);
	}

	@Override
	public Retorno actualizarClaveWs(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.actualizarClaveWs(codUsuario, claveActual, nuevaClave, nuevaClaveR);
	}

	@Override
	public String autenticacionPb(String codUsuario, int codSistema,String clave) {
		// TODO Auto-generated method stub
		return this.seguridadService.autenticacionPb(codUsuario, codSistema,clave);
	}

	@Override
	public String actualizarClavePb(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		// TODO Auto-generated method stub
		return this.seguridadService.actualizarClavePb(codUsuario, claveActual, nuevaClave, nuevaClaveR);
	}

	@Override
	public String menuPb(String codUsuario) {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerListadoUsuarioAccionPorCodigoPb(codUsuario);
	}

	@Override
	public List<SistemaModuloOpcionBean> obtenerMenuWs(Serializable codUsuario,Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerMenuWs(codUsuario, codSistema);
	}

	@Override
	public Retorno logoutWs(String token) throws Exception {
		// TODO Auto-generated method stub
		Retorno retorno = new Retorno();
		this.seguridadService.actualizarAcceso(token);
//		inicio adecuacion seguridad2
		try {
			this.seguridadService.eliminarTokenRedisWs(token);
			retorno.setCodigo(Constants.SUCCESS);
			retorno.setMensaje("Eliminación de acceso y token satisfactoriamente.");
		}catch(Exception e) {
			retorno.setCodigo(Constants.FAILURE);
			retorno.setMensaje("No se pudo eliminar el token del servidor Redis.");
		}
		return retorno;
//		fin adecuacion seguridad2
	}

	@Override
	public List<String> obtenerPermisosWs(String codUsuario) throws Exception {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerListadoUsuarioAccionPorCodigo(codUsuario.toUpperCase());
	}

	@Override
	public String obtenerPermisosPb(String codUsuario) {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerListadoUsuarioAccionPorCodigoPb(codUsuario.toUpperCase());
	}
	
//	inicio adecuacion seguridad2
	@Override
	public Retorno autenticacionUsuarioActWs(String usuario,int codSistema,String clave) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.autenticacionUsuarioAct(usuario, codSistema,clave);
	}
	
	@Override
	public Retorno autenticacionUsuarioCompletaActWs(String usuario, String ip, String token, int codSistema, int codPerfil) throws RuntimeException {
		// TODO Auto-generated method stub
		return this.seguridadService.autenticacionUsuarioCompletaAct(usuario, ip, token, codSistema, codPerfil);
	}
	
	@Override
	public List<SistemaModuloOpcionBean> obtenerMenuActWs(Serializable codSistema, Serializable codUsuario, Serializable codPerfil) throws Exception {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerMenuActWs(codSistema, codUsuario, codPerfil);
	}
	
	@Override
	public List<String> obtenerPermisosActWs(String codUsuario, Integer codSistema, Integer codPerfil) throws Exception {
		// TODO Auto-generated method stub
		return this.seguridadService.obtenerPermisosActWS(codUsuario, codSistema, codPerfil);
	}
	
	@Override
	public TrabajadorBean obtenerTrabajadorWs(String codUsuario) throws Exception {
		return this.seguridadService.obtenerTrabajadorWs(codUsuario);
	}
	
	/*PROCESOS REDIS*/
	@Override
	public Retorno validarTokenWs(String token) throws RuntimeException{
		return this.seguridadService.validarTokenWs(token);
	}
//	fin adecuacion seguridad2
}
