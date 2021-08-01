package cn.mldn.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxy implements InvocationHandler {
	private Object realObject; // 被代理的真实对象
	/**
	 * 进行真实对象的绑定处理，返回一个动态生成的接口类对象
	 * @param realObject 真实主题类
	 * @return 代理类对象
	 */
	public Object bind(Object realObject) {
		this.realObject = realObject;
		return Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(),this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (this.checkTransactionMethod(method.getName())) {	// 执行的业务方法应该是更新处理
			// 开启数据库事务处理
		}
		Object backResult = method.invoke(this.realObject, args) ;	// 调用真实业务 
		try {
			if (this.checkTransactionMethod(method.getName())) {	// 执行的业务方法应该是更新处理
				// 【COMMIT】数据库事务提交
			}
		} catch (Exception e) {
			// 【ROLLBACK】数据库事务回滚
			throw e ;
		}
		return backResult ;
	}
	/**
	 * 检测当前的方法是否需要开启事务控制处理
	 * @param methodName 方法名称
	 * @return 如果需要开启返回true，如果不需要返回false
	 */
	private boolean checkTransactionMethod(String methodName) {
		return methodName.startsWith("add") || methodName.startsWith("edit") || methodName.startsWith("delete") ;
	}
}
