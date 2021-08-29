package cn.mldn.mldnspring.action;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller										// 定义控制器
@RequestMapping("/pages/message/*")				// 访问父路径，与方法中的路径进行组合为完整路径
public class EchoAction {						// 自定义Action程序类
	@RequestMapping("/show")					// 访问的路径
	public ModelAndView echo(String msg) { // 接收请求参数  
		ModelAndView mav = new ModelAndView("message/message_show") ;	// 设置跳转路径
		mav.addObject("echoMessage", "【ECHO】msg = " + msg) ;			// 设置request数据
		return mav ;
	}
	@GetMapping("/input")						// 访问路径
	public String input() {
		return "message/message_input";			// 设置跳转路径
	}
}
