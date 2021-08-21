package cn.mldn.mldnspring.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;

import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.util.JPAEntityFactory;

public class TestCriteria {
	@Test
	public void testSelectAll() {
		CriteriaBuilder criteriaBuilder = JPAEntityFactory.getEntityManager().getCriteriaBuilder() ;	// 查询构建器
		CriteriaQuery<Dept> criteria = criteriaBuilder.createQuery(Dept.class) ; 		// 创建查询处理
		criteria.from(Dept.class) ; 						// 定义FROM子句
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(criteria) ;	// 通过Query查询
		List<Dept> allDepts = query.getResultList() ;		// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close(); 
	}
	
	@Test
	public void testSelectWhereMulti() {
		CriteriaBuilder criteriaBuilder = JPAEntityFactory.getEntityManager().getCriteriaBuilder() ;	// 查询构建器
		CriteriaQuery<Dept> criteria = criteriaBuilder.createQuery(Dept.class) ; 		// 创建查询处理
		Root<Dept> root = criteria.from(Dept.class) ; 					// 定义FROM子句，获取属性定位
		List<Predicate> predicatesList = new ArrayList<Predicate>();	// 保存查询条件
		// 设置两个查询条件：deptno为1L（deptno=1）或者avgsal使用between..and（avgsal between 999.00 and 9999.00）
		predicatesList.add(
				criteriaBuilder.or(criteriaBuilder.equal(root.get("deptno"), 1L),
				criteriaBuilder.between(root.get("avgsal"), 999.00, 9999.00)));
		predicatesList.add(criteriaBuilder.like(root.get("dname"), "%MLDN%")) ;	// 模糊查询
		predicatesList.add(criteriaBuilder.gt(root.get("num"), 10)) ;	// num>10	
		criteria.where(predicatesList.toArray(new Predicate[] {})) ;	// 设置查询条件
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(criteria) ;	// 通过Query查询
		List<Dept> allDepts = query.getResultList() ;		// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close(); 
	}  
	
	@Test
	public void testSelectIN() {
		Set<Long> deptnos = new HashSet<Long>() ;		// 保存部门编号集合
		deptnos.addAll(Arrays.asList(1L,3L,5L)) ;		// 设置要查询的数据范围
		CriteriaBuilder criteriaBuilder = JPAEntityFactory.getEntityManager().getCriteriaBuilder() ;	// 查询构建器
		CriteriaQuery<Dept> criteria = criteriaBuilder.createQuery(Dept.class) ; 		// 创建查询处理
		Root<Dept> root = criteria.from(Dept.class) ; 					// 定义FROM子句，获取属性定位
		Predicate predicate = root.get("deptno").in(deptnos) ;// IN查询处理
		criteria.where(predicate) ; 	// 设置条件
		criteria.orderBy(criteriaBuilder.desc(root.get("deptno"))); // 结果排序
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(criteria) ;	// 通过Query查询
		List<Dept> allDepts = query.getResultList() ;		// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close(); 
	} 

	@Test
	public void testSelectWhere() {
		CriteriaBuilder criteriaBuilder = JPAEntityFactory.getEntityManager().getCriteriaBuilder() ;	// 查询构建器
		CriteriaQuery<Dept> criteria = criteriaBuilder.createQuery(Dept.class) ; 		// 创建查询处理
		Root<Dept> root = criteria.from(Dept.class) ; 					// 定义FROM子句，获取属性定位
		Predicate predicate = criteriaBuilder.gt(root.get("num"), 10) ;	// 创建查询条件
		criteria.where(predicate) ; 									// 设置查询条件
		TypedQuery<Dept> query = JPAEntityFactory.getEntityManager().createQuery(criteria) ;	// 通过Query查询
		List<Dept> allDepts = query.getResultList() ;		// 查询数据
		allDepts.forEach((dept) -> {					// 迭代输出集合
			System.out.println(dept);					// 输出每一个PO对象
		});
		JPAEntityFactory.close(); 
	}
	
}
