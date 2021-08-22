package io.multimodule.rest.endpoints.interfaces;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Local
public interface IRestVersion {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String version();

}