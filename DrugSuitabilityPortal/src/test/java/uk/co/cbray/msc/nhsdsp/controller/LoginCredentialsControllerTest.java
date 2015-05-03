package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
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
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.NewPasswordForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class LoginCredentialsControllerTest {

	@Mock
	private DataAccessObject dao;
	@Mock
	private UserRepository userRepo;
	@Mock
	private HttpSession session;
	private LoginCredentialsController controller;
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(session.getAttribute("userId")).thenReturn(new Integer(1));
		Mockito.when(userRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getUserInstance());
		Mockito.when(userRepo.getUserLoginsForUser((User)anyObject())).thenReturn(TestFactory.getUserLoginListInstance(5));
		
		controller = new LoginCredentialsController();
		model = new ExtendedModelMap();
		controller.setDao(dao);
		controller.setUserRepo(userRepo);
	}
	
	@Test
	public void testPersistNewPasswordMethodCallsDAOUpdateMethod() {
		
		NewPasswordForm form = TestFactory.getNewPasswordFormInstance();
		
		controller.persistNewPassword(form, model, session);
		
		boolean containsSuccess = model.containsAttribute("success");
		
		verify(dao).update((UserLogin)anyObject(), eq(UserLogin.class));
		assertTrue(containsSuccess);
		
	}
	
	@Test
	public void testPersistNewPasswordAddsErrorMessagesToModelIfValidationFails () {
		
		NewPasswordForm form = TestFactory.getNewPasswordFormInstanceInvalid();
		
		controller.persistNewPassword(form, model, session);
		
		boolean containsSuccess = model.containsAttribute("success");
		boolean containsError = model.containsAttribute("error");
		
		verify(dao, times(0)).update((UserLogin)anyObject(), eq(UserLogin.class));
		assertFalse(containsSuccess);
		assertTrue(containsError);

		
	}
}
