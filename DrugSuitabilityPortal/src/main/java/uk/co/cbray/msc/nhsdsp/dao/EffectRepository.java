package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.Effect;

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
		String jpql = "from Effect e where e.id = ?";
		List<Effect> results = getDao().executeJpqlQueryWithParameters(jpql, Effect.class, effectId);
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}
	
	

}
