package io.multimodule.rest.endpoints.implementations;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import io.multimodule.ejb.VersionBean;
import io.multimodule.rest.endpoints.interfaces.IRestVersion;

@Path("/version")
public class RestVersion extends RestSecurity implements IRestVersion{
	
	@EJB
	private VersionBean versionBean;
	
	/**
	 * localhost:8080/multimodule-rest/rest-api/version
	 */
	@Override
	public String version() {
		return versionBean.getVersion();
	}
}