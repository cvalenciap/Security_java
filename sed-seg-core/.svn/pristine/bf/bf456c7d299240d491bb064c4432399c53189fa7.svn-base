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

import pe.com.sedapal.seguridad.core.bean.CentroBean;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.CentroService;

public class CentroServiceTest extends SeguridadTest {
	
	@Autowired
	private CentroService centroService;

	private CentroBean centroBean;
	
	@Test
	public void obtenerListadoCentroTest() {
		List<CentroBean> centros;
		try {
			centros = this.centroService.obtenerListadoCentro();
			for (CentroBean centro : centros) {
				System.out.println(centro.toString());
			}
			Assert.assertTrue(centros != null);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage()); 
		}
	}
	
	@Test
	public void obtenerCentroPorCodTest() {
		CentroBean centro;
		try {
			centro = this.centroService.obtenerCentroPorCodigo(new Integer(10));
			System.out.println(centro.toString());
			Assert.assertNotNull(centro);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
	
	
}
