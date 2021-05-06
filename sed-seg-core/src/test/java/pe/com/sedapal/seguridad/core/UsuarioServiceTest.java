package pe.com.sedapal.seguridad.core;
/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 06/01/2017.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 *  
 */

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.UsuarioService;
import pe.com.sedapal.seguridad.core.util.Util;

public class UsuarioServiceTest extends SeguridadTest {
	
	@Autowired
	private UsuarioService usuarioService;

	private UsuarioBean usuarioBean;
	
	@Test
	public void obtenerListadoUsuarioTest() {
		List<UsuarioBean> usuarios;
		
		try {
			usuarios = usuarioService.obtenerListadoUsuario();
			for(UsuarioBean usuario : usuarios){
				System.out.println(usuario.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	@Test
	public void obtenerUsuarioPorCodUsuarioTest() {
		UsuarioBean usuarioBean = null;
		try {
			usuarioBean = this.usuarioService.obtenerUsuarioPorCodUsuario("SEGADMIN".toUpperCase());
			if(usuarioBean != null)
				System.out.println(usuarioBean.toString());
			Assert.assertTrue(usuarioBean != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void actualizarUsuarioEstadoPorCodUsuarioTest() {
		try {
			this.usuarioService.actualizarUsuarioEstadoPorCodUsuario("lcastro".toUpperCase(), "0", "maldana",46);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	
	@Test
	public void bloqueoUsuarioEstadoPorCodUsuarioTest() {
		try {
			this.usuarioService.bloqueoUsuarioEstadoPorCodUsuario("LCASTRO".toUpperCase(), "1", "0","MALDANA",46);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	
	
}
