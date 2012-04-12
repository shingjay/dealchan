package com.dealchan.backend.admin.controller;

import com.dealchan.backend.admin.scheduler.CrawlerStatus;
import com.dealchan.backend.admin.scheduler.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String checkStatus(Model model, @ModelAttribute("success") String value) {
        System.out.println("REALLY?");
        System.out.println("DONE");
        
        model.addAttribute("status", crawlingService.getCrawlerStatus());
        //model.addAttribute("success", value);
        return "status";
    }

    @RequestMapping(value = "crawl", method = RequestMethod.POST)
    public void startCrawling() {
        System.out.println("WHY THE FUCK CALL ME?");
        crawlingService.crawlAll();
    }

    @RequestMapping(value = "start/{id}", method = RequestMethod.POST)
    public String start(@PathVariable int id, final RedirectAttributes redirectAttrs) {

        redirectAttrs.addFlashAttribute("success", "GOOD");
        System.out.println("FUCK");

        List<CrawlerStatus> crawlerStatuses = crawlingService.getCrawlerStatus();
        CrawlerStatus status = crawlerStatuses.get(id - 1);
        crawlingService.crawl(status.getService());

        return "redirect:../status";

    }

    @RequestMapping(value = "start", method = RequestMethod.POST)
    public String startAll(final RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success", "GOOD");
        System.out.println("FUCK");

        crawlingService.crawlAll();

        return "redirect:status";
    }


    @RequestMapping(value = "stop/{id}", method = RequestMethod.DELETE)
    public String stop(@PathVariable int id, final RedirectAttributes redirectAttrs) {

        redirectAttrs.addFlashAttribute("success", "VERY");
        System.out.println("FUCK");

        List<CrawlerStatus> crawlerStatuses = crawlingService.getCrawlerStatus();
        CrawlerStatus status = crawlerStatuses.get(id - 1);
        crawlingService.stop(status.getService());

        return "redirect:../status";

    }

    @RequestMapping(value = "stop", method = RequestMethod.DELETE)
    public String stopAll(final RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("success", "VERY");
        System.out.println("FUCK");

        crawlingService.stopAll();

        return "redirect:status";
    }
}
