package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;
import pe.com.sedapal.seguridad.core.dao.UsuarioPerfilSistemaDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.UsuarioPerfilSistemaMBDAO;
import pe.com.sedapal.seguridad.core.service.UsuarioPerfilSistemaService;

@Service("usuarioperfilsistemaService")
public class UsuarioPerfilSistemaServiceImpl implements UsuarioPerfilSistemaService{

	@Autowired
	private UsuarioPerfilSistemaDAO usuarioPerfilSistemaDAO;
//
//	capa DAO mybatis
	@Autowired
	private UsuarioPerfilSistemaMBDAO usuarioPerfilSistemaMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioPerfilSistema();
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<UsuarioPerfilBean> usuarioPerfil = new ArrayList<UsuarioPerfilBean>();
		try {
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioPerfilSistema(parametrosBusqueda);
			usuarioPerfil = (List<UsuarioPerfilBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
//		fin adecuación mybatis
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioSistema();
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<UsuarioSistemaBean> usuarioSistema = new ArrayList<UsuarioSistemaBean>();
		try {
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioSistema(parametrosDetalle);
			usuarioSistema = (List<UsuarioSistemaBean>) parametrosDetalle.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioSistema;
//		fin adecuación mybatis
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema, Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioPerfilSistemaPorUsuario(codSistema, codUsuario);
		Map<String, Object> parametrosUsuario = new HashMap<String, Object>();
		List<UsuarioPerfilBean> usuarioPerfil = new ArrayList<UsuarioPerfilBean>();
		try {
			parametrosUsuario.put("codSistema", (Integer) codSistema);
			parametrosUsuario.put("codUsuario", (String) codUsuario);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioPerfilSistemaPorUsuario(parametrosUsuario);
			usuarioPerfil = (List<UsuarioPerfilBean>) parametrosUsuario.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
//		fin adecuación mybatis
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioPerfilSistemaPorCodigo(codPerfil, codSistema);
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		List<UsuarioPerfilBean> usuarioPerfil = new ArrayList<UsuarioPerfilBean>();
		try {
			parametrosCodigo.put("codPerfil", (Integer) codPerfil);
			parametrosCodigo.put("codSistema", (Integer) codSistema);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioPerfilSistemaPorCodigo(parametrosCodigo);
			usuarioPerfil = (List<UsuarioPerfilBean>) parametrosCodigo.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema, Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerUsuarioPerfilSistemaPorCodigo(codPerfil, codSistema, codUsuario);
		Map<String, Object> parametrosUserCodigo = new HashMap<String, Object>();
		UsuarioPerfilBean usuarioPerfil = new UsuarioPerfilBean();
		try {
			parametrosUserCodigo.put("codPerfil", (Integer) codPerfil);
			parametrosUserCodigo.put("codSistema", (Integer) codSistema);
			parametrosUserCodigo.put("codUsuario", (String) codUsuario);
			usuarioPerfilSistemaMBDAO.obtenerUsuarioPerfilSistemaPorCodigo(parametrosUserCodigo);
			usuarioPerfil = !((List<UsuarioPerfilBean>) parametrosUserCodigo.get("pCursor")).isEmpty() ? ((List<UsuarioPerfilBean>) parametrosUserCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioPerfilSistemaDAO.grabarUsuarioPerfilSistema(usuarioPerfilBean);
		try {
			usuarioPerfilSistemaMBDAO.grabarUsuarioPerfilSistema(usuarioPerfilBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioPerfilSistemaDAO.actualizarUsuarioPerfilSistema(usuarioPerfilBean);
		try {
			usuarioPerfilSistemaMBDAO.actualizarUsuarioPerfilSistema(usuarioPerfilBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioPerfilSistemaDAO.actualizarEstadoUsuarioPerfilSistema(codPerfil, codSistema, codUsuario, estado, usuario);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("codPerfil", (Integer) codPerfil);
			parametrosActualizar.put("codSistema", (Integer) codSistema);
			parametrosActualizar.put("codUsuario", (String) codUsuario);
			parametrosActualizar.put("estado", (Integer) estado);
			parametrosActualizar.put("usuario", (String) usuario);
			usuarioPerfilSistemaMBDAO.actualizarEstadoUsuarioPerfilSistema(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch)throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioSistemaPaginarTotal(valueSearch);
		Map<String, Object> parametrosPagina = new HashMap<String, Object>();
		int result = 0;
		try {
			parametrosPagina.put("valueSearch", valueSearch);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioSistemaPaginarTotal(parametrosPagina);
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
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch, String sortColumn)throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.usuarioPerfilSistemaDAO.obtenerListadoUsuarioSistemaPaginar(pageSize, pageIndex, valueSearch, sortColumn);
		Map<String, Object> parametrosPaginar = new HashMap<String, Object>();
		List<UsuarioSistemaBean> listaUsuarioSistema = new ArrayList<UsuarioSistemaBean>();
		try {
			parametrosPaginar.put("pageSize", pageSize);
			parametrosPaginar.put("pageIndex", pageIndex);
			parametrosPaginar.put("valueSearch", valueSearch);
			parametrosPaginar.put("sortColumn", sortColumn);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioSistemaPaginar(parametrosPaginar);
			listaUsuarioSistema = (List<UsuarioSistemaBean>) parametrosPaginar.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaUsuarioSistema;
//		fin adecuación mybatis
	}

//	inicio adecuacion seguridad2
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoUsuarioSistemaPaginarTotalAct(String valueSearch)throws Exception {
		Map<String, Object> parametrosPagina = new HashMap<String, Object>();
		int result = 0;
		try {
			parametrosPagina.put("valueSearch", valueSearch);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioSistemaPaginarTotalAct(parametrosPagina);
			result = parametrosPagina.get("P_TotalReg") != null ? (int) parametrosPagina.get("P_TotalReg") : result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
//	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch, String sortColumn) throws Exception {
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginarAct(int pageSize, int pageIndex, String valueSearch) throws Exception {
		Map<String, Object> parametrosPaginar = new HashMap<String, Object>();
		List<UsuarioSistemaBean> listaUsuarioSistema = new ArrayList<UsuarioSistemaBean>();
		try {
			parametrosPaginar.put("pageSize", pageSize);
			parametrosPaginar.put("pageIndex", pageIndex);
			parametrosPaginar.put("valueSearch", valueSearch);
//			parametrosPaginar.put("sortColumn", sortColumn);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioSistemaPaginarAct(parametrosPaginar);
			listaUsuarioSistema = (List<UsuarioSistemaBean>) parametrosPaginar.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaUsuarioSistema;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(Serializable codUsuario) throws Exception {
		Map<String, Object> parametrosAsociados = new HashMap<String, Object>();
		List<UsuarioPerfilBean> usuarioPerfil = new ArrayList<UsuarioPerfilBean>();
		try {
			parametrosAsociados.put("codUsuario", (String) codUsuario);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioPerfilSistemaPorUsuarioAct(parametrosAsociados);
			usuarioPerfil = (List<UsuarioPerfilBean>) parametrosAsociados.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarAsociacionesActuales(Serializable codUsuario) throws Exception{
		Map<String, Object> parametrosEliminarAsociacion = new HashMap<String, Object>();
		try {
			parametrosEliminarAsociacion.put("codUsuario", (String) codUsuario);
			usuarioPerfilSistemaMBDAO.eliminarAsociacionesActuales(parametrosEliminarAsociacion);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarAuditoriaUsuario(UsuarioPerfilBean asociacion, UsuarioBean usuario) throws Exception{
		Map<String, Object> parametrosAuditoria = new HashMap<String, Object>();
		try {
			parametrosAuditoria.put("codSistema", asociacion.getCodSistema());
			parametrosAuditoria.put("codUsuario", asociacion.getCodUsuario());
			parametrosAuditoria.put("sustentacion", usuario.getSustentacion());
			parametrosAuditoria.put("responsable", usuario.getResponsable());
			parametrosAuditoria.put("codPerfil", asociacion.getCodPerfil());
			parametrosAuditoria.put("codArea", usuario.getCodArea());
			parametrosAuditoria.put("descripcion", usuario.getDescripcion());
			usuarioPerfilSistemaMBDAO.actualizarAuditoriaUsuario(parametrosAuditoria);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*PROCESOS LDAP*/
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(Serializable codUsuario) throws Exception {
		Map<String, Object> parametrosAsociados = new HashMap<String, Object>();
		List<UsuarioPerfilBean> usuarioPerfil = new ArrayList<UsuarioPerfilBean>();
		try {
			parametrosAsociados.put("codUsuario", (String) codUsuario);
			usuarioPerfilSistemaMBDAO.obtenerListadoUsuarioPerfilSistemaPorUsuarioLDAP(parametrosAsociados);
			usuarioPerfil = (List<UsuarioPerfilBean>) parametrosAsociados.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarioPerfil;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarAsociacionesActualesLDAP(Serializable codUsuarioLDAP) throws Exception{
		Map<String, Object> parametrosEliminarAsociacion = new HashMap<String, Object>();
		try {
			parametrosEliminarAsociacion.put("codUsuario", (String) codUsuarioLDAP);
			usuarioPerfilSistemaMBDAO.eliminarAsociacionesActualesLDAP(parametrosEliminarAsociacion);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioPerfilSistemaLDAP(UsuarioPerfilBean usuarioLDAPPerfilBean) throws Exception {
		try {
			usuarioPerfilSistemaMBDAO.grabarUsuarioPerfilSistemaLDAP(usuarioLDAPPerfilBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	fin adecuacion seguridad2
	
}
