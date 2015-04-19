package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@Indexed
public class User implements Serializable, IEntity, Searchable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="USERS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	private BigDecimal id;

	@Temporal(TemporalType.DATE)
	private Calendar dob;

	@Column(name="EMAIL_ADDRESS")
	@Field
	private String emailAddress;

	@Field
	private String forename;

	@Field
	private String surname;
	
	@Column(name="ROLE_NAME")
	private String roleName;
	
	@Transient
	private String displayName;

	//bi-directional many-to-one association to DrugUserSuitability
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<DrugUserSuitability> drugUserSuitabilities;

	//bi-directional many-to-one association to Incident
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<Incident> incidents;

	//bi-directional many-to-one association to PatientAllergy
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<PatientAllergy> patientAllergies;

	//bi-directional many-to-one association to PatientDetail
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<PatientDetail> patientDetails;

	//bi-directional many-to-one association to UserLogin
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private Set<UserLogin> userLogins;

	public User() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Calendar getDob() {
		return this.dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	public void setDob(String dob) {
		
		String [] elements = dob.split("-");
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Integer.parseInt(elements[0]), (Integer.parseInt(elements[1])-1), Integer.parseInt(elements[2]));
		this.dob = gc;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<DrugUserSuitability> getDrugUserSuitabilities() {
		return this.drugUserSuitabilities;
	}

	public void setDrugUserSuitabilities(Set<DrugUserSuitability> drugUserSuitabilities) {
		this.drugUserSuitabilities = drugUserSuitabilities;
	}

	public DrugUserSuitability addDrugUserSuitability(DrugUserSuitability drugUserSuitability) {
		getDrugUserSuitabilities().add(drugUserSuitability);
		drugUserSuitability.setUser(this);

		return drugUserSuitability;
	}

	public DrugUserSuitability removeDrugUserSuitability(DrugUserSuitability drugUserSuitability) {
		getDrugUserSuitabilities().remove(drugUserSuitability);
		drugUserSuitability.setUser(null);

		return drugUserSuitability;
	}

	public Set<Incident> getIncidents() {
		return this.incidents;
	}

	public void setIncidents(Set<Incident> incidents) {
		this.incidents = incidents;
	}

	public Incident addIncident(Incident incident) {
		getIncidents().add(incident);
		incident.setUser(this);

		return incident;
	}

	public Incident removeIncident(Incident incident) {
		getIncidents().remove(incident);
		incident.setUser(null);

		return incident;
	}

	public Set<PatientAllergy> getPatientAllergies() {
		if (this.patientAllergies == null) {
			this.patientAllergies = new HashSet<PatientAllergy>();
		}
		return this.patientAllergies;
	}

	public void setPatientAllergies(Set<PatientAllergy> patientAllergies) {
		this.patientAllergies = patientAllergies;
	}

	public PatientAllergy addPatientAllergy(PatientAllergy patientAllergy) {
		getPatientAllergies().add(patientAllergy);
		patientAllergy.setUser(this);

		return patientAllergy;
	}

	public PatientAllergy removePatientAllergy(PatientAllergy patientAllergy) {
		getPatientAllergies().remove(patientAllergy);
		patientAllergy.setUser(null);

		return patientAllergy;
	}

	public Set<PatientDetail> getPatientDetails() {
		if (this.patientDetails == null) {
			this.patientDetails = new HashSet<PatientDetail>();
		}
		return this.patientDetails;
	}

	public void setPatientDetails(Set<PatientDetail> patientDetails) {
		this.patientDetails = patientDetails;
	}

	public PatientDetail addPatientDetail(PatientDetail patientDetail) {
		getPatientDetails().add(patientDetail);
		patientDetail.setUser(this);

		return patientDetail;
	}

	public PatientDetail removePatientDetail(PatientDetail patientDetail) {
		getPatientDetails().remove(patientDetail);
		patientDetail.setUser(null);

		return patientDetail;
	}

	public Set<UserLogin> getUserLogins() {
		return this.userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

	public UserLogin addUserLogin(UserLogin userLogin) {
		
		if (userLogins == null) {
			userLogins = new HashSet<UserLogin>();
		}
		
		getUserLogins().add(userLogin);
		userLogin.setUser(this);

		return userLogin;
	}

	public UserLogin removeUserLogin(UserLogin userLogin) {
		getUserLogins().remove(userLogin);
		userLogin.setUser(null);

		return userLogin;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String[] getFields() {
		String [] fields = {"forename", "surname", "emailAddress"};
		return fields;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		
	}

	public void setPatientDetails(List<PatientDetail> patientDetails2) {
		if (this.patientDetails == null) {
			this.patientDetails = new HashSet<PatientDetail>();
		}
		
		this.patientDetails.addAll(patientDetails2);
		
	}

	public void setPatientAllergies(List<PatientAllergy> patientAllergies2) {
		if (this.patientAllergies == null) {
			this.patientAllergies = new HashSet<PatientAllergy>();
		}
		this.patientAllergies.addAll(patientAllergies2);
		
	}
	
	

}