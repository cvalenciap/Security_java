package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.dao.UsuarioFormularioAccionDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.UsuarioFormularioAccionMBDAO;
import pe.com.sedapal.seguridad.core.service.UsuarioFormularioAccionService;

@Service("usuarioFormularioAccionService")
public class UsuarioFormularioAccionServiceImpl implements UsuarioFormularioAccionService {

	@Autowired
	private UsuarioFormularioAccionDAO usuarioFormularioAccionDAO;
	
//	capa DAO mybatis
	@Autowired
	private UsuarioFormularioAccionMBDAO usuarioFormularioAccionMBDAO;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioFormularioAccionDAO.obtenerListadoUsuarioAccionPorCodigo(codUsuario);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<String> listaCod = new ArrayList<String>();
		try {
			parametrosBusqueda.put("codUsuario", codUsuario);
			usuarioFormularioAccionMBDAO.obtenerListadoUsuarioAccionPorCodigo(parametrosBusqueda);
			listaCod = (List<String>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaCod;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.usuarioFormularioAccionDAO.obtenerListadoUsuarioAccionPorCodigoPb(codUsuario);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<String> listaCodPb = new ArrayList<String>();
		try {
			parametrosDetalle.put("codUsuario", codUsuario);
			usuarioFormularioAccionMBDAO.obtenerListadoUsuarioAccionPorCodigoPb(parametrosDetalle);
			listaCodPb = (List<String>) parametrosDetalle.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaCodPb;
//		fin adecuación mybatis
	}
	
//	inicio adecuacion seguridad2
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoAct(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception {
		Map<String, Object> parametrosBusquedaLista = new HashMap<String, Object>();
		List<String> listaCod = new ArrayList<String>();
		try {
			parametrosBusquedaLista.put("codUsuario", codUsuario);
			parametrosBusquedaLista.put("codSistema", codSistema);
			parametrosBusquedaLista.put("codPerfil", codPerfil);
			usuarioFormularioAccionMBDAO.obtenerListadoUsuarioAccionPorCodigoAct(parametrosBusquedaLista);
			listaCod = (List<String>) parametrosBusquedaLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaCod;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoPbAct(Serializable codUsuario, Integer codSistema) throws Exception {
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<String> listaCodPb = new ArrayList<String>();
		try {
			parametrosDetalle.put("codUsuario", codUsuario);
			parametrosDetalle.put("codSistema", codSistema);
			usuarioFormularioAccionMBDAO.obtenerListadoUsuarioAccionPorCodigoPb(parametrosDetalle);
			listaCodPb = (List<String>) parametrosDetalle.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaCodPb;
	}
	
	/*PROCESOS LDAP*/
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<String> obtenerListadoUsuarioAccionPorCodigoLDAP(Serializable codUsuario, Integer codSistema, Integer codPerfil) throws Exception {
		Map<String, Object> parametrosBusquedaLista = new HashMap<String, Object>();
		List<String> listaCod = new ArrayList<String>();
		try {
			parametrosBusquedaLista.put("codUsuario", codUsuario);
			parametrosBusquedaLista.put("codSistema", codSistema);
			parametrosBusquedaLista.put("codPerfil", codPerfil);
			usuarioFormularioAccionMBDAO.obtenerListadoUsuarioAccionPorCodigoLDAP(parametrosBusquedaLista);
			listaCod = (List<String>) parametrosBusquedaLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaCod;
	}
	
//	fin adecuacion seguridad2
}
