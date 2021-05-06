package pe.com.sedapal.seguridad.web.bean;

import java.io.Serializable;
import java.util.List;

import pe.com.sedapal.seguridad.core.bean.UsuarioSistemaBean;

public class TablaDatosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5383656062466615263L;
	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;

	private String[][] data;

	private List<UsuarioSistemaBean> usuarioSistemaBeans;

	public TablaDatosBean() {
	}

	public TablaDatosBean(Integer draw, Integer recordsTotal, Integer recordsFiltered, String[][] data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public List<UsuarioSistemaBean> getUsuarioSistemaBeans() {
		return usuarioSistemaBeans;
	}

	public void setUsuarioSistemaBeans(List<UsuarioSistemaBean> usuarioSistemaBeans) {
		this.usuarioSistemaBeans = usuarioSistemaBeans;
	}

}
