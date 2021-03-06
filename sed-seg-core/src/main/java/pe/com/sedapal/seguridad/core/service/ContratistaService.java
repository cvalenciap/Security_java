package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;
import pe.com.sedapal.seguridad.core.bean.SistemaBean;

public interface ContratistaService {
	
	List<ContratistaBean> listarContratistas() throws Exception;

	int obtenerListadoContratistaPaginarTotal(String valueSearch) throws Exception;
	
	List<ContratistaBean> obtenerListadoContratistaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception;
	
    void grabarContratista(ContratistaBean contratistaBean) throws Exception;
	
	void actualizarContratista(ContratistaBean contratistaBean) throws Exception;

	ContratistaBean obtenerContratistaPorId(Serializable id)throws Exception;

	void eliminarContratista(Serializable id)throws Exception;
	
	
}
