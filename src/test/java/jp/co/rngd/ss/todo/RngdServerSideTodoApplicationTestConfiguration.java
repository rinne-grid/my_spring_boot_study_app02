package jp.co.rngd.ss.todo;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import jp.co.rngd.ss.todo.entity.RngdDataSource;

public class RngdServerSideTodoApplicationTestConfiguration {
    @Autowired
    RngdDataSource rngdDataSource;
    @Bean
    public DataSource dataSource() {
        System.out.println(rngdDataSource.getUsername());
        return new TransactionAwareDataSourceProxy(
                DataSourceBuilder
                    .create()
                    .username(rngdDataSource.getUsername())
                    .password(rngdDataSource.getPassword())
                    .url(rngdDataSource.getUrl())
                    .driverClassName(rngdDataSource.getDriverClassName())
                    .build()
        );
    }
}
