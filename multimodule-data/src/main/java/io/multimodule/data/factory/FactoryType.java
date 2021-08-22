package io.multimodule.data.factory;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.jndi.JndiDataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import io.multimodule.data.mappers.SetNamesMapper;

public enum FactoryType {

	DEVELOPMENT(DriverType.MARIADB, "jdbc:mysql://localhost:3306/multimoduleDev", "root", "root", false),
	TEST(DriverType.MARIADB, "jdbc:mysql://localhost:3306/multimoduleTest", "root", "root", false),
	DEVELOPMENT_JNDI("java:/MultiModuleTestDS", true), TEST_JNDI("java:/MultiModuleTest", true);

	private String driver;
	private String url;
	private String username;
	private String password;

	private String jndiName;

	private boolean jndi;

	FactoryType(DriverType driver, String url, String username, String password, boolean jndi) {
		this.driver = driver.toString();
		this.url = url;
		this.username = username;
		this.password = password;
		this.jndi = jndi;
	}

	FactoryType(String jndiName, boolean jndi) {
		this.jndiName = jndiName;
		this.jndi = jndi;
	}

	public String enviromentName() {
		return this.name().toLowerCase();
	}

	public DataSource getDataSource() {
		if (this.jndi) {
			JndiDataSourceFactory jndiFactory = new JndiDataSourceFactory();
			Properties props = new Properties();
			props.setProperty(JndiDataSourceFactory.DATA_SOURCE, this.jndiName);
			// props.setProperty("autoCommitOnClose", "false");

			jndiFactory.setProperties(props);
			return jndiFactory.getDataSource();
		} else {
			PooledDataSource datasource = new PooledDataSource(this.driver, this.url, this.username, this.password);

			// @see http://burtsev.net/en/2012/01/12/100/
			datasource.setPoolPingQuery(SetNamesMapper.SET_NAMES_UTF8MB4);
			datasource.setPoolPingEnabled(true);

			Properties driverProps = new Properties();
			driverProps.setProperty("passwordCharacterEncoding", StandardCharsets.UTF_8.name());
			datasource.setDriverProperties(driverProps);
			
			return datasource;
		}
	}

}
