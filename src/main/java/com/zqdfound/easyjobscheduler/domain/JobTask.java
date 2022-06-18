package com.zqdfound.easyjobscheduler.domain;

import java.util.concurrent.ScheduledFuture;

/**
 * @author zhuangqingdian
 * @date 2022/6/17
 */
public class JobTask {
    public volatile ScheduledFuture<?> future;
    //cancel job
    public boolean cancel(){
        if(this.future != null){
            return future.cancel(true);
        }
        return false;
    }
}
