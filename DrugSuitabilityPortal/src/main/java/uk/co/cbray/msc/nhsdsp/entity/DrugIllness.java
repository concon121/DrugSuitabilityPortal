package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQuery(name="DrugIllness.findAll", query="SELECT i FROM DrugIllness i")
@Table(name="DRUG_ILLNESS")
public class DrugIllness implements Serializable, IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUG_ILLNESS_ID_GENERATOR", sequenceName="DRUG_ILLNESS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUG_ILLNESS_ID_GENERATOR")
	private BigDecimal id;
	
	@Column(name="DRUG_ID")
	private Integer drugId;
	
	@Column(name="ILLNESS_ID")
	private Integer illnessId;
	
	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public Integer getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(Integer illnessId) {
		this.illnessId = illnessId;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public BigDecimal getBigId() {
		return id;
	}

	public Object getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
