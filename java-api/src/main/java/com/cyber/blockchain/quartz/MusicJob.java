package com.cyber.blockchain.quartz;

import org.quartz.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MusicJob implements Job {

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String x = jobDataMap.getString("x");
        String y = jobDataMap.getString("y");
        System.out.println(String.format("%s x=%s, y=%s", df.format(new Date()), x, y));
    }
}
