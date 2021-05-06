package pe.com.sedapal.seguridad.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;

public final class SecurityUtils {
	
	public static User getUserInformation(PageContext pageContext) throws JspException {
		SecurityContext securityContext = (SecurityContext) pageContext.getSession()
				.getAttribute("SPRING_SECURITY_CONTEXT");
		if (securityContext == null) {
			throw new JspException(" Your Session has been expired ");
		}
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			throw new JspException(" Autenticacion failure ");
		}
		User user = null;
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		}
		return user;
	}

}
