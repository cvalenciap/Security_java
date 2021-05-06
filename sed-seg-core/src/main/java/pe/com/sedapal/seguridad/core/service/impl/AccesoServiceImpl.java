package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.dao.AccesoDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.AccesoMBDAO;
import pe.com.sedapal.seguridad.core.service.AccesoService;

@Service("accesoService")
public class AccesoServiceImpl implements AccesoService {

	@Autowired
	private AccesoDAO accesoDAO;
	
//	capa DAO mybatis
	@Autowired
	private AccesoMBDAO accesoMBDAO;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarAcceso(AccesoBean accesoBean) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación para mybatis
//		accesoDAO.grabarAcceso(accesoBean);
		try {
			accesoMBDAO.grabarAcceso(accesoBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		fin adecuación para mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccesoBean> obtenerAccesoSistema(Serializable usuario,Serializable ip) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación para mybatis
//		return accesoDAO.obtenerAccesoSistema(usuario,ip);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<AccesoBean> acceso = new ArrayList<AccesoBean>();
		try {
			parametrosDetalle.put("codUsuario", usuario);
			parametrosDetalle.put("codIp", ip);
			accesoMBDAO.obtenerAccesoSistema(parametrosDetalle);
			acceso = (ArrayList<AccesoBean>) parametrosDetalle.get("pCursor");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return acceso;
//		fin adecuación para mybatis
	}

	@Override
	public void actualizarAcceso(Serializable token) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación para mybatis
//		accesoDAO.actualizarAcceso(token);
		try {
			accesoMBDAO.actualizarAcceso((String) token);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		fin adecuación para mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception {	
		// TODO Auto-generated method stub
//		inicio adecuación para mybatis
//		return this.accesoDAO.obtenerUltimoAccesoSistema(usuario);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<AccesoBean> accesos = new ArrayList<AccesoBean>();
		try {
			parametrosBusqueda.put("codUsuario", usuario);
			accesoMBDAO.obtenerUltimoAccesoSistema(parametrosBusqueda);
			accesos = ((ArrayList<AccesoBean>) parametrosBusqueda.get("pCursor"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accesos;
//		fin adecuación para mybatis
	}
	
}
