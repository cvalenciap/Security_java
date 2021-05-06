package pe.com.sedapal.seguridad.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionCounterListener implements HttpSessionListener {

	private final Logger logger = LoggerFactory.getLogger(SessionCounterListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("SESSION CREADA");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("SESSION ELIMINADA");
	}

}
