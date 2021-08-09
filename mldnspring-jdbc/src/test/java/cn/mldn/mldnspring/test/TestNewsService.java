package cn.mldn.mldnspring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.INewsService;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class) // 设置要使用的测试工具
public class TestNewsService {
	@Autowired
	private INewsService newsService;

	@Test
	public void testFindById() throws Exception {
		System.out.println(this.newsService.get(3L));
		System.out.println(this.newsService.get(3L));
	}
}
