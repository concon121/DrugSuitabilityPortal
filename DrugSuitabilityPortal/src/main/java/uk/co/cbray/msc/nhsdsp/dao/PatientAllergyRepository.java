package uk.co.cbray.msc.nhsdsp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for PatientAllergy and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class PatientAllergyRepository {

	@Autowired
	private DataAccessObject dao;
	
	public void create(PatientAllergy entity) {
		getDao().create(entity);
	}
	
	public void update(PatientAllergy entity) {
		getDao().update(entity, PatientAllergy.class);
	}
	
	public List<PatientAllergy> findPatientAllergysForUser(User user) {
		String jpqlDetail = "from PatientAllergy pd where pd.user = ?";
		List<PatientAllergy> results = getDao().executeJpqlQueryWithParameters(
				jpqlDetail, PatientAllergy.class, user);
		return results;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
	
}
