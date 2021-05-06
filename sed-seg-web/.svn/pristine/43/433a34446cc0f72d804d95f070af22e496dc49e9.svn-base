package pe.com.sedapal.seguridad.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import pe.com.sedapal.seguridad.core.service.SeguridadService;

@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	private final Logger logger = LoggerFactory.getLogger(LogoutListener.class);

	@Autowired
	private SeguridadService seguridadService;

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		logger.info("cerrando session " + event.getId());
		try {
			seguridadService.actualizarAcceso(event.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("cerrando session " + event.getId());
	}

}
