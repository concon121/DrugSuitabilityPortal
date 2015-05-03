package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Effect;
/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Effects and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class EffectRepository {

	@Autowired
	private DataAccessObject dao;
	
	public Effect findByName(String name) {
		String jpql = "from Effect e where e.name = ?";
		List<Effect> results = getDao().executeJpqlQueryWithParameters(jpql, Effect.class, name);
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	public void create(Effect effect) {
		getDao().create(effect);
	}

	public Effect findById(BigDecimal effectId) {
		@SuppressWarnings("unchecked")
		Effect result = getDao().find(effectId, Effect.class);
		return result;
	}
	
	

}
