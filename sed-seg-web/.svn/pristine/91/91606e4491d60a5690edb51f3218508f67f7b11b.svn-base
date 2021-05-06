package pe.com.sedapal.seguridad.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleCustomException(RuntimeException ex) {

		ModelAndView model = new ModelAndView("/errorexception");
		model.addObject("causa", ex.getCause());
		model.addObject("mensaje", ex.getMessage());

		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("/errorexception");
		model.addObject("causa", ex.getCause());
		model.addObject("mensaje", ex.getMessage());

		return model;

	}

}
