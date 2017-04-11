package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handle(final RuntimeException exception) {
		ModelAndView modelAndView = new ModelAndView("exception");
		modelAndView.addObject("name", exception.getClass().getSimpleName());
		modelAndView.addObject("message", exception.getMessage());

		return modelAndView;
	}
	

}
