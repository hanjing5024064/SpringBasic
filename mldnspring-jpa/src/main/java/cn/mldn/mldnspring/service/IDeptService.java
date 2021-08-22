package cn.mldn.mldnspring.service;

import java.util.List;
import java.util.Map;
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
	 * 根据指定范围的ID进行数据查询
	 * @param ids 要查询的部门编号
	 * @return 全部部门信息
	 */
	public List<Dept> list(Set<Long> ids) ;
	
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
	 * 实现数据的分页处理
	 * @param currentPage 当前页 
	 * @param lineSize 每页的数据行
	 * @return 包含以下的返回结果：
	 * 1、key = allDepts、value = 所有的部门信息；
	 * 2、key = deptCount、value = 统计结果；
	 * 3、key = deptPage、value = 总页数。
	 */
	public Map<String,Object> listSplit(int currentPage,int lineSize) ;

}
