package cn.mldn.mldnspring.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptDAO extends Repository<Dept, Long> {		// SpringData数据接口
	/**
	 * 增加新的部门数据，方法名称定义为save可以自动实现merge()功能
	 * @param vo 部门持久化对象
	 * @return 增加成功返回持久化对象
	 */
	public Dept save(Dept vo) ;
	/**
	 * 查询全部部门数据，
	 * @return 部门持久化对象集合
	 */
	public List<Dept> findAll() ;
}

