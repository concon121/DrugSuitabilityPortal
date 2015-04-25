package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugIllness;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidParameterException;
import uk.co.cbray.msc.nhsdsp.forms.IncidentForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
import uk.co.cbray.msc.nhsdsp.utils.SecurityContextHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value={Converter.class})
public class IncidentControllerTest {

	@Mock
	private DataAccessObject dao;
	private IncidentController controller;
	@Mock
	private HttpSession session;
	private Model model;
	
	private Incident incident;
	
	@Before
	public void SetUp() throws InvalidParameterException {
		
		PowerMockito.mockStatic(Converter.class);
		MockitoAnnotations.initMocks(this);
		
		controller = new IncidentController();
		model = new ExtendedModelMap();
		
		Mockito.when(session.getAttribute(anyString())).thenReturn(1);
		Mockito.when(dao.executeJpqlQueryWithParameters(anyString(), eq(Diagnosis.class), anyInt())).thenReturn(TestFactory.getDiagnosisList(5));
		Mockito.when(dao.executeJpqlQueryWithParameters(anyString(), eq(DrugIllness.class), anyInt())).thenReturn(TestFactory.getDrugIllnessList(5));
		Mockito.when(dao.executeNamedQuery(anyString(), eq(Effect.class))).thenReturn(TestFactory.getEffectList(5));
		Mockito.when(dao.executeNamedQuery(anyString(), eq(Drug.class))).thenReturn(TestFactory.getDrugListInstance());
		Mockito.when(dao.find((BigDecimal)anyObject(), eq(User.class))).thenReturn(TestFactory.getUserInstance());
		
		incident = TestFactory.getIncidentInstance();
		
		Mockito.when(Converter.convert((IncidentForm)anyObject(), (DataAccessObject)anyObject(), (HttpSession)anyObject())).thenReturn(incident);
		
		
		controller.setDao(dao);
	}
	
	@Test
	public void testModelContainsErrorsAndOriginalFormWhenFormContainsErrors() {
		
		String drugName = "a drug name";
		String effectName = null;
		
		IncidentForm form = new IncidentForm();
		form.setDrug(drugName);
		form.setEffect(effectName);
		controller.persistIncident(form, model, session);
		
		boolean containsErrors = model.containsAttribute("error");
		boolean containsFormData = model.containsAttribute("formContent");
		
		assertEquals(true, containsErrors);
		assertEquals(true, containsFormData);
	}
	
	@Test
	public void testSuccessScenarioPersistIncident() throws InvalidParameterException {
		String drugName = "a drug name";
		String effectName = "an effect name";
		
		IncidentForm form = new IncidentForm();
		form.setDrug(drugName);
		form.setEffect(effectName);

		controller.persistIncident(form, model, session);
		
		boolean containsErrors = model.containsAttribute("error");
		boolean containsFormData = model.containsAttribute("formContent");
		boolean containsSuccess = model.containsAttribute("success");
		
		assertEquals(false, containsErrors);
		assertEquals(false, containsFormData);
		assertEquals(true, containsSuccess);
		
		verify(dao).create((Incident)anyObject());
	}
}
