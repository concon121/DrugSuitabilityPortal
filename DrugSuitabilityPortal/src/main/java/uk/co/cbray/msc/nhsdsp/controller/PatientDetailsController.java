package uk.co.cbray.msc.nhsdsp.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.cbray.msc.nhsdsp.dao.PatientAllergyRepository;
import uk.co.cbray.msc.nhsdsp.dao.PatientDetailsRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.EthnicityEnum;
import uk.co.cbray.msc.nhsdsp.utils.GenderEnum;
import uk.co.cbray.msc.nhsdsp.utils.PageEnum;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/patient")
public class PatientDetailsController {
	
	@Autowired
	private PatientDetailsRepository patientDetailsRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PatientAllergyRepository patientAllergyRepo;

	@ModelAttribute(value="patientDetails")
	public PatientDetailForm getNewPatientDetails() {
		return new PatientDetailForm();
	}
	
	@RequestMapping(value="/update")
	public String updatePatientDetails(Model model, HttpSession session) {
		
		List<String> availableEthnicities = EthnicityEnum.getAvailableEthnicities();
		model.addAttribute("availableEthnicities", availableEthnicities);
		List<String> availableGenders = GenderEnum.getAvailableGenders();
		model.addAttribute("availableGenders", availableGenders);
		
		// check if patient details already exists for use
		BigDecimal userId = new BigDecimal((Integer)session.getAttribute("userId"));
		User user = getUserRepo().findById(userId);
		List<PatientDetail> detailResults = getPatientDetailsRepo().findPatientDetailsForUser(user);
		List<PatientAllergy> allergyResults = getPatientAllergyRepo().findPatientAllergysForUser(user);
		
		if (detailResults.size() > 0) {
			// get the first result
			PatientDetail detail = detailResults.get(0);
			PatientDetailForm form = Converter.convert(detail, allergyResults);
			model.addAttribute("formContents", form);
		}
		
		return PageEnum.UPDATE_PATIENT_DETAILS.getName();
	}
	
	@RequestMapping(value="/update/persist", method=RequestMethod.POST)
	public String persistPatientDetail(PatientDetailForm detail, Model model, HttpSession session ) {
		
		List<String> errorMessages = Validator.validate(detail);
		
		if (errorMessages.size() > 0) {
			model.addAttribute("error", errorMessages);
			model.addAttribute("patientDetails", detail);
			List<String> availableEthnicities = EthnicityEnum.getAvailableEthnicities();
			model.addAttribute("availableEthnicities", availableEthnicities);
			List<String> availableGenders = GenderEnum.getAvailableGenders();
			model.addAttribute("availableGenders", availableGenders);
			return PageEnum.UPDATE_PATIENT_DETAILS.getName();
		} else {
			// Check if a patientDetail already exists for user
			BigDecimal userId = new BigDecimal((Integer)session.getAttribute("userId"));
			User user = getUserRepo().findById(userId);
			user = Converter.convert(detail, user);
			
			getPatientDetailsRepo().updateAllPatientDetails(user, detail);
			
			model.addAttribute("success", "Successfully updated Patient Details.");
			return PageEnum.PROFILE.getName();
		}
		
	}

	public PatientDetailsRepository getPatientDetailsRepo() {
		return patientDetailsRepo;
	}

	public void setPatientDetailsRepo(PatientDetailsRepository patientDetailsRepo) {
		this.patientDetailsRepo = patientDetailsRepo;
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public PatientAllergyRepository getPatientAllergyRepo() {
		return patientAllergyRepo;
	}

	public void setPatientAllergyRepo(PatientAllergyRepository patientAllergyRepo) {
		this.patientAllergyRepo = patientAllergyRepo;
	}

	
	
	
	
}
