package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * Helper class for al Role realted functions.
 * 
 * @author Connor Bray
 */
public class RoleHelper {

	/**
	 * Get the role with the highest priority. 
	 * 		Priority 0: PATIENT 
	 *		Priority 1: DOCTOR 
	 * 		Priority 2: ADMIN
	 * 
	 * @param grantedAuths
	 *            The authorities that the current user is granted.
	 * @return The role which the user is granted which has the highest
	 *         priority.
	 */
	public static RoleEnum getBaseRole(
			Collection<? extends GrantedAuthority> grantedAuths) {

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
