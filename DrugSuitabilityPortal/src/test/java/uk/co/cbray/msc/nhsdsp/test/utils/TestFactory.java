package uk.co.cbray.msc.nhsdsp.test.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugIllness;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
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
		form.setEthnicity("test");
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

	private static Effect getEffectInstance() {
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

}
