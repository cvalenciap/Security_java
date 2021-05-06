package pe.com.sedapal.seguridad.core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

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

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.bean.CorreoBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.util.Util;

public class SeguridadServiceTest extends SeguridadTest {

	@Autowired
	private SeguridadService seguridadService;

	private SistemaBean sistemaBean;

	private AccesoBean accesoBean;

	@Test
	public void grabarAplicacionTest() {
		sistemaBean = new SistemaBean();
		try {
			this.sistemaBean.setDescripcion("SISTEMA DE CONTROL DE MATERIALES");
			this.sistemaBean.setAbreviatura("SCCM");
			this.sistemaBean.setEstado(1);
			this.sistemaBean.setUsuarioCreacion("LCASTRO");
			this.sistemaBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaBean.setUsuarioModificacion("LCASTRO");
			this.sistemaBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.seguridadService.grabarSistema(this.sistemaBean);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerListadoSistemasTest() {
		List<SistemaBean> sistemas;
		try {
			sistemas = this.seguridadService.obtenerListadoSistemas();
			for (SistemaBean sistemaBean : sistemas) {
				System.out.println(sistemaBean.toString());
			}
			Assert.assertTrue(sistemas != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerAplicacionPorIdTest() {
		SistemaBean aplicacion;
		try {
			aplicacion = this.seguridadService.obtenerSistemaPorId(new Integer(45));
			System.out.println(aplicacion.toString());
			Assert.assertNotNull(aplicacion);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void actualizarEstadoAplicacionTest() {
		try {
			this.seguridadService.actualizarEstadoSistema(new Integer(23), "0", "lcastro");
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void obtenerUsuarioPorCodUsuarioTest() {
		UsuarioBean usuarioBean = null;
		try {
			usuarioBean = this.seguridadService.obtenerUsuarioPorCodUsuario("lcastro".toUpperCase());
			if (usuarioBean != null)
				System.out.println(usuarioBean.toString());
			Assert.assertTrue(usuarioBean != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void ingresarNuevoAccesoTest() {

		try {
			Timestamp tm = Util.fechaActualTimeStamp();
			accesoBean = new AccesoBean(null, "LCASTRO", tm, "0:0:0:0:0:0:0:1", new Integer(348), "A", "I", 0, 1,
					"LCASTRO", tm, "LCASTRO", tm, "token");

			seguridadService.grabarAcceso(accesoBean);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerListaIngresosTest() {

		try {

			List<AccesoBean> accesos = seguridadService.obtenerAccesoSistema("LCASTRO", "0:0:0:0:0:0:0:1");
			for (AccesoBean acceso : accesos) {
				System.out.println(acceso.toString());
			}
			Assert.assertTrue(accesos != null);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void actualizarAccesoTest() {

		try {

			seguridadService.actualizarAcceso("");
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerClavesTest() {

		List<ClaveBean> claves;
		try {

			claves = seguridadService.obtenerClaves("LCASTRO");
			for (ClaveBean claveBean : claves) {
				System.out.println(claveBean.toString());
			}
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void ejecutarProcesosTest() {

		try {
			seguridadService.ejecutarProcesos();
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerTrabajadorPorFichaTest() {

		TrabajadorBean trabajadorBean;
		try {

			trabajadorBean = seguridadService.obtenerTrabajadorPorFicha(15107);
			System.out.println(trabajadorBean.toString());

			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void enviarCorreTest() {

		CorreoBean correoBean = null;
		try {
			correoBean = new CorreoBean("gluiscastro@gmail.com", "gluiscastro@gmail.com", "Nueva Clave", new StringBuilder().append("hola"), "", "");	
			seguridadService.enviarCorreo(correoBean);;
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void accesosPorUsuarioTest() {

		
		try {
			List<String> permisos = seguridadService.obtenerListadoUsuarioAccionPorCodigo("SEGLCASTRO");	
			
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void metodoTest() {

		List<SistemaModuloFormBean> lista = null;
		List<String> ids = new ArrayList<String>();
		List<String> idsAux = new ArrayList<String>();
		String in = "";
		try {
			
			/*lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(46, 1, "17");
			ids = new ArrayList<String>();
			idsAux.add( "1");
			for (SistemaModuloFormBean sistemaModuloFormBean : lista) {
				//System.out.println(sistemaModuloFormBean);
				ids.add("" + sistemaModuloFormBean.getCodFormulario());
				idsAux.add("" + sistemaModuloFormBean.getCodFormulario());
			}
			in = "";
			for (int i = 0; i < ids.size(); i++) {				
				in = in + ids.get(i) + ",";
				//System.out.println(in);
			}
			
			in = in.substring(0,in.length() - 1);*/
			in = "17";
			idsAux.add( in);
			lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(46, 1, in);
			while(!lista.isEmpty()){
				lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(46, 1, in); 
				ids = new ArrayList<String>();
				for (SistemaModuloFormBean sistemaModuloFormBean : lista) {
					//System.out.println(sistemaModuloFormBean);
					ids.add("" + sistemaModuloFormBean.getCodFormulario());
					idsAux.add("" + sistemaModuloFormBean.getCodFormulario());
				}			
				in = "";			
				for (int i = 0; i < ids.size(); i++) {				
					in = in + ids.get(i) + ",";
					//System.out.println(in);
				}
				if(!in.equals("")){
					in = in.substring(0,in.length() - 1);
				}
				
			}
			/*
			lista = this.seguridadService.obtenerSistemaModuloFormPorCodigoHijos(46, 1, in); 
			ids = new ArrayList<String>();
			for (SistemaModuloFormBean sistemaModuloFormBean : lista) {
				//System.out.println(sistemaModuloFormBean);
				ids.add("" + sistemaModuloFormBean.getCodFormulario());
				idsAux.add("" + sistemaModuloFormBean.getCodFormulario());
			}			
			in = "";			
			for (int i = 0; i < ids.size(); i++) {				
				in = in + ids.get(i) + ",";
				//System.out.println(in);
			}
			
			in = in.substring(0,in.length() - 1);
			
			*/
			
			//RESULTADO
			System.out.println("sizze" + idsAux.size());
			for (int i = 0; i < idsAux.size(); i++) {								
				System.out.println(idsAux.get(i));
			}
			
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	

}
