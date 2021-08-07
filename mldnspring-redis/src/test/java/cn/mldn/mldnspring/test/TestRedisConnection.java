 package cn.mldn.mldnspring.test;

import org.junit.Test;

import cn.mldn.mldnspring.util.dbc.RedisConnectionUtil;

public class TestRedisConnection {
	@Test
	public void testConnection() {
		RedisConnectionUtil rcu = new RedisConnectionUtil() ;
		System.out.println(rcu.getConnection());			// 输出对象，不为null表示连接成功
		rcu.close();
	}
}
