package uk.co.cbray.msc.nhsdsp.test.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugIllness;
import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.DiagnosisForm;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.NewEffectForm;
import uk.co.cbray.msc.nhsdsp.forms.NewPasswordForm;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.SearchForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.ViewEffect;
import uk.co.cbray.msc.nhsdsp.forms.ViewIllnessForm;
import uk.co.cbray.msc.nhsdsp.utils.EthnicityEnum;
import uk.co.cbray.msc.nhsdsp.utils.RoleEnum;
import uk.co.cbray.msc.nhsdsp.utils.SecurityContextHelper;

public class TestFactory {

	public static User getUserInstance() {
		User user = new User();
		user.setForename("Test");
		user.setSurname("User");
		user.setDob(new GregorianCalendar());
		user.setEmailAddress("abc@bca.com");
		user.setId(new BigDecimal(1));
		user.setRoleName("TEST_ROLE");

		return user;
	}

	public static List<User> getUserListInstance() {
		List<User> users = new ArrayList<User>();

		users.add(getUserInstance());
		users.add(getUserInstance());
		users.add(getUserInstance());
		users.add(getUserInstance());
		users.add(getUserInstance());

		return users;
	}

	public static PatientDetailForm getPatientDetailFormInstance() {
		PatientDetailForm form = new PatientDetailForm();

		form.setDiabetes("Y");
		form.setEthnicity(EthnicityEnum.CHINESE.getDescription());
		form.setGender("MALE");
		form.setHeight(new BigDecimal(150));
		form.setWeight(new BigDecimal(100));
		form.setSmoker("Y");
		form.setAllergies("one, two, three, four, five");

		return form;
	}

	public static PatientAllergy getPatientAllergyInstance(String name) {
		PatientAllergy allergy = new PatientAllergy();
		allergy.setAllergy(name);
		allergy.setUser(getUserInstance());
		return allergy;
	}

	public static Drug getDrugInstance() {
		Drug drug = new Drug();

		drug.setDescription("Test description");
		drug.setName("test name");
		

		return drug;
	}

	public static List<Drug> getDrugListInstance() {
		List<Drug> drugs = new ArrayList<Drug>();

		drugs.add(getDrugInstance());
		drugs.add(getDrugInstance());
		drugs.add(getDrugInstance());
		drugs.add(getDrugInstance());
		drugs.add(getDrugInstance());

		return drugs;
	}

	public static Illness getIllnessInstance() {
		Illness i = new Illness();

		i.setName("test illness");
		i.setDescription("test description");
		i.setId(new BigDecimal(1));

		return i;
	}

	public static List<Illness> getIllnessListInstance() {
		List<Illness> illnesses = new ArrayList<Illness>();

		illnesses.add(getIllnessInstance());
		illnesses.add(getIllnessInstance());
		illnesses.add(getIllnessInstance());
		illnesses.add(getIllnessInstance());
		illnesses.add(getIllnessInstance());

		return illnesses;
	}

	public static UserDetailForm getInvalidUserDetailFormInstance() {
		return new UserDetailForm();
	}

	public static UserForm getInvalidUserFormInstance() {
		return new UserForm();
	}

	public static UserForm getValidUserFormInstance() {
		UserForm form = new UserForm();
		form.setEmailAddress("abc@bca.com");
		form.setDob("11/11/2011");
		form.setForename("test");
		form.setSurname("test");
		form.setPassword("test");
		form.setPasswordConfirmation("test");
		form.setRoleName("PATIENT");
		form.setUsername("test_user_1");
		return form;
	}

	public static MockSecurityContext getSecurityContextInstance() {
		MockSecurityContext mockSecurityContext = new MockSecurityContext();
		MockAuthentication mockAuth = new MockAuthentication();
		mockAuth.setName(SecurityContextHelper.ANONYMOUS);
		mockAuth.setAuthenticated(false);

		return mockSecurityContext;
	}

	public static UserDetailForm getValidUserDetailFormInstance() {
		UserDetailForm form = new UserDetailForm();

		form.setForename("test");
		form.setSurname("test");
		form.setEmailAddress("test@test.com");
		form.setDob("11-11-2011");

		return form;
	}

	public static Diagnosis getDiagnosisInstance() {
		Diagnosis d = new Diagnosis();

		d.setId(new BigDecimal(1));
		d.setIllness(1);
		d.setUser(1);

		return d;
	}

	public static List<Diagnosis> getDiagnosisList(int size) {
		List<Diagnosis> list = new ArrayList<Diagnosis>();

		for (int i = 0; i < size; i++) {
			list.add(getDiagnosisInstance());
		}

		return list;
	}

	
	
	public static List<DrugIllness> getDrugIllnessList(int size) {
		List<DrugIllness> list = new ArrayList<DrugIllness>();

		for (int i = 0; i < size; i++) {
			list.add(getDrugIllnessInstance());
		}

		return list;
	}

	private static DrugIllness getDrugIllnessInstance() {
		DrugIllness di = new DrugIllness();
		
		di.setDrugId(1);
		di.setId(new BigDecimal(1));
		di.setIllnessId(1);
		
		return di;
	}
	
	public static List<Effect> getEffectList(int size) {
		List<Effect> list = new ArrayList<Effect>();

		for (int i = 0; i < size; i++) {
			list.add(getEffectInstance());
		}

		return list;
	}

	public static Effect getEffectInstance() {
		Effect e = new Effect();
		
		e.setId(new BigDecimal(1));
		e.setDescription("test description");
		e.setName("test name");
		
		return e;
	}

	public static Incident getIncidentInstance() {
		Incident i = new Incident();
		
		i.setDrug(getDrugInstance());
		i.setEffect(getEffectInstance());
		i.setUser(getUserInstance());
		i.setId(new BigDecimal(1));
		
		return i;
	}

