package cn.mldn.mldnspring.action;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller										// 定义控制器
@RequestMapping("/pages/message/*")				// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class EchoAction {						// 自定义Action程序类
	private Logger log = LoggerFactory.getLogger(EchoAction.class) ;	// 日志记录 
	@Autowired
	private MessageSource messageSource ;		// 资源读取
	
	@GetMapping("/message")
	public ModelAndView message() {
		System.out.println(
				"【echo.input.page】" + this.messageSource.getMessage("echo.input.page", null, Locale.getDefault()));
		System.out.println("【welcome.info】"
				+ this.messageSource.getMessage("welcome.info", new Object[] { "李兴华" }, Locale.getDefault()));
		return null;
	}
	
	@PostMapping("/echo")					// 访问的路径为“echo.action”；
	public ModelAndView echo(String msg, Integer level, String tags[], Date pubdate) { // 接收请求参数  
		ModelAndView mav = new ModelAndView("message/message_show") ;			// 不写前缀与后缀
		mav.addObject("echoMessage", "【ECHO】msg = " + msg) ;					// 设置request数据
		mav.addObject("echoLevel", "【ECHO】level = " + level) ;					// 设置request数据
		mav.addObject("echoTags", "【ECHO】tags = " + Arrays.toString(tags)) ;	// 设置request数据
		mav.addObject("echoPubdate", "【ECHO】pubdate = " + pubdate) ;			// 设置request数据
		return mav ;
	}
	@GetMapping("/echo_pre")
	public String echoPre() {
		return "message/message_input";			// 不再需要编写前缀或后缀
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) {	// 设置一个Web数据的绑定转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;	// 定义转换处理
		// 在WEB数据绑定之中注册一个自定义的规则绑定器，该操作主要是对java.util.Date类型处理，允许为null
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}

}
