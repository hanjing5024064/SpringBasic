package cn.mldn.mldnspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.vo.Item;

@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestItem {
	@Autowired
	private Item item ;
	@Test
	public void testItem() {
		System.out.println(this.item);
//		output:
//		Item [title=mldn, createDate=Sun Jul 25 12:51:03 CST 2021]
	}
}
