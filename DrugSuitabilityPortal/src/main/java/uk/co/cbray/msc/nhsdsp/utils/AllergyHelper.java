package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;

/**
 * Helper class for all allergy realted functions.
 * 
 * @author Connor Bray.
 */
public class AllergyHelper {

	/**
	 * Find which allergies to remove from a user, given the current set of
	 * allergies, and the new set of allergies.
	 * 
	 * @param user
	 *            The User object containing the current set of allergies.
	 * @param form
	 *            The PatientDetailForm containing the new set of allergies.
	 * @return The list of allergies to remove.
	 */
	public static List<PatientAllergy> findAllergiesToRemove(User user,
			PatientDetailForm form) {
		List<PatientAllergy> toRemove = new ArrayList<PatientAllergy>();
		List<PatientAllergy> patientAllergies = new ArrayList<PatientAllergy>();
		patientAllergies.addAll(user.getPatientAllergies());
		String[] formAllergies = form.getAllergiesAsArray();

		// Remove old allergies
		for (PatientAllergy allergy : patientAllergies) {
			if (allergy != null && allergy.getAllergy() != null) {
				String thisAllergy = allergy.getAllergy().trim();
				boolean found = false;
				for (String formAllergy : formAllergies) {
					if (formAllergy != null) {
						if (formAllergy.trim().equalsIgnoreCase(thisAllergy)) {
							found = true;
						}
					}
				}
				if (!found) {
					toRemove.add(allergy);
				}
			} else {
				toRemove.add(allergy);
			}

		}

		return toRemove;
	}

}
