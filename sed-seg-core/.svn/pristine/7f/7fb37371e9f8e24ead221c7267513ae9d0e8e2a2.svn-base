package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.MenuBean;

public interface MenuDAO {

	List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception;

	StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo) throws Exception;

	//Agregado para consumir con mybatis
	StringBuilder MenuFormularioModulo(StringBuilder menu, List<MenuBean> list, int i) throws Exception;

}
