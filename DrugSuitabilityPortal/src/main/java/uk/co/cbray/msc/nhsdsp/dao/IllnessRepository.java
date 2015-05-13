package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Illnesses and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class IllnessRepository implements ICrudRepository {

	@Autowired
	private DataAccessObject dao;

	public void create(IEntity entity) {
		getDao().create(entity);
	}

	public IEntity read(Object id) {
		return getDao().find(id, Illness.class);
	}

	public void update(IEntity entity) {
		getDao().update((Illness) entity, Illness.class);
	}

	public void delete(IEntity entity) {
		getDao().delete((Illness) entity, Illness.class);
	}

	public List<Illness> findAll() {
		List<Illness> results = getDao().executeNamedQuery("Illness.findAll",
				Illness.class);
		return results;
	}
	
	public Illness findById(BigDecimal id) {
		Illness i = getDao().find(id, Illness.class);
		return i;
	}

	public List<Illness> findByName(String name) {
		String jpql = "select from Illness i where i.name = ?";
		List<Illness> results = getDao().executeJpqlQueryWithParameters(jpql,
				Illness.class, name);

		return results;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public List<Illness> findAllInCollection(List<BigDecimal> illnessIds) {
		List<Illness> results = new ArrayList<Illness>();
		String jpql = "from Illness i where i.id = ?";
		
		for (BigDecimal id : illnessIds) {
			List<Illness> result = getDao().executeJpqlQueryWithParameters(jpql, Illness.class, id);
			if(!result.isEmpty()) {
				results.add(result.get(0));
			}
		}
		
		return results;
	}

}
