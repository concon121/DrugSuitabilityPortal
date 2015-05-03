package uk.co.cbray.msc.nhsdsp.dao;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import uk.co.cbray.msc.nhsdsp.test.utils.MockEntity;
import uk.co.cbray.msc.nhsdsp.test.utils.MockQuery;

public class DataAccessObjectTest {

	@Mock
	private EntityManager em;
	@Mock
	private TypedQuery<MockEntity> query;
	private DataAccessObject dao;
	
	@Before
	public void SetUp() {
		MockitoAnnotations.initMocks(this);
		dao = new DataAccessObject();
		dao.setEm(em);
	}
	
	@Test
	public void testCreateMethodCallsEMPersist() {
		
		MockEntity entity = new MockEntity();
		
		dao.create(entity);
		
		verify(em).persist(entity);
		
	}
	
	@Test
	public void testFindMethodCallsEMFind() {
		BigDecimal id = new BigDecimal(1);
		
		dao.find(id, MockEntity.class);
		
		verify(em).find(eq(MockEntity.class), eq(id));
	}
	
	@Test
	public void testUpdateMethodCallsEMPersistIfNoIdIsPresent() {
		
		MockEntity entity = new MockEntity();
		
		dao.update(entity, MockEntity.class);
		
		verify(em).persist(entity);
	}
	
	@Test
	public void testUpdateMethodCallsEMMergeIfIDIsPresent() {
		
		MockEntity entity = new MockEntity();
		BigDecimal id = new BigDecimal(1);
		entity.setId(id);
		
		dao.update(entity, MockEntity.class);
		
		verify(em).merge(entity);
	}
	
	@Test
	public void testUpdateAllWithListsMethodCallsPersistIfNoIdIsPresent() {
		List<MockEntity> list = new ArrayList<MockEntity>();
		
		for(int i = 1; i<6; i++) {
			MockEntity entity = new MockEntity();
			list.add(entity);
		}
		
		dao.updateAll(list, MockEntity.class);
		
		verify(em, times(5)).persist((MockEntity)anyObject());
	}
	
	@Test
	public void testUpdateAllWithListsMethodCallsMergeIfIdIsPresent() {
		List<MockEntity> list = new ArrayList<MockEntity>();
		
		for(int i = 1; i<6; i++) {
			BigDecimal id = new BigDecimal(i);
			MockEntity entity = new MockEntity();
			entity.setId(id);
			list.add(entity);
		}
		
		dao.updateAll(list, MockEntity.class);
		
		verify(em, times(5)).merge((MockEntity)anyObject());
	}
	
	@Test
	public void testUpdateAllWithSetsMethodCallsPersistIfNoIdIsPresent() {
		Set<MockEntity> set = new HashSet<MockEntity>();
		
		for(int i = 1; i<6; i++) {
			MockEntity entity = new MockEntity();
			set.add(entity);
		}
		
		dao.updateAll(set, MockEntity.class);
		
		verify(em, times(5)).persist((MockEntity)anyObject());
	}
	
	@Test
	public void testUpdateAllWithSetsMethodCallsMergeIfIdIsPresent() {
		Set<MockEntity> set = new HashSet<MockEntity>();
		
		for(int i = 1; i<6; i++) {
			BigDecimal id = new BigDecimal(i);
			MockEntity entity = new MockEntity();
			entity.setId(id);
			set.add(entity);
		}
		
		dao.updateAll(set, MockEntity.class);
		
		verify(em, times(5)).merge((MockEntity)anyObject());
	}
	
	@Test
	public void testDeleteMethodAttemptsToFindAnEntityBeforeDeletingIt() {
		MockEntity entity = new MockEntity();
		BigDecimal id = new BigDecimal(1);
		entity.setId(id);
		
		Mockito.when(em.find(MockEntity.class, id)).thenReturn(entity);
		
		dao.delete(entity, MockEntity.class);
		
		verify(em).find(MockEntity.class, id);
		verify(em).remove(entity);
	}
	
