package com.bonobo.config;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * To execute various SQL statements, MyBatis would need a SqlSessionFactory
 * object at runtime.
 * Using standard properties in application.yml, Micronaut will define a Hikari-based
 * data source, which will be injected to define SqlSessionFactory.
 */
@Factory
public class MyBatisFactory {

    private final DataSource dataSource;

    public MyBatisFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Context
    public SqlSessionFactory sqlSessionFactory() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("petclinic", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers("com.bonobo.repository");
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
