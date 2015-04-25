package uk.co.cbray.msc.nhsdsp.classification.model;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.NumericalFeatureImpl;
import uk.co.cbray.msc.nhsdsp.utils.AgeHelper;

/**
 * A feature used in the classification of an Incident. The AgeFeature describes
 * a patients age.
 * 
 * @author Connor Bray
 */
public class AgeFeature extends NumericalFeatureImpl {

	private static final Logger LOG = LoggerFactory.getLogger(AgeFeature.class);

	public AgeFeature(Calendar dob) {
		try {
			setValue(AgeHelper.calculateAge(dob));
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set age.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "age";
	}

}
