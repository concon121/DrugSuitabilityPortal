package uk.co.cbray.msc.nhsdsp.dao;

/**
 * Enum used to store error codes and messages for database related exceptions.
 * 
 * @author Connor Bray
 */
public enum DataAccessErrorCode {

	INVALID_PASSWORD("DAE01", "No user login for for the provided username.");

	private String code;
	private String message;

	private DataAccessErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
