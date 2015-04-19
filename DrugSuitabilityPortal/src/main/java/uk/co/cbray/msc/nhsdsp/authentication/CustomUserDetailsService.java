package uk.co.cbray.msc.nhsdsp.authentication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Role;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private DataAccessObject dao;
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<UserLogin> results = getDao().executeJpqlQueryWithParameters("from UserLogin u where u.username = ?", UserLogin.class, username);
		if (results != null && results.size() > 0) {
			UserLogin userLogin = (UserLogin) results.get(0);
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			
			String role = userLogin.getUser().getRoleName();
			GrantedAuthority auth = new SimpleGrantedAuthority(role);
			auths.add(auth);
			LOG.debug("User has authority: " + auth.getAuthority());
			
			UserDetails details = new User(userLogin.getUsername(), userLogin.getPassword(), true, true, true, true, auths);
			return details;
		} else {
			LOG.error("Invalid access attempt, no results found for user.");
			return new User(username,"INVALID",false,false,false,false,Collections.<GrantedAuthority> emptyList());
		}
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	
}
