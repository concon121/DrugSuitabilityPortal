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

import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.RoleEnum;
import uk.co.cbray.msc.nhsdsp.utils.RoleHelper;
import uk.co.cbray.msc.nhsdsp.utils.SecurityContextHelper;
import uk.co.cbray.msc.nhsdsp.utils.Validator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	private static final Logger LOG = LoggerFactory
			.getLogger(UserController.class);

	@ModelAttribute(value = "user")
	public UserForm getUser() {
		return new UserForm();
	}

	@ModelAttribute(value = "userDetails")
	public UserDetailForm getNewUserDetails() {
		return new UserDetailForm();
	}

	private User findLoggedInUser(HttpSession session) {
		User user = getUserRepo().findById(
				new BigDecimal((Integer) session.getAttribute("userId")));
		return user;
	}

	private void populateModel(Model model, HttpSession session)
			throws InvalidEntityConversionTypeException,
			InstantiationException, IllegalAccessException {
		// check if patient details already exists for use

		User user = findLoggedInUser(session);

		if (user != null) {
			UserDetailForm form = Converter.convert(user, UserDetailForm.class);
			model.addAttribute("formContents", form);
		}
	}

	@RequestMapping(value = "/new")
	public String newUser(Model model) {

		RoleEnum baseRole = RoleHelper.getBaseRole(SecurityContextHelper
				.getAuthorities());
		List<String> availableRoles = RoleHelper
				.userCanAddNewUsersWithRoles(baseRole);

		model.addAttribute("availableRoles", availableRoles);

		return "newUserForm";
	}

	@RequestMapping(value = "/new/persist", method = RequestMethod.POST)
	public String submitUserDetails(UserForm newUser, Model model) {

		List<String> errorMessages = Validator.validate(newUser, getUserRepo());

		if (errorMessages.size() > 0) {
			model.addAttribute("error", errorMessages);
			model.addAttribute("formContents", newUser);
			return "newUserForm";
		} else {

			getUserRepo().createNewUser(newUser);

			if (SecurityContextHelper.isUserAnonymous()) {
				model.addAttribute("success",
						"Successfully added user.  Please log in to continue.");
				return "login";
			} else {
				model.addAttribute("success", "Successfully added user.");
				return "home";
			}

		}
	}

	@RequestMapping(value = "/update")
	public String updateUserDetails(Model model, HttpSession session) {

		try {
			populateModel(model, session);
			return "updateUserDetails";
		} catch (InvalidEntityConversionTypeException conversion) {
			LOG.error(
					"InvalidEntityConversionTypeException during conversion from User.class to UserDetailForm.class in UserDetailsController.updateUserDetails",
					conversion);
			return "unknownError";
		} catch (Exception e) {
			LOG.error(
					"Unknown Exception during conversion from User.class to UserDetailForm.class in UserDetailsController.updateUserDetails",
					e);
			return "unknownError";
		}
	}

	@RequestMapping(value = "/update/persist")
	public String persistUserDetails(UserDetailForm form, Model model,
			HttpSession session) {

		List<String> errorMessages = Validator.validate(form);

		if (errorMessages.size() > 0) {
			try {
				model.addAttribute("error", errorMessages);
				populateModel(model, session);
				return "updateUserDetails";
			} catch (InvalidEntityConversionTypeException conversion) {
				LOG.error(
						"InvalidEntityConversionTypeException during conversion from User.class to UserDetailForm.class in UserDetailsController.updateUserDetails",
						conversion);
				return "unknownError";
			} catch (Exception e) {
				LOG.error(
						"Unknown Exception during conversion from User.class to UserDetailForm.class in UserDetailsController.updateUserDetails",
						e);
				return "unknownError";
			}
		} else {

			User user = findLoggedInUser(session);

			user = Converter.convert(form, user);
			getUserRepo().update(user);

			model.addAttribute("success", "Successfully updated User Details.");

			return "profile";
		}
	}

	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String viewUser(ViewUserForm form, Model model, HttpSession session) {
		User user = getUserRepo().findById(form.getUserId());
		UserDetailForm userDetails = null;
		try {
			userDetails = Converter.convert(user, UserDetailForm.class);
		} catch (InvalidEntityConversionTypeException e) {
			LOG.error("Error while converting User to UserDetailsForm.", e);
			return "unknownError";
		} catch (InstantiationException e) {
			LOG.error("Error while converting User to UserDetailsForm.", e);
			return "unknownError";
		} catch (IllegalAccessException e) {
			LOG.error("Error while converting User to UserDetailsForm.", e);
			return "unknownError";
		}
		PatientDetailForm patientDetails = null;
		if (user.getPatientDetails() != null
				&& user.getPatientDetails().size() > 0) {
			patientDetails = Converter.convert(user.getPatientDetails(),
					user.getPatientAllergies());
		}

		if (userDetails != null) {
			model.addAttribute("user", userDetails);
		}
		if (patientDetails != null) {
			model.addAttribute("patient", patientDetails);
		}

		return "viewUser";
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

}
