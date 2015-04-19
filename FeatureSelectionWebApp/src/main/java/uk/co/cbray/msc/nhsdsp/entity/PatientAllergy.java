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
 * The persistent class for the PATIENT_ALLERGIES database table.
 * 
 */
@Entity
@Table(name="PATIENT_ALLERGIES")
@NamedQuery(name="PatientAllergy.findAll", query="SELECT p FROM PatientAllergy p")
public class PatientAllergy implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PATIENT_ALLERGIES_ID_GENERATOR", sequenceName="PATIENT_ALLERGIES_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PATIENT_ALLERGIES_ID_GENERATOR")
	private BigDecimal id;

	private String allergy;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public PatientAllergy() {
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}