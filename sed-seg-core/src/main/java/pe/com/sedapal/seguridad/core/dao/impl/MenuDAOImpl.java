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
import pe.com.sedapal.seguridad.core.bean.MenuBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.MenuDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class MenuDAOImpl implements MenuDAO {

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
	public List<MenuBean> obtenerListadoSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception {
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		Map<String, Object> results = null;
		List<MenuBean> lista = null;

		try {

			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_NCODSISTEMA, OracleTypes.INTEGER));
			paramsInput.add(new SqlParameter(Constants.P_NCODMODULO, OracleTypes.INTEGER));

			paramsOutput = new ArrayList<SqlOutParameter>();
			paramsOutput.add(new SqlOutParameter(Constants.P_CURSOR, OracleTypes.CURSOR, new MenuBean()));

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SP_OBTENER_SIS_MODULO_FORM_MEN, paramsInput,
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
	public StringBuilder obtenerSistemaModuloFormularioMenu(Serializable codSistema, Serializable codModulo)
			throws Exception {
		StringBuilder sb = new StringBuilder();

		try {
			List<MenuBean> lista = this.obtenerListadoSistemaModuloFormularioMenu(codSistema, codModulo);
			this.MenuFormularioModulo(sb, lista, 0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return sb;
	}
	
//	cambio para consumo mybatis
	@Override
	public StringBuilder MenuFormularioModulo(StringBuilder sb, List<MenuBean> listMenu, int id) {
//	private void MenuFormularioModulo(StringBuilder sb, List<MenuBean> listMenu, int id) {
		for (MenuBean menu : listMenu) {
			if (menu.getCodFormularioPadre() == 0) {
				sb.append("<li class=\"dropdown\"><a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">");
				sb.append(menu.getDescripcion());
				sb.append("<b class=\"caret\"></b></a>");
				ItemMenu(sb, listMenu, menu.getCodFormulario());
				sb.append("</li>");
			}
		}
		return sb;
	}

	private void ItemMenu(StringBuilder sb, List<MenuBean> listMenu, int idNode) {
		StringBuilder sb1 = new StringBuilder();

		for (MenuBean bean : listMenu) {
			if (bean.getCodFormularioPadre() == idNode) {
				sb1.append("<li><a href=\"#\">");
				sb1.append(bean.getDescripcion());
				sb1.append("</a></li>");
				ItemSubMenu(sb1, listMenu, bean.getCodFormulario());
			}
		}
		if (sb1.length() > 0) {
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append(sb1.toString());
			sb.append("</ul>");
		}
	}

	private void ItemSubMenu(StringBuilder sb, List<MenuBean> listMenu, int idNodeChild) {
		StringBuilder sb2 = new StringBuilder();

		for (MenuBean bean : listMenu) {
			if (bean.getCodFormularioPadre() == idNodeChild) {
				sb2.append("<li><a href=\"#\">");
				sb2.append(bean.getDescripcion());
				sb2.append("</a></li>");
			}
		}
		if (sb2.length() > 0) {
			sb.append("<ul class=\"dropdown-menu\">");
			sb.append(sb2.toString());
			sb.append("</ul>");
		}

	}

}
