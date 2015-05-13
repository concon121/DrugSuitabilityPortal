package uk.co.cbray.msc.nhsdsp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.Searchable;

/**
 * Generic DAO capable of providing all of the persistence functionality
 * required by the application.
 * 
 * @author Connor Bray
 */
public class DataAccessObject implements ICrudDataAccessObject{

	private static final Logger LOG = LoggerFactory
			.getLogger(DataAccessObject.class);

	@PersistenceContext(unitName = "persistence")
	private EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void create(T entity)
			throws PersistenceException {
		em.persist(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> T find(Object id, Class<T> clazz)
			throws PersistenceException {
		T entity = em.find(clazz, id);
		return entity;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void update(T entity, Class<T> clazz)
			throws PersistenceException {
		if (entity.getId() == null) {
			create(entity);
		} else {
			em.merge(entity);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void updateAll(List<T> entities, Class<T> clazz)
			throws PersistenceException {
		for (T entity : entities) {
			if (entity.getId() == null) {
				create(entity);
			} else {
				em.merge(entity);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void updateAll(Set<T> entities, Class<T> clazz)
			throws PersistenceException {
		for (T entity : entities) {
			if (entity.getId() == null) {
				create(entity);
			} else {
				em.merge(entity);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void delete(T entity, Class<T> clazz)
			throws PersistenceException {
		T found = find(entity.getId(), clazz);
		if (found != null) {
			em.remove(found);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void deleteAll(List<T> entities, Class<T> clazz)
			throws PersistenceException {
		for (T entity : entities) {
			T found = find(entity.getId(), clazz);
			if (found != null) {
				em.remove(found);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> List<T> executeNamedQuery(String name,
			Class<T> clazz) throws PersistenceException {
		Query namedQuery = em.createNamedQuery(name, clazz);
		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) namedQuery.getResultList();
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> List<T> executeJpqlQuery(String jpqlQueryStr,
			Class<T> clazz) {
		Query jpqlQuery = em.createQuery(jpqlQueryStr, clazz);
		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) jpqlQuery.getResultList();
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> List<T> executeJpqlQueryWithParameters(
			String jpqlQueryStr, Class<T> clazz, Object... parameters) {
		Query jpqlQuery = em.createQuery(jpqlQueryStr, clazz);

		int currentParam = 1;
		for (Object parameter : parameters) {
			if (parameter instanceof Collection<?>) {
				Collection<T> coll = (Collection<T>) parameter;
				jpqlQuery.setParameter(currentParam, coll);
			} else {
				jpqlQuery.setParameter(currentParam, parameter);
			}

			currentParam++;
		}

		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) jpqlQuery.getResultList();
		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> List<T> executeNativeQuery(
			String nativeQueryStr, Class<T> clazz) {
		Query nativeQuery = em.createNativeQuery(nativeQueryStr, clazz);
		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) nativeQuery.getResultList();
		return results;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity, N extends Object> List<T> findAll(List<N> ids,
			Class<T> clazz) {

		List<T> results = new ArrayList<T>();

		for (Object id : ids) {
			LOG.debug("Attempting to find Entity with id: " + id);
			T found = find(id, clazz);
			if (found != null) {
				results.add(found);
			} else {
				LOG.debug("Found object was null.");
			}
		}

		return results;
	}

	public void indexData() throws InterruptedException {
		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity & Searchable> List<T> search(Class<T> clazz,
			String searchString) throws InstantiationException,
			IllegalAccessException {

		FullTextEntityManager fullTextEntityManager = Search
				.getFullTextEntityManager(em);
		QueryBuilder qb = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(clazz).get();
		org.apache.lucene.search.Query query = qb.keyword()
				.onFields(clazz.newInstance().getFields())
				.matching(searchString).createQuery();

		javax.persistence.Query persistenceQuery = fullTextEntityManager
				.createFullTextQuery(query, clazz);

		// execute search
		List<T> results = persistenceQuery.getResultList();

		return results;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IEntity> void createAll(Set<T> entities) {

		for (T entity : entities) {
			em.persist(entity);
		}

	}

}
