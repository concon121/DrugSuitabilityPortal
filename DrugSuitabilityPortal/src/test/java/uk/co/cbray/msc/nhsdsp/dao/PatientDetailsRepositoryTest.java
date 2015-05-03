package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.PatientDetail;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class PatientDetailsRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private PatientDetailsRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.executeJpqlQuery(anyString(), eq(PatientDetail.class))).thenReturn(TestFactory.getPatientDetailListInstance(5));
		
		repo = new PatientDetailsRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		PatientDetail i = TestFactory.getPatientDetailInstance();
		
		repo.create(i);
		
		verify(dao).create(i);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		PatientDetail i = TestFactory.getPatientDetailInstance();
		
		repo.update(i);
			
		verify(dao).update(i, PatientDetail.class);
	}
	
	@Test
	public void testFindPatientDetailsForUserMethodCallsDAOMethod() {

		User u = TestFactory.getUserInstance();
		
		repo.findPatientDetailsForUser(u);
		
		verify(dao).executeJpqlQueryWithParameters(anyString(), eq(PatientDetail.class), eq(u));
	}
}
