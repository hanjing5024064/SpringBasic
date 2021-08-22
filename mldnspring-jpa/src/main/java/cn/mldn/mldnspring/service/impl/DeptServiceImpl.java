package cn.mldn.mldnspring.service.impl;

import java.util.List;
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
		return this.deptDAO.save(vo).getDeptno() != null;	// 数据增加
	}
	@Override
	public List<Dept> list() {
		return this.deptDAO.findAll() ; 	// 数据查询
	}
	
	@Override
	public Dept get(long id) {
		return this.deptDAO.findById(id);
	}
	
	@Override
	public List<Dept> gets(Set<Long> ids) {
		return this.deptDAO.findByIds(ids);
	}
	
	@Override
	public Dept getIdAndDname(Dept po) {
		return this.deptDAO.findByIdAndDname(po);
	}
	
	@Override
	public boolean edit(Dept po) {
		return this.deptDAO.doEdit(po) > 0;
	}
	
	@Override
	public boolean remove(long dno) {
		return this.deptDAO.doRemove(dno) > 0;
	}

}

