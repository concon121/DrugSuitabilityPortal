package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;

/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Diagnosis and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class DiagnosisRepository implements ICrudRepository {

	@Autowired
	private DataAccessObject dao;

	public void create(IEntity entity) {
		getDao().create(entity);
	}

	public IEntity read(Object id) {
		return getDao().find(id, Diagnosis.class);
	}

	public void update(IEntity entity) {
		getDao().update((Diagnosis) entity, Diagnosis.class);
	}

	public void delete(IEntity entity) {
		getDao().delete((Diagnosis) entity, Diagnosis.class);
	}

	public List<Diagnosis> findByUserId(BigDecimal userId) {
		String jpql = "from Diagnosis d where d.userId = ?";
		List<Diagnosis> results = getDao().executeJpqlQueryWithParameters(jpql,
				Diagnosis.class, userId);
		return results;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

}
