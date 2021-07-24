package cn.mldn.mldnspring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.service.IDeptService;
import cn.mldn.mldnspring.vo.Dept;
import junit.framework.TestCase;

@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" }) // 进行资源文件定位
@RunWith(SpringJUnit4ClassRunner.class) 				// 设置要使用的测试工具
public class TestDeptService {
	@Resource
	private IDeptService deptService; 					// 注入业务对象

	@Test
	public void testAdd() {
		Dept vo = new Dept(); 							// 创建VO类对象
		vo.setDeptno(10L); 								// 设置属性
		vo.setDname("MLDN教学研发部"); 					// 设置属性
		TestCase.assertTrue(this.deptService.add(vo)); 	// 测试业务方法
	}
}
