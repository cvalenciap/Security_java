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
import pe.com.sedapal.seguridad.core.bean.UsuarioPerfilBean;
import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.UsuarioPerfilSistemaDAO;
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
public class UsuarioPerfilSistemaDAOImpl implements UsuarioPerfilSistemaDAO {
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
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistema() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioPerfilBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioPerfilBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUA_PERF_SIST, paramsInput,
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
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistema() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioSistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioSistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO_SISTEMA, paramsInput,
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
	public int obtenerListadoUsuarioSistemaPaginarTotal(String valueSearch) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		int total = 0;
		

		try {
			paramsInput = new ArrayList<SqlParameter>();

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VALUE_SEARCH, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_TOTAL_REG, OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO_PERFIL_TOT, paramsInput,
					paramsOutput);

			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VALUE_SEARCH, valueSearch);

			results = this.execSp.executeSp(inputs);
			total = (int) ExecuteProcedure.retornaValue(results);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return total;
	}

	@Override
	public List<UsuarioSistemaBean> obtenerListadoUsuarioSistemaPaginar(int pageSize, int pageIndex, String valueSearch,
			String sortColumn) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioSistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_PAGE_SIZE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_PAGE_INDEX, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VALUE_SEARCH, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_SORT_COLUMN, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioSistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO_PERFIL_PAG, paramsInput,
					paramsOutput);

			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_PAGE_SIZE, pageSize);
			inputs.put(Constants.P_PAGE_INDEX, pageIndex);
			inputs.put(Constants.P_VALUE_SEARCH, valueSearch);
			inputs.put(Constants.P_SORT_COLUMN, sortColumn);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorUsuario(Serializable codSistema,
			Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioPerfilBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioPerfilBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUA_PERF_SIST_USU, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public List<UsuarioPerfilBean> obtenerListadoUsuarioPerfilSistemaPorCodigo(Serializable codPerfil,
			Serializable codSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioPerfilBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioPerfilBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUA_PERF_SIST, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public UsuarioPerfilBean obtenerUsuarioPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioPerfilBean> lista = null;
		UsuarioPerfilBean usuarioPerfilBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioPerfilBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_USUA_PERF_SIST_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				usuarioPerfilBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return usuarioPerfilBean;

	}

	@Override
	public void grabarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_USUA_PERF_SIST, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, usuarioPerfilBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, usuarioPerfilBean.getCodSistema());
			inputs.put(Constants.P_VCODUSUARIO, usuarioPerfilBean.getCodUsuario());
			inputs.put(Constants.P_NESTADO, usuarioPerfilBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, usuarioPerfilBean.getUsuarioCreacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarUsuarioPerfilSistema(UsuarioPerfilBean usuarioPerfilBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_USUA_PERF_SIST, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODPERFIL, usuarioPerfilBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, usuarioPerfilBean.getCodSistema());
			inputs.put(Constants.P_VCODUSUARIO, usuarioPerfilBean.getCodUsuario());
			inputs.put(Constants.P_NESTADO, usuarioPerfilBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, usuarioPerfilBean.getUsuarioModificacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarEstadoUsuarioPerfilSistema(Serializable codPerfil, Serializable codSistema,
			Serializable codUsuario, Serializable estado, Serializable usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;

		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_USUA_PERF_SIST_EST, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);
			inputs.put(Constants.P_NESTADO, estado);
			inputs.put(Constants.P_VUSUARIO, usuario);

			results = execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
