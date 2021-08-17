package cn.mldn.mldnspring.dao;

import cn.mldn.mldnspring.vo.News;

public interface INewsDAO {
	/**
	 * 根据ID查询新闻数据
	 * @param nid 新闻ID
	 * @return 新闻数据对象
	 */
	public News findById(Long nid) ;
	/**
	 * 更新新闻数据
	 * @param vo 要更新的新闻数据
	 * @return 更新成功返回true，否则返回false
	 */
	public boolean doUpdate(News vo) ; 
	
	/**
	 * 根据编号删除指定新闻数据
 	 * @param nid 新闻编号
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean doRemove(Long nid) ;
}
