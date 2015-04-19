package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@NamedQuery(name="Illness.findAll", query="SELECT i FROM Illness i")
@Indexed
public class Illness implements Serializable, IEntity, Searchable {
	
	@Id
	@SequenceGenerator(name="ILLNESS_ID_GENERATOR", sequenceName="ILLNESS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ILLNESS_ID_GENERATOR")
	private BigDecimal id;
	
	@Field
	private String name;
	
	@Field
	private String description;
	
	@Transient
	private String displayName;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getId() {
		return id;
	}

	public String[] getFields() {
		String [] fields = {"name", "description"};
		return fields;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
