package com.zqdfound.easyjobscheduler.dto;

import lombok.Data;

/**
 * Repetitive job request dto
 * @author zhuangqingdian
 * @date 2022/6/18
 */
@Data
public class RepetitiveJobDTO {
    private String jobId;
    private String jobName;
    private Long period;
    private String methodName;
}
