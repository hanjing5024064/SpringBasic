package cn.mldn.mldnspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.vo.Dept;

@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestDept {
	@Autowired
	private Dept dept ;
	@Test
	public void testItem() {
		System.out.println(this.dept);
	}
}
