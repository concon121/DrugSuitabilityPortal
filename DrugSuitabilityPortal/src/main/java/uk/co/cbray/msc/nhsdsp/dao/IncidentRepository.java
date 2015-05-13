package uk.co.cbray.msc.nhsdsp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Incidents and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class IncidentRepository implements ICrudRepository{

	@Autowired
	private DataAccessObject dao;
	
	public void create(IEntity entity) {
		getDao().create(entity);
	}

	public IEntity read(Object id) {
		return getDao().find(id, Incident.class);
	}

	public void update(IEntity entity) {
		getDao().update((Incident) entity, Incident.class);
	}

	public void delete(IEntity entity) {
		getDao().delete((Incident) entity, Incident.class);
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public List<Incident> findAllForDrug(Drug drug) {
		String jpql = "from Incident i where i.drug = ?";
		List<Incident> results = getDao().executeJpqlQueryWithParameters(jpql, Incident.class, drug);
		return results;
	}
	
	
	
}
