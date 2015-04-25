package uk.co.cbray.msc.nhsdsp.utils;

import java.util.regex.Pattern;

/**
 * Helper class for all User related functions.
 * 
 * @author Connor Bray
 */
public class UserHelper {

	/**
	 * Validates an email address.
	 * 
	 * Taken from:
	 * http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
	 * 
	 * @param email
	 * @return True if valid, false otherwise.
	 */
	public static boolean validateEmail(String email) {
		if (email != null) {
			Pattern pattern = Pattern
					.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			return pattern.matcher(email).matches();
		} else {
			return false;
		}

	}

}
