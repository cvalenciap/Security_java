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
import pe.com.sedapal.seguridad.core.bean.PerfilSistemaBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.PerfilSistemaDAO;
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
public class PerfilSistemaDAOImpl implements PerfilSistemaDAO {
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
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigo(Serializable codSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilSistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilSistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_PERFILSISTEMA, paramsInput,
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
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPorCodigoActivos(Serializable codSistema) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilSistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilSistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_PERFILSISTEMA_ACT, paramsInput,
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
	public int obtenerListadoPerfilSistemaPaginarTotal(String valueSearch) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		int total = 0;
		int filter = 0;
		
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter("P_ValueSearch", OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter("P_TotalReg", OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_PERFIL_SISTEMA_TOT, paramsInput,
					paramsOutput);
			
			inputs = new HashMap<String, Object>();
			inputs.put("P_ValueSearch", valueSearch);
			
			results = this.execSp.executeSp(inputs);
			total = (int) ExecuteProcedure.retornaValue(results);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return total;
	}
	
	@Override
	public List<PerfilSistemaBean> obtenerListadoPerfilSistemaPaginar(int pageSize, int pageIndex, 
			String valueSearch, String sortColumn) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilSistemaBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter("P_PageSize", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("P_PageIndex", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("P_ValueSearch", OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter("P_SortColumn", OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilSistemaBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_PERFIL_SISTEMA_PAG, paramsInput,
					paramsOutput);
			
			inputs = new HashMap<String, Object>();
			
			inputs.put("P_PageSize", pageSize);
			inputs.put("P_PageIndex", pageIndex);
			inputs.put("P_ValueSearch", valueSearch);
			inputs.put("P_SortColumn", sortColumn);
			
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}

	@Override
	public PerfilSistemaBean obtenerPerfilSistemaPorCodigo(Serializable codPerfil, Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<PerfilSistemaBean> lista = null;
		PerfilSistemaBean perfilSistemaBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));			

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new PerfilSistemaBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_PERFIL_SISTEMA_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				perfilSistemaBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return perfilSistemaBean;

	}

	@Override
	public int grabarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> results = null;
		Map<String, Object> inputs = null;
		int identity = 0;

		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VESTADO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_IDENTITY, OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_PERFIL_SISTEMA, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, perfilSistemaBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, perfilSistemaBean.getCodSistema());
			inputs.put(Constants.P_VDESCRIPCION, perfilSistemaBean.getDescripcion());
			inputs.put(Constants.P_VESTADO, perfilSistemaBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, perfilSistemaBean.getUsuarioCreacion());

			results = this.execSp.executeSp(inputs);
			identity = (int) ExecuteProcedure.retornaValue(results);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return identity;

	}

	@Override
	public void actualizarPerfilSistema(PerfilSistemaBean perfilSistemaBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VESTADO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_PERFIL_SISTEMA, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODPERFIL, perfilSistemaBean.getCodPerfil());
			inputs.put(Constants.P_NCODSISTEMA, perfilSistemaBean.getCodSistema());
			inputs.put(Constants.P_VDESCRIPCION, perfilSistemaBean.getDescripcion());
			inputs.put(Constants.P_VESTADO, perfilSistemaBean.getEstado());
			inputs.put(Constants.P_VUSUARIO, perfilSistemaBean.getUsuarioModificacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarEstadoPerfilSistema(Serializable codPerfil, Serializable codSistema, Serializable estado,
			Serializable usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;

		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VESTADO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_PERFIL_SISTEMA_ESTAD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODPERFIL, codPerfil);
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_VESTADO, estado);
			inputs.put(Constants.P_VUSUARIO, usuario);

			results = execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
