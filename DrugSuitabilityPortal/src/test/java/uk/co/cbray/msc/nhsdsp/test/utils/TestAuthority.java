package uk.co.cbray.msc.nhsdsp.test.utils;

import org.springframework.security.core.GrantedAuthority;

public class TestAuthority implements GrantedAuthority{

	private String authority;
	
	public String getAuthority() {
		return authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
