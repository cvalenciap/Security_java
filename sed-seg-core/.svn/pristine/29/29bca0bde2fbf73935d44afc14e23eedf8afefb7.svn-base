package pe.com.sedapal.seguridad.core.service.impl;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 *  
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.dao.SistemaDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.SistemaMBDAO;
import pe.com.sedapal.seguridad.core.service.SistemaService;

@Service("sistemaService")
public class SistemaServiceImpl implements SistemaService {

	@Autowired
	private SistemaDAO sistemaDAO; 
	
//	capa DAO mybatis
	@Autowired
	private SistemaMBDAO sistemaMBDAO; 

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistema(SistemaBean sistemaBean) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		this.sistemaDAO.grabarSistema(sistemaBean);
		try {
			sistemaMBDAO.grabarSistema(sistemaBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistema(SistemaBean sistemaBean) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		this.sistemaDAO.actualizarSistema(sistemaBean);
		try {
			sistemaMBDAO.actualizarSistema(sistemaBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemas() throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaDAO.obtenerListadoSistemas();
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<SistemaBean> sistemas = new ArrayList<SistemaBean>();
		try {
			sistemaMBDAO.obtenerListadoSistemas(parametrosBusqueda);
			sistemas = (List<SistemaBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemas;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaBean obtenerSistemaPorId(Serializable idSistema) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaDAO.obtenerSistemaPorId(idSistema);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		SistemaBean sistema = new SistemaBean();
		try {
			parametrosDetalle.put("idSistema", idSistema);
			sistemaMBDAO.obtenerSistemaPorId(parametrosDetalle);
			sistema = !((List<SistemaBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<SistemaBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistema;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistema(Serializable idSistema, Serializable estado, Serializable usuario) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		this.sistemaDAO.actualizarEstadoSistema(idSistema, estado, usuario);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("idSistema", idSistema);
			parametrosActualizar.put("estado", estado);
			parametrosActualizar.put("usuario", usuario);
			sistemaMBDAO.actualizarEstadoSistema(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Serializable abreviatura) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaDAO.obtenerListadoSistemasPorAbreviatura(abreviatura);
		Map<String, Object> parametrosAbreviatura = new HashMap<String, Object>();
		List<SistemaBean> sistemas = new ArrayList<SistemaBean>();
		try {
			parametrosAbreviatura.put("abreviatura", abreviatura);
			sistemaMBDAO.obtenerListadoSistemasPorAbreviatura(parametrosAbreviatura);
			sistemas = (List<SistemaBean>) parametrosAbreviatura.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemas;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerListadoSistemasActivos() throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaDAO.obtenerListadoSistemasActivos();
		Map<String, Object> parametrosActivos = new HashMap<String, Object>();
		List<SistemaBean> sistemas = new ArrayList<SistemaBean>();
		try {
			sistemaMBDAO.obtenerListadoSistemasActivos(parametrosActivos);
			sistemas = (List<SistemaBean>) parametrosActivos.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemas;
//		fin adecuación mybatis
	}
	
//	inicio adecuacion seguridad2
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaBean> obtenerDetalleSistemasUsuario(Serializable codUsuario) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> sistemasDetalle = new HashMap<String, Object>();
		List<SistemaBean> sistemas = new ArrayList<SistemaBean>();
		try {
			sistemasDetalle.put("codUsuario", codUsuario);
			sistemaMBDAO.obtenerDetalleSistemasUsuario(sistemasDetalle);
			sistemas = (List<SistemaBean>) sistemasDetalle.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemas;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistemaAct(SistemaBean sistemaBean) throws Exception {
		try {
			sistemaMBDAO.grabarSistemaAct(sistemaBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaAct(SistemaBean sistemaBean) throws Exception {
		try {
			sistemaMBDAO.actualizarSistemaAct(sistemaBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaBean obtenerSistemaPorIdAct(Serializable idSistema) throws Exception {
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		SistemaBean sistema = new SistemaBean();
		try {
			parametrosDetalle.put("idSistema", idSistema);
			sistemaMBDAO.obtenerSistemaPorIdAct(parametrosDetalle);
			sistema = !((List<SistemaBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<SistemaBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistema;
//		fin adecuación mybatis
	}
//	fin adecuacion seguridad2

}
