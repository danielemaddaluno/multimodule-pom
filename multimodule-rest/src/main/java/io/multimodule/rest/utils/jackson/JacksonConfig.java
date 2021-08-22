package io.multimodule.rest.utils.jackson;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.multimodule.data.domain.utils.jackson.JsonUtils;

/**
 * http://stackoverflow.com/a/27347445/3138238
 * @author madx
 *
 */
@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return JsonUtils.getObjectMapper();
	}
}
