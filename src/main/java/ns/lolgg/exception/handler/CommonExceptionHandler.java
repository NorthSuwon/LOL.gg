package ns.lolgg.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	
//	@ExceptionHandler(Exception.class)
//	public String handleError(Exception e, Model model) {
//		model.addAttribute("error-message", e.getMessage());
//		System.out.println(e.getMessage());
//		return "error/common";
//	}
}
