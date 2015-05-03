package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.Diagnosis;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class DiagnosisRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private DiagnosisRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repo = new DiagnosisRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testFindUsersByIdMethodCallsTheCorrectJPQL() {
		String jpql = "from Diagnosis d where d.userId = ?";
		BigDecimal userId = new BigDecimal(1);
		
		repo.findByUserId(userId);
		
		verify(dao).executeJpqlQueryWithParameters(jpql, Diagnosis.class, userId);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		Diagnosis d = TestFactory.getDiagnosisInstance();
		
		repo.create(d);
		
		verify(dao).create(d);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		Diagnosis d = TestFactory.getDiagnosisInstance();
		
		repo.update(d);
			
		verify(dao).update(d, Diagnosis.class);
	}
	
}
