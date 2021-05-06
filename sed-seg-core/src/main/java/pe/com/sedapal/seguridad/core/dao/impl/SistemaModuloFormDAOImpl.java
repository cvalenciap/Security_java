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
import pe.com.sedapal.seguridad.core.bean.SistemaModuloFormBean;
import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.SistemaModuloFormDAO;
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
public class SistemaModuloFormDAOImpl implements SistemaModuloFormDAO {
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
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPorCodigo(Serializable codSistema,
			Serializable codModulo) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloFormBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloFormBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MODULO_FORM, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}
	
	@Override
	public int obtenerListadoSistemaModuloFormPaginarTotal(String tipoBusqueda, String valorBusqueda) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		int total = 0;
		int filter = 0;
		
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter("P_TypeSearch", OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter("P_ValueSearch", OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter("P_TotalReg", OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MODULO_FORM_TOT, paramsInput,
					paramsOutput);
			
			inputs = new HashMap<String, Object>();
			inputs.put("P_TypeSearch", tipoBusqueda);
			inputs.put("P_ValueSearch", valorBusqueda);
			
			results = this.execSp.executeSp(inputs);
			total = (int) ExecuteProcedure.retornaValue(results);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return total;
	}
	
	@Override
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormPaginar(int pageSize, int pageIndex, 
			String tipoBusqueda, String valorBusqueda, String sortColumn) {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloFormBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter("P_PageSize", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("P_PageIndex", OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter("P_TypeSearch", OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter("P_ValueSearch", OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter("P_SortColumn", OracleTypes.VARCHAR));
			
			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloFormBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MODULO_FORM_PAG, paramsInput,
					paramsOutput);
			
			inputs = new HashMap<String, Object>();
			
			inputs.put("P_PageSize", pageSize);
			inputs.put("P_PageIndex", pageIndex);
			inputs.put("P_TypeSearch", tipoBusqueda);
			inputs.put("P_ValueSearch", valorBusqueda);
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
	public List<SistemaModuloFormBean> obtenerListadoSistemaModuloFormInicioPorCodigo(Serializable parametro,
			Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloFormBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VABREVIATURA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloFormBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MODULO_INICIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VABREVIATURA, parametro);
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
	public List<SistemaModuloOpcionBean> obtenerListadoSistemaModuloFormInicioPorCodigov2(Serializable parametro,
			Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloOpcionBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VABREVIATURA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput
					.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloOpcionBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MOD_INICIO_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VABREVIATURA, parametro);
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
	public SistemaModuloFormBean obtenerSistemaModuloFormPorCodigo(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloFormBean> lista = null;
		SistemaModuloFormBean sistemaModuloFormBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloFormBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_SIS_MODULO_FORM_COD, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);

			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				sistemaModuloFormBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return sistemaModuloFormBean;
	}

	@Override
	public int grabarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> results = null;
		Map<String, Object> inputs = null;
		int identity = 0;

		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO_PADRE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NNIVEL_FORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NORDEN_FORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VURL_FORMULARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_IDENTITY, OracleTypes.INTEGER));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_SIS_MODULO_FORM, paramsInput,
					paramsOutput);

			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODSISTEMA, sistemaModuloFormBean.getCodSistema());
			inputs.put(Constants.P_NCODMODULO, sistemaModuloFormBean.getCodModulo());
			inputs.put(Constants.P_NCODFORMULARIO, sistemaModuloFormBean.getCodFormulario());
			inputs.put(Constants.P_VDESCRIPCION, sistemaModuloFormBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, sistemaModuloFormBean.getEstado());
			inputs.put(Constants.P_NCODFORMULARIO_PADRE, sistemaModuloFormBean.getCodFormularioPadre());
			inputs.put(Constants.P_NNIVEL_FORMULARIO, sistemaModuloFormBean.getNivelFormulario());
			inputs.put(Constants.P_NORDEN_FORMULARIO, sistemaModuloFormBean.getOrdenFormulario());
			inputs.put(Constants.P_VURL_FORMULARIO, sistemaModuloFormBean.getUrlFormulario());
			inputs.put(Constants.P_VUSUARIO, sistemaModuloFormBean.getUsuarioCreacion());

			results = this.execSp.executeSp(inputs);
			identity = (int) ExecuteProcedure.retornaValue(results);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return identity;
	}

	@Override
	public void actualizarSistemaModuloForm(SistemaModuloFormBean sistemaModuloFormBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO_PADRE, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NNIVEL_FORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NORDEN_FORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VURL_FORMULARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_SIS_MODULO_FORM, paramsInput,
					paramsOutput);

			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODSISTEMA, sistemaModuloFormBean.getCodSistema());
			inputs.put(Constants.P_NCODMODULO, sistemaModuloFormBean.getCodModulo());
			inputs.put(Constants.P_NCODFORMULARIO, sistemaModuloFormBean.getCodFormulario());
			inputs.put(Constants.P_VDESCRIPCION, sistemaModuloFormBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, sistemaModuloFormBean.getEstado());
			inputs.put(Constants.P_NCODFORMULARIO_PADRE, sistemaModuloFormBean.getCodFormularioPadre());
			inputs.put(Constants.P_NNIVEL_FORMULARIO, sistemaModuloFormBean.getNivelFormulario());
			inputs.put(Constants.P_NORDEN_FORMULARIO, sistemaModuloFormBean.getOrdenFormulario());
			inputs.put(Constants.P_VURL_FORMULARIO, sistemaModuloFormBean.getUrlFormulario());
			inputs.put(Constants.P_VUSUARIO, sistemaModuloFormBean.getUsuarioModificacion());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarEstadoSistemaModuloForm(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario, Serializable estado, Serializable usuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_SIS_MODULO_FORM_EST, paramsInput,
					paramsOutput);

			inputs = new HashMap<String, Object>();

			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);
			inputs.put(Constants.P_NESTADO, estado);
			inputs.put(Constants.P_VUSUARIO, usuario);

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	
	@Override
	public List<SistemaModuloFormBean> obtenerSistemaModuloFormPorCodigoHijos(Serializable codSistema, Serializable codModulo,
			Serializable codFormulario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<SistemaModuloFormBean> lista = null;	
		List<Object> resultado;
		try {

			/*paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODFORMULARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new SistemaModuloFormBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_SIS_MODULO_HIJOS, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_NCODSISTEMA, codSistema);
			inputs.put(Constants.P_NCODMODULO, codModulo);
			inputs.put(Constants.P_NCODFORMULARIO, codFormulario);
			*/
			
		

			String sql = " SELECT F.NCODSISTEMA,F.NCODMODULO,F.NCODFORMULARIO,'',F.NESTADO,F.NCODFORMULARIO_PADRE,F.NNIVEL_FORMULARIO,F.NORDEN_FORMULARIO,F.VURL_FORMULARIO,"+
            " '','','',F.VRESCRE,F.DFECCRE,F.VRESACT,F.DFECACT FROM SISTEMA_MODULO_FORM F"+
            " WHERE F.NCODSISTEMA = " + codSistema + 
             " AND F.NCODMODULO = " + codModulo + 
           " AND F.NCODFORMULARIO_PADRE in (" + codFormulario + ")" ;
			lista = new ArrayList<SistemaModuloFormBean>();
			resultado = jdbcTemplate.query(sql, new SistemaModuloFormBean());
			for (int i = 0; i < resultado.size(); i++) {
				lista.add((SistemaModuloFormBean)resultado.get(i));
			}
			
			//results = this.execSp.executeSp(inputs);
			//lista = ExecuteProcedure.retornaLista(results);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lista;
	}
}
