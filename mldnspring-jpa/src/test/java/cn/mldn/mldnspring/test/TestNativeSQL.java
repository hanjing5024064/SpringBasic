package cn.mldn.mldnspring.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestNativeSQL {
	@Test
	public void testSelectAll() {
		String sql = "SELECT * FROM dept" ; 		 // 原始SQL语句
		// 创建原生SQL查询，该操作只能够使用Query接口进行接收
		Query query = JPAEntityFactory.getEntityManager().createNativeQuery(sql,Dept.class) ;
		List<Dept> allDepts = query.getResultList() ;	// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close();
	}
	
	@Test
	public void testSelectPart() {
		String sql = "SELECT deptno,dname,num FROM dept" ; 		 // 原始SQL语句
		// 创建原生SQL查询，该操作只能够使用Query接口进行接收
		Query query = JPAEntityFactory.getEntityManager().createNativeQuery(sql) ;
		List<Object[]> allDepts = query.getResultList();
		allDepts.forEach((data) -> {					// 迭代输出集合
			System.out.println(Arrays.toString(data));					// 输出数据
		});
		JPAEntityFactory.close();
	} 
	
	@Test
	public void testSelectSplit() {
		int currentPage = 1 ;									// 当前所在页
		int lineSize = 10 ;									// 每页显示数据行数
		String keyWord = "MLDN" ;								// 查询关键字
		String sql = "SELECT * FROM dept WHERE dname LIKE :pkw" ; 		 // 原始SQL语句
		// 创建原生SQL查询，该操作只能够使用Query接口进行接收
		Query query = JPAEntityFactory.getEntityManager().createNativeQuery(sql,Dept.class) ;
		query.setParameter("pkw", "%" + keyWord + "%"); 			// 设置查询关键字
		query.setFirstResult((currentPage - 1) * lineSize) ;	// 开始行
		query.setMaxResults(lineSize) ;						// 取出记录个数
		List<Dept> allDepts = query.getResultList() ;	// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close();
	}
	
}
