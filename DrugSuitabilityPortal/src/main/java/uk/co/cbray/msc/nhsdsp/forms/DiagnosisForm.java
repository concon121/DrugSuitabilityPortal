package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;


public class DiagnosisForm implements IForm {
	
	private Integer userId;
	private String illnessName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getIllnessName() {
		return illnessName;
	}
	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
			
		// do nothing, not required.
		
	}
	
	

}
