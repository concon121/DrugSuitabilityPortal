package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyString;
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

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DiagnosisRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.forms.DiagnosisForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class DiagnosisControllerTest {

	@Mock
	private IllnessRepository illnessRepo;
	@Mock
	private UserRepository userRepo;
	@Mock
	private DiagnosisRepository diagnosisRepo;
	@Mock
	private DataAccessObject dao;
	@Mock
	private HttpSession session;
	
	private Model model;
	private DiagnosisController controller;
	
	private Integer userId = 1;
	
	@Before
	public void SetUp() {
		
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(userRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getUserInstance());
		Mockito.when(illnessRepo.findAll()).thenReturn(TestFactory.getIllnessListInstance());
		Mockito.when(illnessRepo.findByName(anyString())).thenReturn(TestFactory.getIllnessListInstance());
		Mockito.when(illnessRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getIllnessInstance());
		Mockito.when(diagnosisRepo.findByUserId((BigDecimal)anyObject())).thenReturn(TestFactory.getDiagnosisList(5));
		Mockito.when(session.getAttribute(anyString())).thenReturn(userId);

		model = new ExtendedModelMap();
		controller = new DiagnosisController();
		
		controller.setDao(dao);
		controller.setDiagnosisRepo(diagnosisRepo);
		controller.setIllnessRepo(illnessRepo);
		controller.setUserRepo(userRepo);
		
	}
	
	@Test
	public void testSuccessScenarioForDiagnosisControllerChooseUserMethod(){
		
		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));
		
		String result = controller.chooseUser(form, model);
		
		boolean containsChosenUser = model.containsAttribute("chosenUser");
		boolean containsIllnesses = model.containsAttribute("illnesses");
		
		assertEquals("diagnosis", result);
		
		assertEquals(true, containsChosenUser);
		assertEquals(true, containsIllnesses);
	}
	
	@Test
	public void testPersistDiagnosisMethodCallsDiagnosisRepoCreateMethod() {
		
		DiagnosisForm form = TestFactory.getDiagnosisFormInstance();
		
		controller.persistDiagnosis(form, model);
		
		boolean containsSuccess = model.containsAttribute("success");
		
		verify(diagnosisRepo).create((Diagnosis)anyObject());
		assertTrue(containsSuccess);
		
	}
	
	@Test
	public void testViewDiagnosisMethodRetrievesDiagnosisForLoggedInUserAndPopulatesModel() {
		
		controller.viewDiagnosis(model, session);
		
		boolean containsItems = model.containsAttribute("items");
		
		verify(diagnosisRepo).findByUserId(eq(new BigDecimal(userId)));
		assertTrue(containsItems);
	}
	
	@Test
	public void testGetSearchFormReturnsCorrectObjectType() {
		Object form = controller.getSearchForm();
		
		if (form instanceof SearchForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testGetDiagnosisFormReturnsCorrectObjectType() {
		Object form = controller.getDiagnosisForm();
		
		if (form instanceof DiagnosisForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testGetViewUserFormReturnsCorrectObjectType() {
		Object form = controller.getViewUserForm();
		
		if (form instanceof ViewUserForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
}
