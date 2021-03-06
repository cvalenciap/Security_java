package pe.com.sedapal.seguridad.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.mybatis.dao.ContratistaMBDAO;
import pe.com.sedapal.seguridad.core.service.ContratistaService;

@Service
public class ContratistaServiceImpl implements ContratistaService {
	
	@Autowired
	ContratistaMBDAO contratistaMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<ContratistaBean> listarContratistas() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> parametrosListar = new HashMap<String, Object>();
		List<ContratistaBean> listaContratistas = new ArrayList<ContratistaBean>();
		try {
			contratistaMBDAO.listarContratistas(parametrosListar);
			listaContratistas = (List<ContratistaBean>) parametrosListar.get("PAR_OUT_CURSOR");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaContratistas;
	}

	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoContratistaPaginarTotal(String valueSearch) throws Exception {
//		inicio adecuación mybatis
//		return perfilSistemaDAO.obtenerListadoPerfilSistemaPaginarTotal(valueSearch);
		Map<String, Object> parametrosPagina = new HashMap<String, Object>();
		int result = 0;
		try {
			parametrosPagina.put("valueSearch", valueSearch);
			contratistaMBDAO.obtenerListadoContratistaPaginarTotal(parametrosPagina);
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
	public List<ContratistaBean> obtenerListadoContratistaPaginar(int pageSize, int pageIndex, String valueSearch, String sortColumn) throws Exception {
//		inicio adecuación mybatis
//		return perfilSistemaDAO.obtenerListadoPerfilSistemaPaginar(pageSize, pageIndex, valueSearch, sortColumn);
		Map<String, Object> parametrosPaginar = new HashMap<String, Object>();
		List<ContratistaBean> contratista = new ArrayList<ContratistaBean>();
		try {
			parametrosPaginar.put("pageSize", pageSize);
			parametrosPaginar.put("pageIndex", pageIndex);
			parametrosPaginar.put("valueSearch", valueSearch);
			parametrosPaginar.put("sortColumn", sortColumn);
			contratistaMBDAO.obtenerListadoContratistaPaginar(parametrosPaginar);
			contratista = (List<ContratistaBean>) parametrosPaginar.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return contratista;
//		fin adecuación mybatis
	}
	
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarContratista(ContratistaBean contratistaBean) throws Exception {
		try {
			contratistaMBDAO.grabarContratista(contratistaBean);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(": Error en el proceso de registro de contratista");
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarContratista(ContratistaBean contratistaBean) throws Exception {
		try {
			contratistaMBDAO.actualizarContratista(contratistaBean);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception(": Error en el proceso de actualización de contratista");
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ContratistaBean obtenerContratistaPorId(Serializable idContratista) throws Exception {
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		ContratistaBean contratistaBean = new ContratistaBean();
		try {
			parametrosCodigo.put("idContratista", idContratista);
			contratistaMBDAO.obtenerContratistaPorId(parametrosCodigo);
			contratistaBean =   !((List<ContratistaBean>) parametrosCodigo.get("pCursor")).isEmpty() ? ((List<ContratistaBean>) parametrosCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return contratistaBean;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void eliminarContratista(Serializable idContratista) throws Exception {
		
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("idContratista", idContratista);
			contratistaMBDAO.eliminarContratista(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
