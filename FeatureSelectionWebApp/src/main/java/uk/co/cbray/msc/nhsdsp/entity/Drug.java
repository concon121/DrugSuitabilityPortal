package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
 * The persistent class for the DRUG database table.
 * 
 */
@Entity
@NamedQuery(name="Drug.findAll", query="SELECT d FROM Drug d")
@Indexed
public class Drug implements Serializable, IEntity, Searchable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DRUG_ID_GENERATOR", sequenceName="DRUG_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DRUG_ID_GENERATOR")
	private BigDecimal id;

	@Field
	private String description;

	@Field
	private String name;

	@Transient
	private String displayName;
	
	//bi-directional many-to-one association to DrugAllergy
	@OneToMany(mappedBy="drug", fetch=FetchType.EAGER)
	private List<DrugAllergy> drugAllergies;

	//bi-directional many-to-one association to DrugUserSuitability
	@OneToMany(mappedBy="drug", fetch=FetchType.LAZY)
	private List<DrugUserSuitability> drugUserSuitabilities;

	//bi-directional many-to-one association to Incident
	@OneToMany(mappedBy="drug", fetch=FetchType.LAZY)
	private List<Incident> incidents;

	public Drug() {
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

	public List<DrugAllergy> getDrugAllergies() {
		return this.drugAllergies;
	}

	public void setDrugAllergies(List<DrugAllergy> drugAllergies) {
		this.drugAllergies = drugAllergies;
	}

	public DrugAllergy addDrugAllergy(DrugAllergy drugAllergy) {
		getDrugAllergies().add(drugAllergy);
		drugAllergy.setDrug(this);

		return drugAllergy;
	}

	public DrugAllergy removeDrugAllergy(DrugAllergy drugAllergy) {
		getDrugAllergies().remove(drugAllergy);
		drugAllergy.setDrug(null);

		return drugAllergy;
	}

	public List<DrugUserSuitability> getDrugUserSuitabilities() {
		return this.drugUserSuitabilities;
	}

	public void setDrugUserSuitabilities(List<DrugUserSuitability> drugUserSuitabilities) {
		this.drugUserSuitabilities = drugUserSuitabilities;
	}

	public DrugUserSuitability addDrugUserSuitability(DrugUserSuitability drugUserSuitability) {
		getDrugUserSuitabilities().add(drugUserSuitability);
		drugUserSuitability.setDrug(this);

		return drugUserSuitability;
	}

	public DrugUserSuitability removeDrugUserSuitability(DrugUserSuitability drugUserSuitability) {
		getDrugUserSuitabilities().remove(drugUserSuitability);
		drugUserSuitability.setDrug(null);

		return drugUserSuitability;
	}

	public List<Incident> getIncidents() {
		return this.incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public Incident addIncident(Incident incident) {
		getIncidents().add(incident);
		incident.setDrug(this);

		return incident;
	}

	public Incident removeIncident(Incident incident) {
		getIncidents().remove(incident);
		incident.setDrug(null);

		return incident;
	}

	public String[] getFields() {
		String [] fields = {"name", "description"};
		return fields;
	}

	public String getDisplayName() {
		// TODO Auto-generated method stub
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		
	}

}