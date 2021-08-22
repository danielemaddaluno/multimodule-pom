package io.multimodule.data.factory;

public enum DriverType {
	MYSQL("com.mysql.jdbc.Driver"), MARIADB("org.mariadb.jdbc.Driver");

	private DriverType(String driver) {
		this.driver = driver;
	}

	private String driver;

	@Override
	public String toString() {
		return driver;
	}
}
