package uk.co.cbray.msc.nhsdsp.classification.model;

import uk.co.cbray.msc.ml4j.model.InstanceImpl;
import uk.co.cbray.msc.nhsdsp.entity.Drug;

public class IncidentInstance extends InstanceImpl{
	
	private Drug drug;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	
	
	
}
