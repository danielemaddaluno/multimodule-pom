package io.multimodule.data.domain.utils.jackson;

import java.util.List;

import org.joda.money.BigMoney;
import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class JsonUtils {

	static ObjectMapper om = createObjectMapper();

	public static <T> T fromString(String value) {
		try {
			return om.readValue(value, new TypeReference<T>() {
			});
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromString(String value, Class<T> clazz) {
		try {
			return om.readValue(value, clazz);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> List<T> fromStringList(String value, Class<T> clazz) {
		try {
			TypeFactory typeFactory = om.getTypeFactory();
			return om.readValue(value, typeFactory.constructCollectionType(List.class, clazz));
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> String toString(T value) {
		try {
			return om.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			return "{}";
		}
	}

	public static ObjectMapper getObjectMapper() {
		return om;
	}

	private static ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule s = new SimpleModule();
		// I used this when using Xamarin to exchange it with a specific serializer
		// s.addSerializer(LocalDateTime.class, new LocalDateTimeSerializerTypeHandler());
		// s.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializerTypeHandler());
		s.addSerializer(DateTime.class, new JodaDateTimeSerializerTypeHandler());
		s.addDeserializer(DateTime.class, new JodaDateTimeDeserializerTypeHandler());
		s.addSerializer(BigMoney.class, new BigMoneySerializerTypeHandler());
		s.addDeserializer(BigMoney.class, new BigMoneyDeserializerTypeHandler());
		
		// this line was added: see DataObjects file of the app in C#
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// this line added because in java fields are camelized with first char in lower
		// case
		// but in the C# app fields are camelized with first char in Upper case
		objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		objectMapper.registerModule(s);
		// objectMapper.registerModule(new JodaModule());
		return objectMapper;
	}

}