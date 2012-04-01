package com.dealchan.backend.scheduler;

import org.quartz.JobExecutionException;

/**
 * Created by IntelliJ IDEA.
 * User: yingzhe
 * Date: 4/1/12
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Job {
    public void execute() throws JobExecutionException;
}
