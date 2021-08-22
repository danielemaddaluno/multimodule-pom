package io.multimodule.data.domain.utils.jackson;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializerTypeHandler extends JsonSerializer<LocalDateTime> {

	private static final DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
			.append(DateTimeFormatter.ofPattern("yyyy-MM-dd")).appendLiteral('T')
			.append(DateTimeFormatter.ofPattern("HH:mm:ss")).toFormatter();

	@Override
	public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		String stringValue = value != null ? value.format(f) : null;
		if (stringValue != null) {
			jgen.writeString(stringValue);
		} else {
			jgen.writeNull();
		}
	}

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}

	public static String parse(LocalDateTime value) {
		if (value == null)
			return null;
		return value.format(f);
	}

	public static String parse(LocalDateTime value, String format) {
		if (value == null)
			return null;
		DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
				.append(DateTimeFormatter.ofPattern(format)).toFormatter();
		return value.format(f);
	}
}