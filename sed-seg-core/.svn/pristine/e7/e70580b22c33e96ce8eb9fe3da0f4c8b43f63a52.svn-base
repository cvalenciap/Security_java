package pe.com.sedapal.seguridad.core.dao.impl;

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
import pe.com.sedapal.seguridad.core.bean.CorreoBean;
import pe.com.sedapal.seguridad.core.commons.Constants;
import pe.com.sedapal.seguridad.core.dao.CorreoDAO;
import pe.com.sedapal.seguridad.core.jdbc.ExecuteProcedure;

@Repository
public class CorreoDAOImpl implements CorreoDAO {

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
	public void enviarCorreo(CorreoBean correoBean) throws Exception {
		// TODO Auto-generated method stub
		List<SqlParameter> paramsInput = null;
		List<SqlOutParameter> paramsOutput = null;
		Map<String, Object> inputs = null;
		try {
			paramsInput = new ArrayList<SqlParameter>();
			paramsInput.add(new SqlParameter(Constants.P_VFROM, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VSUBJECT, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VMESSAGE, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VCCTO, OracleTypes.VARCHAR));
			paramsInput.add(new SqlParameter(Constants.P_VBCCTO, OracleTypes.VARCHAR));
			paramsOutput = new ArrayList<SqlOutParameter>();

			this.execSp = new ExecuteProcedure(this.dataSource, Constants.SEND_MAIL, paramsInput, paramsOutput);
			inputs = new HashMap<String, Object>();
			inputs.put(Constants.P_VFROM, correoBean.getvFrom());
			inputs.put(Constants.P_VTO, correoBean.getvTo());
			inputs.put(Constants.P_VSUBJECT, correoBean.getvSubject());
			inputs.put(Constants.P_VMESSAGE, correoBean.getvMessage());
			inputs.put(Constants.P_VCCTO, correoBean.getvCcto());
			inputs.put(Constants.P_VBCCTO, correoBean.getvBccto());

			this.execSp.executeSp(inputs);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
