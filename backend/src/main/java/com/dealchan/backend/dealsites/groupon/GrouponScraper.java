package com.dealchan.backend.dealsites.groupon;

import com.dealchan.backend.dealsource.repository.DealSourceRepository;
import com.dealchan.utils.web.CustomWebClient;
import com.dealchan.utils.web.CustomWebClientImpl;
import com.gargoylesoftware.htmlunit.html.*;
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

    @Autowired
    private GrouponDealRepository grouponDealRepository;

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

            // visit the link
            HtmlPage htmlPage = (HtmlPage)webClient.getPage(deal.getLink());
            

            //xpath of city: /html/body/div/div[8]/div/a/span/span
            deal.setCity(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[8]/div/a/span/span").get(0))).asText().split("Deals")[0]);
            //xpath of currentPrice: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span
            deal.setCurrentPrice(Double.parseDouble(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span").get(0))).asText().split("RM")[1]));
            //xpath of discount: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td
            deal.setDiscount(Double.parseDouble(((HtmlTableDataCell)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td").get(0))).asText().split("%")[0]));
            //xpath of saving: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td[2]
            deal.setSaving(Double.parseDouble(((HtmlTableDataCell)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td[2]").get(0))).asText().split("RM")[1]));
            deal.setOriginalPrice(deal.getCurrentPrice() + deal.getSaving());
            //xpath of image url: /html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img
            deal.setImage(((HtmlImage)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img"))).getSrcAttribute());

            //System.out.println(deal.getDiscount());
        }
    }
    
    
    public static void main(String args[]) throws FeedException {
        CustomWebClient client = new CustomWebClientImpl();
        GrouponScraper scraper = new GrouponScraper();
        scraper.webClient = client;
        scraper.scrap();

    }

}
