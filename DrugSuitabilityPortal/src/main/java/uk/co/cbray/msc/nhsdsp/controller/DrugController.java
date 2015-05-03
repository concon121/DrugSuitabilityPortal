package uk.co.cbray.msc.nhsdsp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.forms.DrugForm;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.SearchHelper;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/drug")
public class DrugController {

	@Autowired
	private DrugRepository drugRepo;
	@Autowired
	private EffectRepository effectRepo;
	@Autowired
	private DataAccessObject dao;

	private static final Logger LOG = LoggerFactory
			.getLogger(DrugController.class);

	@ModelAttribute(value = "form")
	public NewDrugForm getNewDrugForm() {
		return new NewDrugForm();
	}

	@RequestMapping(value = "/new")
	public String newDrug() {
		return PageEnum.NEW_DRUG.getName();
	}

	@ModelAttribute("searchForm")
	public SearchForm getSearchForm() {
		LOG.debug("Instantiated new SearchForm");
		return new SearchForm();
	}

	@ModelAttribute("viewEffect")
	public ViewEffect getViewEffectForm() {
		return new ViewEffect();
	}

	@RequestMapping(value = "/new/persist")
	public String persistNewDrug(NewDrugForm form, Model model,
			HttpSession session) {

		List<String> errorMessages = Validator.validate(form, getEffectRepo());
		if (errorMessages.size() > 0) {
			model.addAttribute("error", errorMessages);
			model.addAttribute("formContents", form);
			return PageEnum.NEW_DRUG.getName();
		} else {

			getDrugRepo().createNewDrug(form);
			model.addAttribute("success", "Successfully added a new drug.");

			return PageEnum.HOME.getName();
		}
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String viewDrug(ViewDrugForm form, Model model, HttpSession session) {
		Drug drug = getDrugRepo().findById(form.getDrugId());
		DrugForm drugForm = Converter.convert(drug);

		model.addAttribute("drug", drugForm);

		return PageEnum.VIEW_DRUG.getName();
	}

	@RequestMapping(value = "/effect/search", method = RequestMethod.POST)
	public String searchEffects(SearchForm form, Model model) {

		List<String> errorMessages = Validator.validate(form);

		if (errorMessages.isEmpty()) {

			try {

				SearchHelper.search(getDao(), form, model, Effect.class);

			} catch (InstantiationException e) {
				LOG.error("Exception occurred while searching.", e);
				return PageEnum.UNKNOWN_ERROR.getName();
			} catch (IllegalAccessException e) {
				LOG.error("Exception occurred while searching.", e);
				return PageEnum.UNKNOWN_ERROR.getName();
			} catch (Exception e) {
				LOG.error("Unknown exception occurred while searching.", e);
				return PageEnum.UNKNOWN_ERROR.getName();
			}
		} else {
			model.addAttribute("error", errorMessages);
		}

		return PageEnum.NEW_DRUG.getName();
	}

	public DrugRepository getDrugRepo() {
		return drugRepo;
	}

	public void setDrugRepo(DrugRepository drugRepo) {
		this.drugRepo = drugRepo;
	}

	public EffectRepository getEffectRepo() {
		return effectRepo;
	}

	public void setEffectRepo(EffectRepository effectRepo) {
		this.effectRepo = effectRepo;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
