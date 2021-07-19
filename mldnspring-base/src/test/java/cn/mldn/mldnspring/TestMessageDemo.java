package cn.mldn.mldnspring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.IMessage;
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 进行资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestMessageDemo {
	@Resource(name="messageImpl")	// 根据配置文件名称注入
	private IMessage message ;		// 会自动根据类型进行注入
	@Test
	public void testEcho() {
		System.out.println(this.message.echo("www.mldn.cn")); 
	}

}
