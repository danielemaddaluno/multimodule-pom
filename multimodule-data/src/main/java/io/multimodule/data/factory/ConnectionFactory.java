package io.multimodule.data.factory;

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import io.multimodule.data.mappers.SetNamesMapper;
import io.multimodule.data.typehandlers.bigmoney.CurrencyUnitTypeHandler;
import io.multimodule.data.typehandlers.locale.LocaleTypeHandler;
import io.multimodule.data.typehandlers.time.DateTimeJodaTypeHandler;
import io.multimodule.data.typehandlers.time.LocalDateJodaTypeHandler;
import io.multimodule.data.typehandlers.time.LocalDateTimeJodaTypeHandler;

@SuppressWarnings("unused")
public class ConnectionFactory {
	static FactoryType factoryType;
	private static SqlSessionFactory factory;
	private static final String PROJECT_PACKAGE = "io.multimodule.data";
	private static final String MAPPERS_PACKAGE = "io.multimodule.data.mappers";

	private static SqlSessionFactory getDevelopmentSqlSessionFactoryXml() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("database-config.xml");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return new SqlSessionFactoryBuilder().build(reader);
	}

	private static SqlSessionFactory getSqlSessionFactoryJava() {
		if (ConnectionFactory.factoryType == null)
			ConnectionFactory.factoryType = FactoryType.DEVELOPMENT;
		DataSource dataSource = factoryType.getDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();

		Environment environment = new Environment(factoryType.enviromentName(), transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);

		// Register BigMoney type handler
		TypeHandlerRegistry thr = configuration.getTypeHandlerRegistry();
		thr.register(CurrencyUnit.class, new CurrencyUnitTypeHandler());
		thr.register(LocalDateTime.class, new LocalDateTimeJodaTypeHandler());
		thr.register(DateTime.class, new DateTimeJodaTypeHandler());
		thr.register(LocalDate.class, new LocalDateJodaTypeHandler());
		thr.register(Locale.class, new LocaleTypeHandler());

// 		thr.register(Role.class, new EnumTypeHandler<Role>(Role.class));
//		thr.register(BigMoney.class, new BigMoneyTypeHandler());

		// this line added because in java fields are camelized but in the db fields the
		// underscores are used
		configuration.setMapUnderscoreToCamelCase(true);
		// this line adds all the mapper that for convention are located in the package
		// MAPPERS_PACKAGE
		configuration.addMappers(MAPPERS_PACKAGE);
		return new SqlSessionFactoryBuilder().build(configuration);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		if (factory == null) {
			factory = getSqlSessionFactoryJava();
//			ConnectionFactory.setUtf8mb4(factory);
		}
		return factory;
	}

	// https://kodejava.org/how-do-i-build-sqlsessionfactory-without-xml/
	private static void setUtf8mb4(SqlSessionFactory factory) {
		SqlSession session = factory.openSession();
		try {
			SetNamesMapper mapper = session.getMapper(SetNamesMapper.class);
			mapper.utf8mb4();
		} finally {
			session.close();
		}
	}

}
