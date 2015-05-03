package uk.co.cbray.msc.nhsdsp.test.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class MockQuery <T> implements TypedQuery{

	public int executeUpdate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getFirstResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getHints() {
		// TODO Auto-generated method stub
		return null;
	}

	public LockModeType getLockMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxResults() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Parameter<?> getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parameter<?> getParameter(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Parameter<T> getParameter(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Parameter<T> getParameter(int arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getParameterValue(Parameter<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParameterValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParameterValue(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Parameter<?>> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isBound(Parameter<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getResultList() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getSingleResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setFirstResult(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setHint(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setLockMode(LockModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setMaxResults(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(Parameter arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(Parameter arg0, Calendar arg1,
			TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(Parameter arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(String arg0, Calendar arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(String arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(int arg0, Calendar arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public TypedQuery setParameter(int arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
