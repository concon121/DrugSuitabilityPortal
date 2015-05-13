package uk.co.cbray.msc.nhsdsp.dao;

import java.util.List;
import java.util.Set;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;

public interface ICrudDataAccessObject {
	
	public <T extends IEntity> void create(T entity);
	public <T extends IEntity> T find(Object id, Class<T> clazz);
	public <T extends IEntity> void update(T entity, Class<T> clazz);
	public <T extends IEntity> void updateAll(List<T> entities, Class<T> clazz);
	public <T extends IEntity> void updateAll(Set<T> entities, Class<T> clazz);
	public <T extends IEntity> void delete(T entity, Class<T> clazz);
	public <T extends IEntity> void deleteAll(List<T> entities, Class<T> clazz);

}
