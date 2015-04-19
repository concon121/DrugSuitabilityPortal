package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on 2011 Brittish Census
 * @author connor
 *
 */
public enum EthnicityEnum {

	WHITE("White"),
	GYPSY("Gypsy/Traveller/ Irish Traveller"),
	INDIAN("Asian or Asian British: Indian"),
	PAKISTANI("Asian or Asian British: Pakistani"),
	BANGLADESHI("Asian or Asian British: Bangladeshi"),
	CHINESE("Asian or Asian British: Chinese"),
	OTHER_ASIAN("Asian or Asian British: Other Asian"),
	OTHER("Other");
	
	private String description;
	
	private EthnicityEnum (String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static List<String> getAvailableEthnicities() {
		
		List<String> availableEthnicities = new ArrayList<String>();
		
		for (EthnicityEnum ethnicity : EthnicityEnum.values()) {
			availableEthnicities.add(ethnicity.getDescription());
		}
		
		return availableEthnicities;
	}
	
}
