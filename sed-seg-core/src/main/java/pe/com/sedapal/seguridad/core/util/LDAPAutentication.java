package pe.com.sedapal.seguridad.core.util;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;

//import net.sourceforge.myvd.test.util.OpenLDAPUtils;
import net.sourceforge.myvd.test.util.StartMyVD;
import net.sourceforge.myvd.test.util.StartOpenLDAP;
import net.sourceforge.myvd.test.util.StreamReader;
import net.sourceforge.myvd.test.util.Util;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.BRespuestaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.service.UsuarioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LDAPAutentication {
	
	private static String hostName;
	private static String host;
	private static Integer port;
	private static String loginDN;
	private static String password;
	private static String base;
	
	@Value("#{configuration['ldap.host.name']}")
	public void hostName(String hostName) {
		LDAPAutentication.hostName = hostName;
	}
	
	@Value("#{configuration['ldap.host']}")
	public void host(String host) {
		LDAPAutentication.host = host;
	}
	
	@Value("#{configuration['ldap.port']}")
	public void port(Integer port) {
		LDAPAutentication.port = port;
	}
	
	@Value("#{configuration['ldap.loginDN.generic']}")
	public void loginDN(String loginDN) {
		LDAPAutentication.loginDN = loginDN;
	}
	
	@Value("#{configuration['ldap.password.generic']}")
	public void password(String password) {
		LDAPAutentication.password = password;
	}
	
	@Value("#{configuration['ldap.base']}")
	public void base(String base) {
		LDAPAutentication.base = base;
	}
		
	private static final Logger logger = LoggerFactory.getLogger(LDAPAutentication.class);
	
	private static pe.com.sedapal.seguridad.core.util.Util utilService = new pe.com.sedapal.seguridad.core.util.Util();
	
	private static StartOpenLDAP baseServer;
	private static StartOpenLDAP internalServer;
	private static StartOpenLDAP externalServer;
	private static StartMyVD server;
	private static StartOpenLDAP baseServer2;
		
	public static void setUp() throws Exception {
		try {
//			String hostName = utilService.getPropValues("ldap.host.name", Constants.ARCHIVO_CONFIGURACION);
//			String host = utilService.getPropValues("ldap.host", Constants.ARCHIVO_CONFIGURACION);
//			String port = utilService.getPropValues("ldap.port", Constants.ARCHIVO_CONFIGURACION);
//			String loginDN = utilService.getPropValues("ldap.loginDN.generic", Constants.ARCHIVO_CONFIGURACION);
//			String password = utilService.getPropValues("ldap.password.generic", Constants.ARCHIVO_CONFIGURACION);
//			String base = utilService.getPropValues("ldap.base", Constants.ARCHIVO_CONFIGURACION);
			baseServer = new StartOpenLDAP();
			baseServer.startServer(hostName, port, loginDN, password);
			
			baseServer2 = new StartOpenLDAP();
			baseServer2.startServer(host, port, loginDN, password);
		}catch(Exception e) {
			throw new Exception();
		}
	}

	public static void tearDown() throws Exception {
		baseServer.stopServer();
		baseServer2.stopServer();
	}
	

	public static void testUserSearchNoAttrs(String commonName) throws LDAPException, IOException {
//		String host = utilService.getPropValues("ldap.host", Constants.ARCHIVO_CONFIGURACION);
//		String port = utilService.getPropValues("ldap.port", Constants.ARCHIVO_CONFIGURACION);
//		String loginDN = utilService.getPropValues("ldap.loginDN.generic", Constants.ARCHIVO_CONFIGURACION);
//		String password = utilService.getPropValues("ldap.password.generic", Constants.ARCHIVO_CONFIGURACION);
//		String base = utilService.getPropValues("ldap.base", Constants.ARCHIVO_CONFIGURACION);
		
		LDAPConnection con = new LDAPConnection();
		con.connect(host, Integer.valueOf(port));
		con.bind(3, loginDN, password.getBytes());
		
		LDAPAttributeSet attribs = new LDAPAttributeSet();
		attribs.add(new LDAPAttribute("cn", commonName));
		
		LDAPSearchResults res = con.search(base, 2, loginDN, new String[0], true);
		if (! res.hasMore()) {
			logger.error("no resultados");
			
		}
		
		LDAPEntry fromServer = res.next();
		LDAPEntry control = new LDAPEntry(base, attribs);
		
		if (! Util.compareEntry(fromServer,control)) {
			logger.error("entrada invalida");
		}
		con.disconnect();
	}

	public void testUserSearchWithAttrs() throws LDAPException {

		LDAPAttributeSet attribs = new LDAPAttributeSet();

		attribs.add(new LDAPAttribute("uid", "testUser"));
		attribs.add(new LDAPAttribute("o", "myorg"));

		LDAPEntry entry2 = new LDAPEntry("cn=Test User,ou=internal,o=mycompany,c=us", attribs);

		LDAPConnection con = new LDAPConnection();
		con.connect("localhost", 50983);
		LDAPSearchResults res = con.search("o=mycompany,c=us", 2, "(cn=Test User)", new String[] { "uid", "o" }, false);

		int size = 0;

		while (res.hasMore()) {
			LDAPEntry fromDir = res.next();
			LDAPEntry controlEntry = null;

			if (size == 0) {
				controlEntry = entry2;
			} else if (size == 1) {
				controlEntry = null;
			} else {
				controlEntry = null;
			}

			if (controlEntry == null) {
				logger.error("Entry " + fromDir.getDN() + " should not be returned");
				return;
			}

			if (!Util.compareEntry(fromDir, controlEntry)) {
				logger.error("The entry was not correct : " + fromDir.toString());
				return;
			}

			size++;
		}

		if (size != 1) {
			logger.error("Not the correct number of entries : " + size);
		}
		con.disconnect();
	}

	public static String busquedaUsuarioLDAP(UsuarioBean usuarioLDAP) throws LDAPException, IOException{
		String loginDNRetorno = null;
		String obtenido = "";
		/*conexión*/
		LDAPConnection con = new LDAPConnection();
		try {
			///*****change with properties and admin general****///
//			String host = utilService.getPropValues("ldap.host", Constants.ARCHIVO_CONFIGURACION);
//			String port = utilService.getPropValues("ldap.port", Constants.ARCHIVO_CONFIGURACION);
//			String genericLoginDN = utilService.getPropValues("ldap.loginDN.generic", Constants.ARCHIVO_CONFIGURACION);
//			String genericPassword = utilService.getPropValues("ldap.password.generic", Constants.ARCHIVO_CONFIGURACION);
//			String base = utilService.getPropValues("ldap.base", Constants.ARCHIVO_CONFIGURACION);
			con.connect(host, Integer.valueOf(port));
			con.bind(3, loginDN, password.getBytes());
			
			/*create loginDN and base*/
//			loginDN = "cn=" + usuarioLDAP.getCodUsuarioLDAP();
//			String[] ous = usuarioLDAP.getRutaOU().split("/");
//			for(int i=ous.length-1; i>=0; i--) {
//				loginDN = loginDN + ",ou=" + ous[i].trim();
//				base = base + "ou=" + ous[i].trim() + ",";
//			}
//			loginDN = loginDN + ",dc=sedapal,dc=corp";
//			base = base + "dc=sedapal,dc=corp";
								
			LDAPAttributeSet attribs = new LDAPAttributeSet();
			attribs.add(new LDAPAttribute("cn", usuarioLDAP.getCodUsuarioLDAP()));
			/*busqueda de usuario*/
			LDAPSearchResults res = con.search(base, 2, "cn=" + usuarioLDAP.getCodUsuarioLDAP(), new String[0], true);
			
			/*comparation*/
			if (!res.hasMore()) {
				logger.error("no resultados");
			}else {
				logger.info("encontrado");
				LDAPEntry fromServer = res.next();
				obtenido = fromServer.getDN();
				loginDNRetorno = obtenido;
				con.disconnect();
			}
		}catch(LDAPException e) {
			con.disconnect();
		}
		return loginDNRetorno;	
	}

	public static boolean validarConectividadLDAP(String loginDN, String clave) throws LDAPException, IOException {

		boolean connect = false;
//		String loginDN = "";
		/*instance conexión*/
		LDAPConnection con = new LDAPConnection();
		///*****change with properties****///
		String host = utilService.getPropValues("ldap.host", Constants.ARCHIVO_CONFIGURACION);
		String port = utilService.getPropValues("ldap.port", Constants.ARCHIVO_CONFIGURACION);
		con.connect(host, Integer.valueOf(port));
		
		/*create loginDN */
//		loginDN = "cn=" + usuarioLDAP.getCodUsuarioLDAP();
//		String[] ous = usuarioLDAP.getRutaOU().split("/");
//		for(int i=ous.length-1; i>=0; i--) {
//			loginDN = loginDN + ",ou=" + ous[i].trim();
//		}
//		loginDN = loginDN + ",dc=sedapal,dc=corp";
		
		/*try conection*/
		try{
			con.bind(3, loginDN, clave.getBytes());
			if(con.isConnected() && con.isConnectionAlive()) {
				connect = true;
			}
			con.disconnect();
		}catch(LDAPException e) {
			con.disconnect();
		}
		return connect;

	}
	
	public static void killAllOpenLDAPS() throws Exception {
		Process p = Runtime.getRuntime().exec("/usr/bin/killall slapd");
		
		StreamReader out = new StreamReader(p.getInputStream(),true);
		StreamReader err = new StreamReader(p.getErrorStream(),true);
		
		out.start();
		err.start();
		
		
	}		
}

