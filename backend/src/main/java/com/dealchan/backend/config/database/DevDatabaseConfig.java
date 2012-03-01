package com.dealchan.backend.config.database;

import com.dealchan.backend.config.ProfileConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/1/12
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Profile(ProfileConstant.DEV_PROFILE)
public class DevDatabaseConfig {

//    @Bean
//    public DriverManagerDataSource dataSource() {
//        DriverManagerDataSource source = new DriverManagerDataSource();
//        source.setUrl("jdbc:mysql://localhost:3306/Test");
//        source.setUsername("root");
//        source.setPassword("root");
//        source.setDriverClassName("com.mysql.jdbc.Driver");
//
//        return source;
//    }
}
