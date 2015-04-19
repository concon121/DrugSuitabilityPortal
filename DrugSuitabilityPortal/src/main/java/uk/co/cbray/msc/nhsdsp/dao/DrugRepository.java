package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.DrugAllergy;
import uk.co.cbray.msc.nhsdsp.entity.DrugEffect;
import uk.co.cbray.msc.nhsdsp.entity.DrugIllness;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.forms.NewDrugForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;

public class DrugRepository {

	@Autowired
	private DataAccessObject dao;
	@Autowired
	private EffectRepository effectRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(DrugRepository.class);

	public void update(Drug entity) {
		getDao().update(entity, Drug.class);
	}

	public void create(Drug entity) {
		getDao().create(entity);
	}

	public Drug findById(BigDecimal id) {
		Drug drug = getDao().find(id, Drug.class);
		return drug;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createNewDrug(NewDrugForm form) {

		Drug newDrug = Converter.convert(form);
		getDao().create(newDrug);
		getDao().updateAll(newDrug.getDrugAllergies(), DrugAllergy.class);
		Set<DrugEffect> drugEffects = Converter.convert(newDrug, form.getEffectsAsArray(), getEffectRepo());
		getDao().createAll(drugEffects);

	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public List<Drug> findAllDrugsForIllnesses(List<Illness> illnesses) {
		List<DrugIllness> drugIllnesses = new ArrayList<DrugIllness>();

		String diJpql = "from DrugIllness di where di.illnessId = ?";
		for (Illness i : illnesses) {

			List<DrugIllness> result = getDao().executeJpqlQueryWithParameters(
					diJpql, DrugIllness.class,
					((BigDecimal) i.getId()).intValue());
			if (!result.isEmpty()) {
				drugIllnesses.add(result.get(0));
			}

		}

		LOG.debug("DrugIllness found: " + drugIllnesses.size());
		
		// Get drugs
		List<Drug> drugs = new ArrayList<Drug>();

		String dJpql = "from Drug d where d.id = ?";

		for (DrugIllness di : drugIllnesses) {
			List<Drug> result = getDao().executeJpqlQueryWithParameters(dJpql,
					Drug.class, new BigDecimal(di.getDrugId()));
			if (!result.isEmpty()) {
				drugs.add(result.get(0));
			}
		}
		
		LOG.debug("Drugs found: " + drugs.size());

		return drugs;

	}

	public EffectRepository getEffectRepo() {
		return effectRepo;
	}

	public void setEffectRepo(EffectRepository effectRepo) {
		this.effectRepo = effectRepo;
	}
	
	

}
