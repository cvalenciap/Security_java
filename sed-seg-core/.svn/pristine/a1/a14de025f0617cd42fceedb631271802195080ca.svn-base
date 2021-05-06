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
import pe.com.sedapal.seguridad.core.bean.TrabajadorBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.TrabajadorDAO;
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
public class TrabajadorDAOImpl implements TrabajadorDAO {

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
	public List<TrabajadorBean> obtenerListadoTrabajador() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<TrabajadorBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new TrabajadorBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_TRABAJADOR, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public TrabajadorBean obtenerTrabajadorPorCodigo(Serializable codTrabajador) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<TrabajadorBean> lista = null;
		TrabajadorBean trabajadorBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODTRABAJADOR, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new TrabajadorBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_TRABAJADOR_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODTRABAJADOR, codTrabajador);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				trabajadorBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return trabajadorBean;
	}

	@Override
	public TrabajadorBean obtenerTrabajadorPorFicha(Serializable codFicha) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<TrabajadorBean> lista = null;
		TrabajadorBean trabajadorBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NFICHA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new TrabajadorBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_TRABAJADOR_FICHA, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NFICHA, codFicha);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				trabajadorBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return trabajadorBean;
	}

}
