package pe.com.sedapal.seguridad.core.cron;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import pe.com.sedapal.seguridad.core.service.SeguridadService;

public class SeguridadTimer {

	private final Logger logger = LoggerFactory.getLogger(SeguridadTimer.class);

	@Autowired
	private SeguridadService seguridadService;

	// @Scheduled(cron = CRON)
	//@Scheduled(cron = "0 1 * * * MON-FRI")	 
	public void ejecutarProcesos() {
		logger.info("Inicio ejecucion de procesos " + new Date());
		seguridadService.ejecutarProcesos();
		logger.info("Fin ejecucion de procesos " + new Date());
	}
}
