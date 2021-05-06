package pe.com.sedapal.seguridad.core.service.impl;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 06/01/2017.
 * #Autor: Miguel Aldana.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
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

import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.bean.AreaBean;
import pe.com.sedapal.seguridad.core.bean.CentroBean;
import pe.com.sedapal.seguridad.core.dao.CentroDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.CentroMBDAO;
import pe.com.sedapal.seguridad.core.service.CentroService;

@Service("centroService")
public class CentroServiceImpl implements CentroService{

	@Autowired
	private CentroDAO centroDAO;
	
//	capa DAO mybatis
	@Autowired
	private CentroMBDAO centroMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<CentroBean> obtenerListadoCentro() throws Exception {
//		inicio adecuación para mybatis
//		return centroDAO.obtenerListadoCentro();
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<CentroBean> centros = new ArrayList<CentroBean>();
		try {
			centroMBDAO.obtenerListadoCentro(parametrosBusqueda);
			centros = (List<CentroBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return centros;
//		fin adecuación para mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public CentroBean obtenerCentroPorCodigo(Serializable codCentro) throws Exception {
//		inicio adecuación para mybatis
//		return centroDAO.obtenerCentroPorCodigo(codCentro);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		CentroBean centro = new CentroBean();
		try {
			parametrosDetalle.put("codCentro", codCentro);
			centroMBDAO.obtenerCentroPorCodigo(parametrosDetalle);
			centro = !((List<CentroBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<CentroBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return centro;
//		fin adecuación para mybatis
	}	
	
}
