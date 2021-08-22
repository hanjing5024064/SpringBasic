package cn.mldn.mldnspring.dao;

import java.util.List;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptDAO {				// 这是一个最原始的纯粹接口
	/**
	 * 增加新的部门数据
	 * @param vo 部门持久化对象
	 * @return 增加成功返回true
	 */
	public boolean doCreate(Dept vo) ;
	/**
	 * 查询全部部门数据
	 * @return 部门持久化对象集合
	 */
	public List<Dept> findAll() ;
}

