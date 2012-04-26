package com.dealchan.backend.dealsites.groupon;

import com.dealchan.backend.config.ProfileConstant;
import com.dealchan.backend.config.SchedulerContext;
import com.dealchan.backend.dealsites.groupon.entity.GrouponDeal;
import com.dealchan.backend.dealsites.groupon.service.GrouponScraperService;
import com.dealchan.backend.utils.web.CustomWebClient;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
* Created by IntelliJ IDEA.
* User: anbiniyar
* Date: 3/3/12
* Time: 9:57 PM
* To change this template use File | Settings | File Templates.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = ProfileConstant.YINGZHE_PROFILE)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SchedulerContext.class})
public class GrouponScraperTest {

    @Autowired
    private GrouponScraperService scraperService;

    @Autowired
    private CustomWebClient customWebClient;

    //@Test
    // solution seems to be ask not throw exception if 404 or any errors
    public void brokenJavascriptHrefError() throws Exception {
        URL url = new URL("http://www.groupon.my/deals/klang-valley-kuala-lumpur/zen-korean-bbq/716003506?CID=MY_RSS_217_389_189_22&utm_source=rss_217&utm_medium=rss_389&utm_campaign=rss_189&utm_content=rss_22");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        InputStream stream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        PrintWriter writer = new PrintWriter("/tmp/anbin");

        while(true) {
            String input = reader.readLine();
            if(input == null) {
                break;
            }

            writer.println(input);
        }

        writer.close();
        reader.close();


        customWebClient.getPage("http://www.groupon.my/deals/klang-valley-kuala-lumpur/zen-korean-bbq/716003506?CID=MY_RSS_217_389_189_22&utm_source=rss_217&utm_medium=rss_389&utm_campaign=rss_189&utm_content=rss_22");

    }

    @Test
    public void testScrap() throws Exception {
        List<GrouponDeal> deals = scraperService.scrapAll();
        for(GrouponDeal deal : deals) {
            System.out.println("title : " + deal.getTitle());
            System.out.println("address: " +deal.getAddress());
            String description = deal.getDescription();
            String clean = Jsoup.parse(description).text();

            //System.out.println("description : " + clean);

            if(description == null || description.equals("") || description.isEmpty()) {
                Assert.fail("Shit happened");
            }
            //System.out.println(deal.getCity());
        }
    }
}
