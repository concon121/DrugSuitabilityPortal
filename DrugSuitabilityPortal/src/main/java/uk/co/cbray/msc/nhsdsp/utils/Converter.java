package uk.co.cbray.msc.nhsdsp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugAllergy;
import uk.co.cbray.msc.nhsdsp.entity.DrugEffect;
import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidEntityConversionTypeException;
import uk.co.cbray.msc.nhsdsp.exceptions.InvalidParameterException;
import uk.co.cbray.msc.nhsdsp.forms.DiagnosisForm;
import uk.co.cbray.msc.nhsdsp.forms.DrugForm;
import uk.co.cbray.msc.nhsdsp.forms.IForm;
import uk.co.cbray.msc.nhsdsp.forms.IllnessForm;
import uk.co.cbray.msc.nhsdsp.forms.IncidentForm;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.forms.NewEffectForm;
import uk.co.cbray.msc.nhsdsp.forms.NewPasswordForm;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserDetailForm;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;

/**
 * Helper class for converting objects from one state into another.
 * 
 * @author Connor Bray
 */
public class Converter {

	public static User convert(UserForm form) {

		User user = new User();

		user.setForename(form.getForename());
		user.setSurname(form.getSurname());
		user.setEmailAddress(form.getEmailAddress());
		user.setDob(form.getDob());
		user.setRoleName(form.getRoleName());

		UserLogin login = new UserLogin();
		login.setUsername(form.getUsername());
		login.setPassword(form.getPassword());

		user.addUserLogin(login);

		return user;

	}

	public static <T extends IForm> T convert(User user, Class<T> clazz)
			throws InvalidEntityConversionTypeException,
			InstantiationException, IllegalAccessException {

		T form = clazz.newInstance();
		form.copyFrom(user);

		return form;

	}

	public static User convert(PatientDetailForm form, User user) {

		List<PatientDetail> patientDetails = new ArrayList<PatientDetail>();
		patientDetails.addAll(user.getPatientDetails());
		List<PatientAllergy> patientAllergies = new ArrayList<PatientAllergy>();
		patientAllergies.addAll(user.getPatientAllergies());

		if (patientDetails != null && patientDetails.size() > 0) {

			PatientDetail existing = patientDetails.get(0);

			existing.setDiabetes(form.getDiabetes().equalsIgnoreCase("YES") ? 1
					: 0);
			existing.setEthnicity(form.getEthnicity());
			existing.setHeight(form.getHeight());
			existing.setSmoker(form.getSmoker().equalsIgnoreCase("YES") ? 1 : 0);
			existing.setWeight(form.getWeight());
			existing.setGender(form.getGender());

		} else {

			PatientDetail newDetails = new PatientDetail();

			newDetails
					.setDiabetes(form.getDiabetes().equalsIgnoreCase("YES") ? 1
							: 0);
			newDetails.setEthnicity(form.getEthnicity());
			newDetails.setHeight(form.getHeight());
			newDetails.setSmoker(form.getSmoker().equalsIgnoreCase("YES") ? 1
					: 0);
			newDetails.setWeight(form.getWeight());
			newDetails.setGender(form.getGender());
			newDetails.setUser(user);

			patientDetails.add(newDetails);
		}

		processAllergies(form, user);

		user.setPatientDetails(patientDetails);

		return user;

	}

	private static void processAllergies(PatientDetailForm form, User user) {
		String[] formAllergies = form.getAllergiesAsArray();
		List<PatientAllergy> patientAllergies = new ArrayList<PatientAllergy>();
		patientAllergies.addAll(user.getPatientAllergies());

		if (formAllergies == null || formAllergies.length == 0) {
			patientAllergies = Collections.emptyList();
		} else {
			// Add new allergies
			for (String formAllergy : formAllergies) {
				if (formAllergy != null && formAllergy.length() > 0
						&& !formAllergy.equals("NONE")) {
					boolean found = false;
					for (PatientAllergy patientAllergy : patientAllergies) {
						if (patientAllergy != null
								&& patientAllergy.getAllergy() != null
								&& formAllergy != null) {
							if (patientAllergy.getAllergy().trim()
									.equalsIgnoreCase(formAllergy.trim())) {
								found = true;
							}
						}
					}

					if (!found) {
						PatientAllergy newPatientAllergy = new PatientAllergy();
						newPatientAllergy.setAllergy(formAllergy.trim());
						newPatientAllergy.setUser(user);

						patientAllergies.add(newPatientAllergy);
					}
				}
			}

		}

		user.setPatientAllergies(patientAllergies);
	}

