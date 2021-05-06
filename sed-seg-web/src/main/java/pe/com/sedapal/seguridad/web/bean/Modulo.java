package pe.com.sedapal.seguridad.web.bean;

import java.io.Serializable;

public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8539597871507592001L;
	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Modulo(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public Modulo() {
	}

	@Override
	public String toString() {
		return "Modulos [value=" + value + ", text=" + text + "]";
	}

}
