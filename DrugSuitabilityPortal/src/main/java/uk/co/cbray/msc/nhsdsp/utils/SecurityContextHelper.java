package uk.co.cbray.msc.nhsdsp.utils;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {

	public static final String ANONYMOUS = "anonymousUser";

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

	public static Collection<? extends GrantedAuthority> getAuthorities() {
		
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
				return auths;
			}
		}
		
		return Collections.EMPTY_LIST;
	}

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
