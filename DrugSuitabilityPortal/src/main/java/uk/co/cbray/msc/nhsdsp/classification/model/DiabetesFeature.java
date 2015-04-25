package uk.co.cbray.msc.nhsdsp.classification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.BinaryFeatureImpl;

/**
 * A feature used in the classification of an Incident. The DiabetesFeature describes
 * whether the patient has diabetes.
 * 
 * @author Connor Bray
 */
public class DiabetesFeature extends BinaryFeatureImpl {

	private static final Logger LOG = LoggerFactory
			.getLogger(DiabetesFeature.class);

	public DiabetesFeature(int diabetes) {
		try {
			if (diabetes >= 1) {
				setValue(true);
			} else {
				setValue(false);
			}
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set boolean value diabetes.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "diabetes";
	}

}
