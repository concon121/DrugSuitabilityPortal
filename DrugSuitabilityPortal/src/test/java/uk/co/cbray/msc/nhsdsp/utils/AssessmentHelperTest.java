package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.dao.AssessmentRepository;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.IncidentRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.forms.NewAssessmentForm;
import uk.co.cbray.msc.nhsdsp.test.utils.ClassificationFactory;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class AssessmentHelperTest {

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

	@Before
	public void SetUp() {
		MockitoAnnotations.initMocks(this);

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
	}

	@Test
	public void testSuccessScenarioForAssessmentHelperAssessMethod() {

		NewAssessmentForm form = new NewAssessmentForm();

		form.setIllnessName("test name");
		form.setUserId(1);

		List<DrugUserSuitability> results = AssessmentHelper.assess(form,
				userRepo, illnessRepo, drugRepo, dao, incidentRepo, effectRepo,
				assessmentRepo);

		// Verify the correct number of results exist
		assertEquals(4, results.size());
		// Verify the results have been saved to the database
		verify(assessmentRepo).createAll(anyListOf(DrugUserSuitability.class));

	}

	@Test
	public void testExceptionIsCaughtAndNoResultsAreReturned() {

		NewAssessmentForm form = new NewAssessmentForm();

		form.setIllnessName("test name");
		form.setUserId(1);

		Mockito.when(incidentRepo.findAllForDrug((Drug) anyObject()))
				.thenThrow(Exception.class);
		
		List<DrugUserSuitability> results = AssessmentHelper.assess(form,
				userRepo, illnessRepo, drugRepo, dao, incidentRepo, effectRepo,
				assessmentRepo);
		
		assertEquals(0, results.size());

	}
}
