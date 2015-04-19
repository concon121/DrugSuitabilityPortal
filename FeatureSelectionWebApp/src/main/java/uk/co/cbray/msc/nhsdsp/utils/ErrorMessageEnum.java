package uk.co.cbray.msc.nhsdsp.utils;

public enum ErrorMessageEnum {

	INVALID_EMAIL("Please enter a valid email address."),
	UNKNOWN_USER("Unknown user specified."),
	UNKNOWN_ETHNICITY("Unknown ethnicity specified."),
	USERNAME_ALREADY_EXISTS("The chosen username has already been taken, please choose a different one."),
	PASSWORDS_DO_NOT_MATCH("The passwords entered do not match, please try again."),
	INCOMPLETE_FORM("The form is incomplete, please enter all required information."),
	UNKNOWN_GENDER("Unknown gender specified."),
	NO_RESULTS("No results found, please try again.");
	
	private String message;
	
	private ErrorMessageEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
