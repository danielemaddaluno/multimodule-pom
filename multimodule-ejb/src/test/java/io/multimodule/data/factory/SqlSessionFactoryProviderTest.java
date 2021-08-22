package io.multimodule.data.factory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.cdi.SessionFactoryProvider;

import io.multimodule.ejb.sessions.SqlSessionFactoryProvider;

public class SqlSessionFactoryProviderTest implements SqlSessionFactoryProvider {
	
	@Override
	@TestSessionFactory
    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    public SqlSessionFactory produceFactory() {
        return ConnectionFactory.getSqlSessionFactory();
    }
}
