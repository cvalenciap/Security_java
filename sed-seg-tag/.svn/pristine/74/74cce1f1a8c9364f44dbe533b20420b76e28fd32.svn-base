package pe.com.sedapal.seguridad.html;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pe.com.sedapal.seguridad.util.SecurityUtils;

public class TagSeguridad extends BodyTagSupport {

	private static final long serialVersionUID = -3214972793750152081L;
	private String accion;

	public int doEndTag() throws JspException {
		String bodyText = bodyContent.getString();
		JspWriter out = null;
		List<String> listado = new ArrayList<>();
		try {
			User user = SecurityUtils.getUserInformation(pageContext);

			Collection<GrantedAuthority> permissionsArray = user.getAuthorities();
			for (GrantedAuthority grantedAuthority : permissionsArray) {
				listado.add(grantedAuthority.getAuthority());
			}
			
			if (permissionsArray != null && !permissionsArray.isEmpty() && !"".equals(this.accion)) {				
				if (listado.contains(this.accion)) {
					out = bodyContent.getEnclosingWriter();
					out.print(bodyText);
					return EVAL_BODY_INCLUDE;
				}

			}
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return EVAL_PAGE;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

}
