package com.dealchan.backend.scheduler;

import com.dealchan.backend.config.ProfileConstant;
import com.dealchan.backend.config.SchedulerContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created with IntelliJ IDEA.
 * User: yingzhe
 * Date: 4/1/12
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = ProfileConstant.YINGZHE_PROFILE)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SchedulerContext.class})
public class GrouponTest {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //@Autowired
    //private MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean;

    //@Autowired
    //private SimpleTriggerBean simpleTriggerBean;

    @Test
    public void testGrouponJob() throws Exception
    {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.start();
        //scheduler.scheduleJob(methodInvokingJobDetailFactoryBean.getObject(), simpleTriggerBean);
    }
}