	@Test
	public void testDeleteMethodDoesNotDeleteIfNoEntityExists() {
		MockEntity entity = new MockEntity();
		BigDecimal id = new BigDecimal(1);
		entity.setId(id);
		
		Mockito.when(em.find(MockEntity.class, id)).thenReturn(null);
		
		dao.delete(entity, MockEntity.class);
		
		verify(em).find(MockEntity.class, id);
		verify(em, times(0)).remove(entity);
	}
	
	@Test
	public void testDeleteAllMethodAttemptsToFindAnEntityBeforeDeletingIt() {
		
		List<MockEntity> entities = new ArrayList<MockEntity>();
		
		for (int i = 1; i<6; i++) {
			MockEntity entity = new MockEntity();
			BigDecimal id = new BigDecimal(i);
			entity.setId(id);
			entities.add(entity);
			
			Mockito.when(em.find(MockEntity.class, id)).thenReturn(entity);
		}
		
		
		
		dao.deleteAll(entities, MockEntity.class);
		
		verify(em, times(5)).find(eq(MockEntity.class), (BigDecimal)anyObject());
		verify(em, times(5)).remove((MockEntity)anyObject());
	}
	
	@Test
	public void testDeleteAllMethodDoesNotDeleteIfNoEntityExists() {
		List<MockEntity> entities = new ArrayList<MockEntity>();
		
		for (int i = 1; i<6; i++) {
			MockEntity entity = new MockEntity();
			BigDecimal id = new BigDecimal(i);
			entity.setId(id);
			entities.add(entity);
			
			Mockito.when(em.find(MockEntity.class, id)).thenReturn(null);
		}
		
		dao.deleteAll(entities, MockEntity.class);
		
		verify(em, times(5)).find(eq(MockEntity.class), (BigDecimal)anyObject());
		verify(em, times(0)).remove((MockEntity)anyObject());
	}
	
	@Test
	public void testExecuteNamedQueryCallsEmCreateNamedQueryMethod() {
		
		String name = "theName";
		Mockito.when(em.createNamedQuery(name, MockEntity.class)).thenReturn(query);
		
		dao.executeNamedQuery(name, MockEntity.class);
		
		verify(em).createNamedQuery(name, MockEntity.class);
		verify(query).getResultList();
		
	}
	
	@Test
	public void testExecuteJPQLQueryCallsEmCreateQueryMethod() {
		
		String queryStr = "from MockEntity";
		Mockito.when(em.createQuery(queryStr, MockEntity.class)).thenReturn(query);
		
		dao.executeJpqlQuery(queryStr, MockEntity.class);
		
		verify(em).createQuery(queryStr, MockEntity.class);
		verify(query).getResultList();
		
	}
	
	@Test
	public void testExecuteNativeQueryCallsEmCreateQueryMethod() {
		
		String queryStr = "select * from MOCK_ENTITY";
		Mockito.when(em.createNativeQuery(queryStr, MockEntity.class)).thenReturn(query);
		
		dao.executeNativeQuery(queryStr, MockEntity.class);
		
		verify(em).createNativeQuery(queryStr, MockEntity.class);
		verify(query).getResultList();
		
	}
	
	@Test
	public void testFindAllMethodCallsEMFindMethodTheSameAmmountOfTimesAsIdsProvided() {
		
		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		
		for (int i = 1; i< 6; i++) {
			BigDecimal id = new BigDecimal(i);
			ids.add(id);
		}
		
		dao.findAll(ids, MockEntity.class);
		verify(em, times(ids.size())).find(eq(MockEntity.class), (BigDecimal)anyObject());
		
	}
	
	@Test
	public void testCreateAllMethodCallsEMPersistMethodForEachElementInList() {
		
		Set<MockEntity> entities = new HashSet<MockEntity>();
		
		for (int i = 1; i<6; i++) {
			MockEntity entity = new MockEntity();
			BigDecimal id = new BigDecimal(i);
			entity.setId(id);
			entities.add(entity);
		}
		
		dao.createAll(entities);
		
		verify(em, times(entities.size())).persist((MockEntity)anyObject());
		
		
	}
	
	
}
