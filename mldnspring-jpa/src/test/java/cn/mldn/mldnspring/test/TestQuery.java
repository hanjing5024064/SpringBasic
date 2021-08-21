package cn.mldn.mldnspring.test;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestQuery {
	@Test
	public void testSelectSingle() throws Exception {
		String jpql = "SELECT d FROM Dept AS d WHERE d.deptno=?" ;		// 条件查询
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(jpql, Dept.class) ;
		query.setParameter(0, 1L) ;						// 设置第一个参数
		Dept dept = query.getSingleResult() ;			// 获取一个对象
		System.out.println(dept);						// 输出对象
		JPAEntityFactory.close();						// 关闭连接
	}
	
	@Test
	public void testSelectSingleParameterName() throws Exception {
		String jpql = "SELECT d FROM Dept AS d WHERE d.deptno=:pdeptno" ;		// 条件查询
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(jpql, Dept.class) ;	// 创建TypedQuery接口
		query.setParameter("pdeptno", 1L) ;				// 设置指定名称的参数
		Dept dept = query.getSingleResult() ;			// 获取一个对象
		System.out.println(dept);						// 输出对象
		JPAEntityFactory.close();						// 关闭连接
	}
	
	@Test
	public void testSelectSplit() throws Exception {
		int currentPage = 2 ;		// 当前所在页
		int lineSize = 10 ;			// 每页显示数据行数
		String keyWord = "MLDN" ;	// 查询关键字
		String jpql = "SELECT d FROM Dept AS d WHERE d.dname LIKE ?" ;		// 查询全部数据，以实体类返回
		// 查询全部数据，同时设置返回的实体类型，如果不设置则将以Object类型返回
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(jpql, Dept.class) ;	// 创建TypedQuery接口对象
		query.setParameter(0, "%" + keyWord + "%"); // 设置查询关键字
		query.setFirstResult((currentPage - 1) * lineSize) ;	// 开始行
		query.setMaxResults(lineSize) ;						// 取出记录个数
		List<Dept> allDepts = query.getResultList() ; 	// 查询全部数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close();						// 关闭连接
	}
	
	@Test
	public void testSelectCount() {
		String keyWord = "MLDN" ;	// 查询关键字
		String jpql = "SELECT COUNT(d) FROM Dept AS d WHERE d.dname LIKE ?" ;	// 统计查询
		TypedQuery<Long> query = JPAEntityFactory.getEntityManager().createQuery(jpql,Long.class) ;
		query.setParameter(0, "%" + keyWord + "%"); // 设置查询关键字
		Long count = query.getSingleResult() ;		// 获取一个查询结果
		System.out.println("数据行数：" + count);			// 输出记录个数
		JPAEntityFactory.close(); 					// 关闭连接
	}

	@Test
	public void testSelectPart() throws Exception {
		String jpql = "SELECT d.deptno,d.dname FROM Dept AS d" ;		// 查询部分字段
		TypedQuery<Object[]> query = JPAEntityFactory.getEntityManager().createQuery(jpql, Object[].class) ;	// 创建TypedQuery接口对象
		List<Object[]> allDepts = query.getResultList() ; // 取得全部数据信息
		allDepts.forEach((data)->{
			System.out.println(Arrays.toString(data)); // 将对象数组变为字符串
		});
		JPAEntityFactory.close();						// 关闭连接
	}
	
	@Test
	public void testEdit() throws Exception {
		String jpql = "UPDATE Dept AS d SET d.dname=:dname,d.num=:num WHERE d.deptno=:deptno" ;
		Query query = JPAEntityFactory.getEntityManager().createQuery(jpql) ;	// 创建Query接口对象
		query.setParameter("dname", "MLDNJAVA") ;			// 设置参数内容
		query.setParameter("num", 50) ;					// 设置参数内容
		query.setParameter("deptno", 3L) ;				// 设置参数内容
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		int len = query.executeUpdate() ; 				// 执行之后返回影响的数据行数
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close(); 			// 关闭连接
		System.out.println("更新行数：" + len); 
	}
	 
	@Test
	public void testDelete() throws Exception {
		String jpql = "DELETE FROM Dept AS d WHERE d.deptno=:pdeptno" ;
		Query query = JPAEntityFactory.getEntityManager().createQuery(jpql) ;	// 创建Query接口对象
		query.setParameter("pdeptno", 3L) ;				// 设置参数内容
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		int len = query.executeUpdate() ; 				// 执行之后返回影响的数据行数
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close(); 			// 关闭连接
		System.out.println("更新行数：" + len); 
	}
	 
	@Test
	public void testSelectAll() throws Exception {
		String jpql = "SELECT d FROM Dept AS d" ;		// 查询全部数据，以实体类返回
		// 查询全部数据，同时设置返回的实体类型，如果不设置则将以Object类型返回
		Query query = JPAEntityFactory.getEntityManager().createQuery(jpql, Dept.class) ;
		List<Dept> allDepts = query.getResultList() ; 	// 查询全部数据 
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close();						// 关闭连接
	}
}
