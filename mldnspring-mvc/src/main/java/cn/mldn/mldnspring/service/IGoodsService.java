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
	
	/**
	 * 进行商品信息的分页数据查询，如果没有查询列或查询关键字则进行整体查询
	 * @param keyWord 查询关键字
	 * @param currentPage 当前页
	 * @param lineSize 每页行
	 * @return 返回的内容包含有如下信息：
	 * 1、key = allGoods、value = 全部商品信息；
	 * 2、key = allRecorders、value = 统计结果。
	 * 3、key = allItems、value = 全部的分类信息（Map集合）
	 */
	public Map<String, Object> list(String keyWord, int currentPage, int lineSize) ;
	
	/**
	 * 进行商品修改前的数据查询操作
	 * @return 返回的数据包含有如下内容：
	 * key = allItems、value = 所有的商品分类。
	 * key = allTags、value = 所有的商品标签。
	 * key = goods、value = 要修改的商品信息
	 * key = allTids、value = 商品标签
	 */
	public Map<String,Object> preEdit(long id) ;

	
	/**
	 * 实现商品数据的修改处理：
	 * @param vo 要追加的商品信息
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean edit(Goods vo) ;


}
