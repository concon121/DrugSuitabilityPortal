package uk.co.cbray.msc.nhsdsp.classification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.LiteralFeatureImpl;

/**
 * A feature used in the classification of an Incident. The GenderFeature
 * describes the patients gender.
 * 
 * @author Connor Bray
 */
public class GenderFeature extends LiteralFeatureImpl {

	private static final Logger LOG = LoggerFactory
			.getLogger(GenderFeature.class);

	public GenderFeature(String gender) {
		try {
			setValue(gender);
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set literal value gender.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "gender";
	}

}
