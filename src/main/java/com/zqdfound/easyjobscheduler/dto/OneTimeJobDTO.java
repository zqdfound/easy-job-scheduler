package com.zqdfound.easyjobscheduler.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
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