	public static PatientDetailForm convert(Set<PatientDetail> d,
			Set<PatientAllergy> a) {

		List<PatientDetail> details = new ArrayList<PatientDetail>();
		List<PatientAllergy> allergies = new ArrayList<PatientAllergy>();

		details.addAll(d);
		allergies.addAll(a);

		PatientDetail detail = details.get(0);

		PatientDetailForm form = new PatientDetailForm();

		StringBuffer allergyString = new StringBuffer();
		for (PatientAllergy allergy : allergies) {
			String thisAllergy = allergy.getAllergy();
			allergyString.append(thisAllergy);
			if ((allergies.indexOf(allergy) + 1) < allergies.size()) {
				allergyString.append(", ");
			}
		}

		form.setDiabetes(detail.isDiabetes() == 1 ? "YES" : "NO");
		form.setEthnicity(detail.getEthnicity());
		form.setHeight(detail.getHeight());
		form.setSmoker(detail.isSmoker() == 1 ? "YES" : "NO");
		form.setWeight(detail.getWeight());
		form.setGender(detail.getGender());

		form.setAllergies(allergyString.length() == 0 ? "NONE" : allergyString
				.toString());

		return form;
	}

	public static PatientDetailForm convert(PatientDetail d,
			List<PatientAllergy> a) {

		Set<PatientDetail> details = new HashSet<PatientDetail>();
		Set<PatientAllergy> allergies = new HashSet<PatientAllergy>();

		details.add(d);
		allergies.addAll(a);

		return convert(details, allergies);
	}

	public static User convert(UserDetailForm form, User user) {

		user.setForename(form.getForename());
		user.setSurname(form.getSurname());
		user.setEmailAddress(form.getEmailAddress());
		user.setDob(form.getDob());

		return user;
	}

	public static void convert(NewPasswordForm form, UserLogin login) {

		login.setPassword(form.getPassword());

	}

	public static Drug convert(NewDrugForm form) {
		Drug drug = new Drug();

		drug.setName(form.getName());
		drug.setDescription(form.getDescription());

		String[] formAllergies = form.getAllergiesAsArray();
		List<DrugAllergy> drugAllergies = new ArrayList<DrugAllergy>();

		for (String allergy : formAllergies) {
			if (allergy != null && allergy.length() > 0
					&& !allergy.equalsIgnoreCase("NONE")) {
				DrugAllergy drugAllergy = new DrugAllergy();
				drugAllergy.setAllergy(allergy);
				drugAllergy.setDrug(drug);

				drugAllergies.add(drugAllergy);
			}
		}

		String[] formEffects = form.getEffectsAsArray();

		drug.setDrugAllergies(drugAllergies);

		return drug;
	}

	public static Incident convert(IncidentForm form, DataAccessObject dao,
			HttpSession session) throws InvalidParameterException {

		Incident incident = new Incident();

		BigDecimal userId = new BigDecimal(
				(Integer) session.getAttribute("userId"));
		User user = (User) dao.find(userId, User.class);

		String drugName = form.getDrug();
		String effectName = form.getEffect();

		String jpqlGetDrug = "from Drug d where d.name = ?";
		String jpqlGetEffect = "from Effect e where e.name = ?";

		List<Drug> drugs = dao.executeJpqlQueryWithParameters(jpqlGetDrug,
				Drug.class, drugName);
		List<Effect> effects = dao.executeJpqlQueryWithParameters(
				jpqlGetEffect, Effect.class, effectName);

		if (drugs.size() < 1) {
			throw new InvalidParameterException(drugName,
					"Invalid drug name specified");
		}
		if (effects.size() < 1) {
			throw new InvalidParameterException(effectName,
					"Invalid effect name specified");
		}

		incident.setDrug(drugs.get(0));
		incident.setEffect(effects.get(0));
		incident.setUser(user);

		return incident;
	}

