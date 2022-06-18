package com.zqdfound.easyjobscheduler.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Job entity ,persist in db
 * @author zhuangqingdian
 * @date 2022/6/17
 */
@Data
@Builder
public class Job {
    /**
     * job unique id
     */
    private String jobId;
    /**
     * job types:
     *          oneTime
     *          repetitive
     */
    private String jobType;
    private String jobName;
    /**
     * notStart
     * failed
     * succeed
     */
    private String lastExeStatus;

    private Date createTime;
}
