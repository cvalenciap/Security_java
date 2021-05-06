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
import pe.com.sedapal.seguridad.core.bean.AccionFormularioBean;
import pe.com.sedapal.seguridad.core.bean.AccionUsuarioBean;
import pe.com.sedapal.seguridad.core.bean.ParametroBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.AccionFormularioDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class AccionFormularioDAOImpl implements AccionFormularioDAO {
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
	public void grabarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODACCION_FORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODPARAMETRO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODIGO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_ACCION_FORMULARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODACCION_FORMULARIO, accionFormularioBean.getCodAccionFormulario());
			inputs.put(Constants.P_NCODSISTEMA, accionFormularioBean.getCodSistema());
			inputs.put(Constants.P_NCODMODULO, accionFormularioBean.getCodModulo());
			inputs.put(Constants.P_NCODFORMULARIO, accionFormularioBean.getCodFormulario());
			inputs.put(Constants.P_VCODPARAMETRO, accionFormularioBean.getCodParametro());
			inputs.put(Constants.P_NCODIGO, accionFormularioBean.getCodigo());
			inputs.put(Constants.P_NESTADO, accionFormularioBean.getEstado());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarAccionFormulario(AccionFormularioBean accionFormularioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODACCION_FORMULARIO, OracleTypes.INTEGER));
			
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_ACCION_FORMULARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODACCION_FORMULARIO, accionFormularioBean.getCodAccionFormulario());
			
			inputs.put(Constants.P_NESTADO, accionFormularioBean.getEstado());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccionFormularioBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccionFormularioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_ACCION_FORMULARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public List<AccionFormularioBean> obtenerListadoAccionFormularioPerfilPorCodigo(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccionFormularioBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccionFormularioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_ACCION_FORM_PERFIL, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);
			inputs.put(Constants.P_NCODPERFIL, codPerfil);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public List<AccionUsuarioBean> obtenerListadoAccionFormularioPerfilPorUsuario(Serializable codSistema,
			Serializable codModulo, Serializable codFormulario, Serializable codPerfil) throws Exception{
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<AccionUsuarioBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new AccionUsuarioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_ACC_FORM_PER_USU, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);
			inputs.put(Constants.P_NCODPERFIL, codPerfil);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}
}
