package com.zqdfound.easyjobscheduler.runnable;

import com.zqdfound.easyjobscheduler.mapper.JobMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * an invoker for execute custom job method
 * @author zhuangqingdian
 * @date 2022/6/18
 */
@Slf4j
public class ReflectionInvoker implements Runnable{

    private String jobId;
    private String methodName;
    private JobMapper jobMapper;

    public ReflectionInvoker(String jobId, String methodName,JobMapper jobMapper) {
        this.jobId = jobId;
        this.methodName = methodName;
        this.jobMapper = jobMapper;
    }

    @Override
    public void run() {
        log.info("begin job - {},methodName is {}",this.jobId,this.methodName);
       try {
           Class catClass = Class.forName("com.zqdfound.easyjobscheduler.demos.JobMethods");
           Method method = catClass.getDeclaredMethod(this.methodName);
           method.setAccessible(true);
           String invoke = (String) method.invoke(catClass.newInstance(), null);
           jobMapper.updateStatus(jobId,"succeed");
       }catch (Exception e){
           jobMapper.updateStatus(jobId,"failed");
           throw  new RuntimeException("job exception:" + e.getMessage());
       }
    }
}
