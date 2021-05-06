package pe.com.sedapal.seguridad.core.dao;

import java.io.Serializable;
import java.util.List;

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
public interface UsuarioFormularioAccionDAO {

	List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception;
	
	List<String> obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario) throws Exception;

}
