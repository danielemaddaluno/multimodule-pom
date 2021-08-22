package io.multimodule.data.domain.utils.jackson;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import io.multimodule.data.domain.utils.CalendarUtils;

public class JodaDateTimeDeserializerTypeHandler extends JsonDeserializer<DateTime> {

	@Override
	public DateTime deserialize(JsonParser jp, DeserializationContext ds)
			throws IOException, JsonProcessingException {
		String value = jp.getValueAsString();
		if (value != null && !value.isEmpty() && !value.equals("null"))
			return parse(value);
		return null;
	}

	@Override
	public Class<DateTime> handledType() {
		return DateTime.class;
	}

	public static DateTime parse(String value) {
		return CalendarUtils.fromString(value, DateTimeZone.getDefault());
	}
}