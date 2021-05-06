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
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.dao.SistemaModuloDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.SistemaModuloMBDAO;
import pe.com.sedapal.seguridad.core.service.SistemaModuloService;

@Service("sistemamoduloService")
public class SistemaModuloServiceImpl implements SistemaModuloService{

	@Autowired
	private SistemaModuloDAO sistemaModuloDAO;
	
//	capa DAO mybatis
	@Autowired
	private SistemaModuloMBDAO sistemaModuloMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Serializable codSistema) throws Exception{
//		inicio adecuación mybatis
//		return sistemaModuloDAO.obtenerListadoSistemaModuloPorCodigo(codSistema);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<SistemaModuloBean> sistemaModulo = new ArrayList<SistemaModuloBean>();
		try {
			parametrosBusqueda.put("codSistema", codSistema);
			sistemaModuloMBDAO.obtenerListadoSistemaModuloPorCodigo(parametrosBusqueda);
			sistemaModulo = (List<SistemaModuloBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModulo;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloBean obtenerSistemaModuloPorCodigo(Serializable codSistema, Serializable codModulo) throws Exception {
//		inicio adecuación mybatis
//		return sistemaModuloDAO.obtenerSistemaModuloPorCodigo(codSistema, codModulo);
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		SistemaModuloBean sistemaModulo = new SistemaModuloBean();
		try {
			parametrosCodigo.put("codSistema", codSistema);
			parametrosCodigo.put("codModulo", codModulo);
			sistemaModuloMBDAO.obtenerSistemaModuloPorCodigo(parametrosCodigo);
			sistemaModulo = !((List<SistemaModuloBean>) parametrosCodigo.get("pCursor")).isEmpty() ? ((List<SistemaModuloBean>) parametrosCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModulo;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
//		inicio adecuación mybatis
//		this.sistemaModuloDAO.grabarSistemaModulo(sistemaModuloBean);
		try {
			sistemaModuloMBDAO.grabarSistemaModulo(sistemaModuloBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
//		inicio adecuación mybatis
//		this.sistemaModuloDAO.actualizarSistemaModulo(sistemaModuloBean);
		try {
			sistemaModuloMBDAO.actualizarSistemaModulo(sistemaModuloBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistemaModulo(Serializable codSistema, Serializable codModulo, Serializable estado,Serializable usuario) throws Exception {
//		inicio adecuación mybatis
//		this.sistemaModuloDAO.actualizarEstadoSistemaModulo(codSistema, codModulo, estado, usuario);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("codSistema", codSistema);
			parametrosActualizar.put("codModulo", codModulo);
			parametrosActualizar.put("estado", estado);
			parametrosActualizar.put("usuario", usuario);
			sistemaModuloMBDAO.actualizarEstadoSistemaModulo(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaModuloDAO.obtenerListadoSistemaModuloPorCodigoActivos(codSistema);
		Map<String, Object> parametrosActivos = new HashMap<String, Object>();
		List<SistemaModuloBean> sistemaModulo = new ArrayList<SistemaModuloBean>();
		try {
			parametrosActivos.put("codSistema", codSistema);
			sistemaModuloMBDAO.obtenerListadoSistemaModuloPorCodigoActivos(parametrosActivos);
			sistemaModulo = (List<SistemaModuloBean>) parametrosActivos.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModulo;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaModuloDAO.obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(codSistema);
		Map<String, Object> parametrosCodigoActivos = new HashMap<String, Object>();
		List<SistemaModuloBean> sistemaModulo = new ArrayList<SistemaModuloBean>();
		try {
			parametrosCodigoActivos.put("codSistema", codSistema);
			sistemaModuloMBDAO.obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(parametrosCodigoActivos);
			sistemaModulo = (List<SistemaModuloBean>) parametrosCodigoActivos.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModulo;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloBean obtenerSistemaModuloPorNombre(Serializable codSistema, Serializable nombreModulo) throws Exception {
		// TODO Auto-generated method stub
//		imicio adecuación mybatis
//		return this.sistemaModuloDAO.obtenerSistemaModuloPorNombre(codSistema, nombreModulo);
		Map<String, Object> parametrosNombre = new HashMap<String, Object>();
		SistemaModuloBean sistemaModulo = new SistemaModuloBean();
		try {
			parametrosNombre.put("codSistema", codSistema);
			parametrosNombre.put("nombreModulo", nombreModulo);
			sistemaModuloMBDAO.obtenerSistemaModuloPorNombre(parametrosNombre);
			sistemaModulo = !((List<SistemaModuloBean>) parametrosNombre.get("pCursor")).isEmpty() ? ((List<SistemaModuloBean>) parametrosNombre.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModulo;
//		fin adecuación mybatis
	}

}
