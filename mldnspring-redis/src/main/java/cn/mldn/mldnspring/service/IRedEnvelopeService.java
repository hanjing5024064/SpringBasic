package cn.mldn.mldnspring.service;

import java.util.Map;

public interface IRedEnvelopeService {
	/**
	 * 实现红包数据保存，所有的红包数据保存在List集合之中
	 * @param userid 发红包用户名
	 * @param amount 红包个数
	 * @param money 总金额
	 * @return 保存数据KEY，KEY定义规则“envelope-用户名-时间戳” 
	 */
	public String add(String userid,int amount, double money) ;
	/**
	 * 抢红包处理，红包到手后要记录在Redis之中，使用Hash类型存储，属性KEY为用户名
	 * Hash数据保存的KEY为：“result-红包数据KEY”
	 * @param userid 用户名
	 * @param key 集合KEY
	 * @return 返回抢到的红包数据，如果已经抢过了，将会返回-1.0
	 */
	public Double grab(String userid,String key) ;
	/**
	 * 获取抢红包数据结果
	 * @param key Hash-KEY名称
	 * @return 所有保存在Hash中的数据
	 */
	public Map<Object,Object> result(String key) ;
}
