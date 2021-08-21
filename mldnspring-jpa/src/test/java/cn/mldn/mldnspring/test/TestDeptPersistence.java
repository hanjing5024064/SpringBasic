package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestDeptPersistence {
	@Test
	public void testAdd() {
		JPAEntityFactory.getEntityManager().getTransaction().begin(); // 开启事务
		Dept dept = new Dept();						// 定义持久化对象
		dept.setDname("MLDN教学管理部");				// 设置属性内容
		dept.setAvgsal(8968.88);					// 设置属性内容
		dept.setCreatedate(new Date());				// 设置属性内容
		dept.setNum(8);								// 设置属性内容
		JPAEntityFactory.getEntityManager().persist(dept); 				// 数据持久化
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 事务提交
		JPAEntityFactory.close(); 					// 关闭连接
	}
}
