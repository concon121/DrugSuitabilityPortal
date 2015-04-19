package uk.co.cbray.msc.nhsdsp.forms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;


public class PatientDetailForm implements IForm{
	private static final long serialVersionUID = 1L;

	private String ethnicity;

	private BigDecimal height;

	private BigDecimal weight;
	
	private String smoker;
	
	private String diabetes;
	
	private String allergies;
	
	private String gender;
	
	public PatientDetailForm() {
	}

	public String getEthnicity() {
		return this.ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getSmoker() {
		return smoker;
	}

	public void setSmoker(String smoker) {
		this.smoker = smoker;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}
	
	public String[] getAllergiesAsArray() {
		String [] allergyArray = getAllergies().split(",");
		for (int i = 0; i< allergyArray.length; i++) {
			allergyArray[i] = allergyArray[i].trim();
		}
		return allergyArray;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {
		
		// Do nothing, too complex, leave to converter
		
	}
	
	

}