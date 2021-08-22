package cn.mldn.mldnspring.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import cn.mldn.mldnspring.dao.IDeptDAO;
import cn.mldn.mldnspring.po.Dept;
@Repository	
public class DeptDAOImpl implements IDeptDAO {
	@PersistenceContext		// 利用此注解可以获得EntityManager（配置文件只配置了EntityManagerFactory工厂）
	private EntityManager entityManager ;	// JPA操作对象 

	@Override
	public boolean doCreate(Dept vo) {
		return this.entityManager.merge(vo) != null ;	// 数据增加
	}

	@Override
	public List<Dept> findAll() {
		String jpql = "SELECT d FROM Dept AS d" ;		// JPQL查询
		TypedQuery<Dept> query = this.entityManager.createQuery(jpql,Dept.class) ;
		return query.getResultList() ; 
	}

}
