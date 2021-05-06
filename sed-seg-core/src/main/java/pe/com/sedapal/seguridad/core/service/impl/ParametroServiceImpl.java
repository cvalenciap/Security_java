package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.dao.ParametroDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.ParametroMBDAO;
import pe.com.sedapal.seguridad.core.service.ParametroService;

@Service("parametroService")
public class ParametroServiceImpl implements ParametroService {

	@Autowired
	private ParametroDAO parametroDAO;
	
//	capa DAO mybatis
	@Autowired
	private ParametroMBDAO parametroMBDAO;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Serializable codParametro) throws Exception {
//		inicio adecuacion mybatis
//		return this.parametroDAO.obtenerListadoParametrosPorCodigoParametro(codParametro);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<ParametroBean> parametros = new ArrayList<ParametroBean>();
		try {
			parametrosBusqueda.put("codParametro", codParametro);
			parametroMBDAO.obtenerListadoParametrosPorCodigoParametro(parametrosBusqueda);
			parametros = (List<ParametroBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return parametros;
//		fin adecuacion mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Serializable codParametro, Serializable codigo) throws Exception {
//		inicio adecuacion mybatis
//		return this.parametroDAO.obtenerParametroPorCodigoParametroPorCodigo(codParametro, codigo);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		ParametroBean parametro = new ParametroBean();
		try {
			parametrosDetalle.put("codParametro", codParametro);
			parametrosDetalle.put("codigo", codigo);
			parametroMBDAO.obtenerParametroPorCodigoParametroPorCodigo(parametrosDetalle);
			parametro = !((List<ParametroBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<ParametroBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return parametro;
//		fin adecuacion mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ParametroBean> obtenerListadoParametroUnicos() throws Exception {
//		inicio adecuación mybatis
//		return this.parametroDAO.obtenerListadoParametroUnicos();
		Map<String, Object> parametrosUnicos = new HashMap<String, Object>();
		List<ParametroBean> parametros = new ArrayList<ParametroBean>();
		try {
			parametroMBDAO.obtenerListadoParametroUnicos(parametrosUnicos);
			parametros = (List<ParametroBean>) parametrosUnicos.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return parametros;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarParametro(ParametroBean parametroBean) throws Exception {
//		inicio adecuación mybatis
//		this.parametroDAO.actualizarParametro(parametroBean);
		try {
			parametroMBDAO.actualizarParametro(parametroBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecaución mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ParametroBean obtenerParametroPorCodigoParametro(Serializable codParametro) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.parametroDAO.obtenerParametroPorCodigoParametro(codParametro);
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		ParametroBean parametro = new ParametroBean();
		try {
			parametrosCodigo.put("codParametro", codParametro);
			parametroMBDAO.obtenerParametroPorCodigoParametro(parametrosCodigo);
			parametro = !((List<ParametroBean>) parametrosCodigo.get("pCursor")).isEmpty() ? ((List<ParametroBean>) parametrosCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return parametro;
//		fin adecuación mybatis
	}

}
