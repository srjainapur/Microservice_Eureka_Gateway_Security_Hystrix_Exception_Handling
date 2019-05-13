package com.java.api.gateway.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="SEC_USER")
public class User {
	
	@Id
	@Column(name="ID", length=50, nullable=false, unique=true)
	private String id;
	
	@Email(message="*Please enter a valid email")
	@NotEmpty(message="*Please enter email")
	@Column(name="EMAIL", length=70, nullable=false, unique=true)
	private String email;
	
	@NotEmpty(message = "*Please enter password")
	@Column(name="PASSWORD")
	private String password;
	
	@NotEmpty(message = "*Please provide your name")
	@Column(name="NAME")
    private String name;
    
	@NotEmpty(message = "*Please provide your last name")
	@Column(name="LAST_NAME")
    private String lastName;
	
	@Column(name="ACTIVE")
    private Integer active=1;
	
	@Column(name="IS_LOACKED")
    private boolean isLoacked=false;
	
	@Column(name="IS_EXPIRED")
    private boolean isExpired=false;
	
	@Column(name="IS_ENABLED")
    private boolean isEnabled=true;
    
	@OneToMany(cascade=CascadeType.ALL)	
    private Set<Role> role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
}
