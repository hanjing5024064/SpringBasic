package cn.mldn.mldnspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptDAO extends JpaRepository<Dept, Long> {		// SpringData数据接口
 
}

 