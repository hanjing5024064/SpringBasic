package cn.mldn.mldnspring.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

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
	@Query("SELECT d FROM Dept AS d")	// 写与不写此类语句效果是相同的
	public List<Dept> findAll() ;
	
	/**
	 * 根据id进行查询，在JPQL获取参数时使用Spel表达式获取第一个参数
	 * @param id 要查询的部门编号
	 * @return 部门持久化对象
	 */
	@Query("SELECT d FROM Dept AS d WHERE d.deptno=?#{[0]}")	// 使用Spel语法来进行参数的获得
	public Dept findById(Long id) ;	
	
	/**
	 * 根据指定ID范围实现部门信息查询
	 * @param ids 全部部门ID
	 * @return Dept持久化类集合
	 */
	@Query("SELECT d FROM Dept AS d WHERE d.deptno IN :pids")			// 使用“pids”访问参数
	public List<Dept> findByIds(@Param(value="pids") Set<Long> ids) ;	// 根据设置的id范围查询

	/**
	 * 根据指定的部门ID与部门名称获取部门数据
	 * @param dept 包含有部门数据信息
	 * @return Dept持久化类对象
	 */
	@Query("SELECT d FROM Dept AS d WHERE d.deptno=:#{#mydept.deptno} AND dname=:#{#mydept.dname}")
	public Dept findByIdAndDname(@Param(value="mydept") Dept dept) ;	// 要进行两项查询，同时传递的是一个类

	/**
	 * 进行指定编号的部门信息修改
	 * @param po 传递要修改的部门数据
	 * @return 数据库更新影响的数据行数
	 */
	@Modifying(clearAutomatically=true)						// 追加缓存的清除与更新
	@Query("UPDATE Dept AS d SET d.dname=:#{#mydept.dname},d.num=:#{#mydept.num} WHERE d.deptno=:#{#mydept.deptno}")
	public int doEdit(@Param(value="mydept") Dept po) ;	

	
	/**
	 * 根据指定编号删除部门数据
	 * @param deptno 部门编号
	 * @return 数据库更新影响的数据行数
	 */
	@Modifying(clearAutomatically = true) 					// 追加缓存的清除与更新
	@Query("DELETE FROM Dept AS d WHERE d.deptno=:dno")
	public int doRemove(@Param("dno") Long deptno);

}