	public static List<Incident> getIncidentListInstance(int size) {
		List<Incident> incidents = new ArrayList<Incident>();
		for (int i = 0; i<size; i++) {
			incidents.add(getIncidentInstance());
		}
		return incidents;
	}

	
	
	public static List<DrugUserSuitability> getDrugUserSuitabilityList(int size) {
		List<DrugUserSuitability> list = new ArrayList<DrugUserSuitability>();
		
		for (int i = 0; i<size; i++) {
			list.add(getDrugUserSuitabilityInstance());
		}
		
		return list;
	}

	public static DrugUserSuitability getDrugUserSuitabilityInstance() {
		DrugUserSuitability d = new DrugUserSuitability();
		
		d.setId(new BigDecimal(1));
		d.setEffect(getEffectInstance());
		d.setDrug(getDrugInstance());
		d.setUser(getUserInstance());
		d.setIncompatibility(new BigDecimal(20));
		
		return d;
	}

	public static NewDrugForm getNewDrugFormInstance() {
		NewDrugForm form = new NewDrugForm();
		
		form.setAllergies("allergy one, allergy two, allergy three");
		form.setDescription("a cool description");
		form.setName("a cool name");
		form.setEffects("effect 1, effect 2");
		
		return form;
	}

	public static List<PatientAllergy> getPatientAllergyListInstance(int size) {
		
		List<PatientAllergy> list = new ArrayList<PatientAllergy>();
		
		for (int i = 0; i < size; i++) {
			list.add(getPatientAllergyInstance("a name"));
		}
		
		return list;
	}

	public static PatientDetail getPatientDetailInstance() {
		PatientDetail p = new PatientDetail();
		
		p.setDiabetes(1);
		p.setEthnicity("an ethnicity");
		p.setGender("female");
		p.setHeight(new BigDecimal(150));
		p.setId(new BigDecimal(1));
		p.setSmoker(0);
		p.setUser(getUserInstance());
		p.setWeight(new BigDecimal(80));
		
		return p;
	}

	public static List<PatientDetail> getPatientDetailListInstance(int size) {

		List<PatientDetail> list = new ArrayList<PatientDetail>();
		
		for (int i = 0; i < size; i++) {
			list.add(getPatientDetailInstance());
		}
		
		return list;
		
	}

	public static UserForm getUserFormInstance() {
		UserForm form = new UserForm();
		
		form.setDob("09-08-1992");
		form.setEmailAddress("test.user@test.com");
		form.setForename("test");
		form.setSurname("user");
		form.setPassword("aGroovyPassword");
		form.setPasswordConfirmation("aGroovyPassword");
		form.setRoleName(RoleEnum.ROLE_PATIENT.getName());
		form.setUsername("testUser123");
		
		return form;
	}

	public static NewDrugForm getInvalidNewDrugFormInstance() {
		NewDrugForm form = getNewDrugFormInstance();
		form.setName("");
		
		return form;
	}

	public static ViewDrugForm getViewDrugFormInstance() {
		ViewDrugForm form = new ViewDrugForm();
		
		form.setDrugId(new BigDecimal(1));
		
		return form;
	}

	public static SearchForm getSearchFormInstance(String entity) {
		SearchForm form = new SearchForm();
		
		form.setEntity(entity);
		form.setSearchString("a search string");
		
		return form;
	}

	public static DiagnosisForm getDiagnosisFormInstance() {
		DiagnosisForm form = new DiagnosisForm();
		
		form.setIllnessName("a name");
		form.setUserId(1);
		
		return form;
	}

	public static NewEffectForm getNewEffectFormInstance() {
		NewEffectForm form = new NewEffectForm();
		
		form.setDescription("a description");
		form.setName("a name");
		
		return form;
	}

	public static NewEffectForm getInvalidNewEffectFormInstance() {
		NewEffectForm form = new NewEffectForm();
		return form;
	}

	public static ViewEffect getViewEffectInstance() {
		ViewEffect form = new ViewEffect();
		
		form.setEffectId(new BigDecimal(1));
		
		return form;
	}

	public static ViewEffect getInvalidViewEffectInstance() {
		return new ViewEffect();
	}

	public static ViewIllnessForm getViewIllnessFormInstance() {
		ViewIllnessForm form = new ViewIllnessForm();
		
		form.setIllnessId(new BigDecimal(1));
		
		return form;
	}

	public static NewPasswordForm getNewPasswordFormInstance() {
		NewPasswordForm form = new NewPasswordForm();
		
		form.setPassword("aGroovyPassword");
		form.setPasswordConfirmation("aGroovyPassword");
		
		return form;
	}
	
	public static NewPasswordForm getNewPasswordFormInstanceNotMatching() {
		NewPasswordForm form = new NewPasswordForm();
		
		form.setPassword("aGroovyPassword");
		form.setPasswordConfirmation("invalidPassword");
		
		return form;
	}
	
	public static NewPasswordForm getNewPasswordFormInstanceInvalid() {
		NewPasswordForm form = new NewPasswordForm();
		
		return form;
	}
	
	public static UserLogin getUserLoginInstance() {
		UserLogin login = new UserLogin();
		
		login.setId(new BigDecimal(1));
		login.setPassword("aPassword");
		login.setUser(getUserInstance());
		login.setUsername("aUsername");
		
		return login;
	}

	public static List<UserLogin> getUserLoginListInstance(int size) {
		
		List<UserLogin> list = new ArrayList<UserLogin>();
		
		for (int i = 0; i<size; i++) {
			list.add(getUserLoginInstance());
		}
		
		return list;
	}
	
}
