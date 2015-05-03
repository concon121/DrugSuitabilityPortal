package uk.co.cbray.msc.nhsdsp.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.NewPasswordForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/access")
public class LoginCredentialsController {

	@Autowired
	private DataAccessObject dao;
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute("form")
	public NewPasswordForm getnewPasswordForm() {
		return new NewPasswordForm();
	}
	
	@RequestMapping(value="/update")
	public String updatePassword() {
		
		return PageEnum.UPDATE_PASSWORD.getName();
		
	}
	
	@RequestMapping(value="/update/persist")
	public String persistNewPassword(NewPasswordForm form, Model model, HttpSession session) {
		List<String> errorMessages = Validator.validate(form);
		if (errorMessages.size() > 0) {
			model.addAttribute("error", errorMessages);
			return PageEnum.UPDATE_PASSWORD.getName();
		} else {
			BigDecimal userId = new BigDecimal((Integer)session.getAttribute("userId"));
			User user = (User) getUserRepo().findById(userId);
			List<UserLogin> logins = getUserRepo().getUserLoginsForUser(user);
			if(logins != null || logins.size() != 0) {
				UserLogin login = logins.get(0);
				Converter.convert(form, login);
				getDao().update(login, UserLogin.class);
			}
			
			model.addAttribute("success", "Successfully updated Password.");
			return PageEnum.PROFILE.getName();
		}
		
	}	
	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	
	
}
