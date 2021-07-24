package cn.mldn.mldnspring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.vo.Emp;
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 进行资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestEmp {
	@Resource				// 由于Emp对象只有一个，所以不需要设置bean名称
	private Emp emp ;		// 注入对象
	@Test
	public void testEcho() {
		System.out.println(this.emp); 
		System.out.println(this.emp.getDept()); 
	}
}
