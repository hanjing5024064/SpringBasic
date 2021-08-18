package cn.mldn.mldnspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cn.mldn.mldnspring.dao.INewsDAO;
import cn.mldn.mldnspring.service.INewsService;
import cn.mldn.mldnspring.vo.News;

@Service
public class NewsServiceImpl implements INewsService {
	@Autowired
	private INewsDAO newsDAO;
	@Autowired 	// 可以注入的只是一个接口的实例化对象，但是这些对象还需要配置隔离级别、传播属性
	private PlatformTransactionManager transactionManager;

	@Override
	public News get(long nid) {
		News vo = null;
		// TransactionDefinition通过此类设置传播属性、隔离级别、是否为只读、超时访问
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		// 设置传播属性，表示必须有一个事务启动
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 隔离级别一定要使用默认的隔离级别进行控制
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		transactionDefinition.setReadOnly(true); 					// 只读操作
		// 获取事务的提交状态
		TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
		try {
			vo = this.newsDAO.findById(nid);
			this.transactionManager.commit(transactionStatus); 		// 提交事务
		} catch (Exception e) {
			this.transactionManager.rollback(transactionStatus); 	// 回滚到指定的提交点
		}
		return vo;
	}

	@Override
	public News edit(News vo) {
		if (this.newsDAO.doUpdate(vo)) {
			return vo;
		}
		return null;
	}

	@Override
	public boolean delete(long nid) {
		boolean flag = false;
		// TransactionDefinition通过此类设置传播属性、隔离级别、是否为只读、超时访问
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		// 设置传播属性，表示必须有一个事务启动
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 隔离级别一定要使用默认的隔离级别进行控制
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		// 获取事务的提交状态
		TransactionStatus transactionStatus = this.transactionManager.getTransaction(transactionDefinition);
		try {
			flag = this.newsDAO.doRemove(nid);
			this.transactionManager.commit(transactionStatus); // 提交事务
		} catch (Exception e) {
			e.printStackTrace();
			this.transactionManager.rollback(transactionStatus); // 回滚到指定的提交点
		}
		return flag;
	}
}
