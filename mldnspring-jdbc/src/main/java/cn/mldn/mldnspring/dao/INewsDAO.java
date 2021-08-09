package cn.mldn.mldnspring.dao;

import cn.mldn.mldnspring.vo.News;

public interface INewsDAO {
	/**
	 * 根据ID查询新闻数据
	 * @param nid 新闻ID
	 * @return 新闻数据对象
	 */
	public News findById(Long nid) ;
}
