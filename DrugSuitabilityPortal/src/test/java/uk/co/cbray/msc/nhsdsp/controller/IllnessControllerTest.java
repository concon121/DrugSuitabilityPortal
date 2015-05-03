package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.forms.ViewIllnessForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class IllnessControllerTest {

	@Mock
	private DataAccessObject dao;
	
	private IllnessController controller;
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.find((BigDecimal)anyObject(), eq(Illness.class))).thenReturn(TestFactory.getIllnessInstance());
		
		controller = new IllnessController();
		controller.setDao(dao);
		
		model = new ExtendedModelMap();
	}
	
	@Test
	public void testViewIllnessMethodCallsDaoFindMethodAndPopulatesModel() {
		
		ViewIllnessForm form = TestFactory.getViewIllnessFormInstance();
		
		controller.viewIllness(form, model);
		
		boolean containsIllness = model.containsAttribute("illness");
		
		verify(dao).find(form.getIllnessId(), Illness.class);
		assertTrue(containsIllness);
		
	}
	
}
