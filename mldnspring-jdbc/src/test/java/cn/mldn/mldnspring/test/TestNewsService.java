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
	public void testDelete() throws Exception {
		System.out.println(this.newsService.get(6L));	// 指定数据缓存
		this.newsService.delete(6L) ;					// 删除指定编号新闻，并且清除缓存
		System.out.println(this.newsService.get(6L));	// 此时将无法获取缓存信息
	}

	@Test
	public void testFindById() throws Exception {
		System.out.println(this.newsService.get(30L));
		//System.out.println(this.newsService.get(30L));
	}
	@Test
	public void testEdit() throws Exception {
		System.out.println(this.newsService.get(3L));	// 查询指定编号数据，进行缓存
		News vo = new News() ;
		vo.setNid(3L);
		vo.setTitle("MLDN新闻更新");
		vo.setNote("www.mldn.cn");
		vo.setPrice(3980.00);
		vo.setReadcount(97898);
		vo.setPubdate(new Date());
		this.newsService.edit(vo) ;						// 更新缓存中的数据
		System.out.println(this.newsService.get(3L));	// 获取更新后的内容
	}
}
