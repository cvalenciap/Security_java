package pe.com.sedapal.seguridad.core.mybatis.dao;

import java.util.List;
import java.util.Map;

import pe.com.sedapal.seguridad.core.bean.ContratistaBean;

public interface ContratistaMBDAO {
	
	List<ContratistaBean> listarContratistas(Map<String, Object> parametrosListar) throws Exception;
	void obtenerListadoContratistaPaginarTotal(Map<String, Object> parametrosPagina) throws Exception;
	List<ContratistaBean> obtenerListadoContratistaPaginar(Map<String, Object> parametrosPaginan) throws Exception;
	void grabarContratista(ContratistaBean contratistaBean);
	void actualizarContratista(ContratistaBean contratistaBean);
	void obtenerContratistaPorId(Map<String, Object> parametrosCodigo);
	void eliminarContratista(Map<String, Object> parametrosActualizar);
	
}
