package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.entity.Drug;
import uk.co.cbray.msc.nhsdsp.entity.Effect;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class EffectRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private EffectRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.find(anyObject(), eq(Effect.class))).thenReturn(TestFactory.getEffectInstance());
		Mockito.when(dao.executeJpqlQuery(anyString(), eq(Effect.class))).thenReturn(TestFactory.getEffectList(5));
		
		repo = new EffectRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testFindByIdMethodCallsDaoFindMethod() {
		BigDecimal id = new BigDecimal(1);
		
		repo.findById(id);
		
		verify(dao).find(id, Effect.class);
	}
	
	@Test
	public void testFindByNameMethodCallsTheCorrectJPQL() {
		String jpql = "from Effect e where e.name = ?";
		String name = "name";
		
		repo.findByName(name);
		
		verify(dao).executeJpqlQueryWithParameters(jpql, Effect.class, name);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		Effect e = TestFactory.getEffectInstance();
		
		repo.create(e);
		
		verify(dao).create(e);
	}
	
}
