package uk.co.cbray.msc.nhsdsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.forms.NewEffectForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/effect")
public class EffectController {

	@Autowired
	private EffectRepository effectRepo;

	@ModelAttribute("form")
	public NewEffectForm getEffectForm() {
		return new NewEffectForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newEffect() {
		return PageEnum.NEW_EFFECT.getName();
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String persistEffect(NewEffectForm form, Model model) {

		List<String> errorMessages = Validator.validate(form);
		if (!errorMessages.isEmpty()) {
			model.addAttribute("error", errorMessages);
			return PageEnum.NEW_EFFECT.getName();
		} else {
			Effect effect = Converter.convert(form);
			getEffectRepo().create(effect);
			model.addAttribute("success",
					"Successfully registered a new side effect!");
			return PageEnum.HOME.getName();
		}
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String viewEffect(ViewEffect form, Model model) {

		Effect effect = getEffectRepo().findById(form.getEffectId());
		
		List<String> errorMessages = Validator.validate(form, effect);
		
		if (!errorMessages.isEmpty()) {
			model.addAttribute("error", errorMessages);
		} else {
			model.addAttribute("effect", effect);
		}
		return PageEnum.VIEW_EFFECT.getName();
	}

	public EffectRepository getEffectRepo() {
		return effectRepo;
	}

	public void setEffectRepo(EffectRepository effectRepo) {
		this.effectRepo = effectRepo;
	}

}
