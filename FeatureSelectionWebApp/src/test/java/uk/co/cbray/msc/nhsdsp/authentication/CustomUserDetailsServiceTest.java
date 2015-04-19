package uk.co.cbray.msc.nhsdsp.authentication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uk.co.cbray.msc.nhsdsp.authentication.CustomUserDetailsService;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class CustomUserDetailsServiceTest {
	
	private ApplicationContext ctx;
	@Mock
	private DataAccessObject dao;
	@Mock 
	private EntityManager em;
	@Spy
	private CustomUserDetailsService service;
	
	@Before
	public void SetUp() {
		service = new CustomUserDetailsService();
		MockitoAnnotations.initMocks(this);
		
		dao.setEm(em);
		service.setDao(dao);
	}
	
	@Test
	public void testUserDetailsServiceReturnsCorrectUserDetailsObjectWhenUserLoginIsFound() {
		
		String username = "username";
		String password = "password";
		
		List<UserLogin> logins = new ArrayList<UserLogin>();
		
		UserLogin login = new UserLogin();
		login.setUsername(username);
		login.setPassword(password);
		login.setUser(TestFactory.getUserInstance());
		
		logins.add(login);
		
		when(dao.executeJpqlQueryWithParameters(anyString(), eq(UserLogin.class), anyString())).thenReturn(logins);
		
		
		UserDetails details = service.loadUserByUsername(username);
		
		String actualUsername = details.getUsername();
		String actualPassword = details.getPassword();
		
		assertEquals(username, actualUsername);
		assertEquals(password, actualPassword);
	}
	
	@Test
	public void testUserDetailsServiceReturnsCorrectUserDetailsObjectWhenNoUserLoginIsFound() {
		
		String username = "username";
		String password = "password";
		String invalidPassword = "INVALID";
		
		List<UserLogin> logins = new ArrayList<UserLogin>();
		
		when(dao.executeJpqlQueryWithParameters(anyString(), eq(UserLogin.class), anyString())).thenReturn(logins);
		
		
		UserDetails details = service.loadUserByUsername(username);
		
		String actualUsername = details.getUsername();
		String actualPassword = details.getPassword();
		Collection<? extends GrantedAuthority> auths = details.getAuthorities();
		
		assertEquals(username, actualUsername);
		assertEquals(invalidPassword, actualPassword);
		// test that the user has no authorities
		assertEquals(0, auths.size());
	}
	
}
