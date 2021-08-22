package io.multimodule.data.domain.credentials;

import java.io.Serializable;
import java.security.Principal;


public class UserProvider implements Principal, Serializable {

	private static final long serialVersionUID = -321867426611774490L;

	protected User user;
	protected Provider provider;
	protected Role role;

	public static UserProvider buildEmpty() {
		return buildByUserAndProviderAndRole(null, null, Role.PUBLIC);
	}

	public static UserProvider buildByUser(User user) {
		return buildByUserAndProviderAndRole(user, null, Role.PUBLIC);
	}

	public static UserProvider buildByUserProvider(UserProvider userProvider) {
		User user = userProvider != null ? userProvider.getUser() : null;
		Provider provider = userProvider != null ? userProvider.getProvider() : null;
		Role role = userProvider != null ? userProvider.getRole() : null;
		return buildByUserAndProviderAndRole(user, provider, role);
	}

	public static UserProvider buildByUserAndProviderAndRole(User user, Provider provider, Role role) {
		UserProvider up = new UserProvider();
		up.setUser(user);
		up.setProvider(provider);
		up.setRole(role);
		return up;
	}

	@Override
	public String getName() {
		return user != null ? user.getEmail() : null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProvider other = (UserProvider) obj;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (role != other.role)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
