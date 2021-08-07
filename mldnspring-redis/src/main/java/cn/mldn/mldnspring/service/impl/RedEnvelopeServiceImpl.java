package cn.mldn.mldnspring.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.mldn.mldnspring.service.IRedEnvelopeService;
import cn.mldn.mldnspring.util.SplitMoneyUtil;
@Service
public class RedEnvelopeServiceImpl implements IRedEnvelopeService {
	@Autowired
	private RedisTemplate<String, Double> redisTemplate; 				// Redis操作模版
	@Override
	public String add(String userid,int amount, double money) {
		SplitMoneyUtil smu = new SplitMoneyUtil(amount,money) ;			// 设置总金额和拆分个数
		List<Double> result = smu.getAllPackages() ;					// 获取红包信息
		String key = "envelope-" + userid + "-" + System.currentTimeMillis();
		this.redisTemplate.opsForList().leftPushAll(key, result) ;		// 保存红包数据
		return key ;
	}

	@Override
	public Double grab(String userid,String key) {
		Double popResult = null ;
		String hashKey = "result-" + key ;								// 保存的Hash-Key
		boolean flag = this.redisTemplate.opsForHash().hasKey(hashKey, userid) ;// 是否存在有数据
		if (flag == false) { 											// 不存在，表示没有抢过红包
			popResult = this.redisTemplate.opsForList().leftPop(key) ;	// 取出一个数据
			if (popResult != null) {									// 保存结果
				this.redisTemplate.opsForHash().put(hashKey, userid, popResult);
			}
		} else {
			popResult = -1.0 ;											// 已经抢过了
		}
		return popResult;
	}
	@Override
	public Map<Object, Object> result(String key) {
		return this.redisTemplate.opsForHash().entries(key);			// 获取指定Hash数据
	}
}
