package cn.mldn.mldnspring.service.impl;

import java.util.List;

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
		return this.deptDAO.doCreate(vo);	// 数据增加
	}
	@Override
	public List<Dept> list() {
		return this.deptDAO.findAll() ; 	// 数据查询
	}
}

