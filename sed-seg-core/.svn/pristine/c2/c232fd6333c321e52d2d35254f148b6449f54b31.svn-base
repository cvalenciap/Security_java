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

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.service.ParametroService;

public class ParametroServiceTest extends SeguridadTest {

	@Autowired
	private ParametroService parametroService;

	private ParametroBean parametroBean;
	
	@Test
	public void obtenerListadoParametroNivelTest() {
		List<ParametroBean> parametros;
		
		String tipo ="NIVEL";
		System.out.println("Procesando....");
		try {
			parametros = parametroService.obtenerListadoParametrosPorCodigoParametro(tipo);
			for(ParametroBean parametro : parametros){
				System.out.println(parametro.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void obtenerListadoParametroEstadoTest() {
		List<ParametroBean> parametros;
		
		String tipo ="ESTADO";
		
		try {
			parametros = parametroService.obtenerListadoParametrosPorCodigoParametro(tipo);
			for(ParametroBean parametro : parametros){
				System.out.println(parametro.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void obtenerParametroBloqueoTest() {
		ParametroBean parametro;
				
		
		try {
			parametro = parametroService.obtenerParametroPorCodigoParametro(Constants.BLOQUEO);
			if (parametro != null)
				System.out.println(parametro.toString());
			else
				Assert.assertNotNull(parametro);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
}
