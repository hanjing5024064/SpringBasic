package cn.mldn.mldnspring.service;

import java.util.Map;

import cn.mldn.mldnspring.po.Goods;

public interface IGoodsService {
	/**
	 * 进行商品添加前的数据查询操作
	 * @return 返回的数据包含有如下内容：
	 * 	key = allItems、value = 所有的商品分类。
	 * 	key = allTags、value = 所有的商品标签。
	 */
	public Map<String,Object> preAdd() ;
	
	/**
	 * 实现商品数据的追加处理，新添加商品的dflag内容为0
	 * @param vo 要追加的商品信息（配置好关联关系）
	 * @return 追加成功返回true，否则返回false
	 */
	public boolean add(Goods vo) ;

}
