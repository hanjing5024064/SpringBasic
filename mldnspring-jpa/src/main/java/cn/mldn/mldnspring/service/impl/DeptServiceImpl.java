package cn.mldn.mldnspring.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.IDeptDAO;
import cn.mldn.mldnspring.po.Dept;
import cn.mldn.mldnspring.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {
	@Autowired
	private IDeptDAO deptDAO ;				// 注入IDeptDAO接口实例

	@Override
	public boolean add(Dept vo) {
		return this.deptDAO.save(vo).getDeptno() != null ;
	}

	@Override
	public List<Dept> list() { 
		return (List<Dept>) this.deptDAO.findAll();	// Iterable转为List
	}

	@Override
	public Dept get(long id) {
		// 使用Optional接收返回数据，可以避免null数据
		Optional<Dept> result = this.deptDAO.findById(id) ;
		return result.get() ;
	}
}

