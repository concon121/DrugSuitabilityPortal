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

import uk.co.cbray.msc.nhsdsp.dao.AssessmentRepository;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DiagnosisRepository;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.IncidentRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.forms.AssessmentForm;
import uk.co.cbray.msc.nhsdsp.forms.ChosenPatientForm;
import uk.co.cbray.msc.nhsdsp.forms.NewAssessmentForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.utils.AssessmentHelper;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.SearchHelper;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/assessment")
public class AssessmentController {

	@Autowired
	private AssessmentRepository assessmentRepo;
	@Autowired
	private DataAccessObject dao;
	@Autowired
	private DiagnosisRepository diagnosisRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private IllnessRepository illnessRepo;
	@Autowired
	private DrugRepository drugRepo;
	@Autowired
	private EffectRepository effectRepo;
	@Autowired
	private IncidentRepository incidentRepo;

	private static final Logger LOG = LoggerFactory
			.getLogger(AssessmentController.class);

	@ModelAttribute("searchForm")
	public SearchForm getSearchForm() {
		LOG.debug("Instantiated new SearchForm");
		return new SearchForm();
	}

	@ModelAttribute("viewUser")
	public ViewUserForm getViewUserForm() {
		LOG.debug("Instantiated new ViewUserForm");
		return new ViewUserForm();
	}

	@ModelAttribute("newAssessment")
	public NewAssessmentForm getNewAssessmentForm() {
		return new NewAssessmentForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newAssessment(Model model) {
		return PageEnum.ASSESSMENT.getName();
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchUsers(SearchForm form, Model model) {

		List<String> errorMessages = Validator.validate(form);

		if (errorMessages.isEmpty()) {

			try {

				SearchHelper.searchPatientsOnly(getDao(), form, model);

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

		return PageEnum.ASSESSMENT.getName();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String chooseUser(ViewUserForm form, Model model) {

		try {
			User user = getUserRepo().findById(form.getUserId());
			ChosenPatientForm chosenPatient = Converter.convert(user,
					ChosenPatientForm.class);
			model.addAttribute("chosenUser", chosenPatient);
			List<Diagnosis> diagnoses = getDiagnosisRepo().findByUserId(
					user.getId());
			boolean empty = Validator.validateIsEmpty(diagnoses, model);
			if (!empty) {
				List<String> illnessNames = Converter
						.convertDiagnosisToIllnessNames(diagnoses,
								getIllnessRepo());
				model.addAttribute("illnesses", illnessNames);
			}

		} catch (InvalidEntityConversionTypeException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (InstantiationException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (IllegalAccessException e) {
			LOG.error("Error while converting User to ChosenPatientForm.", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (Exception e) {
			LOG.error("Unknown error occurred.", e);
			return PageEnum.UNKNOWN_ERROR.getName();
		}

		return PageEnum.ASSESSMENT.getName();
	}

	@RequestMapping(value = "/user/illness", method = RequestMethod.POST)
	public String persistAssessment(NewAssessmentForm form, Model model) {

		List<String> errorMessages = Validator.validate(form);

		if (errorMessages.isEmpty()) {

			List<DrugUserSuitability> results = AssessmentHelper.assess(form,
					getUserRepo(), getIllnessRepo(), getDrugRepo(), getDao(),
					getIncidentRepo(), getEffectRepo(), getAssessmentRepo());

			try {
				List<AssessmentForm> formItems = Converter.convert(results,
						AssessmentForm.class);

				model.addAttribute("items", formItems);
				model.addAttribute("success", "Successfully Assessed patient!");
				return PageEnum.VIEW_ASSESSMENTS.getName();

			} catch (InstantiationException e) {
				LOG.error(
						"Exception caught while converting to AssessmentForm",
						e);
				return PageEnum.UNKNOWN_ERROR.getName();
			} catch (IllegalAccessException e) {
				LOG.error(
						"Exception caught while converting to AssessmentForm",
						e);
				return PageEnum.UNKNOWN_ERROR.getName();
			} catch (InvalidEntityConversionTypeException e) {
				LOG.error(
						"Exception caught while converting to AssessmentForm",
						e);
				return PageEnum.UNKNOWN_ERROR.getName();
			}
		} else {
			model.addAttribute("error", errorMessages);
			return PageEnum.ASSESSMENT.getName();
		}

	}

	@RequestMapping(value = "/view")
	public String viewAssessments(Model model, HttpSession session) {

		try {
			BigDecimal userId = new BigDecimal(
					(Integer) session.getAttribute("userId"));

			List<DrugUserSuitability> assessments = getAssessmentRepo()
					.findByUserId(userId);

			List<AssessmentForm> formItems = Converter.convert(assessments,
					AssessmentForm.class);

			List<String> errorMessages = Validator.validate(assessments,
					formItems);
			if (errorMessages.isEmpty()) {
				model.addAttribute("items", formItems);
			} else {
				model.addAttribute("error", errorMessages);
			}
			return PageEnum.VIEW_ASSESSMENTS.getName();
		} catch (InstantiationException instantiation) {
			LOG.error(
					"InstantiationException during conversion from DrugUserSuitability.class to AssessmentForm.class in AssessmentController.viewAssessments",
					instantiation);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (IllegalAccessException access) {
			LOG.error(
					"IllegalAccessException during conversion from DrugUserSuitability.class to AssessmentForm.class in AssessmentController.viewAssessments",
					access);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (InvalidEntityConversionTypeException conversion) {
			LOG.error(
					"InvalidEntityConversionTypeException during conversion from DrugUserSuitability.class to AssessmentForm.class in AssessmentController.viewAssessments",
					conversion);
			return PageEnum.UNKNOWN_ERROR.getName();
		} catch (Exception e) {
			LOG.error(
					"Unknown Exception during conversion from DrugUserSuitability.class to AssessmentForm.class in AssessmentController.viewAssessments",
					e);
			return PageEnum.UNKNOWN_ERROR.getName();
		}
	}

	public AssessmentRepository getAssessmentRepo() {
		return assessmentRepo;
	}

	public void setAssessmentRepo(AssessmentRepository assessmentRepo) {
		this.assessmentRepo = assessmentRepo;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public DiagnosisRepository getDiagnosisRepo() {
		return diagnosisRepo;
	}

	public void setDiagnosisRepo(DiagnosisRepository diagnosisRepo) {
		this.diagnosisRepo = diagnosisRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public IllnessRepository getIllnessRepo() {
		return illnessRepo;
	}

	public void setIllnessRepo(IllnessRepository illnessRepo) {
		this.illnessRepo = illnessRepo;
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

	public IncidentRepository getIncidentRepo() {
		return incidentRepo;
	}

	public void setIncidentRepo(IncidentRepository incidentRepo) {
		this.incidentRepo = incidentRepo;
	}

}
