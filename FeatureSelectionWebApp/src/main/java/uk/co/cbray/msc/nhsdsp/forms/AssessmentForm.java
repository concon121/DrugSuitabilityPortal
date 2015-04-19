package uk.co.cbray.msc.nhsdsp.forms;

import java.math.BigDecimal;

import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class AssessmentForm implements IForm{
	
	private String drug;
	private BigDecimal incompatibility;
	private String effect;
	
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public BigDecimal getIncompatibility() {
		return incompatibility;
	}
	public void setIncompatibility(BigDecimal incompatibility) {
		this.incompatibility = incompatibility;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {

		if (entity instanceof DrugUserSuitability) {
			DrugUserSuitability dus = (DrugUserSuitability) entity;
			setDrug(dus.getDrug().getName());
			setIncompatibility(dus.getIncompatibility());
			setEffect(dus.getEffect().getName());
		} else {
			throw new InvalidEntityConversionTypeException(DrugUserSuitability.class, entity.getClass());
		}
		
	}
	
	
}
