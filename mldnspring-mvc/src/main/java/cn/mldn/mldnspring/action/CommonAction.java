package cn.mldn.mldnspring.action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller										// 定义控制器
public class CommonAction {						// 公共Action
	@RequestMapping("/pages/error")				// 访问路径
	public String error() {
		return "errors";						// 跳转到/WEB-INF/pages/errors.jsp
	} 

}
