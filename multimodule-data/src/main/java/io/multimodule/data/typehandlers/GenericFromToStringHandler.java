package io.multimodule.data.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class GenericFromToStringHandler<T> extends BaseTypeHandler<T> {
	private FromString<T> fromStringInterface;

	public GenericFromToStringHandler(FromString<T> fromStringInterface) {
		super();
		this.fromStringInterface = fromStringInterface;
	}

	public static interface FromString<T>{
		public String toString(T value);
		public T fromString(String stringValue);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T value, JdbcType jdbcType) throws SQLException {
		ps.setString(i, this.fromStringInterface.toString(value));
	}

	@Override
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String stringValue = rs.getString(columnName);
		return this.fromStringInterface.fromString(stringValue);
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String stringValue = rs.getString(columnIndex);
		return this.fromStringInterface.fromString(stringValue);
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String fromString = cs.getString(columnIndex);
		return this.fromStringInterface.fromString(fromString);
	}
}
