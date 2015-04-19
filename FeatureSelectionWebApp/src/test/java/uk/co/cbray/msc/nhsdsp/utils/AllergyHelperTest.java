package uk.co.cbray.msc.nhsdsp.utils;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class AllergyHelperTest {

	@Test
	public void testFindAllergiesToRemoveReturnsTheCorrectResults() {
		User user = TestFactory.getUserInstance();
		PatientDetailForm form = TestFactory.getPatientDetailFormInstance();
		
		List<PatientAllergy> userAllergy = new ArrayList<PatientAllergy>();
		PatientAllergy one = TestFactory.getPatientAllergyInstance("one");
		PatientAllergy two = TestFactory.getPatientAllergyInstance("two");
		PatientAllergy three = TestFactory.getPatientAllergyInstance("three");
		PatientAllergy four = TestFactory.getPatientAllergyInstance("four");
		PatientAllergy five = TestFactory.getPatientAllergyInstance("five");
		PatientAllergy six = TestFactory.getPatientAllergyInstance("six");
		PatientAllergy seven = TestFactory.getPatientAllergyInstance("seven");
		PatientAllergy eight = TestFactory.getPatientAllergyInstance("eight");
		
		userAllergy.add(one);
		userAllergy.add(two);
		userAllergy.add(three);
		userAllergy.add(four);
		userAllergy.add(five);
		userAllergy.add(six);
		userAllergy.add(seven);
		userAllergy.add(eight);
		
		user.setPatientAllergies(userAllergy);
		
		List<PatientAllergy> toRemove = AllergyHelper.findAllergiesToRemove(user, form);
		
		boolean pass = true;
		// Expect six, seven and eight to be in toRemove
		for (PatientAllergy allergy : toRemove) {
			
			if (!allergy.getAllergy().equalsIgnoreCase(six.getAllergy()) &&
				!allergy.getAllergy().equalsIgnoreCase(seven.getAllergy()) &&
				!allergy.getAllergy().equalsIgnoreCase(eight.getAllergy())) {
				pass = false;
				fail();
			}
			
		}
		
		assertTrue(pass);
		assertEquals(3, toRemove.size());
	}
	
}
