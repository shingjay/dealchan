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

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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

    //@Autowired
    private Properties schedulerProperties;

    private void processDatabase()
    {
        schedulerProperties = new Properties();
        try {
            //load a properties file
            schedulerProperties.load(new FileInputStream("scheduler.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ProcessBuilder pb = new ProcessBuilder("python", schedulerProperties.getProperty("root.path") + schedulerProperties.getProperty("scripts.path") + schedulerProperties.getProperty("processor.filename"));
        //Map<String, String> env = pb.environment();
        pb.directory(new File(schedulerProperties.getProperty("root.path") + schedulerProperties.getProperty("scripts.path")));
        InputStream in = null;
        BufferedInputStream buf = null;
        InputStreamReader inread = null;
        BufferedReader bufferedreader = null;
        InputStream in2 = null;
        BufferedInputStream buf2 = null;
        InputStreamReader inread2 = null;
        BufferedReader bufferedreader2 = null;
        try
        {
            Process p = pb.start();

            in = p.getInputStream();
            buf = new BufferedInputStream(in);
            inread = new InputStreamReader(buf);
            bufferedreader = new BufferedReader(inread);
            in2 = p.getErrorStream();
            buf2 = new BufferedInputStream(in2);
            inread2 = new InputStreamReader(buf2);
            bufferedreader2 = new BufferedReader(inread2);

            String line;
            while ((line = bufferedreader.readLine()) != null) {
                System.out.println(line);
            }

            while ((line = bufferedreader2.readLine()) != null) {
                System.out.println(line);
            }

            try
            {
                if (p.waitFor() != 0)
                {
                    System.err.println("error: exit value = " + p.exitValue());
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    bufferedreader.close();
                    inread.close();
                    buf.close();
                    in.close();
                    bufferedreader2.close();
                    inread2.close();
                    buf2.close();
                    in2.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<GrouponDeal> scrapAll() throws FeedException {
        String [] grouponMYURLs = {
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/johor",
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-kuala-lumpur",
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/klang-valley-selangor",
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/penang",
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/sabah",
            "http://api-asia.groupon.de/feed/api/v1/deals/oftheday/MY/travelcity"
        };
        List<GrouponDeal> deals = new ArrayList<GrouponDeal>();
        for (int i = 0 ; i < grouponMYURLs.length ; i++) {
            List<GrouponDeal> MYDeals = (List<GrouponDeal>)scrap(grouponMYURLs[i]);
            for (GrouponDeal gd : MYDeals)
            {
                gd.setCountry("Malaysia");
                grouponDealRepository.save(gd);
            }
            deals.addAll(MYDeals);
        }

        processDatabase();

        return deals;
    }

    public List<GrouponDeal> scrap(String url) throws FeedException {

        ArrayList<GrouponDeal> grouponDealList = new ArrayList<GrouponDeal>();

        Document doc = webClient.getPageAsXml(url);

        SyndFeedInput syndFeedInput = new SyndFeedInput();
        SyndFeed feed = syndFeedInput.build(doc);
        List<SyndEntry> feedList = feed.getEntries();

        for(SyndEntry f : feedList) {

            GrouponDeal deal = new GrouponDeal();
            deal.setDescription(f.getDescription().getValue());
            //System.out.println(deal.getDescription());
            deal.setLink(f.getLink());
            deal.setPubDate(f.getPublishedDate());
            deal.setTitle(f.getTitle());

            System.out.println("deal link : " + deal.getLink());
            
            // visit the link
            HtmlPage htmlPage = (HtmlPage)webClient.getPage(deal.getLink());

            HtmlElement element = htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div[3]/div[2]/div/div/h2").isEmpty() ? null : (HtmlElement)htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div[3]/div[2]/div/div/h2").get(0);
            String header = null;
            if (element != null)
            {
                header = element.asText();
            }
            else
            {
                header = "";
            }
            element = (HtmlElement) htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div[3]/div[2]/div/div").get(0);
            Iterable<HtmlElement> elements = element.getChildElements();
            for(HtmlElement e : elements) {
                element.removeChild(e);
            }
            deal.setAddress(header + ", " + element.asText());

            //xpath of city: /html/body/div/div[8]/div/a/span/span
            if (url.endsWith("travelcity"))
            {
                deal.setCity("Travel");
            }
            else
            {
                deal.setCity(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[8]/div/a/span/span").get(0))).asText().split("Deals")[0]);
            }
            //xpath of currentPrice: /html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span
            deal.setCurrentPrice(Double.parseDouble(((HtmlSpan)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[2]/form/div/span/span").get(0))).asText().split("RM")[1].replaceAll(",", "")));
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

            //checks if deal is sold out
            try
            {
                Calendar timeNow = Calendar.getInstance();
                //xpath for hour: //*[@id="hoursLeft"]
                System.out.print(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"hoursLeft\"]").get(0))).asText() + ",");
                timeNow.add(Calendar.HOUR, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"hoursLeft\"]").get(0))).asText()));
                //xpath for minute: //*[@id="minutesLeft"]
                System.out.print(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"minutesLeft\"]").get(0))).asText() + ",");
                timeNow.add(Calendar.MINUTE, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"minutesLeft\"]").get(0))).asText()));
                //xpath for second: //*[@id="secondsLeft"]
                System.out.print(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"secondsLeft\"]").get(0))).asText() + ",\n");
                timeNow.add(Calendar.SECOND, Integer.parseInt(((HtmlListItem)(htmlPage.getByXPath("//*[@id=\"secondsLeft\"]").get(0))).asText()));
                deal.setTimeEnds(new Timestamp(timeNow.getTimeInMillis()));
            }
            catch (Exception e)
            {
                Calendar timeNow = Calendar.getInstance();
                timeNow.add(Calendar.HOUR, 99999);
                deal.setTimeEnds(new Timestamp(timeNow.getTimeInMillis()));
            }

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
            // /html/body/div/div[9]/div[2]/div/div/div[3]/div/img

            if (!htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img").isEmpty())
            {
                deal.setImage(((HtmlImage)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/form/button/img").get(0))).getSrcAttribute());
            }
            else if (!htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/img").isEmpty())
            {
                deal.setImage(((HtmlImage)(htmlPage.getByXPath("/html/body/div/div[9]/div[2]/div/div/div[3]/div/img").get(0))).getSrcAttribute());
            }

            //deal = grouponDealRepository.save(deal);
            grouponDealList.add(deal);
        }

        return grouponDealList;
    }

    /* What is this for?
    @Override
    public GrouponDeal getDeal() {
        return null;
    }*/

    @Override
    public List getDealsOfTheDay() {
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
