package pe.com.sedapal.seguridad.core.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.Date;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 *  
 */

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;

public final class Util {

	private final static Logger logger = LoggerFactory.getLogger(Util.class);

	private final static Pattern hasUppercase = Pattern.compile("[A-Z]");
	private final static Pattern hasLowercase = Pattern.compile("[a-z]");
	//private final static Pattern hasNumber = Pattern.compile("\\d+");
	private final static Pattern hasNumber = Pattern.compile("^[0-9]$");
	private final static Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");
	//private final static Pattern hasSpecialChar1 = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
	

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private static final Random RANDOM = new SecureRandom();

	public static final int LONGITUD_PASSWORD = 9;

	public static Timestamp fechaActualTimeStamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static Date fechaActualDate() {
		return new Date(Calendar.getInstance().getTimeInMillis());
	}

	public static String generarClave(String vpass) {
		return passwordEncoder.encode(vpass);
	}

	public static boolean compararClave(String ingresada, String claveUsuario) {
		return passwordEncoder.matches(ingresada, claveUsuario);
	}

	public static String validateNewPass(String pass1, String pass2, String usuario, List<ClaveBean> clavesAnteriores,
			String ultimas,String nombre,String apePaterno,String apeMaterno) {

		StringBuilder retVal = new StringBuilder();
		boolean claveAnterior = Boolean.FALSE;
		int cont = 0;

		if (pass1.equals(pass2)) {
			logger.info(pass1 + " = " + pass2);

			/*if (!hasSpecialChar1.matcher(pass1).find()) {
				logger.info("<-- needs a specail character");
				retVal.append("Clave contiene caracteres especiales invalidos.  <br>");				
			}*/
			
			if (pass1.length() < 8) {
				logger.info(" is length < 8");
				retVal.append("La clave debe tener al menos 8 caracteres.");
			}

			if (pass1.toUpperCase().contains("SEDAPAL")) {
				logger.info("<-- Clave no debe contener el nombre de la empresa");
				retVal.append("La clave no debe contener el nombre de la empesa.");
			}

			if (pass1.toUpperCase().contains(usuario.toUpperCase())) {
				logger.info("<-- Clave no debe contener el nombre de su usuario");
				retVal.append("La clave no debe contener el nombre del usuario.");
			}
			
			if (!apePaterno.isEmpty() && pass1.toUpperCase().contains(apePaterno.toUpperCase().trim())) {
				logger.info("<-- Clave no debe contener su apellido paterno");
				retVal.append("La clave no debe contener su apellido paterno.");
			}
			
			if (!apeMaterno.isEmpty() && pass1.toUpperCase().contains(apeMaterno.toUpperCase().trim())) {
				logger.info("<-- Clave no debe contener su apellido materno");
				retVal.append("La clave no debe contener su apellido materno.");
			}
			
			List<String> nombres = new ArrayList<>();
			for (String tempNombre : nombre.split(" ")) {
				nombres.add(tempNombre);
			}
			for (String tempNombre : nombres) {
				if (!tempNombre.isEmpty() && pass1.toUpperCase().contains(tempNombre.toUpperCase().trim())) {
					logger.info("<-- Clave no debe contener su nombre");
					retVal.append("La clave no debe contener su nombre.");
					break;
				}
			}
			
			

			for (ClaveBean claveBean : clavesAnteriores) {
				if (compararClave(pass1, claveBean.getPass())) {
					claveAnterior = Boolean.TRUE;
				}
			}
			if (claveAnterior) {
				logger.info("Clave ingresada no debe ser igual a las ultimas " + ultimas + ".");
				retVal.append("La clave ingresada no debe ser igual a las últimas " + ultimas + ".");
			}
						
			if (!hasUppercase.matcher(pass1).find()) {
				logger.info(pass1 + " <-- needs uppercase");
				//retVal.append("La clave debe tener una o mas letras mayúsculas <br>");
				cont++;
			}

			if (!hasLowercase.matcher(pass1).find()) {
				logger.info(" <-- needs lowercase");
				//retVal.append("La clave debe tener una o mas letras minúsculas <br>");
				cont++;
			}

			//if (!hasNumber.matcher(pass1).find()) {
			if (!hayNumero(pass1)) {
				logger.info(pass1 + " <-- needs a number");
				//retVal.append("La clave debe tener una o mas números <br>");
				cont++;
			}
			if (!hasSpecialChar.matcher(pass1).find()) {
				logger.info(" <-- needs a specail character");
				//retVal.append("Clave debe tener al menos 3 caracteres especiales !,@,#, etc.  <br>");
				cont++;
			}
			if (cont != 0 ){
				if (cont <= 4 && cont != 1){
					retVal.append("La clave debe considerar al menos 3 de las siguientes validaciones: mayúsculas, minúsculas, números o caracteres especiales.");	
				} 
			}
					
				
			
			
			

		} else {
			logger.info(pass1 + " != " + pass2);
			retVal.append("Las nuevas claves ingresadas no coinciden.");
		}
		if (retVal.length() == 0) {
			logger.info("Clave valida");
			retVal.append("Success");
		}

		return retVal.toString();
	}
	
	private static boolean hayNumero(String cadena) {
        boolean ret = false;
        for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) >= 48 && cadena.charAt(i) <= 57) {
				ret = true;
				break;
			}
		}
        return ret;
	}

	public static String generadorPasswordTemporal() {
		String valores = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

		String pw = "";
		for (int i = 0; i < LONGITUD_PASSWORD; i++) {
			int index = (int) (RANDOM.nextDouble() * valores.length());
			pw += valores.substring(index, index + 1);
		}
		return pw;
	}

	public static String escribirDoc(byte[] contenido, String ruta,String nombre) {
		FileOutputStream output;
		File file;
		String path = null;
		try {
			
			path = ruta + nombre;
			logger.info("##### grabando archivo ##### " + path);	
			
			file = new File(path);
			if (!file.exists()) {
				output = new FileOutputStream(path, true);
				output.write(contenido);
				output.close();
			} else {
				output = new FileOutputStream(path, false);
				output.write(contenido);
				output.close();
			}
			logger.info("##### terminando grabado archivo ##### " + path);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return path;
	}
	
//	inicio adecuacion seguridad2
	public String getPropValues(String propertie, String propFileName) throws IOException {
		String result = "";
		InputStream inputStream = null; 
		try {
			Properties prop = new Properties();
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// obtener valor de propiedad
			result = prop.getProperty(propertie);
			if(result == null) {
				throw new IOException();
			}
		} catch (Exception e) {
			throw new IOException();
		} finally {
			inputStream.close();
		}
		return result;
	}
//	fin adecuacion seguridad2
}
