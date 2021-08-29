package cn.mldn.mldnspring.action;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.mldnspring.service.IGoodsService;
import cn.mldn.util.action.abs.AbstractAction;
@Controller											// 定义控制器
@RequestMapping("/pages/back/admin/goods/*")		// 父路径
public class GoodsAction extends AbstractAction {	// 自定义Action程序类
	private Logger logger = LoggerFactory.getLogger(GoodsAction.class) ;	// 日志记录
	@Autowired
	private IGoodsService goodsService ;
	@RequestMapping("add_pre")
	public ModelAndView addPre() {
		Map<String,Object> map = this.goodsService.preAdd() ;
		this.logger.info("商品信息增加前信息查询：" + map);
		ModelAndView mav = new ModelAndView("back/admin/goods/goods_add") ;
		mav.addAllObjects(map) ;					// 保存商品分类与标签数据 
		return mav ;
	}
}
