package com.dealchan.backend.config;

import com.dealchan.backend.config.database.AnbinDatabaseConfig;
import com.dealchan.backend.config.database.DatabaseConfig;
import com.dealchan.backend.config.database.DevDatabaseConfig;
import com.dealchan.backend.config.database.YingZheDatabaseConfig;
import com.dealchan.backend.config.util.SchedulerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Created with IntelliJ IDEA.
 * User: yingzhe
 * Date: 4/1/12
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = "com.dealchan.backend", excludeFilters = @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION) )
@Import(value = {DatabaseConfig.class, YingZheDatabaseConfig.class, AnbinDatabaseConfig.class,DevDatabaseConfig.class, SchedulerConfig.class})
public class SchedulerContext {
}
