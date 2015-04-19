package uk.co.cbray.msc.nhsdsp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import uk.co.cbray.msc.ml4j.api.Instance;
import uk.co.cbray.msc.nhsdsp.classification.model.AgeFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.AllergyFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.DiabetesFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.EffectClassifier;
import uk.co.cbray.msc.nhsdsp.classification.model.EthnicityFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.GenderFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.HeightFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.IncidentInstance;
import uk.co.cbray.msc.nhsdsp.classification.model.SmokerFeature;
import uk.co.cbray.msc.nhsdsp.classification.model.WeightFeature;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;

public class ClassificationConverter {
	
	public static List<Instance> convert(List<Incident> list, DataAccessObject dao) {
		List<Instance> instances = new ArrayList<Instance>();

		List<Effect> effect = dao.executeNamedQuery("Effect.findAll", Effect.class);
		List<EffectClassifier> classifiers = new ArrayList<EffectClassifier>();
		
		for (Effect e : effect) {
			EffectClassifier classifier = new EffectClassifier();
			classifier.setClassifier(e.getName());
			classifiers.add(classifier);
		}
		
		for (Incident i : list) {
			IncidentInstance instance = new IncidentInstance();
			instance.setDrug(i.getDrug());
			instance.setClassifier(findInList(classifiers, i.getEffect().getName()));
			
			User u = i.getUser();
			AgeFeature age = new AgeFeature(u.getDob());
			instance.addFeature(age);
			
			Set<PatientDetail> detailsSet = u.getPatientDetails();
			for(PatientDetail detail : detailsSet) {
				
				DiabetesFeature diabetes = new DiabetesFeature(detail.isDiabetes());
				EthnicityFeature ethnicity = new EthnicityFeature(detail.getEthnicity());
				GenderFeature gender = new GenderFeature(detail.getGender());
				HeightFeature height = new HeightFeature(detail.getHeight());
				SmokerFeature smoker = new SmokerFeature(detail.isSmoker());
				WeightFeature weight = new WeightFeature(detail.getWeight());
				
				instance.addFeature(diabetes);
				instance.addFeature(ethnicity);
				instance.addFeature(gender);
				instance.addFeature(height);
				instance.addFeature(smoker);
				instance.addFeature(weight);
				
			}
			
			Set<PatientAllergy> allergies = u.getPatientAllergies();
			for(PatientAllergy allergy : allergies) {
				AllergyFeature feat = new AllergyFeature(allergy.getAllergy());
				instance.addFeature(feat);
			}
			
			instances.add(instance);
		}
		
		
		return instances;
	}
	
	private static EffectClassifier findInList(List<EffectClassifier> effects, String name) {
		for (EffectClassifier classifier : effects) {
			if (((String)classifier.getClassifier()).equalsIgnoreCase(name)) {
				return classifier;
			}
		}
		return null;
	}

	public static IncidentInstance convertUnclassified(User user, Drug d) {
		IncidentInstance unclassified = new IncidentInstance();
		
		unclassified.setDrug(d);
		AgeFeature age = new AgeFeature(user.getDob());
		unclassified.addFeature(age);
		
		Set<PatientDetail> detailsSet = user.getPatientDetails();
		for(PatientDetail detail : detailsSet) {
			
			DiabetesFeature diabetes = new DiabetesFeature(detail.isDiabetes());
			EthnicityFeature ethnicity = new EthnicityFeature(detail.getEthnicity());
			GenderFeature gender = new GenderFeature(detail.getGender());
			HeightFeature height = new HeightFeature(detail.getHeight());
			SmokerFeature smoker = new SmokerFeature(detail.isSmoker());
			WeightFeature weight = new WeightFeature(detail.getWeight());
			
			unclassified.addFeature(diabetes);
			unclassified.addFeature(ethnicity);
			unclassified.addFeature(gender);
			unclassified.addFeature(height);
			unclassified.addFeature(smoker);
			unclassified.addFeature(weight);
			
		}
		
		return unclassified;
	}
	
}
