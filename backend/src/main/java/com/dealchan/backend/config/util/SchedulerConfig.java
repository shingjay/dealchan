package com.dealchan.backend.config.util;

import com.dealchan.backend.dealsites.groupon.service.GrouponScraperService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerBean;

/**
 * Created by IntelliJ IDEA.
 * User: yingzhe
 * Date: 3/28/12
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SchedulerConfig {
    
    @Bean
    public GrouponScraperService grouponScraperService()
    {
        return new GrouponScraperService();
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setConcurrent(false);
        factoryBean.setTargetObject(grouponScraperService());
        factoryBean.setTargetMethod("getDealsOfTheDay");
        return factoryBean;
    }

    @Bean
    public SimpleTriggerBean simpleTriggerBean()
    {
        SimpleTriggerBean simpleTriggerBean = new SimpleTriggerBean();
        simpleTriggerBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        simpleTriggerBean.setStartDelay(10000);
        simpleTriggerBean.setRepeatInterval(300000);
        return simpleTriggerBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean()
    {
        SimpleTriggerBean simpleTriggerBean = simpleTriggerBean();
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobDetails(new JobDetail[] {simpleTriggerBean.getJobDetail()});
        schedulerFactoryBean.setTriggers(new Trigger[]{simpleTriggerBean});
        return schedulerFactoryBean;
    }
}
