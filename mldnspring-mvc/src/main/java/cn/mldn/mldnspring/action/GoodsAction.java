package cn.mldn.mldnspring.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mldn.util.action.abs.AbstractAction;
@Controller										// 定义控制器
@RequestMapping("/pages/emp/*")					// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class GoodsAction extends AbstractAction {						// 自定义Action程序类
	private Logger logger = LoggerFactory.getLogger(GoodsAction.class) ;	// 日志记录 


}
