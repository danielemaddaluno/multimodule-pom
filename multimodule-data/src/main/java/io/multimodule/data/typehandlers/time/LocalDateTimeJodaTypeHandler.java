package io.multimodule.data.typehandlers.time;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

/**
 * @author madx
 */
public class LocalDateTimeJodaTypeHandler extends BaseTypeHandler<LocalDateTime> {
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime value, JdbcType jdbcType)
			throws SQLException {
		Timestamp t = value != null ? new Timestamp(value.toDateTime(DateTimeZone.UTC).toDate().getTime()) : null;
		ps.setObject(i, t);
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp t = rs.getTimestamp(columnName);
		return getLocalDateTime(t);
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp t = rs.getTimestamp(columnIndex);
		return getLocalDateTime(t);
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp t = cs.getTimestamp(columnIndex);
		return getLocalDateTime(t);
	}

	private static LocalDateTime getLocalDateTime(Timestamp t) {
		return t != null ? new LocalDateTime(t.getTime(), DateTimeZone.UTC) : null;
	}
}
