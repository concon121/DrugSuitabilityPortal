package uk.co.cbray.msc.nhsdsp.test.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;

public class ClassificationFactory {

	public static User getUserBeingAssessed() {
		User user = new User();
		user.setForename("Test");
		user.setSurname("User");
		user.setDob(new GregorianCalendar());
		user.setEmailAddress("abc@bca.com");
		user.setId(new BigDecimal(1));
		user.setRoleName("TEST_ROLE");

		List<PatientDetail> patientDetails = new ArrayList<PatientDetail>();
		patientDetails.add(getPatientDetailInstanceOne());

		user.setPatientDetails(patientDetails);

		return user;
	}

	public static User getUserOne() {
		User user = new User();
		user.setForename("Test");
		user.setSurname("User");
		user.setDob(new GregorianCalendar());
		user.setEmailAddress("abc@bca.com");
		user.setId(new BigDecimal(1));
		user.setRoleName("TEST_ROLE");

		List<PatientDetail> patientDetails = new ArrayList<PatientDetail>();
		patientDetails.add(getPatientDetailInstanceOne());

		user.setPatientDetails(patientDetails);

		return user;
	}

	public static User getUserTwo() {
		User user = new User();
		user.setForename("Test");
		user.setSurname("User");
		user.setDob(new GregorianCalendar());
		user.setEmailAddress("abc@bca.com");
		user.setId(new BigDecimal(1));
		user.setRoleName("TEST_ROLE");

		List<PatientDetail> patientDetails = new ArrayList<PatientDetail>();
		patientDetails.add(getPatientDetailInstanceTwo());

		user.setPatientDetails(patientDetails);

		return user;
	}

	public static User getUserThree() {
		User user = new User();
		user.setForename("Test");
		user.setSurname("User");
		user.setDob(new GregorianCalendar());
		user.setEmailAddress("abc@bca.com");
		user.setId(new BigDecimal(1));
		user.setRoleName("TEST_ROLE");

		List<PatientDetail> patientDetails = new ArrayList<PatientDetail>();
		patientDetails.add(getPatientDetailInstanceThree());

		user.setPatientDetails(patientDetails);

		return user;
	}

	public static PatientDetail getPatientDetailInstanceOne() {
		PatientDetail form = new PatientDetail();

		form.setDiabetes(1);
		form.setEthnicity("test");
		form.setGender("MALE");
		form.setHeight(new BigDecimal(150));
		form.setWeight(new BigDecimal(100));
		form.setSmoker(1);

		return form;
	}

	public static PatientDetail getPatientDetailInstanceTwo() {
		PatientDetail form = new PatientDetail();

		form.setDiabetes(0);
		form.setEthnicity("test");
		form.setGender("FEMALE");
		form.setHeight(new BigDecimal(120));
		form.setWeight(new BigDecimal(70));
		form.setSmoker(1);

		return form;
	}

	public static PatientDetail getPatientDetailInstanceThree() {
		PatientDetail form = new PatientDetail();

		form.setDiabetes(0);
		form.setEthnicity("test");
		form.setGender("MALE");
		form.setHeight(new BigDecimal(170));
		form.setWeight(new BigDecimal(130));
		form.setSmoker(0);

		return form;
	}

	public static Drug getDrug() {
		Drug d = new Drug();

		d.setDescription("a description about drug");
		d.setName("test name");

		return d;
	}
	
	public static List<Drug> getDrugList() {
		List<Drug> list = new ArrayList<Drug>();
		
		list.add(getDrug());
		
		return list;
	}

	public static Effect getEffectOne() {
		Effect e = new Effect();

		e.setName("effect 1");
		e.setDescription("description about effect 1");

		return e;
	}

	public static Effect getEffectTwo() {
		Effect e = new Effect();

		e.setName("effect 2");
		e.setDescription("description about effect 2");

		return e;
	}

	public static Effect getEffectThree() {
		Effect e = new Effect();

		e.setName("effect 3");
		e.setDescription("description about effect 3");

		return e;
	}

	public static Effect getEffectFour() {
		Effect e = new Effect();

		e.setName("effect 4");
		e.setDescription("description about effect 4");

		return e;
	}

	public static Incident getIncident(int user, int effect) {
		Incident i = new Incident();

		i.setId(new BigDecimal(1));
		i.setDrug(getDrug());

		if (effect == 1) {
			i.setEffect(getEffectOne());
		} else if (effect == 2) {
			i.setEffect(getEffectTwo());
		} else if (effect == 3) {
			i.setEffect(getEffectThree());
		} else if (effect == 4) {
			i.setEffect(getEffectFour());
		} else {
			i.setEffect(getEffectOne());
		}

		if (user == 1) {
			i.setUser(getUserOne());
		} else if (user == 2) {
			i.setUser(getUserTwo());
		} else if (user == 3) {
			i.setUser(getUserThree());
		} else {
			i.setUser(getUserOne());
		}

		return i;
	}

	public static List<Incident> getIncidentListInstance() {
		List<Incident> list = new ArrayList<Incident>();

		for (int i = 1; i <= 3; i++) {
			Incident a = getIncident(i, 3);
			Incident b = getIncident(i, 3);
			Incident c = getIncident(i, 3);
			Incident d = getIncident(i, 3);
			Incident e = getIncident(i, 3);
			Incident f = getIncident(i, 4);
			Incident g = getIncident(i, 4);
			Incident h = getIncident(i, 2);
			Incident m = getIncident(i, 2);
			Incident j = getIncident(i, 2);
			Incident k = getIncident(i, 1);
			Incident l = getIncident(i, 1);
			
			list.add(a);
			list.add(b);
			list.add(c);
			list.add(d);
			list.add(e);
			list.add(f);
			list.add(g);
			list.add(h);
			list.add(j);
			list.add(k);
			list.add(l);
			list.add(m);
		}

		return list;
	}

	public static List<Effect> getEffectList() {
		List<Effect> list = new ArrayList<Effect>();
		
		list.add(getEffectOne());
		list.add(getEffectTwo());
		list.add(getEffectThree());
		list.add(getEffectFour());
		
		return list;
	}

}
