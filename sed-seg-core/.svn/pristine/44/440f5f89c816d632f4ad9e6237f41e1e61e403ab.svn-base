package pe.com.sedapal.seguridad.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.CentroBean;
import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.dao.ClaveDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.ClaveMBDAO;
import pe.com.sedapal.seguridad.core.service.ClaveService;

@Service("claveService")
public class ClaveServiceImpl implements ClaveService {

	@Autowired
	private ClaveDAO claveDAO;
	
//	capa DAO mybatis
	@Autowired
	private ClaveMBDAO claveMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ClaveBean> obtenerClaves(String usuario)throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación para mybatis
//		return this.claveDAO.obtenerClaves(usuario);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<ClaveBean> claves = new ArrayList<ClaveBean>();
		try {
			parametrosBusqueda.put("codUsuario", usuario);
			claveMBDAO.obtenerClaves(parametrosBusqueda);
			claves = (List<ClaveBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return claves;
//		fin adecuación para mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void mantenimientoClaves(String usuario, String vpass, String flagTemp) throws Exception{
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		this.claveDAO.mantenimientoClaves(usuario, vpass, flagTemp);
		Map<String, Object> parametrosMantenimiento = new HashMap<String, Object>();
		try {
			parametrosMantenimiento.put("codUsuario", usuario);
			parametrosMantenimiento.put("pass", vpass);
			parametrosMantenimiento.put("flagTemp", flagTemp);
			claveMBDAO.mantenimientoClaves(parametrosMantenimiento);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	public void ejecutarProcesos() throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		this.claveDAO.ejecutarProcesos();
		Map<String, Object> parametrosProcesos = new HashMap<String, Object>();
		try {
			parametrosProcesos.put("codUsuario", "");
			claveMBDAO.ejecutarProcesosTemporal(parametrosProcesos);
			claveMBDAO.ejecutarProcesosObligatorio(parametrosProcesos);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación maybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ClaveBean obtenerUltimaClave(String usuario) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.claveDAO.obtenerUltimaClave(usuario);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		ClaveBean clave = new ClaveBean();
		try {
			parametrosBusqueda.put("codUsuario", usuario);
			claveMBDAO.obtenerUltimaClave(parametrosBusqueda);
			clave = !((List<ClaveBean>) parametrosBusqueda.get("pCursor")).isEmpty() ? ((List<ClaveBean>) parametrosBusqueda.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return clave;
//		fin adecuación mybatis
	}

}
