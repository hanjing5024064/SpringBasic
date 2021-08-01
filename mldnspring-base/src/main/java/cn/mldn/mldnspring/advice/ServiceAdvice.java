package cn.mldn.mldnspring.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Component		// Bean注解配置
@Aspect			// 该类为AOP处理类
public class ServiceAdvice {		// 该类不需要继承任何父类，独立存在
	@Before(value="execution(public * cn.mldn..service..*.*(..)) and args(msg)",argNames="msg")
	public void handleBefore(String tempMsg) { 	// 处理前置通知
		System.out.println("---------------------------------------");
		System.out.println("【### ServiceAdvice-handleBefore ###】进行业务的前置处理操作，参数：" + tempMsg);
	}
	@After(value="execution(public * cn.mldn..service..*.*(..))")
	public void handleAfter() { 	// 处理后置操作通知
		System.out.println("---------------------------------------");
		System.out.println("【### ServiceAdvice-handleAfter ###】进行业务的后置处理操作。");
	}
	@AfterReturning(value="execution(public * cn.mldn..service..*.*(..))",argNames="r",returning="r") 
	public void handleReturn(String retMsg) { 	// 处理后置操作通知
		System.out.println("---------------------------------------");
		System.out.println("【### ServiceAdvice-handleReturn ###】业务方法执行完毕：" + retMsg);
	}
	@AfterThrowing(value="execution(public * cn.mldn..service..*.*(..))",throwing="e",argNames="e")
	public void handleThrow(Exception exp) { // 异常处理通知
		System.out.println("---------------------------------------");
		System.out.println("【### ServiceAdvice-handleThrow ###】方法执行产生了异常：" + exp);
	}
	@Around("execution(public * cn.mldn..service..*.*(..))")
	public Object handleRound(ProceedingJoinPoint point) throws Throwable {	// 定义一个环绕处理通知
		System.out.println("---------------------------------------");
		System.out.println("【A、环绕通知 - handleRound】业务方法调用前。参数：" + Arrays.toString(point.getArgs())) ;
		Object returnValue = null ;	// 表示的是进行方法返回值的接收处理
		try {
			returnValue = point.proceed(new Object[] {"假的参数我乐意传"}) ;	// 修改了真实的传递参数
		} catch (Exception e) {	// 异常向上继续抛出
			System.out.println("【C、环绕通知 - handleRound】产生异常。异常：" + e) ;
			throw e ;
		}
		System.out.println("【B、环绕通知 - handleRound】业务方法执行完毕。返回值：" + returnValue) ;
		return returnValue ;
	}
}
