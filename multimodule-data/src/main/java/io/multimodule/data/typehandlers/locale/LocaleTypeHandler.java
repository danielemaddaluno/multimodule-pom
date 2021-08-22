package io.multimodule.data.typehandlers.locale;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * Use this if it's ok to persist a Locale object as its string
 * representation (it may cause performance problems in ordering with sql)
 * 
 * @see https://stackoverflow.com/questions/12788685/saving-locale-in-a-database
 * @author madx
 *
 */
public class LocaleTypeHandler extends BaseTypeHandler<Locale> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Locale parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, toString(parameter));
	}

	@Override
	public Locale getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String tag = rs.getString(columnName);
		return fromString(tag);
	}

	@Override
	public Locale getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String tag = rs.getString(columnIndex);
		return fromString(tag);
	}

	@Override
	public Locale getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String tag = cs.getString(columnIndex);
		return fromString(tag);
	}
	
	private String toString(Locale locale) {
		return locale != null ? locale.toLanguageTag() : null;
	}
	
	private Locale fromString(String tag) {
		return tag != null ? Locale.forLanguageTag(tag) : null;
	}
	
}
