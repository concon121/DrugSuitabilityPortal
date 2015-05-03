package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;

@Controller
public class ProfileController {

	@Autowired
	private DataAccessObject dao;
	
	
	@RequestMapping(value="/profile")
	public String profile() {
		return PageEnum.PROFILE.getName();
	}
	
	@RequestMapping(value="/profile/accountSettings")
	public String accountSettings() {
		return PageEnum.ACCOUNT_SETTINGS.getName();
	}
	
	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
	
	
	
}
