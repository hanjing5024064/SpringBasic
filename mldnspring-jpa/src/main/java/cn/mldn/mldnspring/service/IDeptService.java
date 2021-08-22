package cn.mldn.mldnspring.service;

import java.util.List;
import java.util.Set;

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
	/**
	 * 根据指定范围的部门编号查询部门信息
	 * @param ids 部门编号集合
	 * @return 部门持久化类集合
	 */
	public List<Dept> gets(Set<Long> ids) ;
	/**
	 * 根据指定部门编号与部门名称查询部门数据
	 * @param po 查询参数
	 * @return 部门持久化对象
	 */
	public Dept getIdAndDname(Dept po) ;
	/**
	 * 部门信息修改
	 * @param po 包含有要修改的数据信息
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean edit(Dept po) ;
	/**
	 * 部门数据删除，可以根据部门编号删除
	 * @param dno 要删除的部门编号
	 * @return 删除成功返回true，否则返回false
	 */
	public boolean remove(long dno) ;
}
