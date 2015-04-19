package uk.co.cbray.msc.nhsdsp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;
import uk.co.cbray.msc.nhsdsp.utils.SecurityContextHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHelper.class)
public class UserControllerTest {

	@Mock
	private UserRepository userRepo;
	private Model model;
	private UserController controller;
	@Mock
	private HttpSession session;
	@Before
	public void setUp() {
		PowerMockito.mockStatic(SecurityContextHelper.class);
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(SecurityContextHelper.isUserAnonymous()).thenReturn(true);
		Mockito.when(session.getAttribute(anyString())).thenReturn(1);
		Mockito.when(userRepo.findById((BigDecimal)anyObject())).thenReturn(TestFactory.getUserInstance());
		
		model = new ExtendedModelMap();
		
		controller = new UserController();
		controller.setUserRepo(userRepo);
	}
	
	@Test
	public void testUserIsNotCreatedIfFormIsInvalid() {
		
		UserForm form = TestFactory.getInvalidUserFormInstance();
		controller.submitUserDetails(form, model);
		
		assertTrue(model.containsAttribute("error"));
		
	}
	
	@Test
	public void testUserIsCreatedWhenFormIsValidAndUserNotLoggedIn() {
		UserForm form = TestFactory.getValidUserFormInstance();
		controller.submitUserDetails(form, model);
		
		verify(userRepo).createNewUser((UserForm)anyObject());
		assertTrue(model.containsAttribute("success"));
	}
	
	@Test
	public void testUserIsCreatedWhenFormIsValidAndUserLoggedIn() {
		
		Mockito.when(SecurityContextHelper.isUserAnonymous()).thenReturn(false);
		
		UserForm form = TestFactory.getValidUserFormInstance();
		controller.submitUserDetails(form, model);
		
		verify(userRepo).createNewUser((UserForm)anyObject());
		assertTrue(model.containsAttribute("success"));
	}
	
	@Test
	public void testUserIsNotUpdatedIfFormIsInvalid() {
		
		UserDetailForm form = TestFactory.getInvalidUserDetailFormInstance();
		controller.persistUserDetails(form, model, session);
		
		assertTrue(model.containsAttribute("error"));
		
	}
	
	@Test
	public void testUserIsUpdatedIfFormIsValid() {
		
		UserDetailForm form = TestFactory.getValidUserDetailFormInstance();
		controller.persistUserDetails(form, model, session);
		
		assertFalse(model.containsAttribute("error"));
		assertTrue(model.containsAttribute("success"));
		
		verify(userRepo).update((User)anyObject());
		
	}
}
