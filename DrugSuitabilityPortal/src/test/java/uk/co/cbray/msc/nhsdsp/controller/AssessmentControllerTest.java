package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.AssessmentRepository;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DiagnosisRepository;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.IncidentRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.forms.NewAssessmentForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewUserForm;
import uk.co.cbray.msc.nhsdsp.test.utils.ClassificationFactory;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class AssessmentControllerTest {

	@Mock
	private UserRepository userRepo;
	@Mock
	private IllnessRepository illnessRepo;
	@Mock
	private DrugRepository drugRepo;
	@Mock
	private DataAccessObject dao;
	@Mock
	private IncidentRepository incidentRepo;
	@Mock
	private EffectRepository effectRepo;
	@Mock
	private AssessmentRepository assessmentRepo;
	@Mock
	private DiagnosisRepository diagnosisRepo;
	@Mock
	private HttpSession session;

	private AssessmentController controller;
	private Model model;

	@Before
	public void SetUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(diagnosisRepo.findByUserId((BigDecimal) anyObject()))
				.thenReturn(TestFactory.getDiagnosisList(5));

		Mockito.when(userRepo.findById((BigDecimal) anyObject())).thenReturn(
				ClassificationFactory.getUserBeingAssessed());
		Mockito.when(illnessRepo.findByName(anyString())).thenReturn(
				TestFactory.getIllnessListInstance());
		Mockito.when(
				drugRepo.findAllDrugsForIllnesses(anyListOf(Illness.class)))
				.thenReturn(ClassificationFactory.getDrugList());
		Mockito.when(incidentRepo.findAllForDrug((Drug) anyObject()))
				.thenReturn(ClassificationFactory.getIncidentListInstance());
		Mockito.when(effectRepo.findByName(eq("effect 1"))).thenReturn(
				ClassificationFactory.getEffectOne());
		Mockito.when(effectRepo.findByName(eq("effect 2"))).thenReturn(
				ClassificationFactory.getEffectTwo());
		Mockito.when(effectRepo.findByName(eq("effect 3"))).thenReturn(
				ClassificationFactory.getEffectThree());
		Mockito.when(effectRepo.findByName(eq("effect 4"))).thenReturn(
				ClassificationFactory.getEffectFour());
		Mockito.when(dao.executeNamedQuery("Effect.findAll", Effect.class))
				.thenReturn(ClassificationFactory.getEffectList());
		Mockito.when(session.getAttribute(anyString())).thenReturn(
				new Integer(1));
		Mockito.when(assessmentRepo.findByUserId((BigDecimal) anyObject()))
				.thenReturn(TestFactory.getDrugUserSuitabilityList(5));

		model = new ExtendedModelMap();
		controller = new AssessmentController();

		controller.setUserRepo(userRepo);
		controller.setAssessmentRepo(assessmentRepo);
		controller.setDao(dao);
		controller.setDiagnosisRepo(diagnosisRepo);
		controller.setDrugRepo(drugRepo);
		controller.setEffectRepo(effectRepo);
		controller.setIllnessRepo(illnessRepo);
		controller.setIncidentRepo(incidentRepo);
	}

	@Test
	public void testChooseUserReturnsUnknownErrorOnException() {

		Mockito.when(userRepo.findById((BigDecimal) anyObject())).thenThrow(
				Exception.class);

		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));

		String result = controller.chooseUser(form, model);

		// Verify the user is redirected to error page n exception
		assertEquals("unknownError", result);
	}

	@Test
	public void testChooseUserReturnsUnknownErrorOnInvalidEntityTypeException() {

		Mockito.when(userRepo.findById((BigDecimal) anyObject())).thenThrow(
				InvalidEntityConversionTypeException.class);

		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));

		String result = controller.chooseUser(form, model);

		// Verify the user is redirected to error page n exception
		assertEquals("unknownError", result);
	}

	@Test
	public void testChooseUserReturnsUnknownErrorOnInstantiationException() {

		Mockito.when(userRepo.findById((BigDecimal) anyObject())).thenThrow(
				InstantiationException.class);

		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));

		String result = controller.chooseUser(form, model);

		// Verify the user is redirected to error page n exception
		assertEquals("unknownError", result);
	}

	@Test
	public void testChooseUserReturnsUnknownErrorOnIllegalAccessException() {

		Mockito.when(userRepo.findById((BigDecimal) anyObject())).thenThrow(
				IllegalAccessException.class);

		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));

		String result = controller.chooseUser(form, model);

		// Verify the user is redirected to error page n exception
		assertEquals("unknownError", result);
	}

	@Test
	public void testOnChooseUserSuccessModelHasBeenPopulatedCorrectly() {
		ViewUserForm form = new ViewUserForm();
		form.setUserId(new BigDecimal(1));

		String result = controller.chooseUser(form, model);

		boolean chosenUserExists = model.containsAttribute("chosenUser");
		boolean illnessesExists = model.containsAttribute("illnesses");

		assertEquals("assessment", result);

		assertEquals(true, chosenUserExists);
		assertEquals(true, illnessesExists);
	}

	@Test
	public void testPersistAssessmentModelContainsErrorMessagesIfFormContainsErrors() {

		NewAssessmentForm form = new NewAssessmentForm();
		form.setIllnessName(null);
		form.setUserId(1);

		controller.persistAssessment(form, model);

		boolean errorExists = model.containsAttribute("error");

		assertEquals(true, errorExists);
	}

	@Test
	public void testPersistAssessmentModelIsPopulatedCorrectlyOnSuccess() {
		NewAssessmentForm form = new NewAssessmentForm();
		form.setIllnessName("a name");
		form.setUserId(1);

		String result = controller.persistAssessment(form, model);

		boolean errorExists = model.containsAttribute("error");
		boolean successExists = model.containsAttribute("success");
		boolean itemsExist = model.containsAttribute("items");

		assertEquals(false, errorExists);
		assertEquals(true, successExists);
		assertEquals(true, itemsExist);

		assertEquals("viewPastAssessments", result);
	}

	@Test
	public void testViewAssessmentsPopulatesModelCorrectlyOnSuccessScenario() {

		controller.viewAssessments(model, session);
		
		boolean containsErrors = model.containsAttribute("error");
		boolean containsItems = model.containsAttribute("items");
		
		assertEquals(false, containsErrors);
		assertEquals(true, containsItems);
	}
	
}
