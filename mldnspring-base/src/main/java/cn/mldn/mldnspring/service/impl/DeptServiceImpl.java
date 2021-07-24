package cn.mldn.mldnspring.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.dao.IDeptDAO;
import cn.mldn.mldnspring.service.IDeptService;
import cn.mldn.mldnspring.vo.Dept;
@Service
public class DeptServiceImpl implements IDeptService {
	@Resource					// 自动注入
	private IDeptDAO deptDAO ;
	@Override
	public boolean add(Dept vo) {
		System.out.println("************ 执行业务层方法 ************");
		return this.deptDAO.doCreate(vo);
	}

}
