package uk.co.cbray.msc.nhsdsp.exceptions;

public class InvalidParameterException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidParameterException(Object parameter, String msg) {
		super(msg + ": " + parameter.toString());
	}
	
}
