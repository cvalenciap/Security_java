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
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.dao.SistemaModuloFormDAO;
import pe.com.sedapal.seguridad.core.mybatis.dao.SistemaModuloFormMBDAO;
import pe.com.sedapal.seguridad.core.service.SistemaModuloFormService;

@Service("sistemamoduloformService")
public class SistemaModuloFormServiceImpl implements SistemaModuloFormService {

	@Autowired
	private SistemaModuloFormDAO sistemaModuloFormDAO;
	
//	capa DAO mybatis
	@Autowired
	private SistemaModuloFormMBDAO sistemaModuloFormMBDAO;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo) throws Exception {
//		inicio adecuación mybatis
//		return sistemaModuloFormDAO.obtenerListadoSistemaModuloFormPorCodigo(codSistema, codModulo);
		Map<String, Object> parametrosBusqueda = new HashMap<String, Object>();
		List<SistemaModuloFormBean> sistemaModuloForm = new ArrayList<SistemaModuloFormBean>();
		try {
			parametrosBusqueda.put("codSistema", (Integer) codSistema);
			parametrosBusqueda.put("codModulo", (Integer) codModulo);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormPorCodigo(parametrosBusqueda);
			sistemaModuloForm = (List<SistemaModuloFormBean>) parametrosBusqueda.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloForm;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Serializable parametro, Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return sistemaModuloFormDAO.obtenerListadoSistemaModuloFormInicioPorCodigo(parametro, codUsuario);
		Map<String, Object> parametrosDetalle = new HashMap<String, Object>();
		List<SistemaModuloFormBean> sistemaModuloForm = new ArrayList<SistemaModuloFormBean>();
		try {
			parametrosDetalle.put("parametro", parametro);
			parametrosDetalle.put("codUsuario", codUsuario);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormInicioPorCodigo(parametrosDetalle);
			sistemaModuloForm = (List<SistemaModuloFormBean>) parametrosDetalle.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloForm;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Serializable parametro, Serializable codUsuario) throws Exception {
//		inicio adecuación mybatis
//		return sistemaModuloFormDAO.obtenerListadoSistemaModuloFormInicioPorCodigov2(parametro, codUsuario);
		Map<String, Object> parametrosDetalle2 = new HashMap<String, Object>();
		List<SistemaModuloOpcionBean> sistemaModuloOpcion = new ArrayList<SistemaModuloOpcionBean>();
		try {
			parametrosDetalle2.put("parametro", parametro);
			parametrosDetalle2.put("codUsuario", codUsuario);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormInicioPorCodigov2(parametrosDetalle2);
			sistemaModuloOpcion = (List<SistemaModuloOpcionBean>) parametrosDetalle2.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloOpcion;
//		fin adecuación mybatis
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo, Serializable codFormulario) throws Exception {
//		inicio adecuación mybatis
//		return sistemaModuloFormDAO.obtenerSistemaModuloFormPorCodigo(codSistema, codModulo, codFormulario);
		Map<String, Object> parametrosCodigo = new HashMap<String, Object>();
		SistemaModuloFormBean sistemaModuloForm = new SistemaModuloFormBean();
		try {
			parametrosCodigo.put("codSistema", codSistema);
			parametrosCodigo.put("codModulo", codModulo);
			parametrosCodigo.put("codFormulario", codFormulario);
			sistemaModuloFormMBDAO.obtenerSistemaModuloFormPorCodigo(parametrosCodigo);
			sistemaModuloForm = !((List<SistemaModuloFormBean>) parametrosCodigo.get("pCursor")).isEmpty() ? ((List<SistemaModuloFormBean>) parametrosCodigo.get("pCursor")).get(0) : null;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloForm;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int grabarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
//		inicio adecuación mybatis
//		return this.sistemaModuloFormDAO.grabarSistemaModuloForm(sistemaModuloFormBean);
		int result = 0;
		Map<String, Object> parametrosGrabar = new HashMap<String, Object>();
		try {
			parametrosGrabar.put("codSistema", sistemaModuloFormBean.getCodSistema());
			parametrosGrabar.put("codModulo", sistemaModuloFormBean.getCodModulo());
			parametrosGrabar.put("codFormulario", sistemaModuloFormBean.getCodFormulario());
			parametrosGrabar.put("descripcion", sistemaModuloFormBean.getDescripcion());
			parametrosGrabar.put("estado", sistemaModuloFormBean.getEstado());
			parametrosGrabar.put("codFormularioPadre", sistemaModuloFormBean.getCodFormularioPadre());
			parametrosGrabar.put("nivelFormulario", sistemaModuloFormBean.getNivelFormulario());
			parametrosGrabar.put("ordenFormulario", sistemaModuloFormBean.getOrdenFormulario());
			parametrosGrabar.put("urlFormulario", sistemaModuloFormBean.getUrlFormulario());
			parametrosGrabar.put("usuarioCreacion", sistemaModuloFormBean.getUsuarioCreacion());			
			sistemaModuloFormMBDAO.grabarSistemaModuloForm(parametrosGrabar);
			result = parametrosGrabar.get("identity") != null ? (int) parametrosGrabar.get("identity") : result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
//		inicio adecuación mybatis
//		this.sistemaModuloFormDAO.actualizarSistemaModuloForm(sistemaModuloFormBean);
		try {
			sistemaModuloFormMBDAO.actualizarSistemaModuloForm(sistemaModuloFormBean);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void actualizarEstadoSistemaModuloForm(Serializable codSistema, Serializable codModulo, Serializable codFormulario, Serializable estado, Serializable usuario) throws Exception {
//		inicio adecuación mybatis
//		this.sistemaModuloFormDAO.actualizarEstadoSistemaModuloForm(codSistema, codModulo, codFormulario, estado, usuario);
		Map<String, Object> parametrosActualizar = new HashMap<String, Object>();
		try {
			parametrosActualizar.put("codSistema", codSistema);
			parametrosActualizar.put("codModulo", codModulo);
			parametrosActualizar.put("codFormulario", codFormulario);
			parametrosActualizar.put("estado", estado);
			parametrosActualizar.put("usuario", usuario);
			sistemaModuloFormMBDAO.actualizarEstadoSistemaModuloForm(parametrosActualizar);
		}catch(Exception e) {
			e.printStackTrace();
		}
//		fin adecuación mybatis
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaModuloFormDAO.obtenerListadoSistemaModuloFormPaginarTotal(tipoBusqueda, valorBusqueda);
		Map<String, Object> parametrosPaginaTotal = new HashMap<String, Object>();
		int result = 0;
		try {
			parametrosPaginaTotal.put("tipoBusqueda", tipoBusqueda);
			parametrosPaginaTotal.put("valorBusqueda", valorBusqueda);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormPaginarTotal(parametrosPaginaTotal);
			result = parametrosPaginaTotal.get("P_TotalReg") != null ? (int) parametrosPaginaTotal.get("P_TotalReg") : result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex, String tipoBusqueda, String valorBusqueda, String sortColumn) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaModuloFormDAO.obtenerListadoSistemaModuloFormPaginar(pageSize, pageIndex, tipoBusqueda, valorBusqueda, sortColumn);
		Map<String, Object> parametrosPaginar = new HashMap<String, Object>();
		List<SistemaModuloFormBean> sistemaModuloForm = new ArrayList<SistemaModuloFormBean>();
		try {
			parametrosPaginar.put("pageSize", pageSize);
			parametrosPaginar.put("pageIndex", pageIndex);
			parametrosPaginar.put("tipoBusqueda", tipoBusqueda);
			parametrosPaginar.put("valorBusqueda", valorBusqueda);
			parametrosPaginar.put("sortColumn", sortColumn);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormPaginar(parametrosPaginar);
			sistemaModuloForm = (List<SistemaModuloFormBean>) parametrosPaginar.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloForm;
//		fin adecuación mybatis
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema, Serializable codModulo, Serializable codFormulario) throws Exception {
		// TODO Auto-generated method stub
//		inicio adecuación mybatis
//		return this.sistemaModuloFormDAO.obtenerSistemaModuloFormPorCodigoHijos(codSistema, codModulo, codFormulario);
		Map<String, Object> parametrosCodigoHijo = new HashMap<String, Object>();
		List<SistemaModuloFormBean> sistemaModuloForm = new ArrayList<SistemaModuloFormBean>();
		try {
			parametrosCodigoHijo.put("codSistema", codSistema);
			parametrosCodigoHijo.put("codModulo", codModulo);
			parametrosCodigoHijo.put("codFormulario", codFormulario);
			sistemaModuloFormMBDAO.obtenerSistemaModuloFormPorCodigoHijos(parametrosCodigoHijo);
			sistemaModuloForm = (List<SistemaModuloFormBean>) parametrosCodigoHijo.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloForm;
//		fin adecución mybatis
	}
	
//	inicio adecuacion seguridad2
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoAct(Serializable codSistema, Serializable codUsuario, Serializable codPerfil) throws Exception {
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<SistemaModuloOpcionBean> sistemaModuloOpcion = new ArrayList<SistemaModuloOpcionBean>();
		try {
			parametrosDetalleLista.put("codSistema", codSistema);
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codPerfil", codPerfil);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormInicioPorCodigoAct(parametrosDetalleLista);
			sistemaModuloOpcion = (List<SistemaModuloOpcionBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloOpcion;
	}
	
	/*PROCESOS LDAP*/
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackFor=Exception.class)
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(Serializable codSistema, Serializable codUsuario, Serializable codPerfil) throws Exception {
		Map<String, Object> parametrosDetalleLista = new HashMap<String, Object>();
		List<SistemaModuloOpcionBean> sistemaModuloOpcion = new ArrayList<SistemaModuloOpcionBean>();
		try {
			parametrosDetalleLista.put("codSistema", codSistema);
			parametrosDetalleLista.put("codUsuario", codUsuario);
			parametrosDetalleLista.put("codPerfil", codPerfil);
			sistemaModuloFormMBDAO.obtenerListadoSistemaModuloFormInicioPorCodigoLDAP(parametrosDetalleLista);
			sistemaModuloOpcion = (List<SistemaModuloOpcionBean>) parametrosDetalleLista.get("pCursor");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sistemaModuloOpcion;
	}
//	fin adecuacion seguridad2
	
}
