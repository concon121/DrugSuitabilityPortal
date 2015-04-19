package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name="\"ROLES\"")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ROLE_NAME")
	private String roleName;

	public Role() {
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getId() {
		return roleName;
	}

}