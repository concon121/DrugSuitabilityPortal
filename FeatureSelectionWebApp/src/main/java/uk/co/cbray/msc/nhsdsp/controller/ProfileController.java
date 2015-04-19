package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;

@Controller
public class ProfileController {

	@Autowired
	private DataAccessObject dao;
	
	
	@RequestMapping(value="/profile")
	public String profile() {
		return "profile";
	}
	
	@RequestMapping(value="/profile/accountSettings")
	public String accountSettings() {
		return "accountSettings";
	}
	
	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
	
	
	
}
