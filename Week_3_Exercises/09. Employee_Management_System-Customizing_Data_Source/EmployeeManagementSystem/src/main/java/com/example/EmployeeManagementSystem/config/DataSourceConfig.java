package com.example.EmployeeManagementSystem.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "departmentDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.department")
    public DataSource departmentDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "employeeJdbcTemplate")
    public JdbcTemplate employeeJdbcTemplate(@Qualifier("employeeDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "departmentJdbcTemplate")
    public JdbcTemplate departmentJdbcTemplate(@Qualifier("departmentDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
