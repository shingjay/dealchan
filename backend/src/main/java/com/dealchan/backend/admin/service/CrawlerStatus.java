package com.dealchan.backend.admin.service;

import com.dealchan.backend.admin.scheduler.CrawlingService;

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
    private CrawlingService service;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Status {
        IDLE,
        RUNNING,
        ERROR;
    }
}
