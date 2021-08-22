package cn.mldn.mldnspring.test;

import javax.persistence.LockModeType;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestLock { 
	@Test
	public void testFind() throws Exception {
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务 
		Dept dept = JPAEntityFactory.getEntityManager().find(Dept.class, 1L,
				LockModeType.OPTIMISTIC_FORCE_INCREMENT) ;				// 乐观锁
//		dept.setNum(9999);												// 修改数据
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 可以回滚或提交 
	}

	
}
