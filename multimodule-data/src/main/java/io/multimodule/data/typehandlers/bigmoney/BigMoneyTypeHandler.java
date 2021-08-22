package io.multimodule.data.typehandlers.bigmoney;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.BigMoney;

/**
 * Use this if it's ok to persist a BigMoney object as its string
 * representation (it may cause performance problems in ordering with sql)
 * @author madx
 *
 */
public class BigMoneyTypeHandler extends BaseTypeHandler<BigMoney> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BigMoney parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter.toString());
	}

	@Override
	public BigMoney getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String bigMoney = rs.getString(columnName);
		return getBigMoney(bigMoney);
	}

	@Override
	public BigMoney getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String bigMoney = rs.getString(columnIndex);
		return getBigMoney(bigMoney);
	}

	@Override
	public BigMoney getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String bigMoney = cs.getString(columnIndex);
		return getBigMoney(bigMoney);
	}

	private static BigMoney getBigMoney(String bigMoney) {
		if (bigMoney != null) return BigMoney.parse(bigMoney);
		return null;
	}
}
