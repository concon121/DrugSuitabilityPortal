package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;
import uk.co.cbray.msc.nhsdsp.utils.SearchHelper;

public class DrugControllerTest {

	@Mock
	private DrugRepository drugRepo;
	@Mock
	private EffectRepository effectRepo;
	@Mock
	private DataAccessObject dao;
	@Mock
	private HttpSession session;
	
	private Model model;
	
	private DrugController controller;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(effectRepo.findByName(anyString())).thenReturn(TestFactory.getEffectInstance());
		Mockito.when(drugRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getDrugInstance());
		
		model = new ExtendedModelMap();
		
		controller = new DrugController();
		controller.setDrugRepo(drugRepo);
		controller.setEffectRepo(effectRepo);
		controller.setDao(dao);
	}
	
	@Test
	public void testPersistNewDrugMethodCallsDrugRepoCreateNewDrugMethod() {
		
		NewDrugForm form = TestFactory.getNewDrugFormInstance();
		
		controller.persistNewDrug(form, model, session);
		
		verify(drugRepo).createNewDrug(form);
		
		boolean containsSuccessMessage = model.containsAttribute("success");
		
		assertTrue(containsSuccessMessage);
	}
	
	@Test
	public void testPersistNewDrugCorrectlyPopulatesTheModelIfValidationFailsForFormEffectDosentExist() {
		
		Mockito.when(effectRepo.findByName(anyString())).thenReturn(null);
		
		NewDrugForm form = TestFactory.getNewDrugFormInstance();
		
		controller.persistNewDrug(form, model, session);
		
		boolean containsErrors = model.containsAttribute("error");
		boolean containsForm = model.containsAttribute("formContents");
		
		assertTrue(containsErrors);
		assertTrue(containsForm);
		
	}
	
	@Test
	public void testPersistNewDrugCorrectlyPopulatesTheModelIfValidationFailsForFormIncompleteForm() {
		
		NewDrugForm form = TestFactory.getInvalidNewDrugFormInstance();
		
		controller.persistNewDrug(form, model, session);
		
		boolean containsErrors = model.containsAttribute("error");
		boolean containsForm = model.containsAttribute("formContents");
		
		assertTrue(containsErrors);
		assertTrue(containsForm);
		
	}
	
	@Test
	public void testViewDrugMethodCallsDrugRepoFindByIDMethodAndPopulatesModel() {
		
		ViewDrugForm form = TestFactory.getViewDrugFormInstance();
		
		controller.viewDrug(form, model, session);
		
		boolean containsDrug = model.containsAttribute("drug");
		
		assertTrue(containsDrug);
		verify(drugRepo).findById(form.getDrugId());
		
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
	public void testGetViewEffectFormReturnsCorrectObjectType() {
		Object form = controller.getViewEffectForm();
		
		if (form instanceof ViewEffect) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testGetNewDrugFormReturnsCorrectObjectType() {
		Object form = controller.getNewDrugForm();
		
		if (form instanceof NewDrugForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
}
