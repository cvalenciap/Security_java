package pe.com.sedapal.seguridad.core;

import org.junit.Test;

import junit.framework.Assert;
import pe.com.sedapal.seguridad.core.util.Util;



public class UtilTest {

	@Test
	public void compararClaveTest() {
		boolean exist = Boolean.FALSE;
		try {
			exist = Util.compararClave("+Qkn65g5J", "$2a$10$YSlNADko63drbodbrUtfGuQvD8IdFq1EvEmJEfAV7WUN7AhTbiHMK");
			Assert.assertTrue(exist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
