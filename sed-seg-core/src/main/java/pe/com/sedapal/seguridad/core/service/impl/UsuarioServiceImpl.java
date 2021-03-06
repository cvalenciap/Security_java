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
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.dao.UsuarioDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.UsuarioMBDAO;
import pe.com.sedapal.seguridad.core.service.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
//	capa DAO mybatis
	@Autowired
	private UsuarioMBDAO usuarioMBDAO;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return this.usuarioDAO.obtenerUsuarioPorCodUsuario(codUsuario);
		Map<String, Object> parametrosLista = new HashMap<String, Object>();
		UsuarioBean usuario = new UsuarioBean();
		try {
			parametrosLista.put("codUsuario", codUsuario);
			usuarioMBDAO.obtenerUsuarioPorCodUsuario(parametrosLista);
			usuario = !((List<UsuarioBean>) parametrosLista.get("PAR_OUT_CURSOR")).isEmpty() ? ((List<UsuarioBean>) parametrosLista.get("PAR_OUT_CURSOR")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerListadoUsuario() throws Exception {
//		inicio adecuación mybatis
//		return usuarioDAO.obtenerListadoUsuario();
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
		try {
			usuarioMBDAO.obtenerListadoUsuario(parametrosBusqueda);
			usuarios = (List<UsuarioBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarios;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioDAO.actualizarUsuarioEstadoPorCodUsuario(codUsuario, estado, usuario,codSistema);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("codUsuario", codUsuario);
			parametrosActualizar.put("estado", estado);
			parametrosActualizar.put("usuario", usuario);
			parametrosActualizar.put("codSistema", codSistema);
			usuarioMBDAO.actualizarUsuarioEstadoPorCodUsuario(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable nrointentos, Serializable usuario,Serializable codSistema) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioDAO.bloqueoUsuarioEstadoPorCodUsuario(codUsuario, estado, nrointentos, usuario,codSistema);
		Map<String, Object> parametrosBloqueo = new HashMap<String, Object>();
		try {
			parametrosBloqueo.put("codUsuario", codUsuario);
			parametrosBloqueo.put("estado", estado);
			parametrosBloqueo.put("nrointentos", nrointentos);
			parametrosBloqueo.put("usuario", usuario);
			parametrosBloqueo.put("codSistema", codSistema);
			usuarioMBDAO.bloqueoUsuarioEstadoPorCodUsuario(parametrosBloqueo);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioClavesErroneas(Serializable codUsuario, int nroIntentos) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
		this.usuarioDAO.actualizarUsuarioClavesErroneas(codUsuario, nroIntentos);
		Map<String, Object> parametrosActClave = new HashMap<String, Object>();
		try {
			parametrosActClave.put("codUsuario", codUsuario);
			parametrosActClave.put("nroIntentos", nroIntentos);
			usuarioMBDAO.actualizarUsuarioClavesErroneas(parametrosActClave);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuario(UsuarioBean usuarioBean) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioDAO.grabarUsuario(usuarioBean);
		try {
			usuarioMBDAO.grabarUsuario(usuarioBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuario(UsuarioBean usuarioBean) throws Exception {
//		inicio adecuación mybatis
//		this.usuarioDAO.actualizarUsuario(usuarioBean);
		try {
			usuarioMBDAO.actualizarUsuario(usuarioBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario, Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.usuarioDAO.obtenerUsuarioPorCodUsuarioCodSistema(codUsuario, codSistema);
		Map<String, Object> parametrosUsuarioCod = new HashMap<String, Object>();
		UsuarioBean usuario = new UsuarioBean();
		try {
			parametrosUsuarioCod.put("codUsuario", codUsuario);
			parametrosUsuarioCod.put("codSistema", codSistema);
			usuarioMBDAO.obtenerUsuarioPorCodUsuarioCodSistema(parametrosUsuarioCod);
			usuario = !((List<UsuarioBean>) parametrosUsuarioCod.get("pCursor")).isEmpty() ? ((List<UsuarioBean>) parametrosUsuarioCod.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
//		fin adecuación mybatis
	}
	
	
//	inicio adecuacion seguridad2
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioBean obtenerUsuarioPorCodUsuarioAct(Serializable codUsuario) throws Exception {
		Map<String, Object> parametrosLista = new HashMap<String, Object>();
		UsuarioBean usuario = new UsuarioBean();
		try {
			parametrosLista.put("codUsuario", codUsuario);
			usuarioMBDAO.obtenerUsuarioPorCodUsuarioAct(parametrosLista);
			usuario = !((List<UsuarioBean>) parametrosLista.get("PAR_OUT_CURSOR")).isEmpty() ? ((List<UsuarioBean>) parametrosLista.get("PAR_OUT_CURSOR")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaAct(Serializable codUsuario, Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> parametrosUsuarioCodLista = new HashMap<String, Object>();
		List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
		try {
			parametrosUsuarioCodLista.put("codUsuario", codUsuario);
			parametrosUsuarioCodLista.put("codSistema", codSistema);
			usuarioMBDAO.obtenerUsuarioPorCodUsuarioCodSistema(parametrosUsuarioCodLista);
			usuarios = (List<UsuarioBean>) parametrosUsuarioCodLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void bloqueoUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado
			, Serializable nrointentos, Serializable usuario) throws Exception {
		Map<String, Object> parametrosBloqueoLista = new HashMap<String, Object>();
		try {
			parametrosBloqueoLista.put("codUsuario", codUsuario);
			parametrosBloqueoLista.put("estado", estado);
			parametrosBloqueoLista.put("nrointentos", nrointentos);
			parametrosBloqueoLista.put("usuario", usuario);
			usuarioMBDAO.bloqueoUsuarioEstadoPorCodUsuarioAct(parametrosBloqueoLista);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioAct(UsuarioBean usuarioBean) throws Exception {
		try {
			usuarioMBDAO.actualizarUsuarioAct(usuarioBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioAct(UsuarioBean usuarioBean) throws Exception {
		try {
			usuarioMBDAO.grabarUsuarioAct(usuarioBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarUsuarioEstadoPorCodUsuarioAct(Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception {
		Map<String, Object> parametrosActualizarAct = new HashMap<String, Object>();
		try {
			parametrosActualizarAct.put("codUsuario", codUsuario);
			parametrosActualizarAct.put("estado", estado);
			parametrosActualizarAct.put("usuario", usuario);
			usuarioMBDAO.actualizarUsuarioEstadoPorCodUsuarioAct(parametrosActualizarAct);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*PROCESOS LDAP*/
	@Override
	@Transactional(rollbackFor=Exception.class)
	public UsuarioBean obtenerUsuarioPorCodUsuarioLDAP(Serializable codUsuario) throws Exception {
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		UsuarioBean usuario = new UsuarioBean();
		try {
			parametrosDetalle.put("codUsuario", codUsuario);
			usuarioMBDAO.obtenerUsuarioPorCodUsuarioLDAP(parametrosDetalle);
			usuario = !((List<UsuarioBean>) parametrosDetalle.get("pCursor")).isEmpty() ? ((List<UsuarioBean>) parametrosDetalle.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<UsuarioBean> obtenerUsuarioPorCodUsuarioCodSistemaLDAP(Serializable codUsuario, Serializable codSistema) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> parametrosUsuarioCodLista = new HashMap<String, Object>();
		List<UsuarioBean> usuarios = new ArrayList<UsuarioBean>();
		try {
			parametrosUsuarioCodLista.put("codUsuario", codUsuario);
			parametrosUsuarioCodLista.put("codSistema", codSistema);
			usuarioMBDAO.obtenerUsuarioPorCodUsuarioCodSistemaLDAP(parametrosUsuarioCodLista);
			usuarios = (List<UsuarioBean>) parametrosUsuarioCodLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void grabarUsuarioLDAP(UsuarioBean usuarioLDAPBean) throws Exception {
		try {
			usuarioMBDAO.grabarUsuarioLDAP(usuarioLDAPBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
//	fin adecuacion seguridad2
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean validacionUsuarioExterno(String codUsuario, Integer codEmpresa, String dniExterno) throws Exception {
		Map<String, Object> parametrosValidExterno = new HashMap<String, Object>();
		boolean flagValid = true;
		try {
			parametrosValidExterno.put("PAR_VCODUSUARIO", codUsuario);
			parametrosValidExterno.put("PAR_N_ID_CONTRATISTA", codEmpresa);
			parametrosValidExterno.put("PAR_V_DES_NRO_DNI", dniExterno);
			usuarioMBDAO.validacionUsuarioExterno(parametrosValidExterno);
			flagValid = ((List<UsuarioBean>) parametrosValidExterno.get("PAR_OUT_CURSOR")).isEmpty() ? true : false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flagValid;
	}
	
}

