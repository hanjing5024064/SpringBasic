package cn.mldn.mldnspring.util.dbc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionUtil {
	public static final int TIMEOUT = 1000; // 连接超时时间
	public static final int SO_TIMEOUT = 100; // 间隔超时时间
	public static final int MAX_ATTEMPTS = 100; // 重试的次数
	private static final String REDIS_AUTH = "" ;		// 认证信息
	private static final int MAX_TOTAL = 200 ;					// 最多允许200个的连接
	private static final int MAX_IDLE = 20 ;					// 没有访问时的最小维持数量
	private static final int MAX_WAIT_MILLIS = 1000 ;			// 最大等待时间
	private static final boolean TEST_ON_BORROW = true ;		// 是否要进行连接测试
	private JedisCluster jedisCluster ;							// JedisCluster
	public RedisConnectionUtil() {								// 构造方法连接数据库	
		JedisPoolConfig config = new JedisPoolConfig() ;		// 进行连接池配置
		config.setMaxTotal(MAX_TOTAL);							// 最大连接数
		config.setMaxIdle(MAX_IDLE);							// 最小维持连接数
		config.setMaxWaitMillis(MAX_WAIT_MILLIS);				// 最大等待时间
		config.setTestOnBorrow(TEST_ON_BORROW); 				// 测试通过后返回可用连接
		// 定义出所有保存RedisCluster集群主机的集合对象
		Set<HostAndPort> allRedisCluster = new HashSet<HostAndPort>();
		allRedisCluster.add(new HostAndPort("redis-cluster-a", 6379));
		allRedisCluster.add(new HostAndPort("redis-cluster-a", 6380));
		allRedisCluster.add(new HostAndPort("redis-cluster-a", 6381));
		allRedisCluster.add(new HostAndPort("redis-cluster-b", 6379));
		allRedisCluster.add(new HostAndPort("redis-cluster-b", 6380));
		allRedisCluster.add(new HostAndPort("redis-cluster-b", 6381));
		allRedisCluster.add(new HostAndPort("redis-cluster-c", 6379));
		allRedisCluster.add(new HostAndPort("redis-cluster-c", 6380));
		allRedisCluster.add(new HostAndPort("redis-cluster-c", 6381));

		this.jedisCluster = new JedisCluster(allRedisCluster, TIMEOUT,
				SO_TIMEOUT, MAX_ATTEMPTS, REDIS_AUTH, config);	// 获取RedisCluster连接

	}
	public JedisCluster getConnection() {						// 获取JedisCluster
		return this.jedisCluster ;
	} 
	public void close() {
		try {
			this.jedisCluster.close(); 							// 连接池关闭
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}

}
