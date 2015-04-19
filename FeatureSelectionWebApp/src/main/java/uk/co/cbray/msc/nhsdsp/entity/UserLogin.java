package uk.co.cbray.msc.nhsdsp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the USER_LOGIN database table.
 * 
 */
@Entity
@Table(name="USER_LOGIN")
@NamedQuery(name="UserLogin.findAll", query="SELECT u FROM UserLogin u")
public class UserLogin implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_LOGIN_ID_GENERATOR", sequenceName="USER_LOGIN_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_LOGIN_ID_GENERATOR")
	private BigDecimal id;

	private String password;

	private String username;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	public UserLogin() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}