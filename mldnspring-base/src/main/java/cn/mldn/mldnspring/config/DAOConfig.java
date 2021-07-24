package cn.mldn.mldnspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.mldn.mldnspring.dao.IDeptDAO;
import cn.mldn.mldnspring.dao.impl.DeptDAOImpl;

@Configuration	// 表示当前的类是一个专门用于配置的实现类
public class DAOConfig {
	// Bean注解等价：<bean id="deptDAONew" class="cn.mldn.mldnspring.dao.impl.DeptDAOImpl" />
	@Bean(name="deptDAONew") 
	public IDeptDAO getDeptDAOInstance() {	// 方法名称可以随便你去编写
		return new DeptDAOImpl() ;			// 返回一个实例化对象
	}

}
