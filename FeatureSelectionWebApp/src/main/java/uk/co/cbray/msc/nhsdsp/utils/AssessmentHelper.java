package uk.co.cbray.msc.nhsdsp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.cbray.msc.ml4j.api.Instance;
import uk.co.cbray.msc.ml4j.exceptions.InvalidArgumentException;
import uk.co.cbray.msc.ml4j.impl.Classification4J;
import uk.co.cbray.msc.nhsdsp.classification.model.EffectClassifier;
import uk.co.cbray.msc.nhsdsp.classification.model.IncidentInstance;
import uk.co.cbray.msc.nhsdsp.dao.AssessmentRepository;
import uk.co.cbray.msc.nhsdsp.dao.DataAccessObject;
import uk.co.cbray.msc.nhsdsp.dao.DrugRepository;
import uk.co.cbray.msc.nhsdsp.dao.EffectRepository;
import uk.co.cbray.msc.nhsdsp.dao.IllnessRepository;
import uk.co.cbray.msc.nhsdsp.dao.IncidentRepository;
import uk.co.cbray.msc.nhsdsp.dao.UserRepository;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.NewAssessmentForm;

public class AssessmentHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(AssessmentHelper.class);

	public static List<DrugUserSuitability> assess(NewAssessmentForm form, UserRepository userRepo,
			IllnessRepository illnessRepo, DrugRepository drugRepo,
			DataAccessObject dao, IncidentRepository incidentRepo,
			EffectRepository effectRepo, AssessmentRepository assessmentRepo) {

		User user = userRepo.findById(new BigDecimal(form.getUserId()));
		List<Illness> illnesses = illnessRepo.findByName(form.getIllnessName());
		List<Drug> drugs = drugRepo.findAllDrugsForIllnesses(illnesses);

		List<DrugUserSuitability> suits = new ArrayList<DrugUserSuitability>();

		try {
			LOG.debug("No of Drugs in collection: " + drugs.size());
			for (Drug d : drugs) {
				IncidentInstance unclassified = ClassificationConverter
						.convertUnclassified(user, d);
				List<Incident> incidents = incidentRepo.findAllForDrug(d);
				List<Instance> dataSet = ClassificationConverter.convert(
						incidents, dao);
				Map<Object, Double> results = Classification4J
						.classifyWithProbabilities(unclassified, dataSet);

				for (Entry<Object, Double> entry : results.entrySet()) {
					String classifier = (String) entry
							.getKey();
					Double percent = entry.getValue();
					DrugUserSuitability newSuit = Converter.convert(user, d,
							classifier, percent, effectRepo);
					suits.add(newSuit);
				}
			}
			LOG.debug("Begin Adding DrugUserSuitabilities");
			assessmentRepo.createAll(suits);
			LOG.debug("End Adding DrugUserSuitabilities");
		} catch (InvalidArgumentException e) {
			LOG.error("Exception while assessing patient.", e);
		} catch (Exception e) {
			LOG.error("Exception while assessing patient.", e);
		}
		
		return suits;
	}
}
