package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;
/**
 * Enum to identify gender.
 * 
 * @author Connor Bray
 */
public enum GenderEnum {
	
	MALE("MALE"),
	FEMALE("FEMALE");
	
	private String name;
	
	private GenderEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<String> getAvailableGenders() {
		
		List<String> availableGenders = new ArrayList<String>();
		
		for (GenderEnum gender: GenderEnum.values()) {
			availableGenders.add(gender.getName());
		}
		
		return availableGenders;
	}
	
}
