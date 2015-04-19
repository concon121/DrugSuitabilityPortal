package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the PATIENT_DETAILS database table.
 * 
 */
@Entity
@Table(name="PATIENT_DETAILS")
@NamedQuery(name="PatientDetail.findAll", query="SELECT p FROM PatientDetail p")
public class PatientDetail implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PATIENT_DETAILS_ID_GENERATOR", sequenceName="PATIENT_DETAILS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_DETAILS_ID_GENERATOR")
	private BigDecimal id;

	private String ethnicity;

	private BigDecimal height;

	private BigDecimal weight;
	
	private int smoker;
	
	private int diabetes;
	
	private String gender;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	public PatientDetail() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int isSmoker() {
		return smoker;
	}

	public void setSmoker(int smoker) {
		if (smoker > 1) {
			this.smoker = 1;
		} else {
			this.smoker = smoker;
		}
	}

	public int isDiabetes() {
		return diabetes;
	}

	public void setDiabetes(int diabetes) {
		if (diabetes > 1) {
			this.diabetes = 1;
		} else {
			this.diabetes = diabetes;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}