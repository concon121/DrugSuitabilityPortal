package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import uk.co.cbray.msc.nhsdsp.entity.User;

@EnableWebMvc
@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/logout")
	public String logout() {
		return "home";
	}
	
	@RequestMapping(value="loginError")
	public String loginError(Model model) {
		model.addAttribute("error", "There was a problem logging you in, please try again.");
		return "login";
	}
	
	@ModelAttribute(value="newUser")
	public User getNewUser() {
		return new User();
	}
}
