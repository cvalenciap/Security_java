package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.dao.PerfilSistemaDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.PerfilSistemaMBDAO;
import pe.com.sedapal.seguridad.core.service.PerfilSistemaService;

@Service("perfilsistemaService")
public class PerfilSistemaServiceImpl implements PerfilSistemaService {

	@Autowired
	private PerfilSistemaDAO perfilSistemaDAO;
	
//	capa DAO mybatis
	@Autowired
	private PerfilSistemaMBDAO perfilSistemaMBDAO;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception {
//		incio adecuación mybatis
//		return perfilSistemaDAO.obtenerListadoPerfilSistemaPorCodigo(codSistema);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilSistema = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosBusqueda.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerListadoPerfilSistemaPorCodigo(parametrosBusqueda);
			perfilSistema = (List<PerfilSistemaBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilSistema;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		return perfilSistemaDAO.obtenerPerfilSistemaPorCodigo(codPerfil, codSistema);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		PerfilSistemaBean perfilSistema = new PerfilSistemaBean();
		try {
			parametrosDetalle.put("codPerfil", codPerfil);
			parametrosDetalle.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerPerfilSistemaPorCodigo(parametrosDetalle);
			perfilSistema = !((List<PerfilSistemaBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<PerfilSistemaBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilSistema;
//		fin adecuación mybatis
	}

	@SuppressWarnings("null")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
//		inicio adecuación mybatis
//		return this.perfilSistemaDAO.grabarPerfilSistema(perfilSistemaBean);
		int result = 0;
		Map<String, Object> parametrosGrabar = new HashMap<String, Object>();
		try {
			parametrosGrabar.put("codPerfil", perfilSistemaBean.getCodPerfil());
			parametrosGrabar.put("codSistema", perfilSistemaBean.getCodSistema());
			parametrosGrabar.put("descripcion", perfilSistemaBean.getDescripcion());
			parametrosGrabar.put("estado", perfilSistemaBean.getEstado());
			parametrosGrabar.put("usuarioCreacion", perfilSistemaBean.getUsuarioCreacion());			
			perfilSistemaMBDAO.grabarPerfilSistema(parametrosGrabar);
			result = parametrosGrabar.get("identity") != null ? (int) parametrosGrabar.get("identity") : result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
//		inicio adecuación mybatis
//		this.perfilSistemaDAO.actualizarPerfilSistema(perfilSistemaBean);
		try {
			perfilSistemaMBDAO.actualizarPerfilSistema(perfilSistemaBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado, Serializable usuario) throws Exception {
//		inicio adecuación mybatis
//		this.perfilSistemaDAO.actualizarEstadoPerfilSistema(codPerfil, codSistema, estado, usuario);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("codPerfil", codPerfil);
			parametrosActualizar.put("codSistema", codSistema);
			parametrosActualizar.put("estado", estado);
			parametrosActualizar.put("usuario", usuario);
			perfilSistemaMBDAO.actualizarEstadoPerfilSistema(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception {
//		inicio adecuación mybatis
//		return perfilSistemaDAO.obtenerListadoPerfilSistemaPaginarTotal(valueSearch);
		Map<String, Object> parametrosPagina = new HashMap<String, Object>();
		int result = 0;
		try {
			parametrosPagina.put("valueSearch", valueSearch);
			perfilSistemaMBDAO.obtenerListadoPerfilSistemaPaginarTotal(parametrosPagina);
			result = parametrosPagina.get("P_TotalReg") != null ? (int) parametrosPagina.get("P_TotalReg") : result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, String valueSearch, String sortColumn) throws Exception {
//		inicio adecuación mybatis
//		return perfilSistemaDAO.obtenerListadoPerfilSistemaPaginar(pageSize, pageIndex, valueSearch, sortColumn);
		Map<String, Object> parametrosPaginar = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilSistema = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosPaginar.put("pageSize", pageSize);
			parametrosPaginar.put("pageIndex", pageIndex);
			parametrosPaginar.put("valueSearch", valueSearch);
			parametrosPaginar.put("sortColumn", sortColumn);
			perfilSistemaMBDAO.obtenerListadoPerfilSistemaPaginar(parametrosPaginar);
			perfilSistema = (List<PerfilSistemaBean>) parametrosPaginar.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilSistema;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.perfilSistemaDAO.obtenerListadoPerfilSistemaPorCodigoActivos(codSistema);
		Map<String, Object> parametrosActivos = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilSistema = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosActivos.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerListadoPerfilSistemaPorCodigoActivos(parametrosActivos);
			perfilSistema = (List<PerfilSistemaBean>) parametrosActivos.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilSistema;
//		fin adecuación mybatis
	}
	
//	inicio adecuacion seguridad2
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigo(Serializable codUsuario, Serializable codSistema) throws Exception {
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilesSistema = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerPerfilesSistemaPorCodigo(parametrosDetalleLista);
			perfilesSistema = (List<PerfilSistemaBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilesSistema;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilSistemaBean> obtenerPerfilesSistemaActivos(Serializable codUsuario, Serializable codSistema) throws Exception{
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilesActivos = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerPerfilesSistemaActivos(parametrosDetalleLista);
			perfilesActivos = (List<PerfilSistemaBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilesActivos;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerDetallePerfilesUsuario(Serializable codUsuario) throws Exception {
		Map<String, Object> detallePerfiles = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilesUsuario = new ArrayList<PerfilSistemaBean>();
		try {
			detallePerfiles.put("codUsuario", codUsuario);
			perfilSistemaMBDAO.obtenerDetallePerfilesUsuario(detallePerfiles);
			perfilesUsuario = (List<PerfilSistemaBean>) detallePerfiles.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilesUsuario;
	}
	
	/*PROCESOS LDAP*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<PerfilSistemaBean> obtenerPerfilesSistemaPorCodigoLDAP(Serializable codUsuario, Serializable codSistema) throws Exception {
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilesSistema = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerPerfilesSistemaPorCodigoLDAP(parametrosDetalleLista);
			perfilesSistema = (List<PerfilSistemaBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilesSistema;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PerfilSistemaBean> obtenerPerfilesSistemaActivosLDAP(Serializable codUsuario, Serializable codSistema) throws Exception{
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<PerfilSistemaBean> perfilesActivos = new ArrayList<PerfilSistemaBean>();
		try {
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codSistema", codSistema);
			perfilSistemaMBDAO.obtenerPerfilesSistemaActivosLDAP(parametrosDetalleLista);
			perfilesActivos = (List<PerfilSistemaBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return perfilesActivos;
	}
//	fin adecuacion seguridad2
	
}
