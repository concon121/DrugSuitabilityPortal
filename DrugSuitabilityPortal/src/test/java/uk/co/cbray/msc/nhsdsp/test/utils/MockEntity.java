package uk.co.cbray.msc.nhsdsp.test.utils;

import java.math.BigDecimal;

import uk.co.cbray.msc.nhsdsp.entity.IEntity;

public class MockEntity implements IEntity {

	private BigDecimal id = null;
	
	public void setId(BigDecimal id) {
		this.id = id;
	}
	
	public Object getId() {
		return id;
	}

}
