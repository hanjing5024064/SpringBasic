package cn.mldn.mldnspring.dao;

import org.springframework.data.repository.CrudRepository;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptDAO extends CrudRepository<Dept, Long> {		// SpringData数据接口
 
}

