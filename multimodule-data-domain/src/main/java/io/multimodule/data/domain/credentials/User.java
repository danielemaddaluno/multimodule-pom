package io.multimodule.data.domain.credentials;

import java.io.Serializable;
import java.math.BigInteger;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable {
	private static final long serialVersionUID = 8671508242793439446L;
	@JsonIgnore
	protected BigInteger id;
	@JsonIgnore
	protected String email;

	protected String displayName;
	
	@JsonIgnore
	protected String name;
	@JsonIgnore
	protected String surname;
	@JsonIgnore
	protected LocalDate dob;
	@JsonIgnore
	protected String idCode;
	
	public User() {}

	public User(BigInteger id, String email, String displayName) {
		this.id = id;
		this.email = email;
		this.displayName = displayName;
	}
	
	public User(User user) {
		this.id = user != null ? user.getId() : null;
		this.email = user != null ? user.getEmail() : null;
		this.displayName = user != null ? user.getDisplayName() : null;
	}
	
	public static User build(BigInteger id) {
		User user = new User();
		user.setId(id);
		return user;
	}
	
	public static User build(String email, String displayName) {
		User user = new User();
		user.setEmail(email);
		user.setDisplayName(displayName);
		return user;
	}
	
	public static boolean checkNull(User user) {
		return user == null || user.getEmail() == null || user.getDisplayName() == null || 
				user.getName() == null || user.getSurname() == null || user.getDob() == null || user.getIdCode() == null;
	}
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
