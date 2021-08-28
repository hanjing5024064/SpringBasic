package cn.mldn.mldnspring.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller										// 定义控制器
@RequestMapping("/pages/web/*")				// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class WebObjectAction {						// 自定义Action程序类
	private Logger log = LoggerFactory.getLogger(WebObjectAction.class) ;	// 日志记录 
	@RequestMapping("/show_one") 
	public ModelAndView showObject(HttpServletRequest request, HttpServletResponse response) { 
		this.log.info("*** 【REQUEST内置对象】ContextPath = " + request.getContextPath()) ;
		this.log.info("*** 【APPLICATION内置对象】RealPath = " + request.getServletContext().getRealPath("/")) ;
		this.log.info("*** 【RESPONSE内置对象】Locale = " + response.getLocale()) ;
		this.log.info("*** 【SESSION内置对象】SessionID = " + request.getSession().getId()) ;
		return null ;
	}
}
