package pe.com.sedapal.seguridad.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.Retorno;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;

@WebService(targetNamespace = "http://www.sedapal.com.pe/seguridad", serviceName = "seguridadWsService", portName = "seguridadWsPort", name = "seguridadws", endpointInterface = "pe.com.sedapal.seguridad.ws.SeguridadWs")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface SeguridadWs {
	@Autowired

	@WebMethod(operationName = "autenticacionUsuarioWs", action = "http://www.sedapal.com.pe/seguridad/autenticacionUsuario")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno autenticacionUsuarioWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codSistema,
			@WebParam(name = "clave", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String clave);

	@WebMethod(operationName = "autenticacionUsuarioCompletaWs", action = "http://www.sedapal.com.pe/seguridad/autenticacionUsuarioCompleta")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno autenticacionUsuarioCompletaWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "ip", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String ip,
			@WebParam(name = "token", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String token,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codSistema);

	@WebMethod(operationName = "olvidarClaveWs", action = "http://www.sedapal.com.pe/seguridad/olvidarClave")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno olvidarClaveWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario);

	@WebMethod(operationName = "actualizarClaveWs", action = "http://www.sedapal.com.pe/seguridad/actualizarClave")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno actualizarClaveWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "claveActual", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String claveActual,
			@WebParam(name = "nuevaClave", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String nuevaClave,
			@WebParam(name = "nuevaClaveR", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String nuevaClaveR);

	@WebMethod(operationName = "obtenerMenuWs", action = "http://www.sedapal.com.pe/seguridad/obtenerMenu")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public List<SistemaModuloOpcionBean> obtenerMenuWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codSistema);
	
	
	@WebMethod(operationName = "logoutWs", action = "http://www.sedapal.com.pe/seguridad/logout")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno logoutWs(
			@WebParam(name = "token", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String token);
	
	
	@WebMethod(operationName = "obtenerPermisosWs", action = "http://www.sedapal.com.pe/seguridad/obtenerPermisos")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public List<String> obtenerPermisosWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario);
	
	
//	inicio adecuacion seguridad2
	@WebMethod(operationName = "autenticacionUsuarioActWs", action = "http://www.sedapal.com.pe/seguridad/autenticacionUsuarioAct")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno autenticacionUsuarioActWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codSistema,
			@WebParam(name = "clave", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String clave);

	@WebMethod(operationName = "autenticacionUsuarioCompletaActWs", action = "http://www.sedapal.com.pe/seguridad/autenticacionUsuarioCompletaAct")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno autenticacionUsuarioCompletaActWs(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario,
			@WebParam(name = "ip", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String ip,
			@WebParam(name = "token", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String token,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codSistema,
			@WebParam(name = "codPerfil", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codPerfil);
	
	@WebMethod(operationName = "obtenerMenuActWs", action = "http://www.sedapal.com.pe/seguridad/obtenerMenuAct")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public List<SistemaModuloOpcionBean> obtenerMenuActWs(
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codSistema,
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario,
			@WebParam(name = "codPerfil", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codPerfil);
	
	@WebMethod(operationName = "obtenerPermisosActWs", action = "http://www.sedapal.com.pe/seguridad/obtenerPermisosAct")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public List<String> obtenerPermisosActWs(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) Integer codSistema,
			@WebParam(name = "codPerfil", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) Integer codPerfil);
	@WebMethod(operationName = "obtenerTrabajadorWs", action = "http://www.sedapal.com.pe/seguridad/obtenerTrabajador")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public TrabajadorBean obtenerTrabajadorWs(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario);
	/*PROCESOS REDIS*/
	@WebMethod(operationName = "validarTokenWs", action = "http://www.sedapal.com.pe/seguridad/validarToken")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	public Retorno validarTokenWs(
			@WebParam(name = "token", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String token);
	
//	fin adecuacion seguridad2
	
}