package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.service.IDeptService;

@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDeptService {
	@Autowired
	private IDeptService deptService ;					// 注入IDeptService业务接口实例
	@Test
	public void testAdd() {
		Dept po = new Dept() ;							// 实例化持久类对象
		po.setDname("MLDN教学部");						// 设置数据
		po.setCreatedate(new Date());					// 设置数据
		po.setNum(55);									// 设置数据
		po.setAvgsal(89998.00);							// 设置数据
		System.out.println(this.deptService.add(po));	// 数据持久化
	}  

	
	@Test
	public void testList() {
		this.deptService.list().forEach((dept)->{	// 迭代输出查询结果
			System.out.println(dept);
		});		
	}
	
	@Test
	public void testListSplit() {
		System.out.println(this.deptService.listSplit(1, 2));
	}


	@Test
	public void testGet() {
		System.out.println(this.deptService.get(1L));		
	}
}  
 