package cn.mldn.mldnspring.action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller								// 定义控制器
public class GlobalAction {				// 定义全局Action类
	@RequestMapping("/loginPage")				// 访问路径
	public String login() {				// 登录表单路径
		return "login";					// 设置跳转路径
	}
	@RequestMapping("/welcomePage")				// 访问路径
	public String welcome() {			// 登录成功路径
		return "welcome";				// 设置跳转路径
	}
	@RequestMapping("/logoutPage")				// 访问路径
	public String logout() {			// 登录成功路径
		return "logout";				// 设置跳转路径
	}
}
