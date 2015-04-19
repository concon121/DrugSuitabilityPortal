package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class RoleHelper {
	
	public static RoleEnum getBaseRole(Collection<? extends GrantedAuthority> grantedAuths) {

		RoleEnum baseRole = null;
		
		for (GrantedAuthority granted : grantedAuths) {

			String roleName = granted.getAuthority();
			for (RoleEnum roleEnum : RoleEnum.values()) {
				if (roleEnum.getName().equals(roleName)) {
					if (baseRole == null) {
						baseRole = roleEnum;
					} else {
						if (baseRole.getPriority() < roleEnum.getPriority()) {
							baseRole = roleEnum;
						}
					}
				}
			}

		}
		
		return baseRole;

	}

	public static List<String> userCanAddNewUsersWithRoles(RoleEnum baseRole) {

		List<String> availableRoles = new ArrayList<String>();
		
		if (baseRole == RoleEnum.ROLE_ADMIN) {
			availableRoles.add(RoleEnum.ROLE_ADMIN.getName());
			availableRoles.add(RoleEnum.ROLE_DOCTOR.getName());
			availableRoles.add(RoleEnum.ROLE_PATIENT.getName());
		} else {
			availableRoles.add(RoleEnum.ROLE_PATIENT.getName());
		}
		
		return availableRoles;
	}
}
