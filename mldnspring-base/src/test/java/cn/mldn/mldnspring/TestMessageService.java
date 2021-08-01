package cn.mldn.mldnspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.IMessageService;


@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestMessageService {
	@Autowired
	private IMessageService messageService ;
	@Test
	public void testEcho() {
		System.out.println(this.messageService.echo("www.mldn.cn")); 
	}
}
