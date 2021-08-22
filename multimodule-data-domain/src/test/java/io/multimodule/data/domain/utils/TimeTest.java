package io.multimodule.data.domain.utils;

import static org.junit.Assert.assertEquals;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fasterxml.jackson.core.io.JsonStringEncoder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TimeTest  {
	@Test
	public void t1_select_one() {
		LocalDate date = LocalDate.now();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("EEE, dd MMM yyyy, HH:mm z");
		String str = date.toString(fmt);
		System.out.println(str);
		
		DateTime t = DateTime.now();
		str = CalendarUtils.toString(t, DateTimeZone.getDefault(), "EEE, dd MMM yyyy, HH:mm z");
		System.out.println(str);
		
	}
	
	@Test
	public void t1_select_one2() {
		System.out.println(WordUtils.capitalize("prova ciao adsas 023 fdafsd"));
		
	}
	
	@Test
	public void t1_select_one3() {
		String date = "2018-05-06T00:00:00+02:00";
		DateTime dt = CalendarUtils.fromString(date, DateTimeZone.getDefault());
		System.out.println(dt);
		
		
		System.out.println(CalendarUtils.toString(dt, DateTimeZone.getDefault()));
	}
	
	@Test
	public void t1_to_then_from_string() {
		DateTime dt = DateTime.now();
		String dts = CalendarUtils.toString(dt, DateTimeZone.getDefault());
		System.out.println(dts);
		
		DateTime dt2 = CalendarUtils.fromString(dts, DateTimeZone.getDefault());
		System.out.println(dt2);
		
		DateTime dt3 = CalendarUtils.fromString(dt.toString(), DateTimeZone.getDefault());
		System.out.println(dt3);
		
	}

	@Test
	public void t2_year_month() {
		DateTime dt = CalendarUtils.getStartDateTimeFromYearMonth(DateTimeZone.forID("CET"), 2018, 9);
		System.out.println(dt);
	}
	
	@Test
	public void t3_days() {
		DateTimeZone dateTimeZone = DateTimeZone.getDefault();
		Set<DateTime> days = new HashSet<>();
		
		String date = "2018-05-06T05:00:00+02:00";
		DateTime dt = CalendarUtils.fromString(date, DateTimeZone.getDefault());
		
		days.add(CalendarUtils.getStartOfDay(dateTimeZone, dt));
		
		String date2 = "2018-05-06T00:00:00+02:00";
		DateTime dt2 = CalendarUtils.fromString(date2, DateTimeZone.getDefault());
		days.add(CalendarUtils.getStartOfDay(dateTimeZone, dt2));
		
		assertEquals(1, days.size());
	}
	
	@Test
	public void t4_to_string() throws Exception {
		// StringEscapeUtils.escapeHtml4(dateStr).replace("+", "%2B")
		String date2 = "2018-05-06T00:00:00+02:00";
		DateTime dt2 = CalendarUtils.fromString(date2, DateTimeZone.getDefault());
		String test = CalendarUtils.toString(dt2, DateTimeZone.getDefault());
		String test2 = CalendarUtils.toString(dt2, DateTimeZone.getDefault());
		System.out.println(test);
		System.out.println(test2);
		
		System.out.println(StringEscapeUtils.escapeHtml4(test2).replace("+", "%2B"));
		System.out.println(test2.replace("+", "%2B"));
		System.out.println(JsonStringEncoder.getInstance().quoteAsString(test2));
		
		System.out.println(URLEncoder.encode("+", StandardCharsets.UTF_8.name()));
	}
	
	@Test
	public void t5_day_month() throws Exception {
		// StringEscapeUtils.escapeHtml4(dateStr).replace("+", "%2B")
		String date2 = "2018-05-06T00:30:00+02:00";
		
		DateTimeZone dtz = DateTimeZone.getDefault();
		DateTime dt = CalendarUtils.fromString(date2, dtz);
		
		System.out.println(CalendarUtils.getDay(dtz, dt));
		
		dtz = DateTimeZone.UTC;
		System.out.println(CalendarUtils.getDay(dtz, dt));
		
	}
	
}
