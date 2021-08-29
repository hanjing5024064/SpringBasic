package cn.mldn.mldnspring.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.IGoodsDAO;
import cn.mldn.mldnspring.dao.IItemDAO;
import cn.mldn.mldnspring.dao.ITagDAO;
import cn.mldn.mldnspring.po.Goods;
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
	@Override
	public boolean add(Goods vo) {
		vo.setDflag(0);									// 新增商品信息删除标记为0
		return this.goodsDAO.save(vo).getGid() != null;	// 商品保存
	}
	
	@Override
	public Map<String, Object> list(String keyWord, int currentPage, int lineSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		Sort sort = new Sort(Sort.Direction.DESC, "gid"); 					// 设置gid字段为降序排列
		// 将分页与排序操作保存到Pageable接口对象之中，这样才可以通过DAO层进行方法调用，页数从0开始
		Pageable pageable = PageRequest.of(currentPage - 1, lineSize, sort);
		if (keyWord == null || "".equals(keyWord)) { 						// 查询全部操作
			Page<Goods> pageGoods = this.goodsDAO.findAll(pageable);		// 数据查询
			map.put("allRecorders", pageGoods.getTotalElements());			// 数据统计
			map.put("allGoods", pageGoods.getContent());					// 数据信息
		} else {															// 模糊查询
			map.put("allRecorders", this.goodsDAO.getSplitCount(keyWord));	// 数据统计
			map.put("allGoods", this.goodsDAO.findSplit(keyWord,pageable));	// 数据信息
		}
		Map<Long, String> itemMap = new HashMap<Long, String>();			// 保存分类信息
		this.itemDAO.findAll().forEach((item)->{
			itemMap.put(item.getIid(), item.getTitle()) ;					// 保存item信息
		});
		map.put("allItems", itemMap) ;										// 商品分类
		return map;
	}

}
