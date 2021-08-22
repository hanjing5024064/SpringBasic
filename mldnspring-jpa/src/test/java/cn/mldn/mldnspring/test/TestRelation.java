package cn.mldn.mldnspring.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import cn.mldn.mldnspring.po.Member;
import cn.mldn.mldnspring.po.Role;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestRelation {

	@Test
	public void testMemberAdd() {
		// member_role关联表里面需要的就是rid的信息，所以下面定义的都是rid的内容
		String rids[] = new String[] { "company", "dept", "emp" }; 		// 定义角色编号
		// 设置了一堆的Role对象只是为了获取一个rid的属性内容，但是关联关系必须要求产生Role集合对象
		List<Role> allRoles = new ArrayList<Role>();					// 保存角色信息
		Member member = new Member();									// 实例化持久类对象
		member.setMid("mldnjava");										// 设置数据
		member.setName("李兴华老师");										// 设置数据
		for (int x = 0; x < rids.length; x++) {							// 配置用户角色
			Role role = new Role();										// 实例化持久类对象
			role.setRid(rids[x]);										// 设置角色
			allRoles.add(role);											// 向集合保存角色信息
		}
		member.setRoles(allRoles); 										// 一个用户有多个角色
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		JPAEntityFactory.getEntityManager().persist(member);			// 持久化数据
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close();										// 关闭连接
	}
	@Test
	public void testMemberFind() {
		Member member = JPAEntityFactory.getEntityManager().find(Member.class, "mldnjava") ; // 获取指定ID数据
		member.getRoles().size() ;				// 发出查询全部角色SQL命令
		JPAEntityFactory.close();				// 关闭数据库连接
	}
	
	@Test
	public void testMemberFindSQL() {
		Member member = JPAEntityFactory.getEntityManager().find(Member.class, "mldnjava") ; // 获取指定ID数据
		String sql = "SELECT rid FROM member_role WHERE mid=:mid" ;		// 原生SQL
		System.out.println("*** 用户ID：" + member.getMid() + "、真实姓名：" + member.getName());
		Query query = JPAEntityFactory.getEntityManager().createNativeQuery(sql) ;
		query.setParameter("mid", member.getMid()) ;
		List<Object> allRids = query.getResultList() ;					// 返回单列数据
		System.out.println("*** 用户角色：" + allRids);
		JPAEntityFactory.close();				// 关闭数据库连接
	}
	
	@Test
	public void testMemberEdit() {
		// member_role关联表里面需要的就是rid的信息，所以下面定义的都是rid的内容
		String rids[] = new String[] { "market", "project", "sale" }; 	// 定义角色编号
		// 设置了一堆的Role对象只是为了获取一个rid的属性内容，但是关联关系必须要求产生Role集合对象
		List<Role> allRoles = new ArrayList<Role>();					// 保存角色信息
		Member member = new Member();									// 实例化持久类对象
		member.setMid("mldnjava");										// 设置数据
		member.setName("李兴华老师");										// 设置数据
		for (int x = 0; x < rids.length; x++) {							// 配置用户角色
			Role role = new Role();										// 实例化持久类对象
			role.setRid(rids[x]);										// 设置角色
			allRoles.add(role);											// 向集合保存角色信息
		}
		member.setRoles(allRoles); 										// 一个用户有多个角色
		JPAEntityFactory.getEntityManager().getTransaction().begin();	// 开启事务
		JPAEntityFactory.getEntityManager().merge(member);				// 更新数据
		JPAEntityFactory.getEntityManager().getTransaction().commit();	// 提交事务
		JPAEntityFactory.close();										// 关闭连接
	}

	
	
}
