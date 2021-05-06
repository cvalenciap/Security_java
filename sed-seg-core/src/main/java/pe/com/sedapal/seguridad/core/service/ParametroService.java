package pe.com.sedapal.seguridad.core.service;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.ParametroBean;

public interface ParametroService {

	List<ParametroBean> obtenerListadoParametrosPorCodigoParametro(Serializable codParametro) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametroPorCodigo(Serializable codParametro, Serializable codigo)
			throws Exception;

	List<ParametroBean> obtenerListadoParametroUnicos() throws Exception;

	void actualizarParametro(ParametroBean parametroBean) throws Exception;

	ParametroBean obtenerParametroPorCodigoParametro(Serializable codParametro) throws Exception;
}
