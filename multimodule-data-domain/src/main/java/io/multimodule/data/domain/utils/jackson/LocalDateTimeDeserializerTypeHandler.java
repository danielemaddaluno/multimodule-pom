package io.multimodule.data.domain.utils.jackson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateTimeDeserializerTypeHandler extends JsonDeserializer<LocalDateTime> {
	private static final DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
			.append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).appendLiteral('T')
			.append(DateTimeFormatter.ofPattern("HH:mm:ss")).toFormatter();

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ds)
			throws IOException, JsonProcessingException {
		String value = jp.getValueAsString();
		if (value != null && !value.isEmpty() && !value.equals("null"))
			return parse(value);
		return null;
	}

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}

	public static LocalDateTime parse(String value) {
		return LocalDateTime.parse(value, f);
	}

	public static LocalDateTime parse(String value, String format) {
		DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
				.append(DateTimeFormatter.ofPattern(format)).toFormatter();
		return LocalDateTime.parse(value, f);
	}
}