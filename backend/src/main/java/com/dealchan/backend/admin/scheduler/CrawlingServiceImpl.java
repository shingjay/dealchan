package com.dealchan.backend.admin.scheduler;

import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.dealsource.adapter.DealSourceAdapter;
import com.dealchan.backend.dealsource.entity.DealSource;
import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CrawlingServiceImpl implements CrawlingService{
    
    @Autowired
    private DealSourceAdapter adapter;
    

    private List<DealSiteService> dealSiteServiceList;
    
    @Autowired
    private DealSourceRepository dealSourceRepository;

    private List<CrawlerStatus> crawlerStatusList;

    
    private HashMap<Class, Thread> activeThreads;

    public CrawlingServiceImpl() {
        activeThreads = new HashMap<Class, Thread>();
    }

    @Autowired
    public void setDealSiteServiceList(List<DealSiteService> dealSiteServiceList ) {
        crawlerStatusList = new ArrayList<CrawlerStatus>();
        this.dealSiteServiceList = dealSiteServiceList;
    }
    
    @Override
    public void crawl() {
        
        // make sure no running threads are present
        for (Thread thread : activeThreads.values()) {
            if (thread.isAlive()) {
                throw new RuntimeException("Service is already running. Please quit all other services");
            }
        }
        
        activeThreads = new HashMap<Class, Thread>();

        for(DealSiteService service: dealSiteServiceList) {
            Task task = new Task(service, dealSourceRepository, adapter);
            Thread thread = new Thread(task);
            thread.start();

            activeThreads.put(service.getClass(), thread);
        }
    }

    @Override
    public void stopAll() {
        for(Thread thread: activeThreads.values()) {
            thread.interrupt();
        }
    }

    @Override
    public void stop(DealSiteService dealSiteService) {
        //To change body of implemented methods use File | Settings | File Templates.
        activeThreads.get(dealSiteService.getClass()).interrupt();
    }

    @Override
    public List<CrawlerStatus> getCrawlerStatus() {
        return this.crawlerStatusList;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
    public void setCrawlerStatusList(List<CrawlerStatus> crawlerStatusList) {
        this.crawlerStatusList = crawlerStatusList;
    }

    private class Task implements Runnable {
        
        private DealSiteService dealSiteService;
        private DealSourceRepository dealSourceRepository;   
        private DealSourceAdapter adapter;
        
        public Task(DealSiteService dealSiteService, 
                    DealSourceRepository dealSourceRepository,
                    DealSourceAdapter adapter ) {
            this.dealSiteService = dealSiteService;
            this.dealSourceRepository = dealSourceRepository;
            this.adapter = adapter;
        }
        
        public void run() {
            Collection<DealSource> dailyDealSources = adapter.convert(dealSiteService.getDealsOfTheDay());

            synchronized (dealSourceRepository) {
                dealSourceRepository.save(dailyDealSources);
            }
        }
    }
        
}
