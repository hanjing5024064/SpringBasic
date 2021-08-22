package cn.mldn.mldnspring.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		for(int i=0;i<5;i++) {
			Dept po = new Dept() ;							// 实例化持久类对象
			po.setDname("MLDN教学部");						// 设置数据
			po.setCreatedate(new Date());					// 设置数据
			po.setNum(55);									// 设置数据
			po.setAvgsal(89998.00);							// 设置数据
			System.out.println(this.deptService.add(po));	// 数据持久化
		}
	} 

	
	@Test
	public void testList() {
		List<Dept> allDepts = this.deptService.list() ;	// 查询全部数据
		allDepts.forEach((dept)->{						// 迭代输出查询结果
			System.out.println(dept);
		}) ;
	}

	@Test
	public void testGet() {
		Dept dept = this.deptService.get(1L) ;			// 查询指定ID数据
		System.out.println(dept);
	}
	
	@Test
	public void testGets() {
		Set<Long> allIds = new HashSet<Long>() ;				// 实例化Set集合，用于保存查询ID
		allIds.addAll(Arrays.asList(1L, 3L, 5L));				// 增加ID数据
		List<Dept> allDepts = this.deptService.gets(allIds) ;	// 数据查询
		allDepts.forEach((dept)->{								// 迭代输出查询结果
			System.out.println(dept);
		}) ;
	}
	
	@Test
	public void testGetIdAndDname() {
		Dept po = new Dept() ;							// 定义部门对象，用于传递参数
		po.setDeptno(1L);								// 设置参数内容
		po.setDname("MLDN教学部");						// 设置参数内容
		System.out.println(this.deptService.getIdAndDname(po));
	}
	
	@Test
	public void testEdit() {
		Dept po = new Dept();						// 定义对象，用于保存更新数据
		po.setDeptno(3L); 							// 设置更新数据
		po.setNum(99);								// 设置更新数据
		po.setDname("魔乐科技教学研发中心");				// 设置更新数据
		System.out.println(this.deptService.edit(po));
	}

	@Test
	public void testRemove() {
		System.out.println(this.deptService.remove(6L));
	}


}
