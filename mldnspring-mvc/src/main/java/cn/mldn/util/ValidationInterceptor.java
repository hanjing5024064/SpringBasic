package cn.mldn.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.mldn.util.validate.ActionMIMEValidationUtil;
import cn.mldn.util.validate.ActionValidationUtil;

public class ValidationInterceptor implements HandlerInterceptor {	// 定义拦截器
	private Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class) ; 
	
	@Autowired
	private MessageSource messageSource ;		// 获取资源数据

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) { // 执行向下转型前应该首先判断其是否是指定类的实例
			HandlerMethod handlerMethod = (HandlerMethod) handler; // 强制转换
			String validationRuleKey = handlerMethod.getBeanType().getName() + "." + handlerMethod.getMethod().getName() ;
			String validationRule = null ; // 保存要读取指定的资源key对应的验证规则
			try {							// 如果指定的key不存在，表示现在不需要进行验证
				validationRule = this.messageSource.getMessage(validationRuleKey, null, null) ;
			} catch (Exception e) {}
			if (validationRule != null) {	// 验证处理操作，则需要进行验证处理
				this.logger.info("【验证规则 - ｛"+request.getRequestURI()+"｝】" + validationRule); 
				ActionValidationUtil avu = new ActionValidationUtil(validationRule, request, this.messageSource) ;
				String errorPage = null ; 								// 错误页
				try {													// 获取当前访问错误页
					errorPage = this.messageSource.getMessage(validationRuleKey + ".error.page", null, null) ;
				} catch (Exception e) {									// 如果没有指定的路径则跳转到公共的errorPage
					errorPage = this.messageSource.getMessage("error.page", null, null) ;
				}
				if (avu.getErrors().size() > 0) {						// 现在有错误信息
					request.setAttribute("errors", avu.getErrors());	// 保存错误信息
					request.getRequestDispatcher(errorPage).forward(request, response);	// 跳转
					return false ; 										// 请求拦截
				} else {
					if (request instanceof DefaultMultipartHttpServletRequest) {	// 有文件上传
						String mimeRule = null; ;
						try {		// 获取文件规则，如果没有则使用公共规则
							mimeRule = this.messageSource.getMessage(validationRuleKey + ".mime.rule", null,null) ;
						} catch (Exception e) {
							mimeRule = this.messageSource.getMessage("mime.rule", null,null) ;
						}
						ActionMIMEValidationUtil amvu = new ActionMIMEValidationUtil(mimeRule,request,this.messageSource) ;
						if (amvu.getErrors().size() > 0) {				// 有错误信息
							request.setAttribute("errors", amvu.getErrors()); 
							request.getRequestDispatcher(errorPage).forward(request, response);
							return false ; 								// 请求拦截
						}
					}
				}
			}
		}
		return true ;	// 返回true表示请求继续，而如果返回了false表示不执行后续的Action或拦截器
	}
}
