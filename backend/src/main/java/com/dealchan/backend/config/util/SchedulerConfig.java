package com.dealchan.backend.config.util;

import com.dealchan.backend.dealsites.groupon.service.GrouponScraperService;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;

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
    public CronTriggerBean cronTriggerBean() throws ParseException
    {
        CronTriggerBean cronTriggerBean = new CronTriggerBean();
        cronTriggerBean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        cronTriggerBean.setStartDelay(10000);
        cronTriggerBean.setCronExpression("0 0 0 1/1 * ? *");   //cron expression: runs everyday at 12am. Ref: http://www.cronmaker.com
        return cronTriggerBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean()
    {
        try
        {
            CronTriggerBean cronTriggerBean = cronTriggerBean();
            SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
            schedulerFactoryBean.setJobDetails(new JobDetail[] {cronTriggerBean.getJobDetail()});
            schedulerFactoryBean.setTriggers(new Trigger[]{cronTriggerBean});
            return schedulerFactoryBean;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
