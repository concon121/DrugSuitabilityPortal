package uk.co.cbray.msc.nhsdsp.controller;

import java.math.BigDecimal;
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
import uk.co.cbray.msc.nhsdsp.dao.DiagnosisRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.forms.ChosenPatientForm;
import uk.co.cbray.msc.nhsdsp.forms.DiagnosisForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.SearchHelper;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/diagnosis")
public class DiagnosisController {

	@Autowired
	private IllnessRepository illnessRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DiagnosisRepository diagnosisRepo;
	@Autowired
	private DataAccessObject dao;

	private static final Logger LOG = LoggerFactory
			.getLogger(DiagnosisController.class);

	@ModelAttribute("searchForm")
	public SearchForm getSearchForm() {
		LOG.debug("Instantiated new SearchForm");
		return new SearchForm();
	}

	@ModelAttribute("diagnosisForm")
	public DiagnosisForm getDiagnosisForm() {
		LOG.debug("Instantiated new DiagnosisForm");
		return new DiagnosisForm();
	}

	@ModelAttribute("viewUser")
	public ViewUserForm getViewUserForm() {
		LOG.debug("Instantiated new ViewUserForm");
		return new ViewUserForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newDiagnosis(Model model) {
		return "diagnosis";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchUsers(SearchForm form, Model model) {

		try {

			SearchHelper.searchPatientsOnly(getDao(), form, model);

		} catch (InstantiationException e) {
			LOG.error("Exception occurred while searching.", e);
			return "unknownError";
		} catch (IllegalAccessException e) {
			LOG.error("Exception occurred while searching.", e);
			return "unknownError";
		} catch (Exception e) {
			LOG.error("Unknown exception occurred while searching.", e);
			return "unknownError";
		}

		return "diagnosis";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String chooseUser(ViewUserForm form, Model model) {

		try {
			User user = getUserRepo().findById(form.getUserId());
			ChosenPatientForm chosenPatient = Converter.convert(user,
					ChosenPatientForm.class);
			model.addAttribute("chosenUser", chosenPatient);
			List<Illness> illnesses = getIllnessRepo().findAll();
			List<String> illnessNames = Converter.convertIllnessToNames(illnesses);
			model.addAttribute("illnesses", illnessNames);

		} catch (InvalidEntityConversionTypeException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return "unknownError";
		} catch (InstantiationException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return "unknownError";
		} catch (IllegalAccessException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return "unknownError";
		} catch (Exception e) {
			LOG.error("Unknown error occurred.", e);
			return "unknownError";
		}

		return "diagnosis";
	}

	@RequestMapping(value = "/user/illness", method = RequestMethod.POST)
	public String persistDiagnosis(DiagnosisForm form, Model model) {

		Diagnosis diagnosis = Converter.convert(form, getIllnessRepo());
		getDiagnosisRepo().create(diagnosis);

		model.addAttribute("success", "Successfully diagnosed patient!");

		return "home";
	}

	@RequestMapping(value = "/view")
	public String viewDiagnosis(Model model, HttpSession session) {

		BigDecimal userId = new BigDecimal(
				(Integer) session.getAttribute("userId"));

		List<Diagnosis> diagnosis = getDiagnosisRepo().findByUserId(userId);
		List<DiagnosisForm> formItems = Converter.convert(diagnosis,
				getIllnessRepo());
		
		List<String> errorMessages = Validator.validate(diagnosis, formItems);
		if (errorMessages.isEmpty()) {
			model.addAttribute("items", formItems);
		} else {
			model.addAttribute("error", errorMessages);
		}

		return "viewPastDiagnosis";
	}

	public IllnessRepository getIllnessRepo() {
		return illnessRepo;
	}

	public void setIllnessRepo(IllnessRepository illnessRepo) {
		this.illnessRepo = illnessRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public DiagnosisRepository getDiagnosisRepo() {
		return diagnosisRepo;
	}

	public void setDiagnosisRepo(DiagnosisRepository diagnosisRepo) {
		this.diagnosisRepo = diagnosisRepo;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
