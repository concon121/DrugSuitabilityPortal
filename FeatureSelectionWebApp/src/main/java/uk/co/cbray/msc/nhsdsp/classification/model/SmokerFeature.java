package uk.co.cbray.msc.nhsdsp.classification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.BinaryFeatureImpl;

public class SmokerFeature extends BinaryFeatureImpl {

	private static final Logger LOG = LoggerFactory
			.getLogger(SmokerFeature.class);
	
	public SmokerFeature(int smoker) {
		try {
			if (smoker >= 1) {
				setValue(true);
			} else {
				setValue(false);
			}
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set boolean value smoker.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "smoker";
	}
	
}
