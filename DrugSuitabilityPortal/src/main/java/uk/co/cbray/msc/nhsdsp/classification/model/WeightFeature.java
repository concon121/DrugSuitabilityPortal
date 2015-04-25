package uk.co.cbray.msc.nhsdsp.classification.model;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.NumericalFeatureImpl;

/**
 * A feature used in the classification of an Incident. The WeightFeature
 * describes the patients weight.
 * 
 * @author Connor Bray
 */
public class WeightFeature extends NumericalFeatureImpl {

	private static final Logger LOG = LoggerFactory
			.getLogger(WeightFeature.class);

	public WeightFeature(BigDecimal weight) {
		try {
			setValue(weight);
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set numerical value weight.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "weight";
	}

}
