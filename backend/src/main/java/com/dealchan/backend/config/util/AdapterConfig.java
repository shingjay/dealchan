package com.dealchan.backend.config.util;

import com.dealchan.backend.dealsites.groupon.entity.GrouponDeal;
import com.dealchan.backend.dealsource.adapter.DealSourceAdapterImpl;
import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;


/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 12:57 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class AdapterConfig {

    @Autowired
    private DealSourceRepository dealSourceRepository;

    private Properties getGrouponMappingProperties()
    {
        Properties grouponMappingProperties = new Properties();
        grouponMappingProperties.setProperty("title", "title");
        grouponMappingProperties.setProperty("currentPrice", "price");
        grouponMappingProperties.setProperty("discount", "discount");
        grouponMappingProperties.setProperty("originalPrice", "originalPrice");
        grouponMappingProperties.setProperty("description", "description");
        grouponMappingProperties.setProperty("timeEnds", "dealEnds");
        grouponMappingProperties.setProperty("address", "address");
        grouponMappingProperties.setProperty("image", "imageUrl");
        grouponMappingProperties.setProperty("link", "dealUrl");
        grouponMappingProperties.setProperty("city", "city");
        grouponMappingProperties.setProperty("country", "country");
        return grouponMappingProperties;
    }

    @Bean(name = "customAdapter")
    public DealSourceAdapterImpl dealSourceAdapter() {
        DealSourceAdapterImpl adapter = new DealSourceAdapterImpl();

        Properties grouponMappingProperties = getGrouponMappingProperties();
        adapter.addCustomMapping(GrouponDeal.class, grouponMappingProperties);

        return adapter;
    }
}
