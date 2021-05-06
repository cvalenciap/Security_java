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
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;

import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.SistemaService;
import pe.com.sedapal.seguridad.core.util.Util;

public class SistemaServiceTest extends SeguridadTest {

	@Autowired
	private SistemaService sistemaService;
	
	private SistemaBean sistemaBean;
	
	@Test
	public void grabarAplicacionTest() {
		sistemaBean = new SistemaBean();
		try {
			this.sistemaBean.setDescripcion("PRUEBA DESDE EL TEST");
			this.sistemaBean.setAbreviatura("SCCM");
			this.sistemaBean.setEstado(1);
			this.sistemaBean.setUsuarioCreacion("MALDANA");
			this.sistemaBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaBean.setUsuarioModificacion("");
			this.sistemaBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.sistemaService.grabarSistema(this.sistemaBean);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/*@Test
	public void actualizarSistemaTest() {
		sistemaBean = new SistemaBean();
		try {
			this.sistemaBean.setCodSistema(31);
			this.sistemaBean.setDescripcion("CONTROL DE MATERIALES");
			this.sistemaBean.setAbreviatura("SCCM");
			this.sistemaBean.setEstado(1);
			this.sistemaBean.setUsuarioCreacion("LCASTRO");
			this.sistemaBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaBean.setUsuarioModificacion("MALDANA");
			this.sistemaBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.sistemaService.actualizarSistema(this.sistemaBean);
			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
}
