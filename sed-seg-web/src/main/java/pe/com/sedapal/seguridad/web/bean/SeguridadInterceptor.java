package pe.com.sedapal.seguridad.web.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pe.com.sedapal.seguridad.core.bean.SistemaModuloOpcionBean;

public class SeguridadInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Pre-handle" + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Post-handle");
		boolean contiene = Boolean.FALSE;
		if (request.getSession().getAttribute("menu") != null) {
			List<String> lstOpciones = (List<String>) request.getSession().getAttribute("menu");
			for (String menu : lstOpciones) {
				System.out.println(menu);
				if (request.getRequestURI().contains(menu)) {
					contiene = Boolean.TRUE;
				}
			}
			if (!contiene) {
				response.sendRedirect("/accesourl");
			}
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Post-handle");
	}

}
