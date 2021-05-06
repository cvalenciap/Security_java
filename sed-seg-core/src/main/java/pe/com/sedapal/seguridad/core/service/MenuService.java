package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.MenuBean;

public interface MenuService {

	List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception;

	StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo) throws Exception;
}
