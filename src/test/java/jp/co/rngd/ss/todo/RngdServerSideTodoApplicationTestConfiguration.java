package jp.co.rngd.ss.todo;


import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

public class RngdServerSideTodoApplicationTestConfiguration {
    @Bean
    public DataSource dataSource() {
        return new TransactionAwareDataSourceProxy(
                DataSourceBuilder
                    .create()
                    .username("springuser")
                    .password("springuser")
                    .url("jdbc:mysql://192.168.99.101:3306/rngd_ss_todo")
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build()
        );
    }
}
