package cn.mldn.mldnspring.test;

import java.util.Date;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestFirstCache {
	@Test
	public void testFind() throws Exception {
		// 通过find()方法查询出来的数据会自动保存在一级缓存之中，同时该对象状态也属于持久态
		Dept dept = JPAEntityFactory.getEntityManager().find(Dept.class, 3L) ;
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		dept.setDname("MLDN教学研发中心");									// 修改持久态对象的属性
		dept.setNum(30);												// 修改持久态对象的属性
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close(); 										// 关闭连接
	}

	@Test
	public void testBatch() { 
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		for (int x = 0 ; x < 1000 ; x ++) {
			Dept dept = new Dept() ;									// 瞬时态对象
			dept.setDname("极限IT教学部 - " + x);							// 设置数据
			dept.setAvgsal(8968.88 + x); 								// 设置数据
			dept.setCreatedate(new Date()); 							// 设置数据
			dept.setNum(888 + x);										// 设置数据
			JPAEntityFactory.getEntityManager().persist(dept); 			// 数据持久化，自动可以获取ID
			if (x % 10 == 0) {											// 每10条记录清除一下缓存
				JPAEntityFactory.getEntityManager().flush(); 			// 强制刷新
				JPAEntityFactory.getEntityManager().clear(); 			// 清空缓存
			}
		}
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close(); 										// 关闭连接
	}

	
	@Test
	public void testAdd() { 
		Dept dept = new Dept() ;					// 创建持久态对象
		dept.setDname("极限IT教学部");					// 设置属性
		dept.setAvgsal(8968.88); 					// 设置属性
		dept.setCreatedate(new Date()); 			// 设置属性
		dept.setNum(888);							// 设置属性
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		// 数据持久化，对象将由瞬时态变为持久态（EntityManager关闭前），并且自动可以获取ID
		JPAEntityFactory.getEntityManager().persist(dept); 				
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		System.out.println("*** 新增数据的ID是：" + dept.getDeptno());		// 自动获得当前增长后ID
		// 将刚刚保存后的对象信息根据ID查询出来，由于一级缓存作用将不会向数据库发出查询指令
		Dept selectDept = JPAEntityFactory.getEntityManager().find(Dept.class, dept.getDeptno()) ;
		System.out.println(selectDept); 
		JPAEntityFactory.close(); 					// 关闭连接
	}

	
}
