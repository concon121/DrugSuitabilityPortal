package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;

public class DiagnosisRepository {
	
	@Autowired
	private DataAccessObject dao;

	public void create(Diagnosis entity) {
		getDao().create(entity);
	}
	
	public void update(Diagnosis entity) {
		getDao().update(entity, Diagnosis.class);
	}
	
	public List<Diagnosis> findByUserId(BigDecimal userId) {
		String jpql = "from Diagnosis d where d.userId = ?";
		List<Diagnosis> results = getDao().executeJpqlQueryWithParameters(
				jpql, Diagnosis.class, userId);
		return results;
	}
	
	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	
	

}
