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
@NamedQuery(name="DrugEffect.findAll", query="SELECT e FROM DrugEffect e")
@Table(name="DRUG_EFFECT")
public class DrugEffect implements Serializable, IEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUG_EFFECT_ID_GENERATOR", sequenceName="DRUG_EFFECT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUG_EFFECT_ID_GENERATOR")
	private BigDecimal id;
	
	@Column(name="DRUG_ID")
	private Integer drugId;
	
	@Column(name="EFFECT_ID")
	private Integer effectid;

	public Integer getDrugId() {
		return drugId;
	}

	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public Integer getEffectid() {
		return effectid;
	}

	public void setEffectid(Integer effectid) {
		this.effectid = effectid;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getId() {
		return id;
	}
	
}
