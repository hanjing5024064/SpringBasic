package cn.mldn.mldnspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.INewsDAO;
import cn.mldn.mldnspring.service.INewsService;
import cn.mldn.mldnspring.vo.News;
@Service
public class NewsServiceImpl implements INewsService {
	@Autowired
	private INewsDAO newsDAO ;
	@Override
	public News get(long nid) {
		return this.newsDAO.findById(nid);
	}
	@Override
	public News edit(News vo) {
		if (this.newsDAO.doUpdate(vo)) {
			return vo ;
		} 
		return null;
	}
}
