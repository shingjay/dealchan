package com.dealchan.backend.config.database;

import com.dealchan.backend.config.ProfileConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by IntelliJ IDEA.
 * User: yingzhe
 * Date: 2/22/12
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Profile(ProfileConstant.YINGZHE_PROFILE)
public class YingZheDatabaseConfig {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/sd");
        source.setUsername("root");
        source.setPassword("password");
        source.setDriverClassName("com.mysql.jdbc.Driver");

        return source;
    }
}
