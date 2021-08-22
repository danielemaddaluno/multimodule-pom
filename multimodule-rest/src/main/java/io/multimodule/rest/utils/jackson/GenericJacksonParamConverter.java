package io.multimodule.rest.utils.jackson;

import javax.ws.rs.ext.ParamConverter;

import io.multimodule.data.domain.utils.jackson.JsonUtils;

public class GenericJacksonParamConverter<T> implements ParamConverter<T> {

//	Class<T> rawType;
//	public GenericJacksonParamConverter(Class<T> rawType) {
//		this.rawType = rawType;
//	}

	@Override
	public T fromString(String value) {
		return JsonUtils.fromString(value);
    }

	@Override
    public String toString(T value) {
		return JsonUtils.toString(value);
    }


}