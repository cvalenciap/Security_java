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
import pe.com.sedapal.seguridad.core.bean.UsuarioBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.UsuarioDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

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
	public UsuarioBean obtenerUsuarioPorCodUsuario(Serializable codUsuario) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioBean> lista = null;
		UsuarioBean usuarioBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_USUARIO_POR_USUARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, codUsuario);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				usuarioBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return usuarioBean;

	}

	@Override
	public List<UsuarioBean> obtenerListadoUsuario() throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioBean> lista = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_USUARIO, paramsInput,
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
	public void actualizarUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable usuario,Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VRESPONSABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA_, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_USUARIO_ESTADO, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);
			inputs.put(Constants.P_NESTADO, estado);
			inputs.put(Constants.P_VRESPONSABLE, usuario);
			inputs.put(Constants.P_NCODSISTEMA_, codSistema);
			execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	@Override
	public void bloqueoUsuarioEstadoPorCodUsuario(Serializable codUsuario, Serializable estado, Serializable nrointentos, Serializable usuario,Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_NUMERO_INTENTOS, OracleTypes.NUMERIC));
			paramsInput.add(new SqlParameter(Constants.P_VRESPONSABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA_, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_USUARIO_BLOQUEADO, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);
			inputs.put(Constants.P_NESTADO, estado);
			inputs.put(Constants.P_NUMERO_INTENTOS, nrointentos);
			inputs.put(Constants.P_VRESPONSABLE, usuario);
			inputs.put(Constants.P_NCODSISTEMA_, codSistema);
			execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void actualizarUsuarioClavesErroneas(Serializable codUsuario,int nroIntentos) throws Exception {
		// TODO Auto-generated method stub

		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NUMERO_INTENTOS, OracleTypes.VARCHAR));
			

			paramsOutput = new ArrayList<SqlOutParameter>();

			execSp = new ExecuteProcedure(dataSource, Constants.SP_UPDATE_USUARIO_INTENTOS, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VCODUSUARIO, codUsuario);
			inputs.put(Constants.P_NUMERO_INTENTOS, nroIntentos);
			

			execSp.executeSp(inputs);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void grabarUsuario(UsuarioBean usuarioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODAREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VRESPONSABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VZONA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODFICHA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NINDICADOR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VPASS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCONEXION, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VSUSTENTACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VDOC, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_CAMBIAR_CLAVE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_NUEVO_USUARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(Constants.P_VCODUSUARIO, usuarioBean.getCodUsuario());
			inputs.put(Constants.P_VDESCRIPCION, usuarioBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, usuarioBean.getEstado());
			inputs.put(Constants.P_NCODAREA, usuarioBean.getCodArea());
			inputs.put(Constants.P_VRESPONSABLE, usuarioBean.getResponsable());
			inputs.put(Constants.P_VZONA, usuarioBean.getZona());
			inputs.put(Constants.P_NCODFICHA, usuarioBean.getCodFicha());
			inputs.put(Constants.P_NINDICADOR, usuarioBean.getIndicador());
			inputs.put(Constants.P_VPASS, usuarioBean.getPass());
			inputs.put(Constants.P_NCONEXION, usuarioBean.getConexion());
			inputs.put(Constants.P_VSUSTENTACION, usuarioBean.getSustentacion());			
			inputs.put(Constants.P_VDOC, usuarioBean.getDoc());
			inputs.put(Constants.P_VFLAG_CAMBIAR_CLAVE, usuarioBean.getFlagCambiarClave());
			inputs.put(Constants.P_NCODSISTEMA, usuarioBean.getCodSistema());
			inputs.put(Constants.P_NCODPERFIL, usuarioBean.getCodPerfil());
			
			this.execSp.executeSp(inputs);		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void actualizarUsuario(UsuarioBean usuarioBean) throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			
			paramsInput.add(new SqlParameter(Constants.P_VCODUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VDESCRIPCION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NESTADO, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODAREA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VRESPONSABLE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VZONA, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODFICHA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NINDICADOR, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VPASS, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCONEXION, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_VSUSTENTACION, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VDOC, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VFLAG_CAMBIAR_CLAVE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODPERFIL, OracleTypes.INTEGER));
			
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_UPDATE_USUARIO, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();			
			inputs.put(Constants.P_VCODUSUARIO, usuarioBean.getCodUsuario());
			inputs.put(Constants.P_VDESCRIPCION, usuarioBean.getDescripcion());
			inputs.put(Constants.P_NESTADO, usuarioBean.getEstado());
			inputs.put(Constants.P_NCODAREA, usuarioBean.getCodArea());
			inputs.put(Constants.P_VRESPONSABLE, usuarioBean.getResponsable());
			inputs.put(Constants.P_VZONA, usuarioBean.getZona());
			inputs.put(Constants.P_NCODFICHA, usuarioBean.getCodFicha());
			inputs.put(Constants.P_NINDICADOR, usuarioBean.getIndicador());
			inputs.put(Constants.P_VPASS, usuarioBean.getPass());
			inputs.put(Constants.P_NCONEXION, usuarioBean.getConexion());
			inputs.put(Constants.P_VSUSTENTACION, usuarioBean.getSustentacion());
			inputs.put(Constants.P_VDOC, usuarioBean.getDoc());
			inputs.put(Constants.P_VFLAG_CAMBIAR_CLAVE, usuarioBean.getFlagCambiarClave());
			inputs.put(Constants.P_VFLAG_CAMBIAR_CLAVE, usuarioBean.getFlagCambiarClave());
			inputs.put(Constants.P_VFLAG_CAMBIAR_CLAVE, usuarioBean.getFlagCambiarClave());
			inputs.put(Constants.P_NCODSISTEMA, usuarioBean.getCodSistema());
			inputs.put(Constants.P_NCODPERFIL, usuarioBean.getCodPerfil());
			
			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public UsuarioBean obtenerUsuarioPorCodUsuarioCodSistema(Serializable codUsuario, Serializable codSistema)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<UsuarioBean> lista = null;
		UsuarioBean usuarioBean = null;
		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VUSUARIO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA_, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new UsuarioBean()));

			this.execSp = new ExecuteProcedure(dataSource, Constants.SP_OBTENER_USUARIO_SISTEMA_ID, paramsInput,
					paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VUSUARIO, codUsuario);
			inputs.put(Constants.P_NCODSISTEMA_, codSistema);
			results = this.execSp.executeSp(inputs);
			lista = ExecuteProcedure.retornaLista(results);
			if (lista != null && !lista.isEmpty())
				usuarioBean = lista.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return usuarioBean;

	}

}


