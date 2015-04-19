package uk.co.cbray.msc.nhsdsp.forms;

import java.util.Calendar;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;


public class UserForm implements IForm{
	private static final long serialVersionUID = 1L;

	private String dob;

	private String emailAddress;

	private String forename;

	private String surname;
	
	private String roleName;
	
	private String username;
	
	private String password;
	
	private String passwordConfirmation;
	
	
	public UserForm() {
	}

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public <E extends IEntity> void copyFrom(E entity) throws InvalidEntityConversionTypeException{
		if (entity instanceof User) {
			User user = (User)entity;
			setDob(user.getDob().get(Calendar.DATE) + "/" + (user.getDob().get(Calendar.MONTH)+1) + "/" + user.getDob().get(Calendar.YEAR));
			setEmailAddress(user.getEmailAddress());
			setForename(user.getForename());
			setSurname(user.getSurname());
		} else {
			throw new InvalidEntityConversionTypeException(User.class, entity.getClass());
		}
		
	}
	
	
	

}