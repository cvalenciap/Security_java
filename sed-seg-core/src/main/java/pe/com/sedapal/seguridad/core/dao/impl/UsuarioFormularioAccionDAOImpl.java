package pe.com.sedapal.seguridad.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleTypes;
import pe.com.sedapal.seguridad.core.bean.AccesosUsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.UsuarioFormularioAccionDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 06/01/2016.
 * #Autor: Miguel Aldana
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author maldana
 * @version 1.0
 * 
 */

@Repository
public class UsuarioFormularioAccionDAOImpl implements UsuarioFormularioAccionDAO {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private DataSource dataSource;
	private ExecuteProcedure execSp;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// this.jdbcTemplate.setQueryTimeout(queryTimeout);

		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<String> obtenerListadoUsuarioAccionPorCodigo(Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccesosUsuarioBean> lista = null;
		List<String> resultados = new ArrayList<String>();
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccesosUsuarioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO_ACCION_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			for (AccesosUsuarioBean accesosUsuarioBean : lista) {
				resultados.add(accesosUsuarioBean.getAcceso());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultados;

	}
	
	@Override
	public List<String> obtenerListadoUsuarioAccionPorCodigoPb(Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccesosUsuarioBean> lista = null;
		List<String> resultados = new ArrayList<String>();
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccesosUsuarioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO_ACCION_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			for (AccesosUsuarioBean accesosUsuarioBean : lista) {
				resultados.add(accesosUsuarioBean.getAcceso());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return resultados;

	}
}
