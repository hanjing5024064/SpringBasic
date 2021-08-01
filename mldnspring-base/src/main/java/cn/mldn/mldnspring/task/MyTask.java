package cn.mldn.mldnspring.task;

import java.text.SimpleDateFormat;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class MyTask {
	@Scheduled(cron="* * * * * ?")	// 任务调度设置
	public void runTask() { 		// 随意定义了一个方法名称
		System.out.println("【当前的日期时间】" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date()));
	}
}
