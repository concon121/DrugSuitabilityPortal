package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.entity.Incident;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class IncidentRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private IncidentRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		repo = new IncidentRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		Incident i = TestFactory.getIncidentInstance();
		
		repo.create(i);
		
		verify(dao).create(i);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		Incident i = TestFactory.getIncidentInstance();
		
		repo.update(i);
			
		verify(dao).update(i, Incident.class);
	}
	
	@Test
	public void testFindAllForDrugMethodCallsDAOMethod() {

		Drug d = TestFactory.getDrugInstance();
		
		repo.findAllForDrug(d);
		
		verify(dao).executeJpqlQueryWithParameters(anyString(), eq(Incident.class), eq(d));
	}
}
