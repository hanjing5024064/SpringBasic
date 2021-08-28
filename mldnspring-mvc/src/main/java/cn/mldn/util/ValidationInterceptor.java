package cn.mldn.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ValidationInterceptor implements HandlerInterceptor {	// 定义拦截器
	private Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class) ; 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.logger.info("1、preHandle()方法执行，" + handler.getClass());
		return true ;	// 返回true表示请求继续，而如果返回了false表示不执行后续的Action或拦截器
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		this.logger.info("2、postHandle()方法执行，" + handler.getClass() + "、ModelAndView = " + modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		this.logger.info("3、afterCompletion()方法执行，" + handler.getClass());
	} 
}
