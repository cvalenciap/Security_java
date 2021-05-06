package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.dao.PerfilAccionDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.PerfilAccionMBDAO;
import pe.com.sedapal.seguridad.core.service.PerfilAccionService;

@Service("perfilaccionService")
public class PerfilAccionServiceImpl implements PerfilAccionService {

	@Autowired
	private PerfilAccionDAO perfilAccionDAO;
	
//	capa DAO mybatis
	@Autowired
	private PerfilAccionMBDAO perfilAccionMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		return perfilAccionDAO.obtenerListadoPerfilAccionPorCodigo(codPerfil, codSistema);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<PerfilAccionBean> perfilAccion = new ArrayList<PerfilAccionBean>();
		try {
			parametrosBusqueda.put("codPerfil", codPerfil);
			parametrosBusqueda.put("codSistema", codSistema);
			perfilAccionMBDAO.obtenerListadoPerfilAccionPorCodigo(parametrosBusqueda);
			perfilAccion = (List<PerfilAccionBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilAccion;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception {
//		inicio adecuación mybatis
//		return perfilAccionDAO.obtenerPerfilAccionPorCodigo(codPerfilAccion);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		PerfilAccionBean perfilAccion = new PerfilAccionBean();
		try {
			parametrosDetalle.put("codPerfilAccion", codPerfilAccion);
			perfilAccionMBDAO.obtenerPerfilAccionPorCodigo(parametrosDetalle);
			perfilAccion = !((List<PerfilAccionBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<PerfilAccionBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilAccion;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
//		inicio adecuación mybatis
//		this.perfilAccionDAO.grabarPerfilAccion(perfilAccionBean);
		try {
			perfilAccionMBDAO.grabarPerfilAccion(perfilAccionBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
//		inicio adecuación mybatis
//		this.perfilAccionDAO.actualizarPerfilAccion(perfilAccionBean);
		try {
			perfilAccionMBDAO.actualizarPerfilAccion(perfilAccionBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		this.perfilAccionDAO.eliminarPerfilAccion(codPerfil, codSistema);
		Map<String, Object> parametrosEliminar = new HashMap<String, Object>();
		try {
			parametrosEliminar.put("codPerfil", codPerfil);
			parametrosEliminar.put("codSistema", codSistema);
			perfilAccionMBDAO.eliminarPerfilAccion(parametrosEliminar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

}
