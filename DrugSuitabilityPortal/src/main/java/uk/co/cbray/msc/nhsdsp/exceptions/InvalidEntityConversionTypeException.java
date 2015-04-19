package uk.co.cbray.msc.nhsdsp.exceptions;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;

public class InvalidEntityConversionTypeException extends Exception {
	
	public <E extends IEntity> InvalidEntityConversionTypeException(Class<E> expected, Class<?> actual) {
		super("Expected object of type: " + expected + " but was: " + actual);
	}
	
}
