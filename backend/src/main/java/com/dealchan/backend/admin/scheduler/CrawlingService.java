package com.dealchan.backend.admin.scheduler;

import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.dealsource.adapter.DealSiteDealSourceAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CrawlingService<T> {
    public DealSiteDealSourceAdapter<T> getDealSiteDealSourceAdapter();
    public DealSiteService<T> getDealSiteService();
    public void crawl();
}
