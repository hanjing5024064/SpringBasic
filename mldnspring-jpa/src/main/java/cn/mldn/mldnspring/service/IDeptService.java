package cn.mldn.mldnspring.service;

import java.util.List;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptService {
	/**
	 * 增加部门数据，调用IDeptDAO.doCreate()方法处理
	 * @param vo 持久化类对象，没有设置主键数据
	 * @return 增加成功返回true，否则返回false
	 */
	public boolean add(Dept vo) ;
	/**
	 * 查询Dept表中的全部数据
	 * @return Dept持久化对象集合
	 */
	public List<Dept> list() ;
	/**
	 * 根据部门编号查询部门信息
	 * @param id 部门编号
	 * @return 部门持久化对象
	 */
	public Dept get(long id) ;
}
