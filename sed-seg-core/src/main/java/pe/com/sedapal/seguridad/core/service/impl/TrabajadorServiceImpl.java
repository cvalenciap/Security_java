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

import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.dao.TrabajadorDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.TrabajadorMBDAO;
import pe.com.sedapal.seguridad.core.service.TrabajadorService;

@Service("trabajadorService")
public class TrabajadorServiceImpl implements TrabajadorService{

	@Autowired
	private TrabajadorDAO trabajadorDAO;
	
//	capa DAO mybatis
	@Autowired
	private TrabajadorMBDAO trabajadorMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<TrabajadorBean> obtenerListadoTrabajador() throws Exception {
//		inicio adecuación mybatis
//		return trabajadorDAO.obtenerListadoTrabajador();
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<TrabajadorBean> trabajadores = new ArrayList<TrabajadorBean>();
		try {
			trabajadorMBDAO.obtenerListadoTrabajador(parametrosBusqueda);
			trabajadores = (List<TrabajadorBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return trabajadores;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TrabajadorBean obtenerTrabajadorPorCodigo(Serializable codTrabajador) throws Exception {
//		inicio adecuación mybatis
//		return trabajadorDAO.obtenerTrabajadorPorCodigo(codTrabajador);
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		TrabajadorBean trabajador = new TrabajadorBean();
		try {
			parametrosCodigo.put("codTrabajador", codTrabajador);
			trabajadorMBDAO.obtenerTrabajadorPorCodigo(parametrosCodigo);
			trabajador = !((List<TrabajadorBean>) parametrosCodigo.get("pCursor")).isEmpty() ? ((List<TrabajadorBean>) parametrosCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return trabajador;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TrabajadorBean obtenerTrabajadorPorFicha(Serializable codFicha) throws Exception {
//		inicio adecuación mybatis
//		return trabajadorDAO.obtenerTrabajadorPorFicha(codFicha);
		Map<String, Object> parametrosFicha = new HashMap<String, Object>();
		TrabajadorBean trabajador = new TrabajadorBean();
		try {
			parametrosFicha.put("codFicha", Integer.parseInt(String.valueOf(codFicha)));
			trabajadorMBDAO.obtenerTrabajadorPorFicha(parametrosFicha);
			trabajador = !((List<TrabajadorBean>) parametrosFicha.get("pCursor")).isEmpty() ? ((List<TrabajadorBean>) parametrosFicha.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return trabajador;
//		fin adecuación mybatis
	}
	
//	inicio adecuacion seguridad2
	@Override
	@Transactional(rollbackFor=Exception.class)
	public TrabajadorBean obtenerDatosTrabajadorWs(Integer codFicha) throws Exception{
		Map<String, Object> parametrosFicha = new HashMap<String, Object>();
		TrabajadorBean trabajador = new TrabajadorBean();
		try {
			parametrosFicha.put("codFicha", Integer.parseInt(String.valueOf(codFicha)));
			trabajadorMBDAO.obtenerDatosTrabajadorWs(parametrosFicha);
			trabajador = !((List<TrabajadorBean>) parametrosFicha.get("pCursor")).isEmpty() ? ((List<TrabajadorBean>) parametrosFicha.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return trabajador;
	}
//	fin adecuacion seguridad2
}
