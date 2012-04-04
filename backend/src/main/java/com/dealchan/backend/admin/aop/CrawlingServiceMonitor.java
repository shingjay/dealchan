package com.dealchan.backend.admin.aop;

import com.dealchan.backend.admin.scheduler.CrawlerStatus;
import com.dealchan.backend.admin.scheduler.CrawlingServiceImpl;
import com.dealchan.backend.dealsites.DealSiteService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/29/12
 * Time: 1:52 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Aspect
public class CrawlingServiceMonitor {
    
    @Autowired
    private List<DealSiteService> dealSiteServiceList;
    
    @Autowired
    private CrawlingServiceImpl crawlingService;
    
    private List<CrawlerStatus> crawlerStatusList;
    
    private HashMap<Class, CrawlerStatus> crawlerStatusHashMap;

    @Pointcut("within(com.dealchan.backend.admin.scheduler.CrawlingServiceImpl)")
    public void allCrawlingServiceImpleMethod() {}

    @Pointcut("execution(* void setDealSiteService(..))")
    public void setDealSiteService() {}

    @Pointcut("within(com.dealchan.backend.dealsites.DealSiteService)")
    public void allDealSiteServiceMethods() {}

    @Pointcut("execution(public * getDealsOfTheDay())")
    public void getDealsOfTheDay() {};

    @PostConstruct
    public void initialize() {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("I AM CALLEEDDDDD!");
        
        List<CrawlerStatus> crawlerStatusList = new ArrayList<CrawlerStatus>();
        crawlerStatusHashMap = new HashMap<Class, CrawlerStatus>();

        for(DealSiteService dealSiteService : dealSiteServiceList) {
            CrawlerStatus crawlerStatus = new CrawlerStatus();
            crawlerStatus.setName(dealSiteService.getClass().getSimpleName());
            crawlerStatus.setService(dealSiteService);
            crawlerStatus.setStatus(CrawlerStatus.Status.IDLE);
            crawlerStatusHashMap.put(dealSiteService.getClass(), crawlerStatus);
            crawlerStatusList.add(crawlerStatus);
        }

        crawlingService.setCrawlerStatusList(crawlerStatusList);

        System.out.println("I AM DONEEE!");
    }


    @Around("execution(public * getDealsOfTheDay())")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        
        System.out.println("FUCKA YOU");
        
        Object retVal = null;
        CrawlerStatus status = crawlerStatusHashMap.get(pjp.getThis().getClass());

        try {
            status.setStatus(CrawlerStatus.Status.RUNNING);
            retVal = pjp.proceed();
            status.setStatus(CrawlerStatus.Status.IDLE);

        } catch (Exception ex) {
            status.setStatus(CrawlerStatus.Status.ERROR);
            throw ex;
        }

        return retVal;
    }


}
