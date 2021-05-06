package pe.com.sedapal.seguridad.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.sedapal.seguridad.core.bean.CorreoBean;
import pe.com.sedapal.seguridad.core.dao.CorreoDAO;
import pe.com.sedapal.seguridad.core.service.CorreoService;

@Service("correoService")
public class CorreoServiceImpl implements CorreoService {
	
	@Autowired
	private CorreoDAO correoDAO; 
	
	@Override
	public void enviarCorreo(CorreoBean correoBean) throws Exception{
		this.correoDAO.enviarCorreo(correoBean);
	}

}
