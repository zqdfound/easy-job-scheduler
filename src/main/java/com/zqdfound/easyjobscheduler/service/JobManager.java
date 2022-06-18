package com.zqdfound.easyjobscheduler.service;

import com.zqdfound.easyjobscheduler.domain.Job;
import com.zqdfound.easyjobscheduler.domain.JobTask;
import com.zqdfound.easyjobscheduler.domain.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create/Read/Update/Delete jobs to run
 * @author zhuangqingdian
 * @date 2022/6/17
 */
@Component
@Slf4j
public class JobManager implements DisposableBean {

    //using concurrentHashMap to storage jobTasks
    private final Map<String, JobTask> jobTaskMap = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    /**
     * add one time execution job
     * @param jobId
     * @param execTime
     * @param jobRunnable
     * @return
     */
    public ResultInfo addOneTimeJob(String jobId,Date execTime, Runnable jobRunnable){
        log.info("adding job-{} to scheduler",jobId);
        try {
            addJobTask(jobId,getOneTimeExeJob(jobRunnable,execTime));
        } catch (Exception e) {
            return new ResultInfo("error","add one time execution job failed："+e.getMessage());
        }
        return new ResultInfo("success","add one time execution job succeed");
    }

    /**
     * add repetitive execution job
     * @param jobId
     * @param period (seconds)
     * @param jobRunnable
     * @return
     */
    public ResultInfo addRepetitiveJob(String jobId,long period, Runnable jobRunnable){
        log.info("adding job-{} to scheduler",jobId);
        try {
            addJobTask(jobId,getRepetitiveExeJob(jobRunnable,period));
        } catch (Exception e) {
            return new ResultInfo("error","add repetitive execution job failed："+e.getMessage());
        }
        return new ResultInfo("success","add repetitive execution job succeed");
    }

    private void addJobTask(String jobId, JobTask jobTask) {
        if(jobTaskMap.containsKey(jobId)){
            //job already exist
            removeJob(jobId);
        }
        jobTaskMap.put(jobId,jobTask);
    }

    /**
     * remove job by jobId
     * @param jobId
     * @return
     */
    public boolean removeJob(String jobId) {
        JobTask jobTask = jobTaskMap.remove(jobId);
        if(jobTask != null){
            return jobTask.cancel();
        }
        return false;
    }

    /**
     * get one time execution job
     * @param job
     * @param execTime job execution startTime
     * @return
     */
    private JobTask getOneTimeExeJob(Runnable job, Date execTime){
        JobTask jobTask = new JobTask();
        jobTask.future = taskScheduler.schedule(job,execTime);
        return jobTask;
    }
    /**
     * get repetitive execution job at a fixed interval
     * @param job
     * @param periodSeconds job execution period (seconds)
     * @return
     */
    private JobTask getRepetitiveExeJob(Runnable job, long periodSeconds){
        //Convert seconds to milliseconds
        long period = periodSeconds * 1000;
        JobTask jobTask = new JobTask();
        jobTask.future = taskScheduler.scheduleAtFixedRate(job,period);
        return jobTask;
    }

    /**
     * close all jobs when bean destroy
     * @throws Exception
     */
    @Override
    public void destroy() {
        for (JobTask j: jobTaskMap.values()) {
            try {
                j.cancel();
            } catch (Exception e) {
                log.error("close job-{} failed",j.toString());
            }
        }
        jobTaskMap.clear();
    }

}
