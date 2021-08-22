package io.multimodule.data.domain.utils.jackson;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

public class JodaLocalDateTimeDeserializerTypeHandler {

	public static LocalDateTime parse(String value, String format) {
		return LocalDateTime.parse(value, DateTimeFormat.forPattern(format));
	}
}