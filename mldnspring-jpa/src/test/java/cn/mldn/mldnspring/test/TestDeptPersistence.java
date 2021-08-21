package cn.mldn.mldnspring.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;

public class TestDeptPersistence {
	@Test
	public void testAdd() {
		// 1、定义EntityManagerFactory工厂类对象
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MLDNJPA");
		// 2、通过工厂类产生EntityManager（Session）
		EntityManager entityManager = entityManagerFactory.createEntityManager(); // 数据库操作
		// 3、更新需要事务的支持，所以通过EntityManager打开一个事务出合理
		entityManager.getTransaction().begin(); 	// 开启事务
		// 4、将需要保存的数据设置到PO类对象之中
		Dept dept = new Dept();						// 定义持久化对象
		dept.setDname("MLDN教学管理部");				// 设置属性内容
		dept.setAvgsal(8968.88);					// 设置属性内容
		dept.setCreatedate(new Date());				// 设置属性内容
		dept.setNum(8);								// 设置属性内容
		// 5、执行数据的持久化处理（保存）
		entityManager.persist(dept); 				// 数据持久化
		// 6、进行事务的提交控制
		entityManager.getTransaction().commit();
		// 7、关闭数据库连接
		entityManager.close(); 						// 关闭Session的操作
		entityManagerFactory.close(); 				// 关闭工厂连接
	}
}
