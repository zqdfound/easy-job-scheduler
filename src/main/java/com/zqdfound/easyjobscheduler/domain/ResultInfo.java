package com.zqdfound.easyjobscheduler.domain;

import lombok.Data;

/**
 * @author zhuangqingdian
 * @date 2021/5/14
 */
@Data
public class ResultInfo {

    private String code;
    private String msg;

    public ResultInfo(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
