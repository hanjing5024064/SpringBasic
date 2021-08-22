package cn.mldn.mldnspring.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.mldn.mldnspring.po.Company;
import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestRelation {
	
	@Test
	public void testFindDept() throws Exception {
		Dept dept = JPAEntityFactory.getEntityManager().find(Dept.class, 3L) ;	// 只查询部门数据
		dept.getCompany().getCname() ;			// 此处将查询公司数据
		JPAEntityFactory.close();				// 关闭数据库连接
	} 
	
	@Test
	public void testFind() throws Exception {
		Company company = JPAEntityFactory.getEntityManager().find(Company.class, 2L) ;
		JPAEntityFactory.close();	
	}
	
	@Test
	public void testFind2() throws Exception {
		Company company = JPAEntityFactory.getEntityManager().find(Company.class, 2L) ;
		company.getDepts().size() ;		// 获取全部部门数据
		JPAEntityFactory.close();		// 关闭数据库连接
	}
	
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
