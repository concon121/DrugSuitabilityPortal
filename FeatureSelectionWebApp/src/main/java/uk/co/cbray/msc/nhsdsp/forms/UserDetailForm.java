package uk.co.cbray.msc.nhsdsp.forms;

import java.util.Calendar;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;


public class UserDetailForm implements IForm, IUserForm{
	
	private String dob;

	private String emailAddress;

	private String forename;

	private String surname;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
