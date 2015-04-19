package uk.co.cbray.msc.nhsdsp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Incident;

public class IncidentRepository {

	@Autowired
	private DataAccessObject dao;
	
	public void create(Incident entity) {
		getDao().create(entity);
	}
	
	public void update(Incident entity) {
		getDao().update(entity, Incident.class);
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
