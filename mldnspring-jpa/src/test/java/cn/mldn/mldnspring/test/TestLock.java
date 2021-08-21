package cn.mldn.mldnspring.test;

import javax.persistence.LockModeType;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestLock { 
	@Test
	public void testFind() throws Exception {
		JPAEntityFactory.getEntityManager().getTransaction().begin(); 		// 开启事务
		JPAEntityFactory.getEntityManager().find(Dept.class, 3L,LockModeType.PESSIMISTIC_WRITE) ;	// 使用悲观锁的形式查询数据
		JPAEntityFactory.getEntityManager().getTransaction().rollback();	// 可以回滚或提交 
	}

}
