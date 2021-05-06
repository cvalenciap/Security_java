package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;
import pe.com.sedapal.seguridad.core.dao.AccionFormularioDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.AccionFormularioMBDAO;
import pe.com.sedapal.seguridad.core.service.AccionFormularioService;

@Service("accionformularioService")
public class AccionFormularioServiceImpl implements AccionFormularioService {

	@Autowired
	private AccionFormularioDAO accionFormularioDAO;
	
//	capa DAO mybatis
	@Autowired
	private AccionFormularioMBDAO accionFormularioMBDAO;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
//		inicio adecuación para mybatis
//		this.accionFormularioDAO.grabarAccionFormulario(accionFormularioBean);
		try {
			accionFormularioMBDAO.grabarAccionFormulario(accionFormularioBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		fin adecuación para mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
//		inicio adecuación para mybatis
//		this.accionFormularioDAO.actualizarAccionFormulario(accionFormularioBean);
		try {
			accionFormularioMBDAO.actualizarAccionFormulario(accionFormularioBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		fin adecuación para mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario) throws Exception {
//		inicio adecuación para mybatis
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<AccionFormularioBean> acciones = new ArrayList<AccionFormularioBean>();
		try {
			parametrosDetalle.put("codSistema", codSistema);
			parametrosDetalle.put("codModulo", codModulo);
			parametrosDetalle.put("codFormulario", codFormulario);
			accionFormularioMBDAO.obtenerListadoAccionFormularioPorCodigo(parametrosDetalle);
			acciones = (ArrayList<AccionFormularioBean>) parametrosDetalle.get("pCursor");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return acciones;
//		return this.accionFormularioDAO.obtenerListadoAccionFormularioPorCodigo(codSistema, codModulo, codFormulario);
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception {
//		inicio adecuación mybatis
//		return this.accionFormularioDAO.obtenerListadoAccionFormularioPerfilPorCodigo(codSistema, codModulo, codFormulario, codPerfil);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<AccionFormularioBean> acciones = new ArrayList<AccionFormularioBean>();
		try {
			parametrosBusqueda.put("codSistema", codSistema);
			parametrosBusqueda.put("codModulo", codModulo);
			parametrosBusqueda.put("codFormulario", codFormulario);
			parametrosBusqueda.put("codPerfil", codPerfil);
			accionFormularioMBDAO.obtenerListadoAccionFormularioPerfilPorCodigo(parametrosBusqueda);
			acciones = (ArrayList<AccionFormularioBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return acciones;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception {
//		inicio adecuación mybatis
//		return this.accionFormularioDAO.obtenerListadoAccionFormularioPerfilPorUsuario(codSistema, codModulo, codFormulario, codPerfil);
		Map<String, Object> parametrosUsuario = new HashMap<String, Object>();
		List<AccionUsuarioBean> acciones = new ArrayList<AccionUsuarioBean>();
		try {
			parametrosUsuario.put("codSistema", codSistema);
			parametrosUsuario.put("codModulo", codModulo);
			parametrosUsuario.put("codFormulario", codFormulario);
			parametrosUsuario.put("codPerfil", codPerfil);
			accionFormularioMBDAO.obtenerListadoAccionFormularioPerfilPorUsuario(parametrosUsuario);
			acciones = (ArrayList<AccionUsuarioBean>) parametrosUsuario.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return acciones;
//		fin adecuación mybatis
	}

}
