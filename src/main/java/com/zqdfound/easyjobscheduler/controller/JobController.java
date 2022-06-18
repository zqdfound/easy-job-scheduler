package com.zqdfound.easyjobscheduler.controller;

import com.zqdfound.easyjobscheduler.domain.Job;
import com.zqdfound.easyjobscheduler.domain.ResultInfo;
import com.zqdfound.easyjobscheduler.dto.OneTimeJobDTO;
import com.zqdfound.easyjobscheduler.dto.RepetitiveJobDTO;
import com.zqdfound.easyjobscheduler.mapper.JobMapper;
import com.zqdfound.easyjobscheduler.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * job controller
 * @author zhuangqingdian
 * @date 2022/6/17
 */
@Api(tags = "job-scheduler-api")
@RestController
@RequestMapping("/job")
public class JobController {


    @Autowired
    JobService jobService;

    @PutMapping("/oneTimeJob")
    @ApiOperation(value = "createOneTimeExecutionJob")
    public ResultInfo createOneTimeExecutionJob(@RequestBody OneTimeJobDTO oneTimeJobDTO){
        return jobService.createOneTimeExecutionJob(oneTimeJobDTO);
    }

    @PutMapping("/repetitiveJob")
    @ApiOperation(value = "create repetitive ExecutionJob")
    public ResultInfo createRepetitiveExecutionJob(@RequestBody RepetitiveJobDTO repetitiveJobDTO){
        return jobService.createRepetitiveExecutionJob(repetitiveJobDTO);
    }

    @PostMapping("/oneTimeJob")
    @ApiOperation(value = "update One Time Execution Job")
    public ResultInfo updateOneTimeExecutionJob(@RequestBody OneTimeJobDTO oneTimeJobDTO){
        return jobService.updateOneTimeExecutionJob(oneTimeJobDTO);
    }
    @PostMapping("/repetitiveJob")
    @ApiOperation(value = "update repetitive Execution Job")
    public ResultInfo updateRepetitiveExecutionJob(@RequestBody RepetitiveJobDTO repetitiveJobDTO){
        return jobService.updateRepetitiveExecutionJob(repetitiveJobDTO);
    }

    @DeleteMapping("/{jobId}")
    @ApiOperation(value = "delete job by job id")
    public ResultInfo deleteJobById(@PathVariable String jobId){
        return jobService.deleteJobById(jobId);
    }
    @GetMapping("/all")
    @ApiOperation(value = "list all jobs")
    public List<Job> listAll(){
        return jobService.listAll();
    }
    @GetMapping("/job/{id}")
    @ApiOperation(value = "get Job By Id")
    public Job getJobById(@PathVariable String id){
        return jobService.getJobById(id);
    }
}