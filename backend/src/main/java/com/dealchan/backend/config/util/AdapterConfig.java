package com.dealchan.backend.config.util;

import com.dealchan.backend.dealsource.adapter.DealSourceAdapterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 12:57 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class AdapterConfig {

    @Bean
    public DealSourceAdapterImpl dealSourceAdapter() {
        DealSourceAdapterImpl adapter = new DealSourceAdapterImpl();
        return adapter;
    }
}
