package cn.mldn.mldnspring.service;

import java.util.Map;

public interface IGoodsService {
	/**
	 * 进行商品添加前的数据查询操作，该操作要执行如下的数据层方法调用：
	 * 1、调用IItemDAO.findAll()查询所有的商品分类信息。
	 * 2、调用ITagDAO.findAll()查询所有的商品标记信息。
	 * @return 返回的数据包含有如下内容：
	 * 	key = allItems、value = 所有的商品分类。
	 * 	key = allTags、value = 所有的商品标签。
	 */
	public Map<String,Object> preAdd() ;
}
