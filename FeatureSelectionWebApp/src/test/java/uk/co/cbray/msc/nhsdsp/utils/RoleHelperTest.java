package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class RoleHelperTest {

	@Test
	public void testNewUserAvailableRolesForAPatientArePATIENT() {
		List<String> roles = RoleHelper
				.userCanAddNewUsersWithRoles(RoleEnum.ROLE_PATIENT);

		assertEquals(1, roles.size());
		assertTrue(roles.get(0).equalsIgnoreCase("PATIENT"));
	}

	@Test
	public void testNewUserAvailableRolesForADoctorArePATIENT() {
		List<String> roles = RoleHelper
				.userCanAddNewUsersWithRoles(RoleEnum.ROLE_DOCTOR);

		assertEquals(1, roles.size());
		assertTrue(roles.get(0).equalsIgnoreCase("PATIENT"));
	}

	@Test
	public void testNewUserAvailableRolesForAdminArePATIENTDOCTORandADMIN() {
		List<String> roles = RoleHelper
				.userCanAddNewUsersWithRoles(RoleEnum.ROLE_ADMIN);

		assertEquals(3, roles.size());
		assertTrue(roles.get(0).equalsIgnoreCase("ADMIN"));
		assertTrue(roles.get(1).equalsIgnoreCase("DOCTOR"));
		assertTrue(roles.get(2).equalsIgnoreCase("PATIENT"));
	}

}
