package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public interface IForm {
	public <E extends IEntity> void copyFrom(E entity) throws InvalidEntityConversionTypeException;
}
