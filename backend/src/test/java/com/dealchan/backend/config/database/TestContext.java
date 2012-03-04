package com.dealchan.backend.config.database;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 3/3/12
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = "com.dealchan.backend", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION) )
@Import(value = {DatabaseConfig.class, YingZheDatabaseConfig.class, AnbinDatabaseConfig.class,DevDatabaseConfig.class})
public class TestContext {
}
