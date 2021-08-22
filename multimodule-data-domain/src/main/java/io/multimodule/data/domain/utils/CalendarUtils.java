package io.multimodule.data.domain.utils;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class CalendarUtils {

	/**
	 * Questo formato è lo stesso usato in DateTime.toString() di Joda It's the
	 * ISO8601 format (yyyy-MM-ddTHH:mm:ss.SSSZZ)
	 */
	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
	public static final String CALENDAR_PATTERN = "yyyy-MM-dd";
	private static final String DAY_FORMAT = "dd";
	private static final String MONTH_FORMAT = "MMM";

	/* TIME ZONES */
	/* TIME ZONES */
	/* TIME ZONES */

	public static TimeZone timeZone(String timezone) {
		return TimeZone.getTimeZone(timezone);
	}

	public static DateTimeZone dateTimeZone(TimeZone timezone) {
		return DateTimeZone.forTimeZone(timezone);
	}

	public static DateTimeZone dateTimeZone(String timezone) {
		return DateTimeZone.forID(timezone);
	}

	/* STRING DAY MONTH */
	/* STRING DAY MONTH */
	/* STRING DAY MONTH */

	protected static String getDay(DateTimeZone dateTimeZone, DateTime dateTime) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(DAY_FORMAT);
		return dtf.print(dateTime.withZone(dateTimeZone));
	}

	protected static String getMonth(DateTimeZone dateTimeZone, DateTime dateTime) {
		DateTimeFormatter dtf = DateTimeFormat.forPattern(MONTH_FORMAT);
		return dtf.print(dateTime.withZone(dateTimeZone));
	}

	public static String getFullDay(DateTimeZone dateTimeZone, DateTime dateTime) {
		return getDay(dateTimeZone, dateTime) + " " + getMonth(dateTimeZone, dateTime);
	}

	public static String getFullDaysDateTime(DateTimeZone dateTimeZone, List<DateTime> calendars) {
		if (calendars == null || calendars.size() == 0) {
			return StringUtils.EMPTY;
		} else if (calendars.size() == 1) {
			return getFullDay(dateTimeZone, calendars.get(0));
		} else {
			Collections.sort(calendars);
			DateTime first = calendars.get(0);
			DateTime last = calendars.get(calendars.size() - 1);

			String firstDay = getDay(dateTimeZone, first);
			String firstMonth = getMonth(dateTimeZone, first);
			String lastDay = getDay(dateTimeZone, last);
			String lastMonth = getMonth(dateTimeZone, last);

			if (firstMonth.equalsIgnoreCase(lastMonth)) {
				return firstDay + "-" + lastDay + " " + firstMonth;
			} else {
				return firstDay + " " + firstMonth + "-" + lastDay + " " + lastMonth;
			}
		}
	}

	/* CALENDAR */
	/* CALENDAR */
	/* CALENDAR */

	public static Calendar todayCalendarNoHourMinuteSecond(TimeZone zone) {
		Calendar today = Calendar.getInstance(zone);
		removeHourMinuteSecond(today);
		return today;
	}

	public static void removeHourMinuteSecond(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	public static Calendar toCalendar(DateTime dt) {
		return dt.toGregorianCalendar();
	}

	public static List<Calendar> toCalendars(List<DateTime> dateTimes) {
		List<Calendar> calendars = new ArrayList<>();
		if (dateTimes != null && !dateTimes.isEmpty()) {
			for (DateTime date : dateTimes) {
				calendars.add(toCalendar(date));
			}
		}
		return calendars;
	}

	/* DATETIME */
	/* DATETIME */
	/* DATETIME */

	public static DateTime toDateTime(Date date) {
		return date != null ? new DateTime(date) : null;
	}

	public static DateTime getStartDateTimeFromYearMonth(DateTimeZone dateTimeZone, int year, int month) {
		return new DateTime(year, month, 1, 0, 0, dateTimeZone);
	}

	public static DateTime getEndDateTimeFromYearMonth(DateTimeZone dateTimeZone, int year, int month) {
		DateTime endDateTime = getStartDateTimeFromYearMonth(dateTimeZone, year, month);
		return endDateTime.plusMonths(1);
	}

	public static DateTime getStartDateTimeFromYearMonth(DateTimeZone dateTimeZone, YearMonth yearMonth) {
		return getStartDateTimeFromYearMonth(dateTimeZone, yearMonth.getYear(), yearMonth.getMonthOfYear());
	}

	public static DateTime getEndDateTimeFromYearMonth(DateTimeZone dateTimeZone, YearMonth yearMonth) {
		return getEndDateTimeFromYearMonth(dateTimeZone, yearMonth.getYear(), yearMonth.getMonthOfYear());
	}

	public static DateTime getStartOfDay(DateTimeZone dateTimeZone) {
		return DateTime.now(dateTimeZone).withTimeAtStartOfDay();
	}

	public static DateTime getStartOfDay(DateTimeZone dateTimeZone, DateTime dateTime) {
		return dateTime.withZone(dateTimeZone).withTimeAtStartOfDay();
	}

	public static DateTime getEndOfDay(DateTimeZone dateTimeZone, DateTime dateTime) {
		return getStartOfDay(dateTimeZone, dateTime).plusHours(23).plusMinutes(59).plusSeconds(59);
	}


	/* YEARMONTH */
	/* YEARMONTH */
	/* YEARMONTH */

	public static boolean equalsYearMonth(YearMonth ym1, YearMonth ym2) {
		return ym1 != null && ym2 != null && ym1.getYear() == ym2.getYear()
				&& ym1.getMonthOfYear() == ym2.getMonthOfYear() ? true : false;
	}

	public static YearMonth getCurrentYearMonth(DateTimeZone dateTimeZone) {
		return YearMonth.now(dateTimeZone);
	}

	public static List<YearMonth> addCurrentYearMonth(DateTimeZone dateTimeZone, List<YearMonth> yearMonths) {
		YearMonth currentYearMonth = CalendarUtils.getCurrentYearMonth(dateTimeZone);
		if (yearMonths.isEmpty() || !CalendarUtils.equalsYearMonth(yearMonths.get(0), currentYearMonth)) {
			ArrayList<YearMonth> yearMonth2 = new ArrayList<>();
			yearMonth2.add(currentYearMonth);
			yearMonth2.addAll(yearMonths);
			return yearMonth2;
		}
		return yearMonths;
	}

	/* FROMSTRING - TOSTRING */
	/* FROMSTRING - TOSTRING */
	/* FROMSTRING - TOSTRING */

	/**
	 * 
	 * @param value
	 * @param dateTimeZone
	 * @param format        can be null, if null it is not used
	 * @param encodeForHtml
	 * @return
	 */
	public static String toString(DateTime value, DateTimeZone dateTimeZone, String format) {
		return value != null ? value.withZone(dateTimeZone).toString(format) : StringUtils.EMPTY;
	}

	public static String toString(DateTime value, DateTimeZone dateTimeZone) {
		return toString(value, dateTimeZone, null);
	}
	
	public static String toStringEncoded(DateTime value, DateTimeZone dateTimeZone) {
		String dateTime = toString(value, dateTimeZone, null);
		return encodeUrl_UTF_8(dateTime);
	}

	/**
	 * 
	 * @param value
	 * @param dateTimeZone
	 * @param format       can be null, if null it is not used
	 * @return
	 */
	public static DateTime fromString(String value, DateTimeZone dateTimeZone, String format) {
		DateTimeFormatter formatter = format != null ? DateTimeFormat.forPattern(format) : null;
		DateTime dateTime = formatter != null ? DateTime.parse(value, formatter) : DateTime.parse(value);
		return dateTime.withZone(dateTimeZone);
	}

	public static DateTime fromString(String value, DateTimeZone dateTimeZone) {
		return fromString(value, dateTimeZone, null);
	}

	/* APP UTILS */
	/* APP UTILS */
	/* APP UTILS */
	
	private static String encodeUrl_UTF_8(String text) {
		return StringEscapeUtils.escapeHtml4(text).replace("+", "%2B");
	}

	public static boolean containedInsideDays(List<DateTime> dates, DateTime dateTime, DateTimeZone dateTimeZone) {
		for (DateTime d : dates) {
			if (CalendarUtils.getStartOfDay(dateTimeZone, dateTime).getMillis() == CalendarUtils
					.getStartOfDay(dateTimeZone, d).getMillis()) {
				return true;
			}
		}
		return false;
	}

	public static List<DateTime> getDays(List<DateTime> dateTimes, DateTimeZone dateTimeZone) {
		Set<DateTime> days = new HashSet<>();
		for (DateTime dateTime : dateTimes) {
			days.add(CalendarUtils.getStartOfDay(dateTimeZone, dateTime));
		}
		
		List<DateTime> daysList = new ArrayList<DateTime>(days);
		Collections.sort(daysList);
		return daysList;
	}

	public static boolean isNowBetweenDates(DateTime startTime, DateTime endTime) {
		return !(startTime.isAfterNow() || endTime.isBeforeNow());
	}
	
	public static int sumMonthsAndGetDays(LocalDate date, int months) {
		LocalDate ld = date;
		LocalDate ldPlus1m = ld.plusMonths(months);
		return Days.daysBetween(ld, ldPlus1m).getDays();
	}
	
	// @see https://www.unixtimeconverter.io/1567382400
	// https://stackoverflow.com/questions/28808305/java-8-how-to-create-a-zoneddatetime-from-an-epoch-value/28808481
	public static ZonedDateTime zonedDateTimeFromEpoch(long unixTimestamp) {
		Instant instant = Instant.ofEpochSecond(unixTimestamp);
		return ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
	}
	
	public static LocalDateTime localDateTimeFromEpoch(long unixTimestamp) {
		ZonedDateTime zdt = zonedDateTimeFromEpoch(unixTimestamp);
		Date date = Date.from(zdt.toInstant());
		return LocalDateTime.fromDateFields(date);
	}
	
	public static LocalDate localDateFromEpoch(long unixTimestamp) {
		LocalDateTime ldt = localDateTimeFromEpoch(unixTimestamp);
		return ldt.toLocalDate();
	}

}