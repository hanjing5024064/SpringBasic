package cn.mldn.mldnspring.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller // 定义控制器
public class GlobalAction { // 定义全局Action类
	private Logger log = LoggerFactory.getLogger(EchoAction.class); // 日志记录
	@Autowired
	private DefaultKaptcha captchaProducer ;

	@RequestMapping("/loginPage") // 访问路径
	public String login() { // 登录表单路径
		return "login"; // 设置跳转路径
	}

	@RequestMapping("/logoffPage") // 访问路径
	public String error403() { // 登录表单路径
		return "logoff"; // 设置跳转路径
	}  
	@PreAuthorize("isAuthenticated()")    
	@RequestMapping("/welcomePage") // 访问路径
	public String welcome() { // 登录成功路径
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;	// 认证对象
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal() ;	// 获取用户详情
//		String username = userDetails.getUsername() ; 			// 获得用户名
//		this.log.info("用户名：" + username);
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities() ; // 获取授权信息
//		this.log.info("授权信息：" + authorities);
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest() ;			// 获取request内置对象
//		boolean isAdminRole = request.isUserInRole("ROLE_ADMIN");	// 判断是否有指定授权信息
//		this.log.info("是否具有“ADMIN”角色：" + isAdminRole); 
		return "welcome"; // 设置跳转路径
	}

	@RequestMapping("/logoutPage") // 访问路径
	public String logout() { // 登录成功路径
		return "logout"; // 设置跳转路径
	}

	@RequestMapping(value = "/RandomCode") 
	public ModelAndView kaptcha() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		HttpSession session = request.getSession();
		response.setHeader("Pragma", "No-cache");	// 不缓存数据
		response.setHeader("Cache-Control", "no-cache");	// 不缓存数据
		response.setDateHeader("Expires", 0);	// 不失效
		response.setContentType("image/jpeg");	// mime类型
		String capText = captchaProducer.createText();// 获取验证码上的文字
		// 将验证码上的文字保存在session中
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		this.log.info("验证码为:" + code);
		BufferedImage image = this.captchaProducer.createImage(capText);// 文字图像
		try {
			OutputStream output = response.getOutputStream() ;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "JPEG", bos);		// 图像输出
			byte[] buf = bos.toByteArray();
			response.setContentLength(buf.length);
			output.write(buf);
			bos.close();
			output.close();
		} catch (Exception e) {}
		return null ;
	}
}
