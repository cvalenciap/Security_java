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

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.SistemaModuloService;
import pe.com.sedapal.seguridad.core.util.Util;

public class ModuloServiceTest extends SeguridadTest {

	@Autowired
	private SeguridadService seguridadService;

	private SistemaModuloBean sistemaModuloBean;
	
	/*@Test
	public void obtenerListadoSistemaModuloPorCodigoTest() {
		List<SistemaModuloBean> lstSistemaModulo;
		
		Integer codigo = 46;
		System.out.println("Procesando....");
		try {
			lstSistemaModulo = seguridadService.obtenerListadoSistemaModuloPorCodigo(codigo);
			for(SistemaModuloBean sistemamodulo : lstSistemaModulo){
				System.out.println(sistemamodulo.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void obtenerSistemaModuloPorCodigoTest() {
		SistemaModuloBean sistemaModulo;
		
		Integer codigo = 1;
		System.out.println("Procesando....");
		try {
			sistemaModulo = seguridadService.obtenerSistemaModuloPorCodigo(46, codigo);
				System.out.println(sistemaModulo.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
	@Test
	public void grabarSistemaModuloTest() {
		sistemaModuloBean = new SistemaModuloBean();
		try {
			this.sistemaModuloBean.setCodSistema(47);
			this.sistemaModuloBean.setDescripcion("PRUEBA DESDE EL TEST");
			this.sistemaModuloBean.setEstado(1);
			this.sistemaModuloBean.setUsuarioCreacion("MALDANA");
			this.sistemaModuloBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaModuloBean.setUsuarioModificacion("");
			this.sistemaModuloBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.seguridadService.grabarSistemaModulo(sistemaModuloBean);

			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
}
