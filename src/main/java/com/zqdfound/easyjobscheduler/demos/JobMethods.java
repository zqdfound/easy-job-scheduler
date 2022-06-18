package com.zqdfound.easyjobscheduler.demos;

import java.util.Date;

/**
 * Write the custom job method that needs to be executed in here
 * @author zhuangqingdian
 * @date 2022/6/18
 */
public class JobMethods {
    public void testDemo(){
        System.out.println("this is test print,"+ new Date());
    }

}
