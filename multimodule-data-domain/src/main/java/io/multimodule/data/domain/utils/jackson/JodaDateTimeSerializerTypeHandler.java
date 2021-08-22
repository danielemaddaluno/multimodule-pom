package io.multimodule.data.domain.utils.jackson;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.multimodule.data.domain.utils.CalendarUtils;

public class JodaDateTimeSerializerTypeHandler extends JsonSerializer<DateTime> {

	@Override
	public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		String stringValue = value != null ? parse(value) : null;
		if (stringValue != null) {
			jgen.writeString(stringValue);
		} else {
			jgen.writeNull();
		}
	}

	@Override
	public Class<DateTime> handledType() {
		return DateTime.class;
	}

	public static String parse(DateTime value) {
		if (value == null)
			return null;
		return CalendarUtils.toString(value, DateTimeZone.getDefault());
	}

}