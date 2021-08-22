package io.multimodule.rest.endpoints.implementations;

import java.math.BigInteger;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import io.multimodule.data.domain.credentials.Provider;
import io.multimodule.data.domain.credentials.Role;
import io.multimodule.data.domain.credentials.User;
import io.multimodule.data.domain.credentials.UserProvider;

public class RestSecurity {

	@Context
	SecurityContext sctx;
	
	protected UserProvider userProvider() {
		return (UserProvider) sctx.getUserPrincipal();
	}

	protected User user() {
		return userProvider().getUser();
	}
	
	protected Provider provider() {
		return userProvider().getProvider();
	}
	
	protected Role role() {
		return userProvider().getRole();
	}

	protected BigInteger userId() {
		return user().getId();
	}

	protected BigInteger providerId() {
		return provider().getId();
	}	

}