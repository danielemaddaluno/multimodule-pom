package io.multimodule.data.factory;

import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * http://hmkcode.com/mybatis-spring-junit-the-missing-part/
 * http://www.mybatis.org/spring/mappers.html
 * This class helps you to make test over a test enviroment. Each class which
 * should test over the TEST {@link FactoryType} should extends this class.
 * @author madx
 *
 */
public class DbTest {
	static { ConnectionFactory.factoryType = FactoryType.TEST; }
	
	public static final void setDataSource() {}
	
	/**
	 * From every test you can call the name.getMethodName()
	 * to get the currently test name
	 */
	@Rule public TestName name = new TestName();
    
//	@BeforeClass
//	public static void onceExecutedBeforeAll() {
//		System.out.println("@BeforeClass: onceExecutedBeforeAll");
//		ConnectionFactory.factoryType = FactoryType.TEST;
//	}
//
//	@AfterClass
//	public static void onceExecutedAfterAll() {
//		System.out.println("@AfterClass: onceExecutedAfterAll");
//		ConnectionFactory.factoryType = FactoryType.TEST;
//	}
}
