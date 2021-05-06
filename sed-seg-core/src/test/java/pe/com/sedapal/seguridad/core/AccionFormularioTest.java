package pe.com.sedapal.seguridad.core;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.AccionFormularioService;

public class AccionFormularioTest extends SeguridadTest {
	
	@Autowired
	private AccionFormularioService accionFormularioService;
	
	private AccionFormularioBean accionFormularioBean;
	
	@Test
	public void grabarAccionFormularioTest() {
		accionFormularioBean = new AccionFormularioBean();
		try {
			this.accionFormularioBean.setCodAccionFormulario(0);
			this.accionFormularioBean.setCodSistema(49);
			this.accionFormularioBean.setCodModulo(1);
			this.accionFormularioBean.setCodFormulario(11);
			this.accionFormularioBean.setCodParametro("ACCIONES");
			this.accionFormularioBean.setCodigo(16);
			this.accionFormularioBean.setEstado(1);
			
			this.accionFormularioService.grabarAccionFormulario(accionFormularioBean);

			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
}
