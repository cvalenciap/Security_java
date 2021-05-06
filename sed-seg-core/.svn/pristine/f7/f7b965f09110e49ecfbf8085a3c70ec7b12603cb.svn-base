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
import pe.com.sedapal.seguridad.core.bean.DatosBean;
import pe.com.sedapal.seguridad.core.bean.PerfilAccionBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.PerfilAccionDAO;
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
public class PerfilAccionDAOImpl implements PerfilAccionDAO {
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
	public List<PerfilAccionBean> obtenerListadoPerfilAccionPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilAccionBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilAccionBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_PERFIL_ACCION, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	public List<DatosBean> obtenerListadoPerfilAccion(int pageSize, int pageIndex, String sortColumn) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<DatosBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsInput.add(new SqlParameter("PageSize", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("PageIndex", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("SortColumn", OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new DatosBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, "PKG_SEGURIDAD.SP_OBTENER_PERFIL_ACCION_TEST",
					paramsInput, paramsOutput);

			inputs = new HashMap<String, Object>();

			inputs.put("PageSize", pageSize);
			inputs.put("PageIndex", pageIndex);
			inputs.put("SortColumn", sortColumn);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;

	}

	@Override
	public PerfilAccionBean obtenerPerfilAccionPorCodigo(Serializable codPerfilAccion) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilAccionBean> lista = null;
		PerfilAccionBean perfilAccionBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL_ACCION, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilAccionBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_PERFIL_ACCION_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL_ACCION, codPerfilAccion);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				perfilAccionBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return perfilAccionBean;

	}

	@Override
	public void grabarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL_ACCION, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODACCION_FORMULARIO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_PERFIL_ACCION, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL_ACCION, perfilAccionBean.getCodPerfilAccion());
			inputs.put(Constants.P_NCODPERFIL, perfilAccionBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, perfilAccionBean.getCodSistema());
			inputs.put(Constants.P_NCODACCION_FORMULARIO, perfilAccionBean.getCodAccionFormulario());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarPerfilAccion(PerfilAccionBean perfilAccionBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL_ACCION, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODACCION_FORMULARIO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_PERFIL_ACCION, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL_ACCION, perfilAccionBean.getCodPerfilAccion());
			inputs.put(Constants.P_NCODPERFIL, perfilAccionBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, perfilAccionBean.getCodSistema());
			inputs.put(Constants.P_NCODACCION_FORMULARIO, perfilAccionBean.getCodAccionFormulario());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void eliminarPerfilAccion(Serializable codPerfil, Serializable codSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_PERFIL_ACCION, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
