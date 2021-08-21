package cn.mldn.mldnspring.test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.jpa.QueryHints;
import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestQueryCache { 
	@Test
	public void testFind() throws Exception {
		String jpql = "SELECT d FROM Dept AS d WHERE d.deptno=:pdeptno" ;
		EntityManager entityManagerA = JPAEntityFactory.getEntityManagerFactory().createEntityManager() ;	// 创建第一个EntityManager
		TypedQuery<Dept> queryA = entityManagerA.createQuery(jpql, Dept.class).setHint(QueryHints.HINT_CACHEABLE, true) ;
		queryA.setParameter("pdeptno", 3L) ;
		System.out.println(queryA.getSingleResult());					// 第一个EntityManager查询
		entityManagerA.close();														// 关闭连接
		System.err.println("--------------------- 华丽丽的分割线 --------------------");
		EntityManager entityManagerB = JPAEntityFactory.getEntityManagerFactory().createEntityManager() ;	// 创建第二个EntityManager
		TypedQuery<Dept> queryB = entityManagerB.createQuery(jpql, Dept.class).setHint(QueryHints.HINT_CACHEABLE, true) ;
		queryB.setParameter("pdeptno", 3L) ;
		System.out.println(queryB.getSingleResult());					// 第二个EntityManager查询
		entityManagerB.close();														// 关闭连接
	}
	

}
