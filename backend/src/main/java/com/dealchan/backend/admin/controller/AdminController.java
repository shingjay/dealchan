package com.dealchan.backend.admin.controller;

import com.dealchan.backend.admin.scheduler.CrawlerStatus;
import com.dealchan.backend.admin.scheduler.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 3:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CrawlingService crawlingService;

    @RequestMapping(value = "status", method = RequestMethod.GET)
    public String checkStatus(Model model) {
        System.out.println("REALLY?");
        System.out.println("DONE");
        
        model.addAttribute("status", crawlingService.getCrawlerStatus());
        return "status";
    }

    @RequestMapping(value = "crawl", method = RequestMethod.POST)
    public void startCrawling() {
        System.out.println("WHY THE FUCK CALL ME?");
        crawlingService.crawl();
    }

    @RequestMapping(value = "{id}/stop", method = RequestMethod.DELETE)
    public void stop() {

    }

    @RequestMapping(value = "stop", method = RequestMethod.DELETE)
    public void stopAll() {
       crawlingService.stopAll();
    }
}
