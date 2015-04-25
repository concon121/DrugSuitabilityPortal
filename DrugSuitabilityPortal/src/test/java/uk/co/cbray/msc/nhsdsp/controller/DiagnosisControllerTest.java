package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;

import java.math.BigDecimal;

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
	
	private Model model;
	private DiagnosisController controller;
	
	@Before
	public void SetUp() {
		
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(userRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getUserInstance());
		Mockito.when(illnessRepo.findAll()).thenReturn(TestFactory.getIllnessListInstance());

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
	
}
