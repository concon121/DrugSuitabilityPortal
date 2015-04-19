package uk.co.cbray.msc.nhsdsp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import uk.co.cbray.msc.nhsdsp.dao.PatientAllergyRepository;
import uk.co.cbray.msc.nhsdsp.dao.PatientDetailsRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.RoleEnum;
import uk.co.cbray.msc.nhsdsp.utils.RoleHelper;
import uk.co.cbray.msc.nhsdsp.utils.SecurityContextHelper;

public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final String ANONYMOUS = "anonymousUser";
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PatientAllergyRepository patientAllergyRepo;
	@Autowired
	private PatientDetailsRepository patientDetailsRepo;

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {

	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView model)
			throws Exception {

		String name = SecurityContextHelper.getUsername();

		if (!name.equalsIgnoreCase(ANONYMOUS)) {

			RoleEnum role = RoleHelper.getBaseRole(SecurityContextHelper.getAuthorities());
			
			List<UserLogin> userLogins = getUserRepo().getUserLoginsForUsername(name);
			UserLogin userLogin = userLogins.get(0);

			User user = userLogin.getUser();
			
			model.getModel().put("username", name);
			model.getModel().put("userRole", role.getName());
			if(user != null) {
				UserDetailForm form = Converter.convert(user, UserDetailForm.class);
				model.getModel().put("userProfile", form);
			}
			PatientDetail details = getPatientDetails(user);
			List<PatientAllergy> allergies = getPatientAllergy(user);
			if (details != null) {
				PatientDetailForm patientDetails = Converter.convert(details, allergies);
				model.getModel().put("patientDetails", patientDetails);
			}

		}

	}

	private PatientDetail getPatientDetails(User user) {

		List<PatientDetail> details = getPatientDetailsRepo().findPatientDetailsForUser(user);
		
		if (details == null || details.size() == 0) {
			return null;
		} else {
			return details.get(0);
		}

	}

	private List<PatientAllergy> getPatientAllergy(User user) {
		List<PatientAllergy> alergies = getPatientAllergyRepo().findPatientAllergysForUser(user);
		return alergies;
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String name = SecurityContextHelper.getUsername();

		if (!name.equalsIgnoreCase(ANONYMOUS)) {
			List<UserLogin> userLogins = getUserRepo().getUserLoginsForUsername(name);
			UserLogin userLogin = userLogins.get(0);
			int userId = userLogin.getUser().getId().intValue();
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("userId", userId);
		}
		
		return true;
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

	public PatientDetailsRepository getPatientDetailsRepo() {
		return patientDetailsRepo;
	}

	public void setPatientDetailsRepo(PatientDetailsRepository patientDetailsRepo) {
		this.patientDetailsRepo = patientDetailsRepo;
	}
	
}
