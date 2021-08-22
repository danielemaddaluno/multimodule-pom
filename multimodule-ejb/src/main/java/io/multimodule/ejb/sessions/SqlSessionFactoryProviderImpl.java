package io.multimodule.ejb.sessions;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.cdi.SessionFactoryProvider;

import io.multimodule.data.factory.ConnectionFactory;

public class SqlSessionFactoryProviderImpl implements SqlSessionFactoryProvider {
	@Override
    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    public SqlSessionFactory produceFactory() {
        return ConnectionFactory.getSqlSessionFactory();
    }
}
