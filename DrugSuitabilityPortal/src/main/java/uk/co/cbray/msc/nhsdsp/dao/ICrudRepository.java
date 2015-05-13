package uk.co.cbray.msc.nhsdsp.dao;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;

public interface ICrudRepository {
	
	public void create(IEntity entity);
	public IEntity read(Object id);
	public void update(IEntity entity);
	public void delete(IEntity entity);

}
