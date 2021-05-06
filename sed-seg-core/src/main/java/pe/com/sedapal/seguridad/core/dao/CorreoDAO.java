package pe.com.sedapal.seguridad.core.dao;

import pe.com.sedapal.seguridad.core.bean.CorreoBean;

public interface CorreoDAO {

	void enviarCorreo(CorreoBean correoBean) throws Exception;
}
