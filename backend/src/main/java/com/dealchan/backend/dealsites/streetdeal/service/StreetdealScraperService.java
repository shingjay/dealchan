package com.dealchan.backend.dealsites.streetdeal.service;

import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.dealsites.streetdeal.entity.StreetdealDeal;
import com.dealchan.backend.dealsites.streetdeal.repository.StreetdealRepository;
import com.dealchan.backend.utils.web.CustomWebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: ong0
 * Date: 4/5/12
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */

public class StreetdealScraperService implements DealSiteService{


    private CustomWebClient webClient;

    @Autowired
    private void setCustomWebClient(CustomWebClient webClient1) {
        this.webClient = webClient1;
    }

    @Autowired
    private StreetdealRepository repository;
    
    String streetdealURL = "http://www.streetdeal.my/home/rss";
    
    public List<StreetdealDeal> scrap() throws FeedException {
        ArrayList<StreetdealDeal> deals = new ArrayList<StreetdealDeal>();
        Document doc = webClient.getPageAsXml(streetdealURL);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed feed = syndFeedInput.build(doc);
        List<SyndEntry> feedList = feed.getEntries();
        int count = 0;
        NodeList list = doc.getElementsByTagName("item");

        for(int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            // per item
            NodeList child = node.getChildNodes();

            StreetdealDeal streetdealDeal = new StreetdealDeal();

            for(int j = 0; j < list.getLength(); j++) {
                //contents of item
                if(child.item(j) instanceof Element) {
                    String text = child.item(j).getNodeName();
                    
                    if(text.equalsIgnoreCase("title")) {
                        streetdealDeal.setTitle(child.item(j).getTextContent());
                    }
                    else if(text.equalsIgnoreCase("description")) {
                        streetdealDeal.setDescription(child.item(j).getTextContent());
                    }
                    else if(text.equalsIgnoreCase("deal:price")) {
                        streetdealDeal.setCurrentPrice(Double.parseDouble(child.item(j).getTextContent()));
                    }
                    else if(text.equalsIgnoreCase("deal:value")) {
                        streetdealDeal.setOriginalPrice(Double.parseDouble(child.item(j).getTextContent()));
                    }
                    else if(text.equalsIgnoreCase("deal:validUntil")) {

                        Date date = null;
                        try {
                            date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(child.item(j).getTextContent());
                        } catch (ParseException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        streetdealDeal.setValidDate(date);
                    }
                    else if(text.equalsIgnoreCase("deal:purchased")) {
                        streetdealDeal.setPurchased(Integer.parseInt(child.item(j).getTextContent()));
                    }
                    else if(text.equalsIgnoreCase("deal:image")) {
                        streetdealDeal.setImage(child.item(j).getTextContent());
                    }
                    else if(text.equalsIgnoreCase("link")) {
                        streetdealDeal.setLink(child.item(j).getTextContent());
                    }
                }
            }
            streetdealDeal.setAddress("Kuala Lumpur");
            deals.add(streetdealDeal);

        }
        return null;
    }
    
    public static void main(String args[]) throws Exception{
//        CustomWebClient webClient1 = new CustomWebClientImpl();
//        StreetdealScraperService service = new StreetdealScraperService();
//        service.setCustomWebClient(webClient1);
//        service.scrap();

    }

    @Override
    public List getDealsOfTheDay() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
