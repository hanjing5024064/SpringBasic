package cn.mldn.mldnspring.service;

import cn.mldn.mldnspring.vo.News;

public interface INewsService {
	/**
	 * 根据ID查询数据
	 * @param nid 新闻编号
	 * @return 新闻数据对象
	 */
	public News get(long nid) ;
}
