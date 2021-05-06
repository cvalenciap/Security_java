package pe.com.sedapal.seguridad.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlElement;

import pe.com.sedapal.seguridad.core.bean.Retorno;

//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface PowerWs {



	@WebMethod(operationName = "autenticacionPb", action = "http://www.sedapal.com.pe/seguridad/autenticacionPb")
	String autenticacionPb(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario,
			@WebParam(name = "codSistema", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) int codSistema,
			@WebParam(name = "clave", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String clave);

	@WebMethod(operationName = "actualizarClavePb", action = "http://www.sedapal.com.pe/seguridad/autenticacionPb")
	String actualizarClavePb(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario,
			@WebParam(name = "claveActual", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String claveActual,
			@WebParam(name = "nuevaClave", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String nuevaClave,
			@WebParam(name = "nuevaClaveR", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String nuevaClaveR);

	@WebMethod(operationName = "menuPb", action = "http://www.sedapal.com.pe/seguridad/menuPbPb")
	String menuPb(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario);
	
	
	@WebMethod(operationName = "obtenerPermisosPb", action = "http://www.sedapal.com.pe/seguridad/obtenerPermisosPb")
	String obtenerPermisosPb(
			@WebParam(name = "codUsuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String codUsuario);
	
	@WebMethod(operationName = "olvidarClavePb", action = "http://www.sedapal.com.pe/seguridad/olvidarClavePb")
	@WebResult(targetNamespace = "http://www.sedapal.com.pe/seguridad", name = "Retorno")
	String olvidarClavePb(
			@WebParam(name = "usuario", targetNamespace = "http://www.sedapal.com.pe/seguridad") @XmlElement(required = true) String usuario);

	
}