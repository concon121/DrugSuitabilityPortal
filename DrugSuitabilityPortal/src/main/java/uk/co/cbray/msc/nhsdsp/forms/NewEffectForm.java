package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class NewEffectForm implements IForm{
	private String name;
	private String description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
		// not required
		
	}
	
	
}
