package uk.co.cbray.msc.nhsdsp.exceptions;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;

public class InvalidEntityConversionTypeException extends Exception {
	
	private static final long serialVersionUID = 6006060760574762716L;

	public <E extends IEntity> InvalidEntityConversionTypeException(Class<E> expected, Class<?> actual) {
		super("Expected object of type: " + expected + " but was: " + actual);
	}
	
}
