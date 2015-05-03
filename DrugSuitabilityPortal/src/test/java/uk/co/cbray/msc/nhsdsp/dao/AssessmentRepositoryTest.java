package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.DrugUserSuitability;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class AssessmentRepositoryTest {
	
	@Mock
	private DataAccessObject dao;
	private AssessmentRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repo = new AssessmentRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testFindUsersByIdMethodCallsTheCorrectJPQL() {
		String jpql = "from DrugUserSuitability s where s.user.id = ?";
		BigDecimal userId = new BigDecimal(1);
		
		repo.findByUserId(userId);
		
		verify(dao).executeJpqlQueryWithParameters(jpql, DrugUserSuitability.class, userId);
	}
	
	@Test
	public void testCreateAllMethodCallsDAOCreateAllMethod() {
		List<DrugUserSuitability> list = TestFactory.getDrugUserSuitabilityList(5);
		
		repo.createAll(list);
		
		verify(dao).createAll(anySetOf(DrugUserSuitability.class));
	}
	

}
