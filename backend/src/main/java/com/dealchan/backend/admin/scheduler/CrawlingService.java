package com.dealchan.backend.admin.scheduler;

import com.dealchan.backend.dealsites.DealSiteService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CrawlingService {
    public void crawlAll();
    public void crawl(DealSiteService dealSiteService);
    public void stopAll();
    public void stop(DealSiteService dealSiteService);
    public List<CrawlerStatus> getCrawlerStatus();
}
