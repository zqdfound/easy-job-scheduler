package com.zqdfound.easyjobscheduler.dto;

import lombok.Data;

import java.util.Date;

/**
 * one time execution job request dto
 * @author zhuangqingdian
 * @date 2022/6/18
 */
@Data
public class OneTimeJobDTO {

    private String jobId;

    private String jobName;

    private Date exeTime;

    private String methodName;
}
