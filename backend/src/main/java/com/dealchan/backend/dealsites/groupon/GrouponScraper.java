package com.dealchan.backend.dealsites.groupon;

import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.utils.web.CustomWebClient;
import com.dealchan.backend.utils.web.CustomWebClientImpl;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import javax.inject.Qualifier;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GrouponScraper implements DealSiteService<GrouponDeal> {
    
    public static final String GROUPON_URL = "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-kuala-lumpur";

//    @Autowired
    private CustomWebClient webClient;

    public void scrap() throws FeedException {

        Document doc = webClient.getPageAsXml(GROUPON_URL);

        // deals with rss
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
            HtmlPage page = (HtmlPage) webClient.getPage(deal.getLink());

            // your code here

        }
    }

    @Override
    public List<GrouponDeal> getDealsOfTheDay() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String args[]) throws FeedException {
        CustomWebClient client = new CustomWebClientImpl();
        GrouponScraper scraper = new GrouponScraper();
        scraper.webClient = client;
        scraper.scrap();

    }

}
