package cn.mldn.mldnspring.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.mldn.mldnspring.cache.NewsEditCache;
import cn.mldn.mldnspring.vo.News;

@CacheConfig(cacheNames="news")		// 进行该接口缓存的统一配置
public interface INewsService {
	/**
	 * 根据ID查询数据
	 * @param nid 新闻编号
	 * @return 新闻数据对象
	 */
	@Cacheable(key = "#nid", unless="#result==null")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public News get(long nid) ;
	/**
	 * 数据更新操作
	 * @param vo 要更新的数据
	 * @return 更新后的数据
	 */
	@NewsEditCache
	@Transactional(propagation=Propagation.REQUIRED)
	public News edit(News vo) ;  
	
	/**
	 * 删除新闻数据
	 * @param nid 新闻编号
	 * @return 删除成功返回true，否则返回false
	 */
	@CacheEvict(key="#nid")
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean delete(long nid) ;
}
