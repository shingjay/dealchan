package com.dealchan.backend.batch;

import com.dealchan.backend.dealsites.groupon.entity.GrouponDeal;
import com.dealchan.backend.dealsource.adapter.DealSourceAdapter;
import com.dealchan.backend.dealsource.entity.DealSource;
import com.dealchan.backend.utils.web.CustomWebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: anbiniyar
 * Date: 4/21/12
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrouponProcessor implements ItemProcessor<SyndEntry,DealSource> {


    private DealSourceAdapter dealSourceAdapter;
    private CustomWebClient webClient;
    private String url;

    public GrouponProcessor(String url)
    {
        this.url = url;
    }

    @Autowired
    public void setDealSourceAdapter(@Qualifier("dealSourceAdapter") DealSourceAdapter dealSourceAdapter) {
        this.dealSourceAdapter = dealSourceAdapter;
    }

    @Autowired
    public void setWebClient(CustomWebClient webClient1)
    {
        this.webClient = webClient1;
    }

    @Override
    public DealSource process(SyndEntry entry) throws Exception {

        GrouponDeal deal = new GrouponDeal();
        deal.setCountry("Malaysia");
        deal.setDescription(entry.getDescription().getValue());
        //System.out.println(deal.getDescription());
        deal.setLink(entry.getLink());
        deal.setPubDate(entry.getPublishedDate());
        deal.setTitle(entry.getTitle());

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

        return dealSourceAdapter.convert(deal);
    }
}
