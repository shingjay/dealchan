//package com.dealchan.backend.dealsource;
//
//import com.dealchan.backend.config.ProfileConstant;
//import com.dealchan.backend.config.database.DatabaseConfig;
//import com.dealchan.backend.config.database.TesDatabaseConfig;
//import com.dealchan.backend.dealsource.entity.DealSource;
//import com.dealchan.backend.dealsource.repository.DealSourceRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import java.util.List;
//
//import static org.junit.Assert.fail;
//
///**
// * Created by IntelliJ IDEA.
// * User: anbiniyar
// * Date: 2/29/12
// * Time: 3:30 AM
// * To change this template use File | Settings | File Templates.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles(profiles = ProfileConstant.ANBIN_PROFILE)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class, TesDatabaseConfig.class} )
//public class DealSourceTest {
//
//    @Autowired
//    private DealSourceRepository dealSourceRepository;
//
//    @Test
//    public void testSave() {
//        DealSource dealSource = new DealSource();
//        dealSource.setTitle("Testing");
//        dealSourceRepository.save(dealSource);
//
//        List<DealSource> dealSources = dealSourceRepository.findAll();
//
//        if(dealSources.size() == 0 ) {
//
//            fail("Didn't work");
//        }
//
//        for(DealSource source : dealSources ) {
//            System.out.println(source.getTitle());
//        }
//
//    }
//}
