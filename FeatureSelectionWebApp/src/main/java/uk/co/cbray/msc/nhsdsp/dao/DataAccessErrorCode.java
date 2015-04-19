package uk.co.cbray.msc.nhsdsp.dao;

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
