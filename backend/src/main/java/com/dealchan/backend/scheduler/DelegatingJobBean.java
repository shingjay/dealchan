package com.dealchan.backend.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by IntelliJ IDEA.
 * User: yingzhe
 * Date: 4/1/12
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class DelegatingJobBean extends QuartzJobBean {
    private static final String APPLICATION_CONTEXT_KEY
            = "applicationContext";
    private static final String JOB_BEAN_NAME_KEY
            = "job.bean.name";
    protected Log log = LogFactory.getLog(getClass());

    @Override
    protected final void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = jobExecutionContext.getScheduler().getContext();
        } catch(SchedulerException e) {
            throw new JobExecutionException(
                    "Failure accessing scheduler context", e);
        }
        ApplicationContext appContext = (ApplicationContext)
                schedulerContext.get(APPLICATION_CONTEXT_KEY);
        String jobBeanName = (String) jobExecutionContext
                .getJobDetail()
                .getJobDataMap().get(JOB_BEAN_NAME_KEY);
        log.info("Starting job: " + jobBeanName);
        Job jobBean = (Job) appContext.getBean(jobBeanName);

        jobBean.execute();
    }
}
