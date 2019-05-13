package com.java.account.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="USER_ONE")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="U_ID", length=10, unique=true)
	private int userId;
	
	@Column(name="FIRST_NAME", length=50, nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", length=50)
	private String lastName;
	
	@Column(name="EMAIL", length=70, unique=true, nullable=false)
	@NotEmpty(message="Please Enter email Id")
	private String email;
	
	@Column(name="PASSWORD", length=50, unique=true, nullable=false)
	private String password;
	
	@Column(name="ACTIVE")
    private Integer active=1;
	
	@Column(name="IS_LOACKED")
    private boolean isLoacked=false;
	
	@Column(name="IS_EXPIRED")
    private boolean isExpired=false;
	
	@Column(name="IS_ENABLED")
    private boolean isEnabled=true;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public boolean isLoacked() {
		return isLoacked;
	}

	public void setLoacked(boolean isLoacked) {
		this.isLoacked = isLoacked;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}	
}
