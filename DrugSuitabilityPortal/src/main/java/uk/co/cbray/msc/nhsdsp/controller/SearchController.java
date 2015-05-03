package uk.co.cbray.msc.nhsdsp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Searchable;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.forms.ViewIllnessForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.utils.ErrorMessageEnum;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.SearchHelper;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private DataAccessObject dao;

	private static final Logger LOG = LoggerFactory
			.getLogger(SearchController.class);

	@ModelAttribute("searchForm")
	public SearchForm getSearchForm() {
		return new SearchForm();
	}

	@ModelAttribute("viewUser")
	public ViewUserForm getViewUserForm() {
		LOG.debug("Instantiated new ViewUserForm");
		return new ViewUserForm();
	}

	@ModelAttribute("viewEffect")
	public ViewEffect getViewEffectForm() {
		return new ViewEffect();
	}

	@ModelAttribute("viewDrug")
	public ViewDrugForm getViewDrugForm() {
		LOG.debug("Instantiated new ViewDrugForm");
		return new ViewDrugForm();
	}

	@ModelAttribute("viewIllness")
	public ViewIllnessForm getViewIllnessForm() {
		LOG.debug("Instantiated new ViewIllnessForm");
		return new ViewIllnessForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewSearch() {
		return PageEnum.SEARCH.getName();
	}

	@RequestMapping(value = "/results", method = RequestMethod.POST)
	public <T extends IEntity & Searchable> String search(SearchForm form,
			Model model) throws InstantiationException, IllegalAccessException {

		List<String> errorMessages = Validator.validate(form);

		if (errorMessages.isEmpty()) {

			try {

				if (form.getEntity().equalsIgnoreCase("User")) {

					SearchHelper.search(getDao(), form, model, User.class);

				} else if (form.getEntity().equalsIgnoreCase("Drug")) {

					SearchHelper.search(getDao(), form, model, Drug.class);

				} else if (form.getEntity().equalsIgnoreCase("Illness")) {

					SearchHelper.search(getDao(), form, model, Illness.class);

				} else if (form.getEntity().equalsIgnoreCase("Effect")) {

					SearchHelper.search(getDao(), form, model, Effect.class);

				} else {
					
					model.addAttribute("error", ErrorMessageEnum.NO_RESULTS.getMessage());
					
				}

				return PageEnum.SEARCH_RESULTS.getName();

			} catch (InstantiationException e) {
				LOG.error("Exception occurred while searching.", e);
				return PageEnum.UNKNOWN_ERROR.getName();
			} catch (IllegalAccessException e) {
				LOG.error("Exception occurred while searching.", e);
				return PageEnum.UNKNOWN_ERROR.getName();
			}
		} else {
			model.addAttribute("error", errorMessages);
			return PageEnum.SEARCH_RESULTS.getName();
		}

	}

	@RequestMapping("/index")
	public String indexData(Model model) {

		try {
			getDao().indexData();
		} catch (InterruptedException ie) {
			LOG.error("Failed to index data", ie);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (Exception e) {
			LOG.error("Failed to index data", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		}
		model.addAttribute("success", "Successfully indexed the database.");
		return PageEnum.INDEX_DATA.getName();
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
