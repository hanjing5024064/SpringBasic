package cn.mldn.mldnspring.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestDeptPersistence {
	@Test
	public void testFind() throws Exception {
		// 查询部门编号为3（deptno=3）的部门数据信息，并且设置接收目标对象类型
		Dept dept = JPAEntityFactory.getEntityManager().find(Dept.class, 9L);
		System.out.println(dept);
	}

	@Test
	public void testReference() throws Exception {
		// 查询部门编号为3（deptno=3）的部门数据信息，并且设置接收目标对象类型
		Dept dept = JPAEntityFactory.getEntityManager().getReference(Dept.class, 9L);
		System.out.println(dept);
	}	 
	
	@Test
	public void testEdit() throws Exception {
		JPAEntityFactory.getEntityManager().getTransaction().begin(); // 开启事务
		Dept dept = new Dept();						// 定义持久化对象
		dept.setDeptno(1L);							// 设置主键
		dept.setDname("MLDN教学研发中心");				// 设置属性内容
//		dept.setAvgsal(7868.88);					// 设置属性内容
//		dept.setCreatedate(new SimpleDateFormat("yyyy-MM-dd")
//				.parse("2006-11-11")); 				// 设置属性内容
//		dept.setNum(8);								// 设置属性内容
		// 此时会返回一个当前的持久化对象
		Dept mergeDept = JPAEntityFactory.getEntityManager().merge(dept); // 数据更新
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 事务提交
//		JPAEntityFactory.close(); 					// 关闭连接
//		JPAEntityFactory.getEntityManagerFactory().close();
	}
	
	@Test
	public void testRemove() {
		JPAEntityFactory.getEntityManager().getTransaction().begin(); // 开启事务
		// 根据ID查询出指定数据行的持久化对象
		Dept dept = JPAEntityFactory.getEntityManager().find(Dept.class, 5L) ;
		JPAEntityFactory.getEntityManager().remove(dept); 				// 数据删除
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 事务提交
//		JPAEntityFactory.close(); 					// 关闭连接
	}
	
	@Test
	public void testAdd() {
		JPAEntityFactory.getEntityManager().getTransaction().begin(); // 开启事务
		Dept dept = new Dept();						// 定义持久化对象
		dept.setDname("MLDN教学管理部");				// 设置属性内容
//		dept.setAvgsal(8968.88);					// 设置属性内容
//		dept.setCreatedate(new Date());				// 设置属性内容
//		dept.setNum(8);								// 设置属性内容
		JPAEntityFactory.getEntityManager().persist(dept); 				// 数据持久化
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 事务提交
//		JPAEntityFactory.close(); 					// 关闭连接
//		JPAEntityFactory.getEntityManagerFactory().close();
	}
}
