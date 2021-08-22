package io.multimodule.data.typehandlers.time;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * @author madx
 *
 */
public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime value, JdbcType jdbcType) throws SQLException {
		if(value!=null) value = value.truncatedTo(ChronoUnit.SECONDS);
		ps.setObject(i, value);
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		LocalDateTime l = rs.getObject(columnName, LocalDateTime.class);
		return l != null ? l.truncatedTo(ChronoUnit.SECONDS) : null;
	}

	@Override
	public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		LocalDateTime l = rs.getObject(columnIndex, LocalDateTime.class);
		return l != null ? l.truncatedTo(ChronoUnit.SECONDS) : null;
	}

	@Override
	public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		LocalDateTime l = cs.getObject(columnIndex, LocalDateTime.class);
		return l != null ? l.truncatedTo(ChronoUnit.SECONDS) : null;
	}
}
