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
import pe.com.sedapal.seguridad.core.bean.AccesoBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.AccesoDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class AccesoDAOImpl implements AccesoDAO {

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
	public void grabarAcceso(AccesoBean accesoBean) throws Exception {
		// TODO Auto-generated method stub
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			// paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA,
			// OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			// paramsInput.add(new SqlParameter(Constants.P_DFECFECHA,
			// OracleTypes.DATE));
			paramsInput.add(new SqlParameter(Constants.P_NCODIP, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODAREA, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(Constants.P_VCODACCESO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VCODINGRESO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCORRELATIVO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMBER));
			paramsInput.add(new SqlParameter(Constants.P_VTOKEN, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_ACCESO, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			// inputs.put(Constants.P_NCODSISTEMA, accesoBean.getCodSistema());
			inputs.put(Constants.P_VCODUSUARIO, accesoBean.getCodUsuario());
			// inputs.put(Constants.P_DFECFECHA, new Date());
			inputs.put(Constants.P_NCODIP, accesoBean.getCodIp());
			inputs.put(Constants.P_NCODAREA, accesoBean.getCodArea());
			inputs.put(Constants.P_VCODACCESO, accesoBean.getCodAcceso());
			inputs.put(Constants.P_VCODINGRESO, accesoBean.getCodIngreso());
			inputs.put(Constants.P_NCORRELATIVO, accesoBean.getCorrelativo());
			inputs.put(Constants.P_NESTADO, accesoBean.getEstado());
			inputs.put(Constants.P_VTOKEN, accesoBean.getToken());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<AccesoBean> obtenerAccesoSistema(Serializable usuario, Serializable ip) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccesoBean> lista = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODIP, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccesoBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_ACCESO_SISTEMA, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, usuario);
			inputs.put(Constants.P_NCODIP, ip);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return lista;

	}

	@Override
	public void actualizarAcceso(Serializable token) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VTOKEN, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_INVALIDAR_ACCESO, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VTOKEN, token);

			this.execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<AccesoBean> obtenerUltimoAccesoSistema(Serializable usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccesoBean> lista = null;
		AccesoBean accesoBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccesoBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_ULTIMO_ACCESO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, usuario);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return lista;

	}
}
