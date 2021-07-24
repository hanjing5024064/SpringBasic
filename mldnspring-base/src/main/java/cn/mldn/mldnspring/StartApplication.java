package cn.mldn.mldnspring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.mldn.mldnspring.vo.Message;

public class StartApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-base.xml");
		Message msg = ctx.getBean("message", Message.class); // 获得程序类
		msg.send("www.mldn.cn");
		ctx.registerShutdownHook(); // 调用销毁方法
	}

}
