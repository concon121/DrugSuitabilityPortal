package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserHelperTest {
	
	@Test
	public void testValidateEmailReturnsTrueForValidEmail() {
		String email = "connor.bray@email.com";
		boolean isValid = UserHelper.validateEmail(email);
		assertTrue(isValid);
	}
	
	@Test
	public void testValidateEmailReturnsFalseForInValidEmail() {
		String email = "connor.bray";
		boolean isValid = UserHelper.validateEmail(email);
		assertFalse(isValid);
	}
	
}
