package attendance.common.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import attendance.common.controller.GenericController;

public abstract class GenericControllerImpl<T> implements GenericController<T> {
	@Autowired
	EntityManagerFactory manager;
	protected EntityManager entityManager;

	@PostConstruct
	private void init() {
		entityManager = manager.createEntityManager();
	}

	@Override
	@Transactional
	public T add(T newObject) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(newObject);
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().commit();
			}
		}
		return newObject;
	}

	@Override
	@Transactional
	public void update(T existingObj) {
		entityManager.getTransaction().begin();
		entityManager.merge(existingObj);
		entityManager.getTransaction().commit();
	}

	@Override
	public T get(Long id) {
		List<T> resultList = getAll(Arrays.asList(id));
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	@Override
	public List<T> getAll(List<Long> ids) {
		return entityManager.createQuery("from " + getObjectClass().getName() + " where id in(:ids)", getObjectClass())
				.setParameter("ids", ids).getResultList();
	}

	@Override
	public T get(String key, String keyName) {
		List<T> resultList = getAllObjects(Arrays.asList(key), keyName);
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	@Override
	public List<T> getAllObjects(List<String> keys, String keyName) {
		return entityManager.createQuery("from " + getObjectClass().getName() + " where " + keyName + " in(:keys)",
				getObjectClass()).setParameter("keys", keys).getResultList();
	}

	@Override
	public List<T> list() {
		return entityManager.createQuery("from " + getObjectClass().getName(), getObjectClass()).getResultList();
	}

	public List<T> search(String fieldName, String value) {
		return search(fieldName, (Object) value);
	}

	@Override
	public List<T> search(String json) {
		JSONObject obj = new JSONObject(json);
		return search(obj.getString("fieldName"), obj.get("value"));
	}

	public List<T> search(String fieldName, Object value) {
		String whereClause = fieldName + "=(:value)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", value);
		return search(whereClause, params);
	}

	@Override
	public List<T> search(String whereClause, Map<String, Object> params) {
		TypedQuery<T> query = prepareQuery(whereClause, params);
		return query.getResultList();
	}

	@Override
	public List<T> search(String whereClause, Map<String, Object> params, int recordCount) {
		TypedQuery<T> query = prepareQuery(whereClause, params);
		query.setMaxResults(recordCount);
		return query.getResultList();
	}

	public TypedQuery<T> prepareQuery(String whereClause, Map<String, Object> params) {
		TypedQuery<T> query = entityManager.createQuery("from " + getObjectClass().getName() + " where " + whereClause,
				getObjectClass());
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	@Override
	public void delete(Long id) {
		deleteAll(Arrays.asList(id));
	}

	@Override
	@Transactional
	public void deleteAll(List<Long> ids) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from " + getObjectClass().getName() + " where id in(:ids)")
				.setParameter("ids", ids).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(String key, String keyName) {
		deleteAllObjects(Arrays.asList(key), keyName);
	}

	@Override
	@Transactional
	public void deleteAllObjects(List<String> keys, String keyName) {
		entityManager.getTransaction().begin();
		entityManager.createQuery("delete from " + getObjectClass().getName() + " where " + keyName + " in(:keys)")
				.setParameter("keys", keys).executeUpdate();
		entityManager.getTransaction().commit();
	}
}
