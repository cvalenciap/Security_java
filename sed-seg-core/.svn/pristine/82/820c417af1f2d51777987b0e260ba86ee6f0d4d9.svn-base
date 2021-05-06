package pe.com.sedapal.seguridad.core.dao.impl;

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
import pe.com.sedapal.seguridad.core.bean.ClaveBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.ClaveDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class ClaveDAOImpl implements ClaveDAO {

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
	public List<ClaveBean> obtenerClaves(String usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<ClaveBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new ClaveBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_CLAVES, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, usuario);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public void mantenimientoClaves(String usuario, String vpass, String flagTemp) throws Exception

	{
		// TODO Auto-generated method stub

		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VPASS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_TEMP, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_MANTENIMIENTO_CLAVES, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, usuario);
			inputs.put(Constants.P_VPASS, vpass);
			inputs.put(Constants.P_VFLAG_TEMP, flagTemp);
			this.execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void ejecutarProcesos() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_CAMBIO_CLAVE_OBLIGATORIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, "");
			this.execSp.executeSp(inputs);
			
			
			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_CAMBIO_CLAVE_TMP, paramsInput,
					paramsOutput);
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public ClaveBean obtenerUltimaClave(String usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<ClaveBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new ClaveBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_VERI_CLAVE_INVALIDA, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, usuario);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty()) {
				return lista.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}

}
