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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.sedapal.seguridad.core.bean.AreaBean;
import pe.com.sedapal.seguridad.core.dao.AreaDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.AreaMBDAO;
import pe.com.sedapal.seguridad.core.service.AreaService;

@Service("areaService")
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private AreaMBDAO areaMBDAO;
	
//	Inicio adecuación mybatis
//	@Override
//	public List<AreaBean> obtenerListadoArea() throws Exception {
//		return areaDAO.obtenerListadoArea();
//	}

//	@Override
//	public AreaBean obtenerAreaPorCodigo(Serializable codArea) throws Exception {
//		return areaDAO.obtenerAreaPorCodigo(codArea);
//	}
	
//	******************************METODOS MYBATIS*********************************************
	
	private final Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<AreaBean> obtenerListadoArea() throws Exception {
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		 List<AreaBean> lista = new ArrayList<AreaBean>();
		 List<AreaBean> lista2 = new ArrayList<AreaBean>();
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss SSS");
			
//			System.out.println("INICIO SIN MYBATIS ("+formatoFecha.format(new Date()).toString()+")");
			
			logger.info("INICIO SIN MYBATIS ("+formatoFecha.format(new Date()).toString()+")");
			lista2 = areaDAO.obtenerListadoArea();
			
//			System.out.println("FIN SIN MYBATIS ("+formatoFecha.format(new Date()).toString()+"): "+lista2.size());
			logger.info("FIN SIN MYBATIS ("+formatoFecha.format(new Date()).toString()+"): "+lista2.size());
//			System.out.println("INICIO CON MYBATIS ("+formatoFecha.format(new Date()).toString()+")");
			logger.info("INICIO CON MYBATIS ("+formatoFecha.format(new Date()).toString()+")");
			areaMBDAO.listarTodos(parametrosBusqueda);
			lista = (List<AreaBean>) parametrosBusqueda.get("pCursor");
//			System.out.println("FIN CON MYBATIS ("+formatoFecha.format(new Date()).toString()+"): "+lista.size());
			logger.info("FIN CON MYBATIS ("+formatoFecha.format(new Date()).toString()+"): "+lista.size());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AreaBean obtenerAreaPorCodigo(Serializable codArea) throws Exception {
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		AreaBean area = new AreaBean();
		try {
			parametrosDetalle.put("idArea", codArea);
			areaMBDAO.obtenerAreaXCodigo(parametrosDetalle);
			area = !((ArrayList<AreaBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((ArrayList<AreaBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}
	
//	******************************FIN METODOS MYBATIS*********************************************	
//	fin adecuación mybatis
}
