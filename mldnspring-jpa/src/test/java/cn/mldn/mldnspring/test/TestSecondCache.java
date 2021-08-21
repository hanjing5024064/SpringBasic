package cn.mldn.mldnspring.test;

import javax.persistence.EntityManager;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestSecondCache { 
	@Test
	public void testFind() throws Exception {
		EntityManager entityManagerA = JPAEntityFactory.getEntityManagerFactory().createEntityManager() ;	// 创建第一个EntityManager
		System.out.println(entityManagerA.find(Dept.class, 3L));					// 第一个EntityManager查询
		entityManagerA.close();														// 关闭连接
		System.err.println("--------------------- 华丽丽的分割线 --------------------");
		EntityManager entityManagerB = JPAEntityFactory.getEntityManagerFactory().createEntityManager() ;	// 创建第二个EntityManager
		System.out.println(entityManagerB.find(Dept.class, 3L));					// 第二个EntityManager查询
		entityManagerB.close();														// 关闭连接
	}
	

}
