package pe.com.sedapal.seguridad.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;


public class ContratistaBean extends Auditoria implements Serializable, RowMapper<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idContratista;
	private String desRazon;
	private String desRazonCorta;
	private String direccion;
	private Integer codDocRazon;
	private String nroDocRazon;
	private String desRepresentante;
	private Integer codDocRepresentante;
	private String nroDocRepresentante;
	private String correo;
	private String telefono;
	private String flagNivel;
//	Auditoria
	private String codEstado;
	private String desEstado;
	
	private boolean readOnly;
	
	public ContratistaBean() {
			super();
			// TODO Auto-generated constructor stub
		}
	
    public ContratistaBean(Integer idContratista, String desRazon, String desRazonCorta, String direccion,
			Integer codDocRazon, String nroDocRazon, String desRepresentante, Integer codDocRepresentante,
			String nroDocRepresentante, String correo, String telefono, String codEstado, String desEstado) {
//		super();
		this.idContratista = idContratista;
		this.desRazon = desRazon;
		this.desRazonCorta = desRazonCorta;
		this.direccion = direccion;
		this.codDocRazon = codDocRazon;
		this.nroDocRazon = nroDocRazon;
		this.desRepresentante = desRepresentante;
		this.codDocRepresentante = codDocRepresentante;
		this.nroDocRepresentante = nroDocRepresentante;
		this.correo = correo;
		this.telefono = telefono;
		this.codEstado = codEstado;
		this.desEstado = desEstado;
	}	
	
	public Integer getIdContratista() {
		return idContratista;
	}
	public void setIdContratista(Integer idContratista) {
		this.idContratista = idContratista;
	}
	public String getDesRazon() {
		return desRazon;
	}
	public void setDesRazon(String desRazon) {
		this.desRazon = desRazon;
	}
	public String getDesRazonCorta() {
		return desRazonCorta;
	}
	public void setDesRazonCorta(String desRazonCorta) {
		this.desRazonCorta = desRazonCorta;
	}
	public Integer getCodDocRazon() {
		return codDocRazon;
	}
	public void setCodDocRazon(Integer codDocRazon) {
		this.codDocRazon = codDocRazon;
	}
	public String getNroDocRazon() {
		return nroDocRazon;
	}
	public void setNroDocRazon(String nroDocRazon) {
		this.nroDocRazon = nroDocRazon;
	}
	public String getDesRepresentante() {
		return desRepresentante;
	}
	public void setDesRepresentante(String desRepresentante) {
		this.desRepresentante = desRepresentante;
	}
	public Integer getCodDocRepresentante() {
		return codDocRepresentante;
	}
	public void setCodDocRepresentante(Integer codDocRepresentante) {
		this.codDocRepresentante = codDocRepresentante;
	}
	public String getNroDocRepresentante() {
		return nroDocRepresentante;
	}
	public void setNroDocRepresentante(String nroDocRepresentante) {
		this.nroDocRepresentante = nroDocRepresentante;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new ContratistaBean(rs.getInt(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
				rs.getString(5), rs.getString(6), rs.getInt(7),  rs.getString(8),  rs.getString(9),  rs.getString(10),  rs.getString(11),  rs.getString(12));
	}
	
	public boolean isNew() {
		return (this.idContratista == null);
	}

	@Override
	public String toString() {
		return "ContratistaBean [idContratista=" + idContratista + ", desRazon=" + desRazon + ", desRazonCorta=" + desRazonCorta
				+ ", direccion=" + direccion + ", codDocRazon=" + codDocRazon + ", nroDocRazon=" + nroDocRazon
				+ ", desRepresentante=" + desRepresentante + ", codDocRepresentante=" + codDocRepresentante + ", nroDocRepresentante=" + nroDocRepresentante + ", correo="
				+ correo + ", telefono=" + telefono + ", codEstado=" + codEstado + ", desEstado=" + desEstado 
				+ ", getUsuarioCreacion()=" + getUsuarioCreacion() + ", getFechaCreacion()="
				+ getFechaCreacion() + ", getUsuarioModificacion()=" + getUsuarioModificacion()
				+ ", getFechaModificacion()=" + getFechaModificacion() + "]";
	}

	public String getFlagNivel() {
		return flagNivel;
	}

	public void setFlagNivel(String flagNivel) {
		this.flagNivel = flagNivel;
	}
	
	public boolean isReadOnly() {
		if(this.codEstado !=null){
			return (this.codEstado.equals("0"));	
		}
		
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

}
