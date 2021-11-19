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
        //TODO add mappers
        //configuration.addMapper();
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
