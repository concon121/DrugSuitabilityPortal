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

public class AssessmentRepository {
	@Autowired
	private DataAccessObject dao;
	
	private static final Logger LOG = LoggerFactory.getLogger(AssessmentRepository.class);
	
	public List<DrugUserSuitability> findByUserId(BigDecimal userId) {
		String jpql = "from DrugUserSuitability s where s.user.id = ?";
		List<DrugUserSuitability> assessments = getDao().executeJpqlQueryWithParameters(jpql, DrugUserSuitability.class, userId);
		return assessments;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void createAll(List<DrugUserSuitability> suits) {
		LOG.debug("Create all DrugUserSuitabilities started: " + suits.size());
		Set<DrugUserSuitability> suitsSet = new HashSet<DrugUserSuitability>();
		suitsSet.addAll(suits);
		getDao().createAll(suitsSet);
		LOG.debug("Create all DrugUserSuitabilities ended.");
	}
	
	
}
