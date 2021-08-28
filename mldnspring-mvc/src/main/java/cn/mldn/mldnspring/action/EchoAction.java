package cn.mldn.mldnspring.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller										// 定义控制器
@RequestMapping("/pages/message/*")				// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class EchoAction {						// 自定义Action程序类
	private Logger log = LoggerFactory.getLogger(EchoAction.class) ;	// 日志记录
	@RequestMapping("/echo")					// 访问的路径为“echo.action”；
	public ModelAndView echo(String msg) {		// 该参数会根据参数名称自动进行匹配处理
		this.log.info("*** EchoAction接收到请求参数，msg = " + msg);		// 日志输出
		// ModelAndView主要功能是设置跳转路径以及进行request属性的保存
		ModelAndView mav = new ModelAndView("/pages/message/message_show.jsp") ;
		mav.addObject("echoMessage", "【ECHO】msg = " + msg) ;			// 设置request数据
		return mav ;
	}
	@RequestMapping("/echo_pre")
	public String echoPre() {
		return "/pages/message/message_input.jsp" ;
	}
}
