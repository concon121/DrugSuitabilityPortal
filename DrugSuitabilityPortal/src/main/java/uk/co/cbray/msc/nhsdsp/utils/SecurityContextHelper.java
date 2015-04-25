package uk.co.cbray.msc.nhsdsp.utils;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Helper class for accessing the current SecurityContext. Provides a readable
 * api, and also performs validation on the SecurityContext to prevent null
 * pointer exceptions.
 * 
 * @author Connor Bray
 */
public class SecurityContextHelper {

	public static final String ANONYMOUS = "anonymousUser";

	/**
	 * Checks if the user is anonymous or logged in.
	 * 
	 * @return True is user is anonymous, false if user is logged in.
	 */
	public static boolean isUserAnonymous() {

		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				String name = auth.getName();
				if (name.equalsIgnoreCase(ANONYMOUS)) {
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

	/**
	 * Find the authorities grated to the user.
	 * 
	 * @return The granted authorities.
	 */
	public static Collection<? extends GrantedAuthority> getAuthorities() {

		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Collection<? extends GrantedAuthority> auths = auth
						.getAuthorities();
				return auths;
			}
		}

		return Collections.EMPTY_LIST;
	}

	/**
	 * Find the username of the current logged in user.
	 * 
	 * @return The username, defaults to anonymousUser if the user is not logged
	 *         in.
	 */
	public static String getUsername() {

		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				String name = auth.getName();
				return name;
			}
		}

		return ANONYMOUS;
	}
}
