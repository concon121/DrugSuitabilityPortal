package uk.co.cbray.msc.nhsdsp.forms;

import java.math.BigDecimal;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class ViewEffect implements IForm{
	
	public BigDecimal effectId;

	public BigDecimal getEffectId() {
		return effectId;
	}

	public void setEffectId(BigDecimal effectId) {
		this.effectId = effectId;
	}

	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
		// not required
		
	}

}
