package pe.com.sedapal.seguridad.ws;

import java.io.Serializable;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.ws.service.IntegracionService;

@WebService(targetNamespace = "http://www.sedapal.com.pe/seguridad", serviceName = "seguridadWsService", portName = "seguridadWsPort", name = "seguridadws", endpointInterface = "pe.com.sedapal.seguridad.ws.SeguridadWs")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class SeguridadWsImpl implements SeguridadWs {

	@Autowired
	private IntegracionService integracionService;

	private final Logger logger = LoggerFactory.getLogger(SeguridadWsImpl.class);

	public Retorno autenticacionUsuarioWs(String usuario, int codSistema,String clave) {
		logger.info("*************** usuario *************** " + usuario);
		logger.info("*************** codSistema *************** " + codSistema);
		return this.integracionService.autenticacionUsuarioWs(usuario, codSistema,clave);
	}

	public Retorno autenticacionUsuarioCompletaWs(String usuario, String ip, String token, int codSistema) {
		logger.info("*************** usuario *************** " + usuario);
		logger.info("*************** ip *************** " + ip);
		logger.info("*************** token *************** " + token);
		logger.info("*************** codSistema *************** " + codSistema);
		return this.integracionService.autenticacionUsuarioCompletaWs(usuario, ip, token, codSistema);
	}

	public Retorno olvidarClaveWs(String usuario) {
		logger.info("*************** usuario *************** " + usuario);
		return this.integracionService.olvidarClaveWs(usuario);
	}

	public Retorno actualizarClaveWs(String usuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		logger.info("*************** usuario *************** " + usuario);
		logger.info("*************** claveActual *************** " + claveActual);
		logger.info("*************** nuevaClave *************** " + nuevaClave);
		logger.info("*************** nuevaClaveR *************** " + nuevaClaveR);
		return this.integracionService.actualizarClaveWs(usuario, claveActual, nuevaClave, nuevaClaveR);
	}

	@Override
	public List<SistemaModuloOpcionBean> obtenerMenuWs(String codUsuario,String codSistema){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** usuario *************** " + codUsuario);
			logger.info("*************** sistema *************** " + codSistema);
			return this.integracionService.obtenerMenuWs(codUsuario, codSistema);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Retorno logoutWs(String token) {
		logger.info("*************** usuario *************** " + token);
		try {
			return this.integracionService.logoutWs(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<String> obtenerPermisosWs(String codUsuario){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** usuario *************** " + codUsuario);			
			return this.integracionService.obtenerPermisosWs(codUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	inicio adecuacion seguridad2
	public Retorno autenticacionUsuarioActWs(String usuario, int codSistema,String clave) {
		logger.info("*************** usuario *************** " + usuario);
		logger.info("*************** codSistema *************** " + codSistema);
		return this.integracionService.autenticacionUsuarioActWs(usuario, codSistema,clave);
	}

	public Retorno autenticacionUsuarioCompletaActWs(String usuario, String ip, String token, int codSistema, int codPerfil) {
		logger.info("*************** usuario *************** " + usuario);
		logger.info("*************** ip *************** " + ip);
		logger.info("*************** token *************** " + token);
		logger.info("*************** codSistema *************** " + codSistema);
		logger.info("*************** codPerfil *************** " + codPerfil);
		return this.integracionService.autenticacionUsuarioCompletaActWs(usuario, ip, token, codSistema, codPerfil);
	}
	
	@Override
	public List<SistemaModuloOpcionBean> obtenerMenuActWs(String codSistema, String codUsuario, String codPerfil){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** usuario *************** " + codUsuario);
			logger.info("*************** sistema *************** " + codSistema);
			logger.info("*************** perfil *************** " + codPerfil);
			return this.integracionService.obtenerMenuActWs(codSistema, codUsuario, codPerfil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<String> obtenerPermisosActWs(String codUsuario, Integer codSistema, Integer codPerfil){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** usuario *************** " + codUsuario);
			logger.info("*************** sistema *************** " + codSistema);
			logger.info("*************** perfil *************** " + codPerfil);
			return this.integracionService.obtenerPermisosActWs(codUsuario, codSistema, codPerfil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public TrabajadorBean obtenerTrabajadorWs(String codUsuario){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** usuario *************** " + codUsuario);
			return this.integracionService.obtenerTrabajadorWs(codUsuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*PROCESIS REDIS*/
	@Override
	public Retorno validarTokenWs(String token){
		// TODO Auto-generated method stub
		try {
			logger.info("*************** token *************** " + token);
			return this.integracionService.validarTokenWs(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	fin adecuacion seguridad2
	
}