package web.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String ERROR_PAGE = "error";
	private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		return new ModelAndView(ERROR_PAGE, ERROR_MESSAGE_ATTRIBUTE, e.getMessage());
	}

}
