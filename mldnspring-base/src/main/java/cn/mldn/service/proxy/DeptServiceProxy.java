package cn.mldn.service.proxy;

import cn.mldn.service.IDeptService;
import cn.mldn.vo.Dept;

public class DeptServiceProxy implements IDeptService {
	private IDeptService deptService ;
	public DeptServiceProxy(IDeptService deptService) {
		this.deptService = deptService ;
	}
	@Override
	public boolean add(Dept vo) {
		try {
			// 开启数据库事务处理
			this.deptService.add(vo) ; // 调用真实业务层方法
			// 【COMMIT】执行完毕提交事务
		} catch (Exception e) {
			// 【ROLLBACK】出现异常事务回滚
		}
		return true;
	}
}
