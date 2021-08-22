package io.multimodule.data.typehandlers.time;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @author madx
 */
public class DateTimeJodaTypeHandler extends BaseTypeHandler<DateTime> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime value, JdbcType jdbcType)
			throws SQLException {
		Timestamp t = value != null ? new Timestamp(value.toDateTime(DateTimeZone.UTC).toDate().getTime()) : null;
		ps.setObject(i, t);
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp t = rs.getTimestamp(columnName);
		DateTime dt = getDateTime(t);
		return dt;
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp t = rs.getTimestamp(columnIndex);
		DateTime dt = getDateTime(t);
		return dt;
	}

	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp t = cs.getTimestamp(columnIndex);
		DateTime dt = getDateTime(t);
		return dt;
	}

	private static DateTime getDateTime(Timestamp t) {
		return t != null ? new DateTime(t.getTime(), DateTimeZone.UTC) : null;
	}
}
