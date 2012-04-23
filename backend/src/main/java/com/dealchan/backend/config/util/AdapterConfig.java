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

    //src
    /*private long id;
    private String title;
    private String link;
    private String description;
    private Date pubDate;
    private String city;
    private String country;
    private double currentPrice;
    private double discount;
    private double saving;
    private double originalPrice;
    private Timestamp timeEnds;
    private String extraInformation;
    private boolean active;
    private int bought;
    private String image;
    private String address;*/

    //dest
    /*private Long id;
    private String title;
    private double price;
    private double discount;
    private double originalPrice;
    private String description;
    private Timestamp dealEnds;
    private Timestamp createdAt;
    private String address;
    private String imageUrl;
    private String dealUrl;
    private String category;
    private String city;
    private String country;*/

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

    @Bean
    public DealSourceAdapterImpl dealSourceAdapter() {
        DealSourceAdapterImpl adapter = new DealSourceAdapterImpl();

        Properties grouponMappingProperties = getGrouponMappingProperties();
        adapter.addCustomMapping(GrouponDeal.class, grouponMappingProperties);

        return adapter;
    }
//
//    @Bean
//    public CustomWebClient customWebClient() {
//        return new CustomWebClientImpl();
//    }
//
//    @Bean
//    @Scope(value = "step")
//    public RSSReader rssReader() {
//        RSSReader reader = new RSSReader("http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-kuala-lumpur");
//        reader.setWebClient(customWebClient());
//        return reader;
//    }
//
//    @Bean
//    public RSSWriter rssWriter() {
//        RSSWriter writer = new RSSWriter();
//        writer.setDealSourceRepository(dealSourceRepository);
//        return writer;
//    }
//
//    @Bean
//    private GrouponProcessor grouponProcessor() {
//        GrouponProcessor grouponProcessor = new GrouponProcessor();
//        grouponProcessor.setDealSourceAdapter(dealSourceAdapter());
//        return grouponProcessor;
//    }
//
//    @Bean
//    private TaskletStep step() {
//        TaskletStep taskletStep = new TaskletStep();
//        taskletStep.
//
//    }
//
//    @Bean
//    private Tasklet tasklet() {
//        ChunkOrientedTasklet chunkOrientedTasklet = new ChunkOrientedTasklet();
//    }


}
