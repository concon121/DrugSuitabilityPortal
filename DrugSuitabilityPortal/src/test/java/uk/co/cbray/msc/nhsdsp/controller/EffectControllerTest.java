package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.forms.NewEffectForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class EffectControllerTest {

	@Mock
	private EffectRepository effectRepo;
	
	private EffectController controller;
	private Model model;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(effectRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getEffectInstance());
		
		model = new ExtendedModelMap();
		
		controller = new EffectController();
		controller.setEffectRepo(effectRepo);
	}
	
	@Test
	public void testPersistEffectMethodCallsEffectRepoCreateMethodAndPopulatesModel() {
		
		NewEffectForm form = TestFactory.getNewEffectFormInstance();
		
		controller.persistEffect(form, model);
		
		boolean containsSuccess = model.containsAttribute("success");
		
		verify(effectRepo).create((Effect)anyObject());
		assertTrue(containsSuccess);
	}
	
	@Test
	public void testPersistEffectMethodAddsErrorMessagesToModelWhenValidationFails() {
		
		NewEffectForm form = TestFactory.getInvalidNewEffectFormInstance();
		
		controller.persistEffect(form, model);
		
		boolean containsSuccess = model.containsAttribute("success");
		boolean containsError = model.containsAttribute("error");
		
		verify(effectRepo, times(0)).create((Effect)anyObject());
		assertFalse(containsSuccess);
		assertTrue(containsError);
	}
	
	@Test
	public void testViewEffectCallsEffectRepoFindByIdMethodAndPopulatesModel() {
		
		ViewEffect form = TestFactory.getViewEffectInstance();
		
		controller.viewEffect(form, model);
		
		boolean containsEffect = model.containsAttribute("effect");
		
		verify(effectRepo).findById((BigDecimal)anyObject());
		assertTrue(containsEffect);
	}
	
	@Test
	public void testViewEffectAddsErrorMessagesToModelWhenValidaionFails() {
		
		ViewEffect form = TestFactory.getInvalidViewEffectInstance();
		
		controller.viewEffect(form, model);
		
		boolean containsEffect = model.containsAttribute("effect");
		boolean containsError = model.containsAttribute("error");
		
		assertFalse(containsEffect);
		assertTrue(containsError);
	}
	
	@Test
	public void testGetNewEffectFormReturnsCorrectObjectType() {
		Object form = controller.getEffectForm();
		
		if (form instanceof NewEffectForm) {
			assertTrue(true);
		} else {
			fail();
		}
	}
}
