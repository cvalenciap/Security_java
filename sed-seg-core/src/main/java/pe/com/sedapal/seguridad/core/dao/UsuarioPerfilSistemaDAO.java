package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;

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
public interface UsuarioPerfilSistemaDAO {
	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception;

	int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch) throws Exception;

	List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception;

	List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema,
			Serializable codUsuario) throws Exception;

	UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario) throws Exception;

	void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception;

	void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable codUsuario,
			Serializable estado, Serializable usuario) throws Exception;
	
}
