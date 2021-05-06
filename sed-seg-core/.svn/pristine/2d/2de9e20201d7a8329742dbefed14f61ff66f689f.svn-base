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
import pe.com.sedapal.seguridad.core.bean.SistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.SistemaDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 * 
 */

@Repository
public class SistemaDAOImpl implements SistemaDAO {

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
	public void grabarSistema(SistemaBean sistemaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VABREVIATURA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VPROGRAMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_NIVEL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_SISTEMA, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VDESCRIPCION, sistemaBean.getDescripcion());
			inputs.put(Constants.P_VABREVIATURA, sistemaBean.getAbreviatura());
			inputs.put(Constants.P_NESTADO, sistemaBean.getEstado());
			inputs.put(Constants.P_VPROGRAMA, sistemaBean.getPrograma());
			inputs.put(Constants.P_VFLAG_NIVEL, sistemaBean.getFlagNivel());
			inputs.put(Constants.P_VUSUARIO, sistemaBean.getUsuarioCreacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void actualizarSistema(SistemaBean sistemaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VABREVIATURA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VPROGRAMA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_NIVEL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_SISTEMA, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODSISTEMA, sistemaBean.getCodSistema());
			inputs.put(Constants.P_VDESCRIPCION, sistemaBean.getDescripcion());
			inputs.put(Constants.P_VABREVIATURA, sistemaBean.getAbreviatura());
			inputs.put(Constants.P_NESTADO, sistemaBean.getEstado());
			inputs.put(Constants.P_VPROGRAMA, sistemaBean.getPrograma());
			inputs.put(Constants.P_VFLAG_NIVEL, sistemaBean.getFlagNivel());
			inputs.put(Constants.P_VUSUARIO, sistemaBean.getUsuarioModificacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<SistemaBean> obtenerListadoSistemas() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SISTEMAS, paramsInput,
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
	
	public List<SistemaBean> obtenerListadoSistemasActivos() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SISTEMAS_ACTIVOS, paramsInput,
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
	public SistemaBean obtenerSistemaPorId(Serializable idSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaBean> lista = null;
		SistemaBean sistemaBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_SISTEMA_ID, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, idSistema);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				sistemaBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return sistemaBean;

	}

	@Override
	public void actualizarEstadoSistema(Serializable idSistema, Serializable estado, Serializable usuario)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_NIVEL, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_SISTEMA_ESTADO, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, idSistema);
			inputs.put(Constants.P_VFLAG_NIVEL, estado);
			inputs.put(Constants.P_VUSUARIO, usuario);
			execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		// return sistemaBean;

	}

	@Override
	public List<SistemaBean> obtenerListadoSistemasPorAbreviatura(Serializable abreviatura) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VABREVIATURA, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SISTEMAS_POR_ABRE, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			
			inputs.put(Constants.P_VABREVIATURA, abreviatura);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return lista;

	}

}
