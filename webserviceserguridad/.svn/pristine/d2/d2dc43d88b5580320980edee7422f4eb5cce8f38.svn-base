package pe.com.sedapal.seguridad.ws;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.ws.service.IntegracionService;

//Service Implementation Bean

@WebService(endpointInterface = "pe.com.sedapal.seguridad.ws.PowerWs")
public class PowerWsImpl implements PowerWs {

	@Autowired
	private IntegracionService integracionService;

	private final Logger logger = LoggerFactory.getLogger(PowerWsImpl.class);

	@Override
	public String autenticacionPb(String codUsuario, int codSistema, String clave) {
		// TODO Auto-generated method stub
		logger.info("##### INICIANDO METODO AUTENTICACION PB ####");
		return this.integracionService.autenticacionPb(codUsuario, codSistema, clave);
	}

	@Override
	public String actualizarClavePb(String codUsuario, String claveActual, String nuevaClave, String nuevaClaveR) {
		// TODO Auto-generated method stub
		logger.info("##### INICIANDO METODO ACTUALIZAR CLAVE PB ####");
		return this.integracionService.actualizarClavePb(codUsuario, claveActual, nuevaClave, nuevaClaveR);
	}

	@Override
	public String menuPb(String codUsuario) {
		// TODO Auto-generated method stub
		return this.integracionService.menuPb(codUsuario);
	}
	

	@Override
	public String obtenerPermisosPb(String codUsuario) {
		
		logger.info("##### OBTENER PERMISOS DE USUARIO PB ####"+codUsuario);
		return this.integracionService.obtenerPermisosPb(codUsuario);
	}
	
	@Override
	public String olvidarClavePb(String usuario) {
		logger.info("*************** usuario *************** " + usuario);
		return this.integracionService.olvidarClavePb(usuario);
	}
}