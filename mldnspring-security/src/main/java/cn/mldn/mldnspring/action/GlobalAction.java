package cn.mldn.mldnspring.action;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Controller								// 定义控制器
public class GlobalAction {				// 定义全局Action类
	private Logger log = LoggerFactory.getLogger(EchoAction.class) ;	// 日志记录 
	@RequestMapping("/loginPage")				// 访问路径
	public String login() {				// 登录表单路径
		return "login";					// 设置跳转路径
	}
	@RequestMapping("/welcomePage")								// 访问路径
	public String welcome() {									// 登录成功路径
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;	// 认证对象
		UserDetails userDetails = (UserDetails) authentication.getPrincipal() ;	// 获取用户详情
		String username = userDetails.getUsername() ; 			// 获得用户名
		this.log.info("用户名：" + username);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities() ; // 获取授权信息
		this.log.info("授权信息：" + authorities);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest() ;			// 获取request内置对象
		boolean isAdminRole = request.isUserInRole("ADMIN");	// 判断是否有指定授权信息
		this.log.info("是否具有“ADMIN”角色：" + isAdminRole); 
		return "welcome";										// 设置跳转路径
	} 
	@RequestMapping("/logoutPage")				// 访问路径
	public String logout() {			// 登录成功路径
		return "logout";				// 设置跳转路径
	}
}
