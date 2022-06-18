# easy-job-scheduler
job-scheduler 
It was using springboot taskscheduler and Java Reflection to implement an easy job scheduler.
project needs jdk8 and mysql

**GET START**
runing job_scheduler.sql to init database,
runing EasyJobSchedulerApplication.java to start project , visit http://localhost:8080/doc.html#/home to get API 
![image](https://user-images.githubusercontent.com/41464360/174422084-e6f5b57a-d501-44c2-add7-b9ccacfc4607.png)

**DEMO**
to runing an custom job，you need to define this method in JobMethods.java and note down your method name
![image](https://user-images.githubusercontent.com/41464360/174422212-0e3d6985-e339-4d0b-b074-4999e3d07fe0.png)

1.send a request to create a repetitive job
![image](https://user-images.githubusercontent.com/41464360/174422292-3a288382-b073-4ae6-9890-25cec682ccb7.png)
The background will call the task cyclically according to the method name
![image](https://user-images.githubusercontent.com/41464360/174422329-1eb13ec0-53ee-4d20-8bfe-dcd17eb2527d.png)

2.send a request to create a one time execution job
![img_1.png](img_1.png)
![img_2.png](img_2.png)
**COMMENTS**
Regardless of whether the execution is successful or timeout, the scheduled task will retry the execution.
If we want to make the interaction faster,maybe can search job infos in  jobTaskMap rather than db,please refer to 
![img.png](img.png)




