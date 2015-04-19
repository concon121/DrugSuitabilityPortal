package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the DRUG_USER_SUITABILITY database table.
 * 
 */
@Entity
@Table(name="DRUG_USER_SUITABILITY")
@NamedQuery(name="DrugUserSuitability.findAll", query="SELECT d FROM DrugUserSuitability d")
public class DrugUserSuitability implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUG_USER_SUITABILITY_ID_GENERATOR", sequenceName="DRUG_USER_SUITABILITY_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUG_USER_SUITABILITY_ID_GENERATOR")
	private BigDecimal id;

	private BigDecimal incompatibility;

	//bi-directional many-to-one association to Drug
	@ManyToOne
	private Drug drug;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Effect effect;

	public DrugUserSuitability() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIncompatibility() {
		return this.incompatibility;
	}

	public void setIncompatibility(BigDecimal incompatibility) {
		this.incompatibility = incompatibility;
	}

	public Drug getDrug() {
		return this.drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	

}