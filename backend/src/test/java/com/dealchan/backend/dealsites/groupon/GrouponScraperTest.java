package com.dealchan.backend.dealsites.groupon;

import com.dealchan.backend.config.ProfileConstant;
import com.dealchan.backend.config.database.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 3/3/12
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = ProfileConstant.YINGZHE_PROFILE)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContext.class})
public class GrouponScraperTest {

    @Autowired
    private GrouponScraper scraper;

    @Test
    public void testScrap() throws Exception {
        scraper.scrap();
    }
}
