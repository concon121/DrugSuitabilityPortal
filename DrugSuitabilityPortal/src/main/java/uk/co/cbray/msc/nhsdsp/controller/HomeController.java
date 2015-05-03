package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.utils.PageEnum;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String root() {
		return PageEnum.HOME.getName();
	}
	
	@RequestMapping(value="/home")
	public String home() {
		return PageEnum.HOME.getName();
	}
	
}