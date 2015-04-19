package uk.co.cbray.msc.nhsdsp.forms;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;

public class IncidentForm implements IForm{

	private String drug;
	private String effect;
	
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
	
	
	public <E extends IEntity> void copyFrom(E entity)
			throws InvalidEntityConversionTypeException {

		if (entity instanceof Incident) {
			Incident incident = (Incident)entity;
			setDrug(incident.getDrug().getName());
			setEffect(incident.getEffect().getName());
		} else {
			throw new InvalidEntityConversionTypeException(Incident.class, entity.getClass());
		}
		
	}
	
	
	
}
