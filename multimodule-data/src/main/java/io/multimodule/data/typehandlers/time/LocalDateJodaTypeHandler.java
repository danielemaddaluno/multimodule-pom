package io.multimodule.data.typehandlers.time;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 * @author madx
 */
public class LocalDateJodaTypeHandler extends BaseTypeHandler<LocalDate> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDate value, JdbcType jdbcType)
			throws SQLException {
		Timestamp t = value != null ? new Timestamp(value.toDate().getTime()) : null;
		ps.setObject(i, t);
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp t = rs.getTimestamp(columnName);
		return getLocalDate(t);
	}

	@Override
	public LocalDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp t = rs.getTimestamp(columnIndex);
		return getLocalDate(t);
	}

	@Override
	public LocalDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp t = cs.getTimestamp(columnIndex);
		return getLocalDate(t);
	}

	private static LocalDate getLocalDate(Timestamp t) {
		return t != null ? new LocalDate(t.getTime(), DateTimeZone.UTC) : null;
	}
}
