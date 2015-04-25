package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.IForm;
import uk.co.cbray.msc.nhsdsp.forms.IncidentForm;
import uk.co.cbray.msc.nhsdsp.forms.NewAssessmentForm;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.NewEffectForm;
import uk.co.cbray.msc.nhsdsp.forms.NewPasswordForm;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;

/**
 * Encapsulates the logic for validating various different types of objects and
 * entities.
 * 
 * @author Connor Bray
 */
public class Validator {

	public static List<String> validate(UserForm user, UserRepository dao) {

		List<String> errorMessages = new ArrayList<String>();

		if (user.getDob() == null || user.getEmailAddress() == null
				|| user.getForename() == null || user.getPassword() == null
				|| user.getRoleName() == null
				|| user.getPasswordConfirmation() == null
				|| user.getSurname() == null || user.getUsername() == null) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		boolean validEmail = UserHelper.validateEmail(user.getEmailAddress());
		if (!validEmail) {
			errorMessages.add(ErrorMessageEnum.INVALID_EMAIL.getMessage());
		}

		if (user.getPassword() != null) {
			if (user.getPasswordConfirmation() != null) {
				if (!user.getPassword().equals(user.getPasswordConfirmation())) {
					errorMessages.add(ErrorMessageEnum.PASSWORDS_DO_NOT_MATCH
							.getMessage());
				}
			}
		}

		// check if user with username already exists
		List<UserLogin> results = dao.getUserLoginsForUsername(user
				.getUsername());
		if (results.size() > 0) {
			errorMessages.add(ErrorMessageEnum.USERNAME_ALREADY_EXISTS
					.getMessage());
		}

		return errorMessages;
	}

	public static List<String> validate(PatientDetailForm detail) {

		List<String> errorMessages = new ArrayList<String>();

		if (detail.getDiabetes() == null || detail.getEthnicity() == null
				|| detail.getHeight() == null || detail.getSmoker() == null
				|| detail.getWeight() == null || detail.getGender() == null) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		boolean validEthnicity = false;
		for (EthnicityEnum ethnicity : EthnicityEnum.values()) {
			if (ethnicity.getDescription().equalsIgnoreCase(
					detail.getEthnicity())) {
				validEthnicity = true;
			}
		}

		if (!validEthnicity) {
			errorMessages.add(ErrorMessageEnum.UNKNOWN_ETHNICITY.getMessage());
		}

		boolean validGender = false;
		for (GenderEnum gender : GenderEnum.values()) {
			if (gender.getName().equalsIgnoreCase(detail.getGender())) {
				validGender = true;
			}
		}

		if (!validGender) {
			errorMessages.add(ErrorMessageEnum.UNKNOWN_GENDER.getMessage());
		}

		return errorMessages;

	}

	public static List<String> validate(NewPasswordForm form) {

		List<String> errorMessages = new ArrayList<String>();

		if (form.getPassword() == null
				|| form.getPasswordConfirmation() == null) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		if (!form.getPassword().equals(form.getPasswordConfirmation())) {
			errorMessages.add(ErrorMessageEnum.PASSWORDS_DO_NOT_MATCH
					.getMessage());
		}

		return errorMessages;
	}

	public static List<String> validate(NewDrugForm form, EffectRepository repo) {

		List<String> errorMessages = new ArrayList<String>();
		if (form.getName() == null || form.getDescription() == null) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		String[] effects = form.getEffectsAsArray();
		for (String effect : effects) {
			Effect realEffect = repo.findByName(effect);
			if (realEffect == null) {
				errorMessages.add("Could not find effect in database: "
						+ effect);
			}
		}

		return errorMessages;
	}

	public static List<String> validate(IncidentForm form) {
		List<String> errorMessages = new ArrayList<String>();

		if (form.getDrug() == null || form.getDrug().trim().equals("")
				|| form.getDrug().trim().equals("NONE")
				|| form.getDrug().trim().equals("Please Select")
				|| form.getEffect() == null
				|| form.getEffect().trim().equals("")
				|| form.getEffect().trim().equals("NONE")
				|| form.getEffect().trim().equals("Please Select")) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		return errorMessages;
	}

	public static List<String> validate(UserDetailForm form) {

		List<String> errorMessages = new ArrayList<String>();

		if (form.getSurname() == null || form.getForename() == null
				|| form.getEmailAddress() == null || form.getDob() == null
				|| form.getDob().equals("")
				|| form.getDob().equals("dd/mm/yyyy")) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		return errorMessages;
	}

	public static boolean validateIsEmpty(List<?> list, Model model) {
		List<String> errorMessages = new ArrayList<String>();

		if (list == null || list.isEmpty()) {
			errorMessages.add(ErrorMessageEnum.NO_RESULTS.getMessage());
			model.addAttribute("error", errorMessages);
		}

		if (errorMessages.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public static List<String> validate(NewEffectForm form) {
		List<String> errorMessages = new ArrayList<String>();

		if (form.getName() == null || form.getName().trim().equals("")) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		else if (form.getDescription() == null
				|| form.getDescription().trim().equals("")) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		return errorMessages;
	}

	public static List<String> validate(ViewEffect form, Effect effect) {
		List<String> errorMessages = new ArrayList<String>();

		if (form.getEffectId() == null) {
			errorMessages.add(ErrorMessageEnum.NO_RESULTS.getMessage());
		} else if (effect == null) {
			errorMessages.add(ErrorMessageEnum.NO_RESULTS.getMessage());
		}

		return errorMessages;
	}

	public static <E extends IEntity, F extends IForm> List<String> validate(
			List<E> entityList, List<F> formItems) {
		List<String> errorMessages = new ArrayList<String>();

		if (entityList.isEmpty()) {
			errorMessages.add(ErrorMessageEnum.NO_RESULTS.getMessage());
		} else if (formItems.isEmpty()) {
			errorMessages.add(ErrorMessageEnum.NO_RESULTS.getMessage());
		}

		return errorMessages;
	}

	public static List<String> validate(NewAssessmentForm form) {
		List<String> errorMessages = new ArrayList<String>();

		if (form.getIllnessName() == null
				|| form.getIllnessName().trim().equals("")
				|| form.getIllnessName().trim()
						.equalsIgnoreCase("Please Select")
				|| form.getIllnessName().trim().equalsIgnoreCase("NONE")) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		else if (form.getUserId() == null) {
			errorMessages.add(ErrorMessageEnum.INCOMPLETE_FORM.getMessage());
		}

		return errorMessages;
	}

}
