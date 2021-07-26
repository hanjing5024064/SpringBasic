package cn.mldn.mldnspring.demo;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask { // 定义一个定时调度需要处理的任务程序类
	@Override
	public void run() {
		System.out.println(
				"【当前日期时间】" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
						.format(new java.util.Date()));
	}
} 

public class TimeTaskDemo {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 0, 1000); // 每秒执行一次
	}

}
