package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;

/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Assessments and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class AssessmentRepository implements ICrudRepository{
	@Autowired
	private DataAccessObject dao;

	private static final Logger LOG = LoggerFactory
			.getLogger(AssessmentRepository.class);

	public void create(IEntity entity) {
		getDao().create(entity);
	}

	public IEntity read(Object id) {
		return getDao().find(id, DrugUserSuitability.class);
	}

	public void update(IEntity entity) {
		getDao().update((DrugUserSuitability) entity, DrugUserSuitability.class);
	}

	public void delete(IEntity entity) {
		getDao().delete((DrugUserSuitability) entity, DrugUserSuitability.class);
	}
	
	public List<DrugUserSuitability> findByUserId(BigDecimal userId) {
		String jpql = "from DrugUserSuitability s where s.user.id = ?";
		List<DrugUserSuitability> assessments = getDao()
				.executeJpqlQueryWithParameters(jpql,
						DrugUserSuitability.class, userId);
		return assessments;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void createAll(List<DrugUserSuitability> suits) {
		LOG.debug("Create all DrugUserSuitabilities started: " + suits.size());
		Set<DrugUserSuitability> suitsSet = new HashSet<DrugUserSuitability>();
		suitsSet.addAll(suits);
		getDao().createAll(suitsSet);
		LOG.debug("Create all DrugUserSuitabilities ended.");
	}
	
	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
