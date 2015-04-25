package uk.co.cbray.msc.nhsdsp.utils;

/**
 * Enum to identify role.
 * 
 * @author Connor Bray
 */
public enum RoleEnum {
	
	ROLE_PATIENT("PATIENT", 0),
	ROLE_DOCTOR("DOCTOR", 1),
	ROLE_ADMIN("ADMIN", 2);
	
	private String name;
	private int priority;
	
	private RoleEnum(String name, int priority) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
	
}
