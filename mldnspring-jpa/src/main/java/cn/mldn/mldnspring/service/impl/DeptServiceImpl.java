package cn.mldn.mldnspring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
		Sort sort = new Sort(Sort.Direction.DESC,"deptno") ; 	// 设置deptno字段为降序排列 
		return (List<Dept>) this.deptDAO.findAll(sort) ;		// 排序查询
	}

	@Override
	public Dept get(long id) {
		// 使用Optional接收返回数据，可以避免null数据
		Optional<Dept> result = this.deptDAO.findById(id) ;
		return result.get() ;
	}
	
	
	@Override
	public Map<String, Object> listSplit(int currentPage, int lineSize) {
		Sort sort = new Sort(Sort.Direction.DESC, "deptno"); 		// 设置deptno字段为降序排列
		// 将分页与排序操作保存到Pageable接口对象之中，这样才可以通过DAO层进行方法调用，页数从0开始
		Pageable pageable = PageRequest.of(currentPage - 1, lineSize, sort);
		// Page中会自动保存全部数据记录、总记录数，同时也会计算出总页数
		Page<Dept> pageDept = this.deptDAO.findAll(pageable);		// 数据查询
		Map<String, Object> map = new HashMap<String, Object>();	// 保存返回结果
		map.put("allDepts", pageDept.getContent());					// 保存返回数据
		map.put("deptCount", pageDept.getTotalElements());			// 保存总记录数
		map.put("deptPage", pageDept.getTotalPages());				// 保存总页数
		return map;
	}
}

