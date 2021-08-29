package cn.mldn.mldnspring.action;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.mldnspring.po.Goods;
import cn.mldn.mldnspring.po.Item;
import cn.mldn.mldnspring.po.Tag;
import cn.mldn.mldnspring.service.IGoodsService;
import cn.mldn.util.action.abs.AbstractAction;
import cn.mldn.util.web.SplitPageUtil;
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
	
	@RequestMapping("goods_add")
	public ModelAndView add(Goods goods, long iid, String tid[], MultipartFile pic) throws Exception {
		ModelAndView mav = new ModelAndView(super.getMessage("forward.page")) ;	// 获取跳转路径
		String msg = "商品数据增加失败！" ;
		String url = "/pages/back/admin/goods/add_pre.action" ;	// 显示后的跳转路径
		List<Tag> allTags = new ArrayList<Tag>() ;
		for (int x = 0 ; x < tid.length ; x ++) {	// 将标签信息保存到集合中
			Tag tag = new Tag() ;
			tag.setTid(Long.parseLong(tid[x])); 	// 保存tid编号
			allTags.add(tag) ;
		}
		Item item = new Item() ;
		item.setIid(iid);							// 保存iid编号
		goods.setItem(item);						// 保存商品与分类关系
		goods.setTags(allTags);						// 保存商品与标签关系
		if (pic == null && pic.isEmpty()) {
			goods.setPhoto("nophoto.png");	// 默认图片名称
		} else {									// 创建新的图片名称
			goods.setPhoto(UUID.randomUUID() + "." + pic.getContentType()
				.substring(pic.getContentType().lastIndexOf("/") + 1) ); 
		}
		if (this.goodsService.add(goods)) {			// 保存成功
			if (!(pic == null && pic.isEmpty())) {	// 有文件上传
				String photoPath = ContextLoader.getCurrentWebApplicationContext()
					.getServletContext().getRealPath("/WEB-INF/upload/") + goods.getPhoto();
				pic.transferTo(new File(photoPath)); // 保存上传文件 			
			}
			msg = "商品数据增加成功！" ;
		}
		mav.addObject("msg", msg) ;					// 保存提示信息
		mav.addObject("url", url) ;					// 保存跳转路径
		return mav ;
	}
	
	@RequestMapping("goods_list") 
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("back/admin/goods/goods_list") ;
		SplitPageUtil spu = new SplitPageUtil("/pages/back/admin/goods/list.action") ;
		mav.addAllObjects(this.goodsService.list(spu.getKeyWord(), spu.getCurrentPage(), spu.getLineSize())) ;
		return mav ; 
	}

}
