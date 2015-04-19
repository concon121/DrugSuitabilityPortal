package uk.co.cbray.msc.nhsdsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.forms.IllnessForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewIllnessForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;

@Controller
@RequestMapping("/illness")
public class IllnessController {

	@Autowired
	private DataAccessObject dao;
	
	@RequestMapping(value="/view", method=RequestMethod.POST)
	public String viewIllness(ViewIllnessForm form, Model model) {
		
		Illness illness = getDao().find(form.getIllnessId(), Illness.class);
		IllnessForm illnessForm = Converter.convert(illness);
		
		model.addAttribute("illness", illnessForm);
		
		return "viewIllness";
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
	
	
	
}
