package com.dealchan.backend.admin.controller;

import com.dealchan.backend.admin.scheduler.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:24 AM
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CrawlingService crawlingService;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public void checkStatus() {

    }

    @RequestMapping(value = "/crawl", method = RequestMethod.POST)
    public void crawl() {

    }

    @RequestMapping(value = "/{id}/stop", method = RequestMethod.DELETE)
    public void stop() {

    }

    @RequestMapping(value = "/stop", method = RequestMethod.DELETE)
    public void stopAll() {

    }
}
