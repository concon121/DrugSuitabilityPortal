package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class NewDrugForm implements IForm{
	
	private String name;
	private String description;
	private String allergies;
	private String effects;
	
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
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getEffects() {
		return effects;
	}
	public void setEffects(String effects) {
		this.effects = effects;
	}
	
	public String[] getAllergiesAsArray() {
		String [] allergyArray = getAllergies().split(",");
		for (int i = 0; i< allergyArray.length; i++) {
			allergyArray[i] = allergyArray[i].trim();
		}
		return allergyArray;
	}
	
	public String[] getEffectsAsArray() {
		String [] effectArray = getEffects().split(",");
		for (int i = 0; i< effectArray.length; i++) {
			effectArray[i] = effectArray[i].trim();
		}
		return effectArray;
	}
	
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {

		// Do nothing, not required
		
	}
	
	
}
