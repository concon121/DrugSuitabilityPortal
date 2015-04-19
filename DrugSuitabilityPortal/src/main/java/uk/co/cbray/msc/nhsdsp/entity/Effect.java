package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * The persistent class for the EFFECT database table.
 * 
 */
@Entity
@NamedQuery(name = "Effect.findAll", query = "SELECT e FROM Effect e")
@Indexed
public class Effect implements Serializable, IEntity, Searchable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "EFFECT_ID_GENERATOR", sequenceName = "EFFECT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EFFECT_ID_GENERATOR")
	private BigDecimal id;
	@Field
	private String description;
	@Field
	private String name;
	@Transient
	private String displayName;

	// bi-directional many-to-one association to Incident
	@OneToMany(mappedBy = "effect", fetch = FetchType.LAZY)
	private Set<Incident> incidents;

	public Effect() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Incident> getIncidents() {
		return this.incidents;
	}

	public void setIncidents(Set<Incident> incidents) {
		this.incidents = incidents;
	}

	public Incident addIncident(Incident incident) {
		getIncidents().add(incident);
		incident.setEffect(this);

		return incident;
	}

	public Incident removeIncident(Incident incident) {
		getIncidents().remove(incident);
		incident.setEffect(null);

		return incident;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String[] getFields() {
		String[] fields = { "name", "description" };
		return fields;
	}

}