package com.zqdfound.easyjobscheduler.service;

import com.sun.org.apache.regexp.internal.RE;
import com.zqdfound.easyjobscheduler.domain.Job;
import com.zqdfound.easyjobscheduler.domain.ResultInfo;
import com.zqdfound.easyjobscheduler.dto.OneTimeJobDTO;
import com.zqdfound.easyjobscheduler.dto.RepetitiveJobDTO;
import com.zqdfound.easyjobscheduler.mapper.JobMapper;
import com.zqdfound.easyjobscheduler.runnable.ReflectionInvoker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhuangqingdian
 * @date 2022/6/18
 */
@Service
public class JobService {

    @Autowired
    private JobManager jobManager;

    @Autowired
    private JobMapper jobMapper;


    public ResultInfo createOneTimeExecutionJob(OneTimeJobDTO oneTimeJobDTO) {
        Job job = Job.builder()
                .jobId(UUID.randomUUID().toString())
                .jobType("oneTime")
                .jobName(oneTimeJobDTO.getJobName())
                .lastExeStatus("notStart")
                .createTime(new Date())
                .build();
        jobMapper.addJob(job);
        return jobManager.addOneTimeJob(job.getJobId(),oneTimeJobDTO.getExeTime(),getMethodRunnableByName(job.getJobId(), oneTimeJobDTO.getMethodName()));
    }

    public ResultInfo createRepetitiveExecutionJob(RepetitiveJobDTO repetitiveJobDTO) {
        Job job = Job.builder()
                .jobId(UUID.randomUUID().toString())
                .jobType("repetitive")
                .jobName(repetitiveJobDTO.getJobName())
                .lastExeStatus("notStart")
                .createTime(new Date())
                .build();
        jobMapper.addJob(job);
        return jobManager.addRepetitiveJob(job.getJobId(),repetitiveJobDTO.getPeriod(),getMethodRunnableByName(job.getJobId(), repetitiveJobDTO.getMethodName()));
    }



    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateOneTimeExecutionJob(OneTimeJobDTO oneTimeJobDTO) {
        if(StringUtils.isEmpty(oneTimeJobDTO.getJobId())){
            return new ResultInfo("error","jobId required");
        }
        jobMapper.deleteById(oneTimeJobDTO.getJobId());
        jobManager.removeJob(oneTimeJobDTO.getJobId());
        Job job = Job.builder()
                .jobId(UUID.randomUUID().toString())
                .jobType("oneTime")
                .jobName(oneTimeJobDTO.getJobName())
                .lastExeStatus("notStart")
                .createTime(new Date())
                .build();
        jobMapper.addJob(job);
        return jobManager.addOneTimeJob(job.getJobId(),oneTimeJobDTO.getExeTime(),getMethodRunnableByName(job.getJobId(), oneTimeJobDTO.getMethodName()));
    }
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo updateRepetitiveExecutionJob(RepetitiveJobDTO repetitiveJobDTO) {
        if(StringUtils.isEmpty(repetitiveJobDTO.getJobId())){
            return new ResultInfo("error","jobId required");
        }
        jobMapper.deleteById(repetitiveJobDTO.getJobId());
        jobManager.removeJob(repetitiveJobDTO.getJobId());
        Job job = Job.builder()
                .jobId(UUID.randomUUID().toString())
                .jobType("repetitive")
                .jobName(repetitiveJobDTO.getJobName())
                .lastExeStatus("notStart")
                .createTime(new Date())
                .build();
        jobMapper.addJob(job);
        return jobManager.addRepetitiveJob(job.getJobId(),repetitiveJobDTO.getPeriod(),getMethodRunnableByName(job.getJobId(), repetitiveJobDTO.getMethodName()));
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo deleteJobById(String id) {
        jobMapper.deleteById(id);
       if(jobManager.removeJob(id)){
           return new ResultInfo("success","remove succeed");
        }
        return new ResultInfo("error","remove failed");
    }

    public List<Job> listAll() {
        return jobMapper.listAll();
    }
    public Job getJobById(String id) {
        return jobMapper.getJobById(id);
    }

    /**
     * get runnable job by method name
     * @param jobId
     * @param methodName
     * @return
     */
    private Runnable getMethodRunnableByName(String jobId,String methodName){
        return new ReflectionInvoker(jobId,methodName,this.jobMapper);
    }



}
