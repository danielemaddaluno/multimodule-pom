package io.multimodule.data.domain.utils.jackson;


import org.joda.time.LocalDateTime;

public class JodaLocalDateTimeSerializerTypeHandler {

	public static String parse(LocalDateTime value, String format) {
		return value.toString(format);
	}
}