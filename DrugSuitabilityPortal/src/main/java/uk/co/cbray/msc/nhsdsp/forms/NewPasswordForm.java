package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class NewPasswordForm implements IForm{
	private String password;
	private String passwordConfirmation;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
		
		// Do nothing, not required
		
	}
	
}
