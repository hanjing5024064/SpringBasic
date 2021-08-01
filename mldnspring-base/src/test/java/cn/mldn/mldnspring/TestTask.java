package cn.mldn.mldnspring;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestTask {
	@Test
	public void testQuartZ() {
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
