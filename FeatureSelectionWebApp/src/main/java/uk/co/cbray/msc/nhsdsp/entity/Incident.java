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


/**
 * The persistent class for the INCIDENT database table.
 * 
 */
@Entity
@NamedQuery(name="Incident.findAll", query="SELECT i FROM Incident i")
public class Incident implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INCIDENT_ID_GENERATOR", sequenceName="INCIDENT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INCIDENT_ID_GENERATOR")
	private BigDecimal id;

	//bi-directional many-to-one association to Drug
	@ManyToOne
	private Drug drug;

	//bi-directional many-to-one association to Effect
	@ManyToOne
	private Effect effect;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Incident() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Drug getDrug() {
		return this.drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Effect getEffect() {
		return this.effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}