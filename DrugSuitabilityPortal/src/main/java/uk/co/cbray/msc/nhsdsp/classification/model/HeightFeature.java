package uk.co.cbray.msc.nhsdsp.classification.model;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.NumericalFeatureImpl;

/**
 * A feature used in the classification of an Incident. The HeightFeature
 * describes the patients height.
 * 
 * @author Connor Bray
 */
public class HeightFeature extends NumericalFeatureImpl {

	private static final Logger LOG = LoggerFactory
			.getLogger(HeightFeature.class);

	public HeightFeature(BigDecimal height) {
		try {
			setValue(height);
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set numerical value height.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "height";
	}

}
