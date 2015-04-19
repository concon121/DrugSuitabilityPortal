package uk.co.cbray.msc.nhsdsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	public static final Logger LOG = LoggerFactory
			.getLogger(ErrorController.class);

	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "accessDenied";
	}

	@ExceptionHandler(value = Exception.class)
	public String defaultErrorhandler(Exception ex) {
		LOG.error("Unknown Exception occurred.", ex);
		return "unknownError";
	}

	@RequestMapping(value = "/404")
	public String handleResourceNotFoundException() {
		return "pageNotFound";
	}

}
