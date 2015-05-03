package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anySetOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class UserRepositoryTest {
	
	@Mock
	private DataAccessObject dao;
	private UserRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.executeJpqlQuery(anyString(), eq(User.class))).thenReturn(TestFactory.getUserListInstance());
		
		repo = new UserRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		User i = TestFactory.getUserInstance();
		
		repo.create(i);
		
		verify(dao).create(i);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		User i = TestFactory.getUserInstance();
		
		repo.update(i);
			
		verify(dao).update(i, User.class);
	}
	
	@Test
	public void testFindByIdMethodCallsDaoFindMethod() {
		BigDecimal id = new BigDecimal(1);
		
		repo.findById(id);
		
		verify(dao).find(id, User.class);
	}
	
	@Test
	public void testGetUserLoginsForUsernameMethodCallsDAOMethod() {

		String username = "username";
		
		repo.getUserLoginsForUsername(username);
		
		verify(dao).executeJpqlQueryWithParameters(anyString(), eq(UserLogin.class), eq(username));
	}
	@Test
	public void testGetUserLoginsForUserMethodCallsDAOMethod() {

		User u = TestFactory.getUserInstance();
		
		repo.getUserLoginsForUser(u);
		
		verify(dao).executeJpqlQueryWithParameters(anyString(), eq(UserLogin.class), eq(u));
	}
	
	@Test
	public void testCreateNewUserMethodCreatesBothTheUserAndTheUserLogins() {
		
		UserForm form = TestFactory.getUserFormInstance();
		
		repo.createNewUser(form);
		
		verify(dao).create((User)anyObject());
		verify(dao).createAll(anySetOf(UserLogin.class));
		
	}
}
