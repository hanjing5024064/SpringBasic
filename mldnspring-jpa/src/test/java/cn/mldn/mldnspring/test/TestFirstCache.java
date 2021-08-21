package cn.mldn.mldnspring.test;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestFirstCache {
	@Test
	public void testFind() throws Exception {
		Dept deptA = JPAEntityFactory.getEntityManager().find(Dept.class, 3L) ;
		System.out.println("第一次使用find()查询：" + deptA);
		deptA.setDname("修改第一次查询结果的部门名称");			// 修改数据
		deptA.setNum(-1);									// 修改数据
		System.out.println("--------------------- 华丽的分割线 --------------------");
		JPAEntityFactory.getEntityManager().refresh(deptA); // 刷新缓存数据，否则还是上次查询后保存到缓存中的数据
		Dept deptB = JPAEntityFactory.getEntityManager().find(Dept.class, 3L) ;
		System.out.println("第二次使用find()查询：" + deptB);
	}

}
