package uk.co.cbray.msc.nhsdsp.classification.model;

import uk.co.cbray.msc.ml4j.model.InstanceImpl;
import uk.co.cbray.msc.nhsdsp.entity.Drug;

/**
 * An extension of the Classification4J InstanceImpl class. Created to make
 * obvious that the Incident is the instance. This class also keeps track of the
 * drug associated with the incident.
 * 
 * @author Connor Bray
 */
public class IncidentInstance extends InstanceImpl {

	private Drug drug;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

}
