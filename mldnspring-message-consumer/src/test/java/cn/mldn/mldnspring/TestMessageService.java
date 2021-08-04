package cn.mldn.mldnspring;

import java.util.concurrent.TimeUnit;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;


@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestMessageService {
	@Autowired
	private Destination destination ;
	@Test
	public void testEcho() throws InterruptedException {
		TestCase.assertTrue(true);
		TimeUnit.MINUTES.sleep(10);
	}
}
