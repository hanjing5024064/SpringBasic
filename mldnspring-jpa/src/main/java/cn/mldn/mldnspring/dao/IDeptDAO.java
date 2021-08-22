package cn.mldn.mldnspring.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.mldn.mldnspring.po.Dept;

public interface IDeptDAO extends PagingAndSortingRepository<Dept, Long> {		// SpringData数据接口
 
}

