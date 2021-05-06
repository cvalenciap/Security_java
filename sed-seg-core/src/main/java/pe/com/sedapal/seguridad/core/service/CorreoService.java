package pe.com.sedapal.seguridad.core.service;

import pe.com.sedapal.seguridad.core.bean.CorreoBean;

public interface CorreoService {

	void enviarCorreo(CorreoBean correoBean) throws Exception;
}
