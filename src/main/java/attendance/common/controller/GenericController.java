package attendance.common.controller;

import java.util.List;
import java.util.Map;

public interface GenericController<T> {
	T add(T newObject) throws Exception;

	void update(T existingObj);

	T get(Long id);

	List<T> getAll(List<Long> ids);

	T get(String key, String keyName);

	List<T> getAllObjects(List<String> keys, String keyName);

	List<T> list();

	List<T> search(String fieldName, Object value);

	List<T> search(String whereClause, Map<String, Object> params);

	List<T> search(String whereClause, Map<String, Object> params, int recordCount);

	void delete(Long id);

	void deleteAll(List<Long> ids);

	void delete(String key, String keyName);

	void deleteAllObjects(List<String> keys, String keyName);

	Class<T> getObjectClass();
}
