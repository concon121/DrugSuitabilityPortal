package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.entity.Illness;
import uk.co.cbray.msc.nhsdsp.test.utils.TestFactory;

public class IllnessRepositoryTest {

	@Mock
	private DataAccessObject dao;
	private IllnessRepository repo;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dao.executeJpqlQuery(anyString(), eq(Illness.class))).thenReturn(TestFactory.getIllnessListInstance());
		
		repo = new IllnessRepository();
		repo.setDao(dao);
	}
	
	@Test
	public void testCreateMethodCallsDAOCreateMethod() {
		Illness i = TestFactory.getIllnessInstance();
		
		repo.create(i);
		
		verify(dao).create(i);
	}
	
	@Test
	public void testUpdateMethodCallsDaoUpdateMethod() {
		Illness i = TestFactory.getIllnessInstance();
		
		repo.update(i);
			
		verify(dao).update(i, Illness.class);
	}
	
	@Test
	public void testFindByIdMethodCallsDaoFindMethod() {
		BigDecimal id = new BigDecimal(1);
		
		repo.findById(id);
		
		verify(dao).find(id, Illness.class);
	}
	
	@Test
	public void testFindByNameMethodCallsTheCorrectJPQL() {
		String jpql = "select from Illness i where i.name = ?";
		String name = "name";
		
		repo.findByName(name);
		
		verify(dao).executeJpqlQueryWithParameters(jpql, Illness.class, name);
	}
	
	@Test
	public void testFindAllMethodCallsTheCorrectNamedQuery() {
		String namedQuery = "Illness.findAll";
		
		repo.findAll();
		
		verify(dao).executeNamedQuery(namedQuery, Illness.class);
	}
}
