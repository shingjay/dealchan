package com.dealchan.backend.dealsites.groupon.service;

import com.dealchan.backend.dealsites.DealSiteService;
import com.dealchan.backend.dealsites.groupon.entity.GrouponDeal;
import com.dealchan.backend.dealsites.groupon.repository.GrouponDealRepository;
import com.dealchan.backend.utils.web.CustomWebClient;
import com.dealchan.backend.utils.web.CustomWebClientImpl;
import com.gargoylesoftware.htmlunit.html.*;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/20/12
 * Time: 8:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GrouponScraperService implements DealSiteService  {

    @Autowired
    private CustomWebClient webClient;

    @Autowired
    private GrouponDealRepository grouponDealRepository;


    public List<GrouponDeal> scrapAll() throws FeedException {
        String [] grouponURLs = {
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/johor"
            //"http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-kuala-lumpur",
            //"http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-selangor",
            //"http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/penang",
            //"http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/sabah"
            //http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/travelcity"
        };
        List<GrouponDeal> deals = new ArrayList<GrouponDeal>();
        for (int i = 0 ; i < grouponURLs.length ; i++) {
            deals.addAll((List<GrouponDeal>)scrap(grouponURLs[i]));
        }
        return deals;
    }

    public List<GrouponDeal> scrap(String url) throws FeedException {

        ArrayList<GrouponDeal> grouponDealList = new ArrayList<GrouponDeal>();

        Document doc = webClient.getPageAsXml(url);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed feed = syndFeedInput.build(doc);
        List<SyndEntry> feedList = feed.getEntries();

        //test quartz
        int i = 0;
        
        for(SyndEntry f : feedList) {
            //test quartz
            if (i == 3) break;
            i++;

            GrouponDeal deal = new GrouponDeal();
            deal.setDescription(f.getDescription().getValue());
            //System.out.println(deal.getDescription());
            deal.setLink(f.getLink());
            deal.setPubDate(f.getPublishedDate());
            deal.setTitle(f.getTitle());

            System.out.println("deal link : " + deal.getLink());
            
            // visit the link
            HtmlPage htmlPage = (HtmlPage)webClient.getPage(deal.getLink());

            deal.setAddress(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div[3]/div[2]/div/div/h2").get(0))).asText());

            //xpath of city: /html/body/div/div[8]/div/a/span/span
            deal.setCity(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[8]/div/a/span/span").get(0))).asText().split("Deals")[0]);
            //xpath of currentPrice: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span
            deal.setCurrentPrice(Double.parseDouble(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span").get(0))).asText().split("RM")[1]));
            //xpath of discount: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td
            if (!htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td").isEmpty())
            {
                deal.setDiscount(Double.parseDouble(((HtmlTableDataCell)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td").get(0))).asText().split("%")[0]));
                //xpath of saving: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td[2]
                deal.setSaving(Double.parseDouble(((HtmlTableDataCell)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/table/tbody/tr[2]/td[2]").get(0))).asText().split("RM")[1].replaceAll(",", "")));
                deal.setOriginalPrice(deal.getCurrentPrice() + deal.getSaving());
            }
            else
            {
                deal.setOriginalPrice(deal.getCurrentPrice());
            }

            Calendar timeNow = Calendar.getInstance();
            //xpath for hour: //*[@id="hoursLeft"]
            timeNow.add(Calendar.HOUR, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"hoursLeft\"]").get(0))).asText()));
            //xpath for minute: //*[@id="minutesLeft"]
            timeNow.add(Calendar.MINUTE, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"minutesLeft\"]").get(0))).asText()));
            //xpath for second: //*[@id="secondsLeft"]
            timeNow.add(Calendar.SECOND, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"secondsLeft\"]").get(0))).asText()));
            deal.setTimeEnds(new Timestamp(timeNow.getTimeInMillis()));

            //xpath for active deal://*[@id="dealTakePlace"]
            deal.setActive(!htmlPage.getByXPath("//*[@id=\"dealTakePlace\"]").isEmpty());

            //xpath for amount sold: //*[@id=""jDealSoldAmount""]
            if (((HtmlSpan)(htmlPage.getByXPath("//*[@id=\"jDealSoldAmount\"]").get(0))).asText().equals(""))
            {
                deal.setBought(0);
            }
            else
            {
                deal.setBought(Integer.parseInt(((HtmlSpan)(htmlPage.getByXPath("//*[@id=\"jDealSoldAmount\"]").get(0))).asText()));
            }
            //xpath of image url: /html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img
            deal.setImage(((HtmlImage)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img").get(0))).getSrcAttribute());

            deal = grouponDealRepository.save(deal);
            grouponDealList.add(deal);
        }

        return grouponDealList;
    }

    @Override
    public List getDealsOfTheDay() {
        System.out.println("FUCK YEA!");
        try {
            return scrapAll();  //To change body of implemented methods use File | Settings | File Templates.
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) throws FeedException {
        CustomWebClient client = new CustomWebClientImpl();
        GrouponScraperService scraperService = new GrouponScraperService();
        scraperService.webClient = client;
        scraperService.scrapAll();

    }

}
