package uk.co.cbray.msc.nhsdsp.classification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.LiteralFeatureImpl;

/**
 * A feature used in the classification of an Incident. The AllergyFeature describes
 * one of the patients allergies.
 * 
 * @author Connor Bray
 */
public class AllergyFeature extends LiteralFeatureImpl {
	
private static final Logger LOG = LoggerFactory.getLogger(AgeFeature.class);
	
	public AllergyFeature(String allergy) {
		try {
			setValue(allergy);
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set alergy.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "allergy";
	}

}
