package cn.mldn.mldnspring.test;

import org.junit.Test;

import cn.mldn.mldnspring.po.Company;
import cn.mldn.mldnspring.po.Details;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestRelation { 
	@Test
	public void testAdd() throws Exception {
		Company company = new Company() ;								// 创建持久化类对象
		company.setCname("魔乐科技软件学院");								// 设置属性
		Details details = new Details() ;								// 创建持久化类对象
		details.setAddress("北京市天安门");								// 设置属性
		details.setCapital(500000.00);									// 设置属性
		company.setDetails(details);									// 设置一对一关联
		details.setCompany(company);									// 设置一对一关联
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开始事务
		JPAEntityFactory.getEntityManager().persist(company); 			// 持久化数据
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close();										// 关闭连接

	}
	
	@Test
	public void testFind() throws Exception {
		Company company = JPAEntityFactory.getEntityManager().find(Company.class, 3L) ;
		JPAEntityFactory.close();
	}
}
