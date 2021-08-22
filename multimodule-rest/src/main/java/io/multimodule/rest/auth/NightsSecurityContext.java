package io.multimodule.rest.auth;

import java.io.Serializable;
import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import io.multimodule.data.domain.credentials.UserProvider;

public class NightsSecurityContext implements SecurityContext, Serializable{

	private static final long serialVersionUID = 8020890009693657536L;
	private SecurityContext securityContext;
	private UserProvider principal;
	
	public NightsSecurityContext(SecurityContext securityContext, UserProvider principal) {
		this.principal = principal;
		this.securityContext = securityContext;
	}
	
	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

	@Override
	public boolean isUserInRole(String role) {
		return securityContext.isUserInRole(role);
	}

	@Override
	public boolean isSecure() {
		return securityContext.isSecure();
	}

	@Override
	public String getAuthenticationScheme() {
		return securityContext.getAuthenticationScheme();
	}
}
