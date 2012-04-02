//package com.dealchan.backend.utils.web;
//
//import com.dealchan.backend.config.ProfileConstant;
//import com.dealchan.backend.config.database.TestContext;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
///**
// * Created by IntelliJ IDEA.
// * User: ong0
// * Date: 3/20/12
// * Time: 7:05 PM
// * To change this template use File | Settings | File Templates.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = ProfileConstant.YINGZHE_PROFILE)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestContext.class})
//public class CustomWebClientImplTest {
//
//    @Autowired
//    private CustomWebClient customWebClient;
//
//    @Test
//    public void testGetPageWithoutError(){
//        String url = "https://www.google.com/";
//        customWebClient.getPage(url);
//    }
//
//    @Test(expected = Exception.class)
//    public void testGetPageWithError(){
//        String url = "http://www.groupon.my/deals/klang-valley-kuala-lumpur/zen-korean-bbq/%3Ca%20href=";
//        customWebClient.getPage(url);
//    }
//
//    @Test
//    public void testGetPageWhenSubUrlHasError(){
//        String url = "http://www.groupon.my/deals/klang-valley-kuala-lumpur/zen-korean-bbq/716003506?CID=MY_RSS_217_389_189_22&utm_source=rss_217&utm_medium=rss_389&utm_campaign=rss_189&utm_content=rss_22";
//        customWebClient.getPage(url);
//    }
//}
