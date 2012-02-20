package com.dealchan.backend.dealsites.groupon;

import com.dealchan.utils.web.CustomWebClient;
import com.dealchan.utils.web.CustomWebClientImpl;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GrouponScraper {
    
    public static final String GROUPON_URL = "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-kuala-lumpur";

    @Autowired
    private CustomWebClient webClient;

    public void scrap() throws FeedException {

        Document doc = webClient.getPageAsXml(GROUPON_URL);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed feed = syndFeedInput.build(doc);
        List<SyndEntry> feedList = feed.getEntries();
        
        for(SyndEntry f : feedList) {

            GrouponDeal deal = new GrouponDeal();
            deal.setDescription(f.getDescription().toString());
            deal.setLink(f.getLink());
            deal.setPubDate(f.getPublishedDate());
            deal.setTitle(f.getTitle());

            // visist the link
            webClient.getPage(deal.getLink());
        }
    }
    
    
    public static void main(String args[]) throws FeedException {
        CustomWebClient client = new CustomWebClientImpl();
        GrouponScraper scraper = new GrouponScraper();
        scraper.webClient = client;
        scraper.scrap();

    }

}
