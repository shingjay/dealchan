package com.dealchan.backend.admin.scheduler;

import com.dealchan.backend.dealsites.DealSiteService;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CrawlerStatus {
    
    private String name;
    private Status status;
    private DealSiteService service;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DealSiteService getService() {
        return service;
    }

    public void setService(DealSiteService service) {
        this.service = service;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        IDLE,
        RUNNING,
        ERROR;
    }
}
