package uk.co.cbray.msc.nhsdsp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.forms.PatientDetailForm;
import uk.co.cbray.msc.nhsdsp.utils.AllergyHelper;

public class PatientDetailsRepository {

	@Autowired
	private DataAccessObject dao;
	
	public void create(PatientDetail entity) {
		getDao().create(entity);
	}
	
	public void update(PatientDetail entity) {
		getDao().update(entity, PatientDetail.class);
	}
	
	public List<PatientDetail> findPatientDetailsForUser(User user) {
		String jpqlDetail = "from PatientDetail pd where pd.user = ?";
		List<PatientDetail> results = getDao().executeJpqlQueryWithParameters(
				jpqlDetail, PatientDetail.class, user);
		return results;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateAllPatientDetails(User user, PatientDetailForm detail) {

		List<PatientAllergy> toRemove = AllergyHelper.findAllergiesToRemove(user, detail);
		getDao().updateAll(user.getPatientAllergies(), PatientAllergy.class);
		getDao().updateAll(user.getPatientDetails(), PatientDetail.class);
		getDao().deleteAll(toRemove, PatientAllergy.class);
		
	}
	
	
	
	

}
