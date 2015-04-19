package uk.co.cbray.msc.nhsdsp.classification.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.model.LiteralFeatureImpl;

public class EthnicityFeature extends LiteralFeatureImpl {
	
	private static final Logger LOG = LoggerFactory
			.getLogger(EthnicityFeature.class);
	
	public EthnicityFeature(String ethnicity) {
		try {
			setValue(ethnicity);
		} catch (InvalidArgumentException e) {
			LOG.error("Unable to set literal value ethnicity.", e);
		}
	}

	@Override
	public String getFeatureName() {
		return "ethnicity";
	}
	
}
