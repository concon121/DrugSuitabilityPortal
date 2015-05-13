package uk.co.cbray.msc.nhsdsp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.IEntity;
import uk.co.cbray.msc.nhsdsp.entity.User;
import uk.co.cbray.msc.nhsdsp.entity.UserLogin;
import uk.co.cbray.msc.nhsdsp.forms.UserForm;
import uk.co.cbray.msc.nhsdsp.utils.Converter;
/**
 * An implementation of the Facade design pattern, this class encapsulates
 * persistence logic for Users and provides a simple and readable api.
 * 
 * @author Connor Bray
 */
public class UserRepository implements ICrudRepository {
	
	@Autowired
	private DataAccessObject dao;
	
	public void create(IEntity entity) {
		getDao().create(entity);
	}

	public IEntity read(Object id) {
		return getDao().find(id, User.class);
	}

	public void update(IEntity entity) {
		getDao().update((User) entity, User.class);
	}

	public void delete(IEntity entity) {
		getDao().delete((User) entity, User.class);
	}
	
	public User findById(BigDecimal id) {
		User user = getDao().find(id, User.class);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void createNewUser(UserForm user) {
		User convertedUser = Converter.convert(user);
		
		getDao().create(convertedUser);
		getDao().createAll(convertedUser.getUserLogins());
	}
	
	public List<UserLogin> getUserLoginsForUsername(String username) {
		String jpql = "from UserLogin u where username = ?";
		List<UserLogin> results = dao.executeJpqlQueryWithParameters(jpql,
				UserLogin.class, username);
		return results;
	}
	
	public List<UserLogin> getUserLoginsForUser(User user) {
		String jpql = "from UserLogin l where l.user = ?";
		List<UserLogin> results = getDao().executeJpqlQueryWithParameters(jpql, UserLogin.class, user);
		return results;
	}

	public DataAccessObject getDao() {
		return dao;
	}

	public void setDao(DataAccessObject dao) {
		this.dao = dao;
	}
	
	

}
