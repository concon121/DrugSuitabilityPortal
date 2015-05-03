package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.PatientAllergyRepository;
import uk.co.cbray.msc.nhsdsp.dao.PatientDetailsRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class PatientDetailsControllerTest {
	
	@Mock
	private PatientDetailsRepository patientDetailsRepo;
	@Mock
	private UserRepository userRepo;
	@Mock
	private PatientAllergyRepository patientAllergyRepo;
	@Mock
	private HttpSession session;
	
	private PatientDetailsController controller;
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(session.getAttribute("userId")).thenReturn(new Integer(1));
		Mockito.when(userRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getUserInstance());
		Mockito.when(patientAllergyRepo.findPatientAllergysForUser((User)anyObject())).thenReturn(TestFactory.getPatientAllergyListInstance(5));
		Mockito.when(patientDetailsRepo.findPatientDetailsForUser((User)anyObject())).thenReturn(TestFactory.getPatientDetailListInstance(5));
		
		model = new ExtendedModelMap();
		
		controller = new PatientDetailsController();
		controller.setPatientAllergyRepo(patientAllergyRepo);
		controller.setPatientDetailsRepo(patientDetailsRepo);
		controller.setUserRepo(userRepo);
	}
	
	@Test
	public void testUpdatePatientDetailsPopulatesModelCorrectly() {
		
		controller.updatePatientDetails(model, session);
		
		boolean containsEthnicities = model.containsAttribute("availableEthnicities");
		boolean containsGenders = model.containsAttribute("availableGenders");
		boolean containsFormContents = model.containsAttribute("formContents");
		
		assertTrue(containsEthnicities);
		assertTrue(containsGenders);
		assertTrue(containsFormContents);
	}
	
	@Test
	public void testPersistPatientDetailsCallsPatientDetailsRepoUpdateAllMethod() {
		
		PatientDetailForm detail = TestFactory.getPatientDetailFormInstance();
		
		controller.persistPatientDetail(detail, model, session);
		
		boolean containsSuccess = model.containsAttribute("success");
		
		verify(patientDetailsRepo).updateAllPatientDetails((User)anyObject(), eq(detail));
		assertTrue(containsSuccess);
	}
	
	@Test
	public void testPersistPatientDetailsPopulatesModelCorrectlyOnValidationFailure() {
		
		PatientDetailForm detail = TestFactory.getPatientDetailFormInstance();
		detail.setEthnicity("randomAndInvalid");
		
		controller.persistPatientDetail(detail, model, session);
		
		boolean containsEthnicities = model.containsAttribute("availableEthnicities");
		boolean containsGenders = model.containsAttribute("availableGenders");
		boolean containsFormContents = model.containsAttribute("patientDetails");
		boolean containsError = model.containsAttribute("error");
		
		assertTrue(containsEthnicities);
		assertTrue(containsGenders);
		assertTrue(containsFormContents);
		assertTrue(containsError);
	}
	
	@Test
	public void testGetNewPatientDetailsReturnsCorrectObjectType() {
		Object form = controller.getNewPatientDetails();
		
		if (form instanceof PatientDetailForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}

}
