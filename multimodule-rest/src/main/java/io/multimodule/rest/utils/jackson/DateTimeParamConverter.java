package io.multimodule.rest.utils.jackson;

import javax.ws.rs.ext.ParamConverter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import io.multimodule.data.domain.utils.CalendarUtils;

public class DateTimeParamConverter implements ParamConverter<DateTime> {

	@Override
	public DateTime fromString(String value) {
		if (value != null && !value.isEmpty() && !value.equals("null"))
			return CalendarUtils.fromString(value, DateTimeZone.getDefault());
		return null;
	}

	@Override
	public String toString(DateTime value) {
		return value != null ? CalendarUtils.toStringEncoded(value, DateTimeZone.getDefault()) : null;
	}

}