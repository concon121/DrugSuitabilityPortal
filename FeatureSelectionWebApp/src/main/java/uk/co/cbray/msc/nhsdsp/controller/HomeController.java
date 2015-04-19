package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public String root() {
		return "home";
	}
	
	@RequestMapping(value="/home")
	public String home() {
		return "home";
	}
	
}