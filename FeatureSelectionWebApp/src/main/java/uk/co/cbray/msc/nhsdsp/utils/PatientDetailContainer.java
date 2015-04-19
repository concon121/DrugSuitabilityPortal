package uk.co.cbray.msc.nhsdsp.utils;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;

public class PatientDetailContainer {
	
	private PatientDetail detail;
	private PatientAllergy allergy;
	
	public PatientDetail getDetail() {
		return detail;
	}
	public void setDetail(PatientDetail detail) {
		this.detail = detail;
	}
	public PatientAllergy getAllergy() {
		return allergy;
	}
	public void setAllergy(PatientAllergy allergy) {
		this.allergy = allergy;
	}
	
}
