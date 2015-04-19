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
 * The persistent class for the DRUG_ALLERGY database table.
 * 
 */
@Entity
@Table(name="DRUG_ALLERGY")
@NamedQuery(name="DrugAllergy.findAll", query="SELECT d FROM DrugAllergy d")
public class DrugAllergy implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUG_ALLERGY_ID_GENERATOR", sequenceName="DRUG_ALLERGY_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUG_ALLERGY_ID_GENERATOR")
	private BigDecimal id;

	private String allergy;

	//bi-directional many-to-one association to Drug
	@ManyToOne
	private Drug drug;

	public DrugAllergy() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAllergy() {
		return this.allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public Drug getDrug() {
		return this.drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

}