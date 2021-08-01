package cn.mldn.mldnspring.advice;

public class ServiceAdvice {		// 该类不需要继承任何父类，独立存在
	public void handleBefore(String tempMsg) { 	// 处理前置通知
		System.out.println("【### ServiceAdvice-handleBefore ###】进行业务的前置处理操作，参数：" + tempMsg);
	}
	public void handleAfter() { 	// 处理后置操作通知
		System.out.println("【### ServiceAdvice-handleAfter ###】进行业务的后置处理操作。");
	}
}
