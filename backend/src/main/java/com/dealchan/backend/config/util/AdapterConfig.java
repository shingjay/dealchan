package com.dealchan.backend.config.util;

import com.dealchan.backend.dealsource.adapter.DealSourceAdapterImpl;
import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import com.dealchan.backend.utils.web.CustomWebClient;
import com.dealchan.backend.utils.web.CustomWebClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DealSourceRepository dealSourceRepository;

    @Bean
    public DealSourceAdapterImpl dealSourceAdapter() {
        DealSourceAdapterImpl adapter = new DealSourceAdapterImpl();
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
