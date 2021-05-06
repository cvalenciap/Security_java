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
import pe.com.sedapal.seguridad.core.bean.MenuBean;
import pe.com.sedapal.seguridad.core.dao.MenuDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.MenuMBDAO;
import pe.com.sedapal.seguridad.core.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	
//	capa DAO mybatis
	@Autowired
	private MenuMBDAO menuMBDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo) throws Exception {
//		inicio adecuación mybatis
//		return this.menuDAO.obtenerListadoSistemaModuloFormularioMenu(codSistema, codModulo);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<MenuBean> menus = new ArrayList<MenuBean>();
		try {	
			parametrosBusqueda.put("codSistema", Integer.parseInt(String.valueOf(codSistema)));
			parametrosBusqueda.put("codModulo", Integer.parseInt(String.valueOf(codModulo)));
			menuMBDAO.obtenerListadoSistemaModuloFormularioMenu(parametrosBusqueda);
			menus = (List<MenuBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menus;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo) throws Exception {
//		inicio adecuación mybatis
//		return this.menuDAO.obtenerSistemaModuloFormularioMenu(codSistema, codModulo);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		StringBuilder menu = new StringBuilder();
		try {	
			parametrosBusqueda.put("codSistema", codSistema);
			parametrosBusqueda.put("codModulo", codModulo);
			menuMBDAO.obtenerListadoSistemaModuloFormularioMenu(parametrosBusqueda);
			menu = menuDAO.MenuFormularioModulo(menu, (List<MenuBean>) parametrosBusqueda.get("pCursor"), 0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return menu;
//		fin adecuación mybatis
	}
	
}
