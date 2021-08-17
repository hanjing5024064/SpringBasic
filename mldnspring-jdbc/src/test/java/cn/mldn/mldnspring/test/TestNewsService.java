package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.INewsService;
import cn.mldn.mldnspring.vo.News;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // 设置要使用的测试工具
public class TestNewsService {
	@Autowired
	private INewsService newsService;

	@Test
	public void testFindById() throws Exception {
		System.out.println(this.newsService.get(30L));
		//System.out.println(this.newsService.get(30L));
	}
	@Test
	public void testEdit() throws Exception {
		System.out.println(this.newsService.get(3L));
		News vo = new News() ;
		vo.setNid(3L);
		vo.setTitle("MLDN新闻更新");
		vo.setNote("www.mldn.cn");
		vo.setPrice(3980.00);
		vo.setReadcount(97898);
		vo.setPubdate(new Date());
		this.newsService.edit(vo) ;
		
		System.out.println(this.newsService.get(3L));
	}
}
