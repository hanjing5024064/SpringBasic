package cn.mldn.mldnspring.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.IGoodsDAO;
import cn.mldn.mldnspring.dao.IItemDAO;
import cn.mldn.mldnspring.dao.ITagDAO;
import cn.mldn.mldnspring.service.IGoodsService;
import cn.mldn.mldnspring.service.abs.AbstractService;
@Service
public class GoodsServiceImpl extends AbstractService implements IGoodsService {
	@Autowired
	private IItemDAO itemDAO ;							// 注入IItemDAO数据接口实例
	@Autowired
	private ITagDAO tagDAO ;							// 注入ITagDAO数据接口实例
	@Autowired
	private IGoodsDAO goodsDAO ;						// 注入IGoodsDAO数据接口实例
	@Override
	public Map<String, Object> preAdd() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allItems", this.itemDAO.findAll()) ;	// 查询全部分类信息
		map.put("allTags", this.tagDAO.findAll()) ;		// 查询全部标签信息
		return map; 
	}

}
