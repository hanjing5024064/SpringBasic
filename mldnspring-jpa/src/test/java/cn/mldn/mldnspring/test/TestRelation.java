package cn.mldn.mldnspring.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mldn.mldnspring.po.Company;
import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestRelation { 
	@Test
	public void testAdd() throws Exception {
		Company company = new Company();								// 实例化“一”方对象
		company.setCname("魔乐科技软件学院");								// 设置数据
		List<Dept> allDepts = new ArrayList<Dept>(); 					// 保存所有的部门信息
		for (int x = 0; x < 3; x++) {
			Dept dept = new Dept(); 									// 实例化“多”方对象
			dept.setDname("教学研发" + x + "部");
			dept.setCompany(company); 									// 需要获得cid信息
			allDepts.add(dept);											// 保存集合信息
		}
		company.setDepts(allDepts);										// 一对多关联
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开始事务
		JPAEntityFactory.getEntityManager().persist(company); 			// 持久化数据
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close();										// 关闭连接

	}
	
}
