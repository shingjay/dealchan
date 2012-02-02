package com.dealchan.backend.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/1/12
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableTransactionManagement
@ImportResource("WEB-INF/spring/jpa-config.xml")
public class DatabaseConfig implements TransactionManagementConfigurer {

    @Autowired
    DataSource dataSource;


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
