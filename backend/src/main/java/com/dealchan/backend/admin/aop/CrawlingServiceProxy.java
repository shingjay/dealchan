package com.dealchan.backend.admin.aop;

import com.dealchan.backend.admin.scheduler.CrawlingService;
import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.dealsource.adapter.DealSiteDealSourceAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class CrawlingServiceProxy<T> implements CrawlingService {

    private DealSiteDealSourceAdapter<T> dealSiteDealSourceAdapter;
    private DealSiteService<T> dealSiteService;

    public DealSiteDealSourceAdapter<T> getDealSiteDealSourceAdapter() {
        return dealSiteDealSourceAdapter;
    }

    public void setDealSiteDealSourceAdapter(DealSiteDealSourceAdapter<T> dealSiteDealSourceAdapter) {
        this.dealSiteDealSourceAdapter = dealSiteDealSourceAdapter;
    }

    public DealSiteService<T> getDealSiteService() {
        return dealSiteService;
    }

    public void setDealSiteService(DealSiteService<T> dealSiteService) {
        this.dealSiteService = dealSiteService;
    }

    @Override
    public void crawl() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
