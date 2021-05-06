package pe.com.sedapal.seguridad.core;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.util.Util;

public class PerfilSistemaTest extends SeguridadTest{

	@Autowired
	private SeguridadService seguridadService;
	
	private PerfilSistemaBean perfilSistemaBean;
	
	
	
	@Test
	public void oobtenerPerfilSistemaPorCodigoTest() {
		PerfilSistemaBean perfil;
		try {
			perfil = this.seguridadService.obtenerPerfilSistemaPorCodigo(4, 46);

			System.out.println(perfil.toString());

			Assert.assertTrue(perfil != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/*
	@Test
	public void obtenerListadoPerfilSistemaPorCodigoTest() {
		List<PerfilSistemaBean> perfiles;
		try {
			perfiles = this.seguridadService.obtenerListadoPerfilSistemaPorCodigo(41);
			for (PerfilSistemaBean perfil : perfiles) {
				System.out.println(perfil.toString());
			}
			Assert.assertTrue(perfiles != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void grabarPerfilSistemaTest() {
		perfilSistemaBean = new PerfilSistemaBean();
		try {
			this.perfilSistemaBean.setCodSistema(41);
			this.perfilSistemaBean.setDescripcion("PRUEBA DESDE EL TEST");
			this.perfilSistemaBean.setEstado("1");
			this.perfilSistemaBean.setUsuarioCreacion("MALDANA");
			this.perfilSistemaBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.perfilSistemaBean.setUsuarioModificacion("");
			this.perfilSistemaBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.seguridadService.grabarPerfilSistema(this.perfilSistemaBean);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
	
	@Test
	public void obtenerListadoSistemaModuloFormInicioPorCodigoTest() {
		//List<PerfilSistemaBean> perfiles;
		List<SistemaModuloFormBean> perfiles;
		try {
			perfiles = this.seguridadService.obtenerListadoSistemaModuloFormInicioPorCodigo("SEG","MALDANA");
			for (SistemaModuloFormBean perfil : perfiles) {
				System.out.println(perfil.toString());
			}
			Assert.assertTrue(perfiles != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
}
