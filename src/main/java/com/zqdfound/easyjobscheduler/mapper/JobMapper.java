package com.zqdfound.easyjobscheduler.mapper;

import com.zqdfound.easyjobscheduler.domain.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zhuangqingdian
 * @date 2022/6/17
 */
@Mapper
public interface JobMapper {
    @Insert("insert into jobs (job_id,job_type,job_name,last_exe_status,create_time) values (#{jobId},#{jobType},#{jobName},#{lastExeStatus},#{createTime})")
    void addJob(Job job);

    @Select("select * from jobs order by create_time desc")
    List<Job> listAll();

    @Select("select * from jobs where job_id = #{id}")
    Job getJobById(String id);

    @Delete("delete from jobs where job_id = #{id}")
    void deleteById(String id);

    @Update("update jobs set last_exe_status = #{status} where job_id=#{jobId}")
    void updateStatus(String jobId, String status);
}
