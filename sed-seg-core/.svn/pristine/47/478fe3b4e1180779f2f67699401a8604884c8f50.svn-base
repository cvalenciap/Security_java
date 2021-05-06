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
import pe.com.sedapal.seguridad.core.bean.SistemaModuloBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.SistemaModuloDAO;
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
public class SistemaModuloDAOImpl implements SistemaModuloDAO {
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
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigo(Serializable codSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SISTEMA_MODULO, paramsInput,
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
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivos(Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SISTEMA_MODULO_ACT, paramsInput,
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
	public List<SistemaModuloBean> obtenerListadoSistemaModuloPorCodigoActivosModuloActivos(Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MOD_ACT_MACT, paramsInput,
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
	public SistemaModuloBean obtenerSistemaModuloPorCodigo(Serializable codSistema, Serializable codModulo)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloBean> lista = null;
		SistemaModuloBean sistemaModuloBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_SISTEMA_MODULO_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				sistemaModuloBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sistemaModuloBean;
	}

	@Override
	public void grabarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_SISTEMA_MODULO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, sistemaModuloBean.getCodSistema());
			inputs.put(Constants.P_NCODMODULO, sistemaModuloBean.getCodModulo());
			inputs.put(Constants.P_VDESCRIPCION, sistemaModuloBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, sistemaModuloBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, sistemaModuloBean.getUsuarioCreacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarSistemaModulo(SistemaModuloBean sistemaModuloBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_SISTEMA_MODULO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODSISTEMA, sistemaModuloBean.getCodSistema());
			inputs.put(Constants.P_NCODMODULO, sistemaModuloBean.getCodModulo());
			inputs.put(Constants.P_VDESCRIPCION, sistemaModuloBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, sistemaModuloBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, sistemaModuloBean.getUsuarioModificacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarEstadoSistemaModulo(Serializable codSistema, Serializable codModulo, Serializable estado,
			Serializable usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;

		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_SISTEMA_MODULO_ESTAD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NESTADO, estado);
			inputs.put(Constants.P_VUSUARIO, usuario);

			results = execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public SistemaModuloBean obtenerSistemaModuloPorNombre(Serializable codSistema, Serializable nombreModulo)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloBean> lista = null;
		SistemaModuloBean sistemaModuloBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_SISTEMA_MODULO_NOM, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_VDESCRIPCION, nombreModulo);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				sistemaModuloBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sistemaModuloBean;
	}

}
