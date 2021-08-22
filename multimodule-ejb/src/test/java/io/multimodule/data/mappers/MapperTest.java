package io.multimodule.data.mappers;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.junit.runner.RunWith;

import io.multimodule.data.factory.DbTest;
import io.multimodule.data.factory.TestSessionFactory;
import io.multimodule.data.factory.WeldJUnit4Runner;
import io.multimodule.ejb.VersionBean;

@RunWith(WeldJUnit4Runner.class)
public class MapperTest {
	static {
		DbTest.setDataSource();
	}
	
	@Inject
	@TestSessionFactory
	public TimeMapper timeMapper;
	
	@EJB
	public VersionBean versionBean;
	
}
