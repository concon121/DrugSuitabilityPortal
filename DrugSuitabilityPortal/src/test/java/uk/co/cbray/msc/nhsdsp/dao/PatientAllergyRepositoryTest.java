package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.PatientAllergy;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class PatientAllergyRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private PatientAllergyRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.executeJpqlQuery(anyString(), eq(PatientAllergy.class))).thenReturn(TestFactory.getPatientAllergyListInstance(5));
		
		repo = new PatientAllergyRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		PatientAllergy i = TestFactory.getPatientAllergyInstance("a name");
		
		repo.create(i);
		
		verify(dao).create(i);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		PatientAllergy i = TestFactory.getPatientAllergyInstance("a name");
		
		repo.update(i);
			
		verify(dao).update(i, PatientAllergy.class);
	}
	
	@Test
	public void testFindPatientAllergysForUserMethodCallsDAOMethod() {

		User u = TestFactory.getUserInstance();
		
		repo.findPatientAllergysForUser(u);
		
		verify(dao).executeJpqlQueryWithParameters(anyString(), eq(PatientAllergy.class), eq(u));
	}
}
