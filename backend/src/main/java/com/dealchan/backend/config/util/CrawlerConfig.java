package com.dealchan.backend.config.util;

import com.dealchan.backend.utils.web.CustomWebClient;
import com.dealchan.backend.utils.web.CustomWebClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrawlerConfig {

    @Bean
    public CustomWebClient webClient() {
        CustomWebClient customWebClient = new CustomWebClientImpl();
        customWebClient.getWebClient().setThrowExceptionOnScriptError(false);
        return customWebClient;
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(25);
    }

}
