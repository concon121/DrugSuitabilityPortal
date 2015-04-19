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


@Entity
@NamedQuery(name="Diagnosis.findAll", query="SELECT i FROM Diagnosis i")
public class Diagnosis implements Serializable, IEntity {
	
	@Id
	@SequenceGenerator(name="DIAGNOSIS_ID_GENERATOR", sequenceName="DIAGNOSIS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIAGNOSIS_ID_GENERATOR")
	private BigDecimal id;
	
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="ILLNESS_ID")
	private Integer illnessId;
	
	public Integer getUser() {
		return userId;
	}

	public void setUser(Integer userId) {
		this.userId = userId;
	}

	public Integer getIllness() {
		return illnessId;
	}

	public void setIllness(Integer illnessId) {
		this.illnessId = illnessId;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getId() {
		return id;
	}

}
