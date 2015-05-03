package uk.co.cbray.msc.nhsdsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.utils.PageEnum;

@Controller
public class ErrorController {

	public static final Logger LOG = LoggerFactory
			.getLogger(ErrorController.class);

	@RequestMapping(value = "/403")
	public String accessDenied() {
		return PageEnum.ACCESS_DENIED.getName();
	}

	@ExceptionHandler(value = Exception.class)
	public String defaultErrorhandler(Exception ex) {
		LOG.error("Unknown Exception occurred.", ex);
		return PageEnum.UNKNOWN_ERROR.getName();
	}

	@RequestMapping(value = "/404")
	public String handleResourceNotFoundException() {
		return PageEnum.PAGE_NOT_FOUND.getName();
	}

}
