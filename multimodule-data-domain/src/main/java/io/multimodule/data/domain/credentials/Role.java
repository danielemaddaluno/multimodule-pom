package io.multimodule.data.domain.credentials;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
	PUBLIC, USER, SCAN, EDIT, ADMIN;

	private static String SEPARATOR = "#";

	public static Set<Role> fromString(String roleStringList) {
		Set<Role> roles = new HashSet<Role>();
		for (String role : roleStringList.split(SEPARATOR)) {
			roles.add(Role.valueOf(role));
		}
		return roles;
	}
	
	public Set<Role> asSet() {
		Set<Role> roles = new HashSet<Role>();
		roles.add(this);
		return roles;
	}
	
	public static Set<Role> getRoles(Role[] roles) {
		Set<Role> rolesSet = new HashSet<Role>();
		for (Role r : roles) {
			rolesSet.add(r);
		}
		return rolesSet;
	}

	public static String toString(Set<Role> roles) {
		StringBuilder b = new StringBuilder();
		for (Role r : roles) {
			b.append(r.toString()).append(SEPARATOR);
		}
		
		return b.substring(0, b.length() - SEPARATOR.length());
	}
	
	public static Set<Role> valuesSet(){
		return new HashSet<Role>(Arrays.asList(Role.values()));
	}
	
	/**
	 * Check if role level is ok for the current role admitted
	 * @param role
	 * @return
	 */
	public boolean isRoleAuthorized(Role role) {
		return role != null ? this.ordinal() <= role.ordinal() : false;
	}
}
