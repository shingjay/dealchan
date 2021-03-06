package com.dealchan.backend.config.database;

import com.dealchan.backend.config.ProfileConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Profile(ProfileConstant.ANBIN_PROFILE)
public class AnbinDatabaseConfig {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:mysql://localhost:8889/senior_design");
        source.setUsername("root");
        source.setPassword("root");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        return source;
    }
}
