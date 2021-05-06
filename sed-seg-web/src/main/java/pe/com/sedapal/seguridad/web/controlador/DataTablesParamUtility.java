package pe.com.sedapal.seguridad.web.controlador;

import javax.servlet.http.HttpServletRequest;

import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean;
import pe.com.sedapal.seguridad.core.bean.JQueryDataTableParamBean.DTParameters;

public class DataTablesParamUtility {

	public static DTParameters getParam(HttpServletRequest request) {
		DTParameters param = null;
		;
		if (request.getParameter("draw") != null && request.getParameter("draw") != "") {
			JQueryDataTableParamBean tableParam = new JQueryDataTableParamBean();
			param = tableParam.new DTParameters();

			param.setDraw(Integer.parseInt(request.getParameter("draw")));
			param.setSearch(request.getParameter("search[value]"));			
			param.setStart(Integer.parseInt(request.getParameter("start")));
			param.setLength(Integer.parseInt(request.getParameter("length")));

		}
		return param;
	}
}
