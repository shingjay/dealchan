package com.dealchan.backend.config.util;

import com.dealchan.backend.utils.web.CustomWebClient;
import com.dealchan.backend.utils.web.CustomWebClientImpl;
import org.springframework.context.annotation.Bean;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrawlerConfig {

    @Bean
    public CustomWebClient getClient() {
        return new CustomWebClientImpl();
    }

}
