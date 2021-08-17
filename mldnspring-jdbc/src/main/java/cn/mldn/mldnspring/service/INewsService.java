package cn.mldn.mldnspring.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import cn.mldn.mldnspring.vo.News;

public interface INewsService {
	/**
	 * 根据ID查询数据
	 * @param nid 新闻编号
	 * @return 新闻数据对象
	 */
	@Cacheable(cacheNames = "news", key = "#nid", unless="#result==null")
	public News get(long nid) ;
	/**
	 * 数据更新操作
	 * @param vo 要更新的数据
	 * @return 更新后的数据
	 */
	@CachePut(cacheNames = "news", key = "#vo.nid", unless="#result==null")
	public News edit(News vo) ;  
}
