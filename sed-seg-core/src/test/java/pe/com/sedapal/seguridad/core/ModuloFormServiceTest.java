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

import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;

import pe.com.sedapal.seguridad.core.config.SeguridadTest;
import pe.com.sedapal.seguridad.core.dao.SistemaModuloFormDAO;
import pe.com.sedapal.seguridad.core.service.SeguridadService;
import pe.com.sedapal.seguridad.core.service.SistemaModuloFormService;
import pe.com.sedapal.seguridad.core.util.Util;

public class ModuloFormServiceTest  extends SeguridadTest {

	@Autowired
	private SeguridadService seguridadService;
	
	@Autowired
	private SistemaModuloFormService sistemaModuloFormService;
	
	@Autowired
	private SistemaModuloFormDAO sistemaModuloFormDAO;


	private SistemaModuloFormBean sistemaModuloFormBean;
	
	/*@Test
	public void obtenerListadoSistemaModuloFormPorCodigoTest() {
		List<SistemaModuloFormBean> lstSistemaModuloForm;
		
		System.out.println("Procesando....");
		try {
			lstSistemaModuloForm = seguridadService.obtenerListadoSistemaModuloFormPorCodigo(0,0);
			for(SistemaModuloFormBean sistemamoduloform : lstSistemaModuloForm){
				System.out.println(sistemamoduloform.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
	/*@Test
	public void obtenerSistemaModuloFormPorCodigoTest() {
		SistemaModuloFormBean sistemaModuloForm;
		
		System.out.println("Procesando....");
		try {
			sistemaModuloForm = seguridadService.obtenerSistemaModuloFormPorCodigo(49,1,1);
			System.out.println(sistemaModuloForm.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
	@Test
	public void obtenerListadoSistemaModuloFormInicioPorCodigoTest() {
		List<SistemaModuloFormBean> listSistemaModuloForm;
		
		System.out.println("Procesando....");
		try {
			listSistemaModuloForm = seguridadService.obtenerListadoSistemaModuloFormInicioPorCodigo("SEG","MALDANA");
			
			for(SistemaModuloFormBean sistemaModuloForm : listSistemaModuloForm){
				System.out.println(sistemaModuloForm.toString());
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	/*@Test
	public void grabarSistemaModuloFormTest() {
		sistemaModuloFormBean = new SistemaModuloFormBean();
		try {
			this.sistemaModuloFormBean.setCodSistema(49);
			this.sistemaModuloFormBean.setCodModulo(1);
			this.sistemaModuloFormBean.setDescripcion("PRUEBA DESDE EL TEST");
			this.sistemaModuloFormBean.setEstado(1);
			this.sistemaModuloFormBean.setUrlFormulario("/test");
			this.sistemaModuloFormBean.setUsuarioCreacion("MALDANA");
			this.sistemaModuloFormBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaModuloFormBean.setUsuarioModificacion("");
			this.sistemaModuloFormBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.sistemaModuloFormDAO.grabarSistemaModuloForm(sistemaModuloFormBean);

			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
	
	
	/*@Test
	public void actualizarSistemaModuloFormTest() {
		sistemaModuloFormBean = new SistemaModuloFormBean();
		try {
			this.sistemaModuloFormBean.setCodSistema(48);
			this.sistemaModuloFormBean.setCodModulo(1);
			this.sistemaModuloFormBean.setCodFormulario(9);
			this.sistemaModuloFormBean.setDescripcion("AAA TEST");
			this.sistemaModuloFormBean.setEstado(1);
			this.sistemaModuloFormBean.setCodFormularioPadre(8);
			this.sistemaModuloFormBean.setUrlFormulario("/test");
			this.sistemaModuloFormBean.setNivelFormulario(2);
			this.sistemaModuloFormBean.setOrdenFormulario(1);
			//this.sistemaModuloFormBean.setUsuarioCreacion("MALDANA");
			//this.sistemaModuloFormBean.setFechaCreacion(Util.fechaActualTimeStamp());
			this.sistemaModuloFormBean.setUsuarioModificacion("MALDANA");
			this.sistemaModuloFormBean.setFechaModificacion(Util.fechaActualTimeStamp());
			this.sistemaModuloFormService.actualizarSistemaModuloForm(sistemaModuloFormBean);

			Assert.assertTrue(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}*/
}
