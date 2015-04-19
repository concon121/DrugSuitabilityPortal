package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;

public class AllergyHelper {

	public static List<PatientAllergy> findAllergiesToRemove(User user,
			PatientDetailForm form) {
		List<PatientAllergy> toRemove = new ArrayList<PatientAllergy>();
		List<PatientAllergy> patientAllergies = new ArrayList<PatientAllergy>();
		patientAllergies.addAll(user.getPatientAllergies());
		String [] formAllergies = form.getAllergiesAsArray();
		
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