	public static <E extends IEntity, F extends IForm> List<F> convert(
			List<E> entities, Class<F> targetType)
			throws InstantiationException, IllegalAccessException,
			InvalidEntityConversionTypeException {

		List<F> formItems = new ArrayList<F>();

		for (E entity : entities) {
			F form = targetType.newInstance();
			form.copyFrom(entity);
			formItems.add(form);
		}

		return formItems;
	}

	public static DrugForm convert(Drug drug) {
		DrugForm form = new DrugForm();
		form.setName(drug.getName());
		form.setDescription(drug.getDescription());

		StringBuffer buffer = new StringBuffer();
		boolean first = true;
		for (DrugAllergy a : drug.getDrugAllergies()) {
			if (!first) {
				buffer.append(", ");
			}
			buffer.append(a.getAllergy());
			if (first) {
				first = false;
			}
		}

		form.setAllergies(buffer.toString());

		return form;
	}

	public static IllnessForm convert(Illness illness) {
		IllnessForm form = new IllnessForm();

		form.setName(illness.getName());
		form.setDescription(illness.getDescription());
		return form;
	}

	public static Diagnosis convert(DiagnosisForm form, IllnessRepository dao) {
		Diagnosis d = new Diagnosis();
		d.setUser(form.getUserId());

		List<Illness> illnesses = dao.findByName(form.getIllnessName());

		if (illnesses != null | !illnesses.isEmpty()) {
			Illness i = illnesses.get(0);
			d.setIllness(((BigDecimal) i.getId()).intValue());
		}

		return d;
	}

	public static List<DiagnosisForm> convert(List<Diagnosis> list,
			IllnessRepository dao) {

		List<DiagnosisForm> formItems = new ArrayList<DiagnosisForm>();

		for (Diagnosis d : list) {
			DiagnosisForm form = new DiagnosisForm();
			form.setUserId(d.getUser());
			Illness i = dao.findById(new BigDecimal(d.getIllness()));
			form.setIllnessName(i.getName());

			formItems.add(form);
		}

		return formItems;

	}

	/**
	 * Convert a collection of entity to a collection of illness names.
	 * 
	 * @param entities
	 * @return
	 */
	public static List<String> convertIllnessToNames(List<Illness> illnesses) {

		List<String> names = new ArrayList<String>();

		for (Illness i : illnesses) {
			names.add(i.getName());
		}

		return names;
	}

	public static List<String> convertDiagnosisToIllnessNames(
			List<Diagnosis> diagnoses, IllnessRepository repo) {

		List<String> names = new ArrayList<String>();
		List<BigDecimal> illnessIds = new ArrayList<BigDecimal>();

		for (Diagnosis d : diagnoses) {
			illnessIds.add(new BigDecimal(d.getIllness()));
		}

		List<Illness> illnesses = repo.findAllInCollection(illnessIds);

		for (Illness i : illnesses) {
			names.add(i.getName());
		}

		return names;
	}

	public static DrugUserSuitability convert(User user, Drug drug,
			String classifier, Double percent, EffectRepository repo) {
		DrugUserSuitability suit = new DrugUserSuitability();

		Effect effect = repo.findByName(classifier);

		suit.setDrug(drug);
		suit.setUser(user);
		suit.setIncompatibility(new BigDecimal((int) (percent * 100)));
		suit.setEffect(effect);

		return suit;
	}

	public static Effect convert(NewEffectForm form) {
		Effect effect = new Effect();

		effect.setName(form.getName());
		effect.setDescription(form.getDescription());

		return effect;
	}

	public static Set<DrugEffect> convert(Drug drug, String[] effectsAsArray,
			EffectRepository effectRepo) {
		Set<DrugEffect> effects = new HashSet<DrugEffect>();

		for (String name : effectsAsArray) {
			Effect effect = effectRepo.findByName(name);

			DrugEffect de = new DrugEffect();

			de.setDrugId(drug.getId().intValue());
			de.setEffectid(effect.getId().intValue());

			effects.add(de);
		}

		return effects;
	}

}
