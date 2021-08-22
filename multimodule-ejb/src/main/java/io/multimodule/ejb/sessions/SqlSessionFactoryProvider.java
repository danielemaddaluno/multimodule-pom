package io.multimodule.ejb.sessions;

import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public interface SqlSessionFactoryProvider {

    SqlSessionFactory produceFactory() throws IOException;
}
