package cn.mldn.mldnspring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.vo.Dept;
@ContextConfiguration(locations = { "classpath:spring/spring-base.xml" })	// 进行资源文件定位
@RunWith(SpringJUnit4ClassRunner.class)	// 设置要使用的测试工具
public class TestDept {
	@Resource
	private Dept dept ;		// 注入对象
	@Test
	public void testBean() {
		System.out.println("部门编号：" + this.dept.getDeptno() + "、部门名称：" + this.dept.getDname());
		this.dept.getInfos().forEach((info)->{	// 循环输出部门信息
			System.out.println("\t|- 信息：" + info);
		});
	}
} 
