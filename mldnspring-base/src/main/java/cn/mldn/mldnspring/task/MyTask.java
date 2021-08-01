package cn.mldn.mldnspring.task;

import java.text.SimpleDateFormat;

public class MyTask {			// 不再强制性继承任何父类
	public void runTask() { 	// 随意定义的一个方法名称
		System.out.println("【当前的日期时间】" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date()));
	}
}
