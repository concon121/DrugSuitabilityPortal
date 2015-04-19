package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class NewAssessmentForm implements IForm{
	
	public String illnessName;
	public Integer userId;
	
	public String getIllnessName() {
		return illnessName;
	}
	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
		// Not required
		
	}
	
	

}
