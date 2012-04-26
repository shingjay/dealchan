package com.dealchan.backend.dealsites.streetdeal.service;

/**
 * Created by IntelliJ IDEA.
 * User: ong0
 * Date: 4/17/12
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */

import com.dealchan.backend.config.ProfileConstant;
import com.dealchan.backend.config.SchedulerContext;
import com.dealchan.backend.dealsites.DealSiteService.*;
import com.dealchan.backend.dealsites.groupon.service.GrouponScraperService;
import com.dealchan.backend.dealsites.streetdeal.entity.StreetdealDeal;
import com.dealchan.backend.dealsites.streetdeal.entity.StreetdealDeal.*;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = ProfileConstant.ANBIN_PROFILE)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SchedulerContext.class})
public class StreetdealScraperServiceTest {

    @Autowired
    private StreetdealScraperService scraperService;

    @Autowired
    private CustomWebClient customWebClient;

    @Test
    public void testScrap() throws Exception {
        List<StreetdealDeal> deals = scraperService.scrap();
        for(StreetdealDeal deal : deals) {
            System.out.println("title : " + deal.getTitle());
            System.out.println("address: " +deal.getAddress());
            String description = deal.getDescription();
            String clean = Jsoup.parse(description).text();

            System.out.println("description : " + clean);

            if(description == null || description.equals("") || description.isEmpty()) {
                Assert.fail("Shit happened");
            }
            //System.out.println(deal.getCity());
        }
    }
}
