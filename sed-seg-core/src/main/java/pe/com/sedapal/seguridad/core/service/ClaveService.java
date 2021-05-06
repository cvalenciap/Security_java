package pe.com.sedapal.seguridad.core.service;

import java.util.List;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;

public interface ClaveService {

	List<ClaveBean> obtenerClaves(String usuario) throws Exception;

	void mantenimientoClaves(String usuario, String vpass, String flagTemp) throws Exception;

	void ejecutarProcesos() throws Exception;

	ClaveBean obtenerUltimaClave(String usuario) throws Exception;
}
